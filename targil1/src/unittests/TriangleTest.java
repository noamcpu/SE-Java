
package unittests;

import static org.junit.Assert.*;

import primitives.*;
import geometries.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static geometries.Intersectable.GeoPoint;

/**
 * Test class of Triangle 
 */
public class TriangleTest {
	static Point3D p1 = new Point3D(0, 0, 0);
	static Point3D p2 = new Point3D(0, 3, 0);
	static Point3D p3 = new Point3D(4, 0, 0);
	static Triangle triangle = new Triangle(p1, p2, p3);

	/**
	 * test Method for findintersections of triangle
	 */
	@Test
	 public void testFindIntersections() {
		ArrayList<GeoPoint> list = new ArrayList<GeoPoint>();
		Triangle tri = new Triangle(new Point3D(2, 0, 0), new Point3D(0, 0, 2), new Point3D(-1, 0, 0));

		// the point is inside the Triangle
		Ray ray = new Ray(new Point3D(0, -1, 0), new Vector(0, 1, 1));
		Point3D p = new Point3D(0, 0, 1);
		list.add(new GeoPoint(tri, p));
		assertEquals(list, tri.findIntersections(ray));

		// ray intersects outside triangle
		list.clear();
		ray = new Ray(new Point3D(0, 0, 3), new Vector(0, -2, 3));
		assertEquals(null, tri.findIntersections(ray));

		// checking if one Intersects is one of triangle point
		list.clear();
		ray = new Ray(new Point3D(0, 2, 0), new Vector(0, -2, 2));
		assertEquals(null, tri.findIntersections(ray));

		// the point between the two sides of the triangle
		ray = new Ray(new Point3D(0, 0, 3), new Vector(0, 1, 1));
		assertEquals(null, tri.findIntersections(ray));

		// the point on one side of the triangle
		list.clear();
		ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 1, 1));
		assertEquals(null, tri.findIntersections(ray));

		// point on one of the triangular ribs
		list.clear();
		ray = new Ray(new Point3D(-2, 0, 0), new Vector(0, 1, 1));
		assertEquals(null, tri.findIntersections(ray));

		// the point is close to one of the triangle
		list.clear();
		ray = new Ray(new Point3D(-1, 0, 1), new Vector(0, 1, 1));
		assertEquals(null, tri.findIntersections(ray));

	}

}
