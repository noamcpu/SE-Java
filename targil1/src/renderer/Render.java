package renderer;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import _scene.Scene;
import elements.LightSource;
import geometries.Geometry;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

public class Render {
	ImageWriter _imageWriter;
	Scene _scene;

	// ***************** Constructors ********************** //
	/**
	 * Constructor
	 * 
	 * @param _imageWriter
	 * @param _scene
	 */
	public Render(ImageWriter _imageWriter, Scene _scene) {
		this._imageWriter = _imageWriter;
		this._scene = _scene;
	}

	// ***************** Operations ******************** //
	/**
	 * apply the image on the screen
	 */
	public void renderImage() {
		for (int i = 0; i < _imageWriter.getHeight(); i++)
			for (int j = 0; j < _imageWriter.getWidth(); j++) {
				Ray ray = _scene.get_camera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), j, i,
						_scene.get_distance(), _imageWriter.getWidth(), _imageWriter.getHeight());
				List<GeoPoint> intersectionPoints = _scene.get_geometries().findIntersections(ray);
				if (intersectionPoints == null)
					_imageWriter.writePixel(j, i, _scene.get_background().getColor());
				// else, find the closest intersection to the camera and and paint the pixel
				// with the geometry's color
				else {
					GeoPoint closestPoint = getClosestPoint(intersectionPoints);
					_imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
				}
			}
	}

	public static int MAX_CALC_COLOR_LEVEL = 10;

	/**
	 * calcColor that send to the recursive calcColor
	 * 
	 * @param geopoint
	 * @param inRay
	 * @return
	 */
	/*
	 * private Color calcColor(GeoPoint geopoint) { Color color=new Color(
	 * _scene.get_ambietLight().getIntensity()); return
	 * color.add(geopoint.geometry.getEmission()); }
	 */
	private Color calcColor(GeoPoint intersection) {
		Color color = _scene.get_ambietLight().getIntensity();
		color = color.add(intersection.geometry.getEmission());
		return color;
	}

	/**
	 * finding the closest point
	 * 
	 * @param intersectionPoints
	 * @return the point
	 */
	private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
		Point3D p0 = _scene.get_camera().get_p0();
		double minValue = intersectionPoints.get(0).getPoint().distance(p0);
		GeoPoint minPoint = intersectionPoints.get(0);
		for (int i = 0; i < intersectionPoints.size(); ++i) {
			Point3D p = intersectionPoints.get(i).getPoint();
			double d0 = p.distance(p0);
			if (d0 < minValue) {
				minValue = d0;
				minPoint.point = p;
			}
		}
		return minPoint;

	}

	/**
	 * makes grid on the image
	 * 
	 * @param interval how frequent the grid appears
	 */
	public void printGrid(int interval, primitives.Color color) {
		// for all the Y (height) pixels in the view plane
		for (int i = 1; i < _imageWriter.getNy(); i++) {
			// for all the X (width) pixels in the view plane
			for (int j = 1; j < _imageWriter.getNx(); j++)
				// paint the pixel after the interval with the wanted color
				if (i % interval == 0 || j % interval == 0)
					_imageWriter.writePixel(j, i, color.getColor());
		}
	}


    /**
     * calculate the color of intersection point
     * 
     * @param geopoint - the intersection point
     * @return the color
     */
	private Color calc_Color(GeoPoint intersection) {
		Color color = _scene.get_ambietLight().getIntensity();
		color = color.add(intersection.geometry.getEmission());
		Vector v = intersection.point.sub(_scene.get_camera().get_p0()).normalize();
		Vector n = intersection.geometry.getNormal(intersection.point);
		int nShininess = intersection.geometry.material.get_nShininess();
		double kd = intersection.geometry.material.get_Kd();
		double ks = intersection.geometry.material.get_Ks();
		for (LightSource lightSource : _scene.getLights()) {
		Vector l = lightSource.getL(intersection.point);
		if ((n.dotProduct(l)) == (n.dotProduct(v))) {
		Color lightIntensity = lightSource.getIntensity(intersection.point);
		color = color.add(calcDiffusive(kd, l, n, lightIntensity),
		calcSpecular(ks, l, n, v, nShininess, lightIntensity));
		}
		}
		return color;
		}
    /**
     * calculate diffusive effect
     * 
     * @param kd             - the diffusive's material
     * @param l              - vector from the light source to the object
     * @param n              - the normal vector to the geometry in the intersection
     *                       point
     * @param lightIntensity - the light's intensity
     * @return kd * dotProduct(l,n)
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
	double scal = kd * l.dotProduct(n);
	if (scal < 0)
	    scal = -scal;
	return lightIntensity.scale(scal);
    }

    /**
     * calculate the specular effect
     * 
     * @param ks             - the specular's material
     * @param l              - vector from the light source to the object
     * @param n              - the normal vector to the geometry in the intersection
     *                       point
     * @param v              - the vector from the camera to intersection point
     * @param nShininess     - the shininess' material
     * @param lightIntensity - the light's intensity
     * @return ks* dotProduct(-v,r)^nShininess.
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
	double lenght = 2 * l.dotProduct(n);
	Vector r = l.sub(n.scaling(lenght)).normalization();
	double vr = -r.dotProduct(v);
	if (vr <= 0)
	    return Color.BLACK;
	return lightIntensity.scale(ks * Math.pow(vr, nShininess));
    }


	/**
	 * return the image
	 */
	public void writeToImage() {
		_imageWriter.writeToimage();
	}

}
