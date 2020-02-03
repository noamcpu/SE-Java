package unittests;

import primitives.*;
import geometries.*;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static geometries.Intersectable.GeoPoint;

public class SphereTest {

	/**
	 * return normal of sphere
	 */

	@Test
	public void testGetNormal() {
		Point3D p = new Point3D(0, 0, 0);
		Sphere s = new Sphere(5, p);
		assertEquals(new Vector(0, 0, 1), s.getNormal(new Point3D(0, 0, 5)));
	}

	/**
	 * return intersections of sphere
	 */
	@Test
	public void testFindIntersections() {
		// sphere
		Point3D p = new Point3D(0, 0, 0);
		Sphere s = new Sphere(2, p);
		/****************** EP ******************/
		// test1 regular case of 0 intersections
		assertEquals(null, s.findIntersections(new Ray(new Point3D(-10, 0, 0), new Vector(-10, 10, 0))));
		// test2 check regular case of 2 intersections
		List<GeoPoint> intersections = Arrays.asList(new GeoPoint(s, new Point3D(0, 0, 2)),
				new GeoPoint(s, new Point3D(-2, 0, 0)));
		assertEquals( intersections,
				s.findIntersections(new Ray(new Point3D(-3, 0, -1), new Vector(2, 0, 2))));
		// test3 check regular case of 1 intersection
		 double x = 1.0696938456699;
		double y = 1.6898979485566;
		intersections = Arrays.asList(new GeoPoint(s, new Point3D(x, y, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				s.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(3, 1, 0))));
		// test4 the line have two intersection points
		assertEquals("Sphere findIntersection error : error shouldn't intersect ", null,
				s.findIntersections(new Ray(new Point3D(-3, 0, -1), new Vector(-2, 0, -2))));
		/****************** BVA ******************/
		/******* BVA case 1 *******/
		// P0 is on the sphere and the ray points to the outside
		assertEquals("Sphere findIntersection error ", null,
				s.findIntersections(new Ray(new Point3D(-2, 0, 0), new Vector(-1, 0, 0))));
		// P0 is outside and there is no intersection.
		assertEquals("Sphere findIntersection error ", null,
				s.findIntersections(new Ray(new Point3D(-3, 0, 0), new Vector(-1, 0, 0))));
		// the ray is from the center of the sphere
		intersections = Arrays.asList(new GeoPoint(s, new Point3D(-2, 0, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				s.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(-1, 0, 0))));
		// the ray is from outside the sphere to the other side of the sphere by the
		// diameter
		intersections = Arrays.asList(new GeoPoint(s, new Point3D(-2, 0, 0)),new GeoPoint(s,new Point3D(2, 0, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				s.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(-1, 0, 0))));
		// the ray is from the sphere to the other side of the sphere by the diameter
		intersections = Arrays.asList(new GeoPoint(s, new Point3D(-2, 0, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				s.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, 0, 0))));
		// the ray is from the inside of the sphere by the diameter
		intersections = Arrays.asList(new GeoPoint(s, new Point3D(-2, 0, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				s.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(-1, 0, 0))));

		/******* BVA case 2 *******/
		// the ray is from the sphere to the other side of the sphere
		intersections = Arrays.asList(new GeoPoint(s, new Point3D(0, 2, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				s.findIntersections(new Ray(new Point3D(-2, 0, 0), new Vector(1, 1, 0))));
		// ray is from the sphere
		assertEquals("Sphere findIntersection error ", null,
				s.findIntersections(new Ray(new Point3D(0, 2, 0), new Vector(1, 1, 0))));

		/******* BVA case 3 *******/
		// ray is tangent to the sphere
		intersections = Arrays.asList(new GeoPoint(s, new Point3D(0, 2, 0)));
		assertEquals("Sphere findIntersection error ", null,
				s.findIntersections(new Ray(new Point3D(-2, 2, 0), new Vector(1, 0, 0))));
		// ray is from the tangent to the sphere
		assertEquals("Sphere findIntersection error ", null,
				s.findIntersections(new Ray(new Point3D(0, 2, 0), new Vector(1, 0, 0))));
		// ray is on the tangent line of the sphere
		intersections = Arrays.asList(new GeoPoint(s, new Point3D(0, 2, 0)));
		assertEquals("Sphere findIntersection error ", null,
				s.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(1, 0, 0))));

		/******* BVA case 4 *******/
		// ray is orthogonal to the radius but from outside the sphere
		assertEquals("Sphere findIntersection error ", null,
				s.findIntersections(new Ray(new Point3D(0, 3, 0), new Vector(1, 0, 0))));
	}
}