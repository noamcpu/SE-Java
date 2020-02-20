package renderer;

import java.util.List;

import scene.Scene;
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
	for (int i = 0; i < _imageWriter.getNx(); i++)
	    for (int j = 0; j < _imageWriter.getNy(); j++) {
		Ray ray =
			_scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNx(), i, j,
				_scene.getDistance(), _imageWriter.getWidth(), _imageWriter.getHeight());
		GeoPoint closestPoint = findClosestIntersection(ray);
		_imageWriter.writePixel(i, j, closestPoint == null ? _scene.getBackground().getColor()
			: calcColor(closestPoint, ray).getColor());
	    }
    }

	public static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * calculates the color at a certain point (pixel) by calling calculator function
     *
     * @param - GeoPoint in the space (pixel on the view plane)
     * @return the color of the point
     */
    public Color calcColor(GeoPoint geopoint, Ray inRay) {
        return calcColor(geopoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(
                _scene.getAmbient().getIntensity());
    }

    public static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * calculates the color at a certain point (pixel)
     *
     * @param - GeoPoint in the space (pixel on the view plane)
     * @return the color of the point
     */

    public Color calcColor(GeoPoint geopoint, Ray inRay, int level, double k) {
        if (level == 0 || k < MIN_CALC_COLOR_K) return Color.BLACK;
        Color color = geopoint.geometry.getEmission(); // remove Ambient Light

        Vector v = geopoint.point.sub(_scene.getCamera().getP0()).normalization();
        Vector n = geopoint.geometry.getNormal(geopoint.point);
        double kd = geopoint.geometry.getMaterial().getKd();
        double ks = geopoint.geometry.getMaterial().getKs();
        int nShininess = geopoint.geometry.getMaterial().getShininess();
        for (LightSource lightSource : _scene.getLights()) {
            Vector l = lightSource.getL(geopoint.point);
            double e1 = n.dotProduct(l);
            double e2 = n.dotProduct(v);
            if ((e1 > 0 && e2 > 0) || (e1 < 0 && e2 < 0)) {
                double ktr = transparency(l, n, geopoint);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        double kr = geopoint.geometry.getMaterial().getKr();
        double kkr = k * kr;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(n, geopoint.point, inRay);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null)
                color = color.add(calcColor(reflectedPoint, reflectedRay,
                        level - 1, kkr).scale(kr));
        }
        double kt = geopoint.geometry.getMaterial().getKt();
        double kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(geopoint.point, inRay);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null)
                color = color.add(calcColor(refractedPoint, refractedRay,
                        level - 1, kkt).scale(kt));
        }
        return color;
    }

    public static final double EPS = 1.0;

    /**
     * the function calculates the transparency of the geometry ang the point
     *
     * @param l        - the direction of the light source
     * @param n        - the normal to the geometry
     * @param geopoint - the transparency in the point
     * @return the transparency intense
     */
    public double transparency(Vector l, Vector n, GeoPoint geopoint) {
    	double d = 0;
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(n.dotProduct(lightDirection) > 0 ? EPS : -EPS);
        Point3D point = geopoint.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) return 1;
        double ktr = 1;
        for (GeoPoint gp : intersections)
            if (gp.point.distance(geopoint.point) <= d)
                ktr *= gp.geometry.getMaterial().getKt();
        return ktr;
    }
    /**
     * the function finds the closest intersection point using the getClosestPoint
     * that take a intersection list of geopoints and return the closest.
     *
     * @param ray in space
     * @return the closest geometry intersection point to the camera
     */
    public GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> points = _scene.getGeometries().findIntersections(ray);
        if (points == null)
            return null;
        Point3D p0 = ray.getP();
        return pointClosestTo(points, p0);
    }
    /**
     * the function calculates the reflected ray from the geometry
     *
     * @param n     - the normal to the geometry
     * @param point - the intersection point
     * @param inRay - the ray to the point
     * @return the reflected ray
     */
    public Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
        Vector v = inRay.getDirection();
        Vector r = v.sub(n.scale(v.dotProduct(n) * 2));
        Vector new_v = v.add((n.scale(v.dotProduct(n))).scale(-2));
        Point3D newPoint = point.add(new_v.scale(0.1));
        return new Ray(newPoint, new_v);
    }

    /**
     * the function refracts the ray by Snell's law
     *
     * @param point - the intersection point
     * @param inRay - the ray to the point
     * @return the refracted ray
     */
    public Ray constructRefractedRay(Point3D point, Ray inRay) {
        Vector v = inRay.getDirection();
        Point3D newPoint = point.add(v.scale(0.1));
        return new Ray(newPoint, v);
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
     * finds the closest point from a given list to a point
     *
     * @param points
     * @param p0
     * @return the closest point
     */
    public GeoPoint pointClosestTo(List<GeoPoint> points, Point3D p0) {
        double minValue = points.get(0).getPoint().distance(p0);
        GeoPoint minPoint = points.get(0);
        for (int i = 0; i < points.size(); ++i) {
            GeoPoint p = points.get(i);
            double d0 = p.point.distance(p0);
            if (d0 < minValue) {
                minValue = d0;
                minPoint = p;
            }
        }
        return minPoint;
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
	public Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
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
	public Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		double lenght = 2 * l.dotProduct(n);
		Vector r = l.sub(n.scale(lenght)).normalization();
		double vr = -r.dotProduct(v);
		if (vr <= 0)
			return Color.BLACK;
		return lightIntensity.scale(ks * Math.pow(vr, nShininess));
	}

	/**
	 * return the image
	 */
	public void writeToImage() {
		_imageWriter.writeToImage();
	}

}

