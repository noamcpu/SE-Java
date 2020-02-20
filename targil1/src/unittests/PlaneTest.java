package unittests;

import geometries.*;

import static org.junit.Assert.*;

import primitives.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static geometries.Intersectable.GeoPoint;

/**
 * Test class of Polygon {@link geometries.Plane}}
 */
public class PlaneTest {

	/**
	 * test Method for {@link geometries.Plane#getNormal (geomtries.Plane)}
	 */
	@Test
	public void testGetNormal() {
		Point3D p = new Point3D(0, 0, 0);
		Vector vec = new Vector(0, 1, 0);
		Plane plane = new Plane(vec, p);
		assertEquals("Get normal function error", vec, plane.getNormal());
	}

	/**
	 * return intersections of plane
	 */
	@Test
	// ray intersects with plane
	public void testFindIntersections() {
		List<GeoPoint> list = new ArrayList<GeoPoint>();
		Vector n = new Vector(0, 0, 1);
		Point3D q = new Point3D(1, 1, 0);
		Plane p = new Plane(n, q);
		// EP-ray intersects with the plane
		Vector v1 = new Vector(1, 1, -1);
		Point3D q1 = new Point3D(0, 0, 1);
		Ray r1 = new Ray(q1, v1);
		list.add(new GeoPoint(p, new Point3D(1, 1, 0)));
		assertEquals("error", list, p.findIntersections(r1));

		// EP ray does not intersect with the plane
		list.clear();
		Vector v2 = new Vector(1, 1, 1);
		Point3D q2 = new Point3D(0, 0, 1);
		Ray r2 = new Ray(q2, v2);
		assertEquals(null, p.findIntersections(r2));
		// BVA-ray parallel to the plane in the plane
		list.clear();
		Vector v3 = new Vector(1, 1, 0);
		Point3D q3 = new Point3D(0, 0, 0);
		Ray r3 = new Ray(q3, v3);
		assertEquals(null, p.findIntersections(r3));

		// BVA- ray parallel to the plane not in the plane
		list.clear();
		Vector v4 = new Vector(-1, 0, 1);
		Point3D q4 = new Point3D(2, 0, 0);
		Ray r4 = new Ray(q4, v4);
		assertEquals(null, p.findIntersections(r4));

		// ray orthogonal to the plane and begins into the plane
		list.clear();
		Vector v5 = new Vector(0, 0, 1);
		Point3D q5 = new Point3D(0, 0, 0);
		Ray r5 = new Ray(q5, v5);
		assertEquals(null, p.findIntersections(r5));
		// ray orthogonal to the plane and begins before the plane
		list.clear();
		Vector v6 = new Vector(0, 0, 1);
		Point3D q6 = new Point3D(0, 0, -1);
		Ray r6 = new Ray(q6, v6);
		Point3D t1 = new Point3D(0, 0, 0);
		list.add(new GeoPoint(p, t1));
		assertEquals(list, p.findIntersections(r6));
		// ray orthogonal to the plane and begins after the plane
		list.clear();
		Vector v7 = new Vector(0, 0, 1);
		Point3D q7 = new Point3D(0, 0, 2);
		Ray r7 = new Ray(q7, v7);
		assertEquals(null, p.findIntersections(r7));
		// BVA ray begins in the plane
		list.clear();
		Vector v8 = new Vector(1, 1, 1);
		Point3D q8 = new Point3D(0, 0, 0);
		Ray r8 = new Ray(q8, v8);
		assertEquals(null, p.findIntersections(r8));

		// BVA ray begins in the reference point of the plane
		list.clear();
		Vector v9 = new Vector(1, 1, 1);
		Point3D q9 = new Point3D(1, 1, 0);
		Ray r9 = new Ray(q9, v9);
		assertEquals(null, p.findIntersections(r9));
	}
}
