package geometries;

import primitives.*;

public interface  Geometry extends Intersectable  {
	/**
	 * Calculates vector orthogonal to the plane
	 * 
	 * @param p point of plane
	 * @return normal vector
	 */
	Vector getNormal(Point3D p);

}
