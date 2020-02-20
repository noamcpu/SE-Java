package unittests;

import static org.junit.Assert.*;
import static geometries.Intersectable.GeoPoint;

import geometries.*;
import primitives.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class of Sphere {@link geometries.Sphere}
 */
public class SphereTest {
	/**
	 * test Method for {@link geometries.Sphere#getNormal (geomtries.Sphere)}
	 */
	@Test
	public void testGetNormal() {
		Point3D center = new Point3D(0, 0, 0);
		Sphere sphere = new Sphere(2, center);
		Point3D p = new Point3D(0, 2, 0);
		Vector vec = new Vector(0, 1, 0);
		assertEquals("Get normal function error", sphere.getNormal(p), vec);
	}

	/**
	 * return intersections of sphere
	 */
	@Test
	public void testFindIntersections() {
		// sphere
		Point3D p = new Point3D(0, 0, 0);
		Sphere s = new Sphere(5, p);
		/****************** EP ******************/
		// test1 regular case of 0 intersections
		assertEquals(null, s.findIntersections(new Ray(new Point3D(-10, 0, 0), new Vector(-10, 10, 0))));
		// test2 check regular case of 2 intersections
		List<GeoPoint> l = new ArrayList<GeoPoint>();
		Ray r = new Ray(new Point3D(-6, 0, 0), new Vector(12, 0, 0));
		Vector u = new Vector(6, 0, 0);
		double Tm = new Vector(12, 0, 0).normalization().dotProduct(u);
		double D = Math.pow(u.length(), 2) - Math.pow(Tm, 2);
		double d = Math.sqrt(D);
		double Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		Point3D p1 = (r.getP().add(r.getDirection().scale(Tm + Th)));
		Point3D p2 = (r.getP().add(r.getDirection().scale(Tm - Th)));

		l.add(new GeoPoint(s,p1)); 
		l.add(new GeoPoint(s,p2)); 

		assertEquals(l, s.findIntersections(r));

		// test3 check regular case of 1 intersection
		List<GeoPoint> l1 = new ArrayList<GeoPoint>();
		Ray r1 = new Ray(new Point3D(0, 0, 1), new Vector(0, 0, 6));
		Vector u1 = new Vector(0, 0, -1);
		Tm = new Vector(0, 0, 6).normalization().dotProduct(u1);
		D = Math.pow(u1.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l1.add(new GeoPoint (s,r1.getP().add(r1.getDirection().scale(Tm + Th))));
		assertEquals(l1, s.findIntersections(r1));

		// test4 the line have two intersection points
		List<GeoPoint> l2 = new ArrayList<GeoPoint>();
		Ray r2 = new Ray(new Point3D(-10, 0, 0), new Vector(-15, 0, 0));
		assertEquals(null, s.findIntersections(r2));
		/****************** BVA ******************/
		// test5 P0 is on the sphere and the ray points to the outside
		List<GeoPoint> l3 = new ArrayList<GeoPoint>();
		Ray r3 = new Ray(new Point3D(-5, 0, 0), new Vector(-5, 0, 0));
		assertEquals(null, s.findIntersections(r3));

		// test6 the ray is from the center of the sphere
		List<GeoPoint> l4 = new ArrayList<GeoPoint>();
		Ray r4 = new Ray(new Point3D(1, 0, 0), new Vector(0, 0, 1));
		Vector u2 = new Vector(-1, 0, 0);
		Tm = new Vector(0, 0, 1).normalization().dotProduct(u2);
		D = Math.pow(u2.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l4.add(new GeoPoint (s,r4.getP().add(r4.getDirection().scale(Tm + Th))));
		assertEquals(l4, s.findIntersections(r4));

		// test7 P0 is outside and there is no intersection.
		List<GeoPoint> l5 = new ArrayList<GeoPoint>();
		Ray r5 = new Ray(new Point3D(-7, 0, 0), new Vector(-2, 0, 0));
		assertEquals(null, s.findIntersections(r5));

		// test8 the ray is from outside the sphere to the other side of the sphere
		List<GeoPoint> l6 = new ArrayList<GeoPoint>();
		Ray r6 = new Ray(new Point3D(-6, 0, 0), new Vector(6, 0, 0));
		Vector u3 = new Vector(6, 0, 0);
		Tm = new Vector(6, 0, 0).normalization().dotProduct(u3);
		D = Math.pow(u3.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		p1 =(r6.getP().add(r6.getDirection().scale(Tm + Th)));
		p2 =(r6.getP().add(r6.getDirection().scale(Tm - Th)));

		l6.add(new GeoPoint (s,p1));
		l6.add(new GeoPoint (s,p2));

		assertEquals(l6, s.findIntersections(r6));

		// test9 the ray is from the sphere to the other side of the sphere by the
		// diameter
		List<GeoPoint> l7 = new ArrayList<GeoPoint>();
		Ray r7 = new Ray(new Point3D(6, 0, 0), new Vector(-8, 0, 0));
		Vector u4 = new Vector(-6, 0, 0);
		Tm = new Vector(-8, 0, 0).normalization().dotProduct(u4);
		D = Math.pow(u4.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		p1 =(r7.getP().add(r7.getDirection().scale(Tm + Th)));
		p2 =(r7.getP().add(r7.getDirection().scale(Tm - Th)));

		l7.add(new GeoPoint (s,p1));
		l7.add(new GeoPoint (s,p2));

		assertEquals(l7, s.findIntersections(r7));

		// test10 the ray is from the inside of the sphere by the diameter
		List<GeoPoint> l8 = new ArrayList<GeoPoint>();
		Ray r8 = new Ray(new Point3D(3, 0, 0), new Vector(7, 0, 0));
		Vector u5 = new Vector(-3, 0, 0);
		Tm = new Vector(7, 0, 0).normalization().dotProduct(u5);
		D = Math.pow(u5.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l8.add(new GeoPoint (s,r8.getP().add(r8.getDirection().scale(Tm + Th))));
		assertEquals(l8, s.findIntersections(r8));

		// test11 the ray is from the sphere to the other side of the sphere
		List<GeoPoint> l9 = new ArrayList<GeoPoint>();
		Ray r9 = new Ray(new Point3D(-5, 0, 0), new Vector(5, 5, 0));
		Vector u6 = new Vector(5, 0, 0);
		Tm = new Vector(5, 5, 0).normalization().dotProduct(u6);
		D = Math.pow(u6.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l9.add(new GeoPoint (s,r9.getP().add(r9.getDirection().scale(Tm + Th))));
		assertEquals(l9, s.findIntersections(r9));

		// test12 ray is from the sphere
		List<GeoPoint> l10 = new ArrayList<GeoPoint>();
		Ray r10 = new Ray(new Point3D(-5, 0, 0), new Vector(-1, 0, 0));
		Vector u7 = new Vector(5, 0, 0);
		Tm = new Vector(-1, 0, 0).normalization().dotProduct(u7);
		D = Math.pow(u7.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l10.add(new GeoPoint (s,r10.getP().add(r10.getDirection().scale(Tm - Th))));
		assertEquals(null, s.findIntersections(r10));

		// test13 ray is tangent to the sphere
		List<GeoPoint> l11 = new ArrayList<GeoPoint>();
		Ray r11 = new Ray(new Point3D(0, 0, -5), new Vector(0, 0, -1));
		Vector u8 = new Vector(0, 0, 5);
		Tm = new Vector(0, 0, -1).normalization().dotProduct(u8);
		D = Math.pow(u8.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l11.add(new GeoPoint (s,r11.getP().add(r11.getDirection().scale(Tm - Th))));
		assertEquals(null, s.findIntersections(r11));

		// test14 ray is from the tangent to the sphere
		List<GeoPoint> l12 = new ArrayList<GeoPoint>();
		Ray r12 = new Ray(new Point3D(-5, 5, 0), new Vector(1, 0, 0));
		Vector u9 = new Vector(5, -5, 0);
		Tm = new Vector(1, 0, 0).normalization().dotProduct(u9);
		D = Math.pow(u9.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l12.add(new GeoPoint (s,r12.getP().add(r12.getDirection().scale(Tm + Th))));
		assertEquals(null, s.findIntersections(r12));

		// test15 ray is on the tangent line of the sphere
		List<GeoPoint> l13 = new ArrayList<GeoPoint>();
		Ray r13 = new Ray(new Point3D(4, 5, 0), new Vector(1, 0, 0));
		Vector u10 = new Vector(-4, -5, 0);
		Tm = new Vector(1, 0, 0).normalization().dotProduct(u10);
		D = Math.pow(u10.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l13.add(new GeoPoint (s,r13.getP().add(r13.getDirection().scale(Tm + Th))));
		assertEquals(null, s.findIntersections(r13));

		// test16 ray is orthogonal to the radius but from outside the sphere
		List<GeoPoint> l14 = new ArrayList<GeoPoint>();
		Ray r14 = new Ray(new Point3D(0, 6, 0), new Vector(1, 0, 0));
		Vector u11 = new Vector(0, -6, 0);
		Tm = new Vector(1, 0, 0).normalization().dotProduct(u11);
		D = Math.pow(u11.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l14.add(new GeoPoint (s,r14.getP().add(r14.getDirection().scale(Tm + Th))));
		assertEquals(null, s.findIntersections(r14));

	}

}
