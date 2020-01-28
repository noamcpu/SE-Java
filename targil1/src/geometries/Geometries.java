package geometries;

import primitives.Point3D;
import primitives.Ray;
import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {

	private List<Intersectable> shapes = new ArrayList<Intersectable>();

	// ***************** Constructors ********************** //

	/**
	 * constructor
	 * 
	 * @param intersectable
	 */
	public Geometries() {
		shapes = new ArrayList<Intersectable>();
	}

	/**
	 * constructs a list with geometries (one or more)
	 *
	 * @param list of geometry shapes.
	 */
	public Geometries(Intersectable... geometries) {
		for (int i = 0; i < geometries.length; ++i) {
			shapes.add(geometries[i]);
		}
	}

	public Geometries(List<Intersectable> intersectable) {

		this.shapes = intersectable;
	}

	// ***************** Operations ******************** //

	/**
	 * function for adding geometry
	 * 
	 * @param a
	 */
	public void add(Intersectable... geometries) {
		for (int i = 0; i < geometries.length; ++i) {
			shapes.add(geometries[i]);
		}
	}

	/**
	 * finds intersections of the ray with the geometries that are in the list
	 *
	 * @param ray in space.
	 * @return intersection points
	 */
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> intersections = new ArrayList<>();
		for (int i = 0; i < shapes.size(); ++i) {
			// if an intersection is found, add it to the list
			if (shapes.get(i).findIntersections(ray) != null)
				intersections.add(shapes.get(i).findIntersections(ray).get(0));
		}
		// if no intersections were found, return null
		if (intersections.size() == 0)
			return null;
		return intersections;
	}
}
