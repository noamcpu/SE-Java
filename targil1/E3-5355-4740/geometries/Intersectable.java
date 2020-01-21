package geometries;

import java.util.List;
import primitives.*;


public interface Intersectable {
	/**
	 * Find the intersections with the geometry
	 * 
	 * @param r
	 * @return list of intersections points
	 */
	List<Point3D> findIntersections(Ray r);

}

