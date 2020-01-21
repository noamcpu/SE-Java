package renderer;

import java.awt.*;
import java.awt.Color;

import elements.*;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.*;
import java.util.List;

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

	// *****************Getters/Setters******************//

	public Scene get_scene() {
		return _scene;
	}

	public void set_scene(Scene _scene) {
		this._scene = _scene;
	}

	public ImageWriter get_imageWriter() {
		return _imageWriter;
	}

	public void set_imageWriter(ImageWriter _imageWriter) {
		this._imageWriter = _imageWriter;
	}

	// ***************** Operations ******************** //

	/**
	 * apply the image on the screen
	 */
	public void renderImage() {
		for (int i = 0; i < _imageWriter.getNy(); ++i)
			for (int j = 0; j < _imageWriter.getNx(); ++j) {
				Ray ray = _scene.get_camera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), j, i,
						_scene.get_distance(), _imageWriter.getWidth(), _imageWriter.getHeight());
				List<Point3D> intersectionPoints = _scene.get_geometries().findIntersections(r);
				if (intersectionPoints == null)
					_imageWriter.writePixel(j, i, _scene.get_background().getColor());
				else {
					Point3D closestPoint = getClosestPoint(intersectionPoints);
					_imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
				}
			}
	}
	/**
	 * calcColor that send to the recursive calcColor
	 * 
	 * @param geopoint
	 * @param inRay
	 * @return
	 */
	private primitives.Color calcColor(Point3D p) {
		return _scene.get_ambietLight().getIntensity();
	}

	/**
     * find the closest intersection point with geometry
     * 
     * @param ray
     * @return
     */
    private List<GeoPoint> getClosestIntersection(Ray ray) {
	List<GeoPoint> intersectionPoints = _scene.get_geometries().findIntersections(ray);
	return intersectionPoints;
    }

	
	/**
	 * makes grid on the image
	 * 
	 * @param interval how frequent the grid appears
	 */
	public void printGrid(int interval, Color color) {
		for (int i = 0; i < _imageWriter.getNx(); i++) {
			for (int j = 0; j < _imageWriter.getNy(); j++)
				if (i % interval == 0 || j % interval == 0)
					_imageWriter.writePixel(j, i, color);
		}
	}

	/**
	 * return the image
	 */
	public void writeToImage() {
		_imageWriter.writeToimage();
	}

}
