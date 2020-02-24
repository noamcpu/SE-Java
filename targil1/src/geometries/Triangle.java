package geometries;

import primitives.*;

public class Triangle extends Polygon {
	// ***************** Constructors ********************** //
	/**
	 * constructor
	 * 
	 * @param points
	 * @param plane
	 */
	public Triangle(Material material, Color emmission, Point3D p1, Point3D p2, Point3D p3) {
		super(material, emmission, p1, p2, p3);
	}
}
