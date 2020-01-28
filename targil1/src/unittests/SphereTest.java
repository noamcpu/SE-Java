package unittests;

import primitives.*;
import geometries.*;
import geometries.Intersectable.GeoPoint;

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
		Sphere s = new Sphere(5, p);
		/****************** EP ******************/
		// test1 regular case of 0 intersections
		assertEquals(null, s.findIntersections(new Ray(new Point3D(-10, 0, 0), new Vector(-10, 10, 0))));
		// test2 check regular case of 2 intersections
		List<Point3D> l = new ArrayList<Point3D>();
		Ray r = new Ray(new Point3D(-6, 0, 0), new Vector(12, 0, 0));
		Vector u = new Vector(6, 0, 0);
		double Tm = new Vector(12, 0, 0).normalization().dotProduct(u);
		double D = Math.pow(u.length(), 2) - Math.pow(Tm, 2);
		double d = Math.sqrt(D);
		double Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		Point3D p1 = new Point3D(r.getP().addition(r.getDirection().scaling(Tm + Th)));
		Point3D p2 = new Point3D(r.getP().addition(r.getDirection().scaling(Tm - Th)));

		l.add(p1);
		l.add(p2);

		assertEquals(l, s.findIntersections(r));

		// test3 check regular case of 1 intersection
		List<Point3D> l1 = new ArrayList<Point3D>();
		Ray r1 = new Ray(new Point3D(0, 0, 1), new Vector(0, 0, 6));
		Vector u1 = new Vector(0, 0, -1);
		Tm = new Vector(0, 0, 6).normalization().dotProduct(u1);
		D = Math.pow(u1.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l1.add(new Point3D(r1.getP().addition(r1.getDirection().scaling(Tm + Th))));
		assertEquals(l1, s.findIntersections(r1));

		// test4 the line have two intersection points
		List<Point3D> l2 = new ArrayList<Point3D>();
		Ray r2 = new Ray(new Point3D(-10, 0, 0), new Vector(-15, 0, 0));
		assertEquals(null, s.findIntersections(r2));
		/****************** BVA ******************/
		// test5 P0 is on the sphere and the ray points to the outside
		List<Point3D> l3 = new ArrayList<Point3D>();
		Ray r3 = new Ray(new Point3D(-5, 0, 0), new Vector(-5, 0, 0));
		assertEquals(null, s.findIntersections(r3));

		// test6 the ray is from the center of the sphere
		List<Point3D> l4 = new ArrayList<Point3D>();
		Ray r4 = new Ray(new Point3D(0, 0, 0), new Vector(6, 0, 0));
		Vector u2 = new Vector(0, 0, 0);
		Tm = new Vector(6, 0, 0).normalization().dotProduct(u2);
		D = Math.pow(u2.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l4.add(new Point3D(r4.getP().addition(r4.getDirection().scaling(Tm + Th))));
		assertEquals(l4, s.findIntersections(r4));

		// test7 P0 is outside and there is no intersection.
		List<Point3D> l5 = new ArrayList<Point3D>();
		Ray r5 = new Ray(new Point3D(-7, 0, 0), new Vector(-2, 0, 0));
		assertEquals(null, s.findIntersections(r5));

		// test8 the ray is from outside the sphere to the other side of the sphere
		List<Point3D> l6 = new ArrayList<Point3D>();
		Ray r6 = new Ray(new Point3D(-6, 0, 0), new Vector(6, 0, 0));
		Vector u3 = new Vector(6, 0, 0);
		Tm = new Vector(6, 0, 0).normalization().dotProduct(u3);
		D = Math.pow(u3.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		p1 = new Point3D(r6.getP().addition(r6.getDirection().scaling(Tm + Th)));
		p2 = new Point3D(r6.getP().addition(r6.getDirection().scaling(Tm - Th)));

		l6.add(p1);
		l6.add(p2);

		assertEquals(l6, s.findIntersections(r6));

		// test9 the ray is from the sphere to the other side of the sphere by the
		// diameter
		List<Point3D> l7 = new ArrayList<Point3D>();
		Ray r7 = new Ray(new Point3D(6, 0, 0), new Vector(-8, 0, 0));
		Vector u4 = new Vector(-6, 0, 0);
		Tm = new Vector(-8, 0, 0).normalization().dotProduct(u4);
		D = Math.pow(u4.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		p1 = new Point3D(r7.getP().addition(r7.getDirection().scaling(Tm + Th)));
		p2 = new Point3D(r7.getP().addition(r7.getDirection().scaling(Tm - Th)));

		l7.add(p1);
		l7.add(p2);

		assertEquals(l7, s.findIntersections(r7));

		// test10 the ray is from the inside of the sphere by the diameter
		List<Point3D> l8 = new ArrayList<Point3D>();
		Ray r8 = new Ray(new Point3D(3, 0, 0), new Vector(7, 0, 0));
		Vector u5 = new Vector(-3, 0, 0);
		Tm = new Vector(7, 0, 0).normalization().dotProduct(u5);
		D = Math.pow(u5.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l8.add(new Point3D(r8.getP().addition(r8.getDirection().scaling(Tm + Th))));
		assertEquals(l8, s.findIntersections(r8));

		// test11 the ray is from the sphere to the other side of the sphere
		List<Point3D> l9 = new ArrayList<Point3D>();
		Ray r9 = new Ray(new Point3D(-5, 0, 0), new Vector(5, 5, 0));
		Vector u6 = new Vector(5, 0, 0);
		Tm = new Vector(5, 5, 0).normalization().dotProduct(u6);
		D = Math.pow(u6.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l9.add(new Point3D(r9.getP().addition(r9.getDirection().scaling(Tm + Th))));
		assertEquals(l9, s.findIntersections(r9));

		// test12 ray is from the sphere
		List<Point3D> l10 = new ArrayList<Point3D>();
		Ray r10 = new Ray(new Point3D(-5, 0, 0), new Vector(-1, 0, 0));
		Vector u7 = new Vector(5, 0, 0);
		Tm = new Vector(-1, 0, 0).normalization().dotProduct(u7);
		D = Math.pow(u7.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l10.add(new Point3D(r10.getP().addition(r10.getDirection().scaling(Tm - Th))));
		assertEquals(null, s.findIntersections(r10));

		// test13 ray is tangent to the sphere
		List<Point3D> l11 = new ArrayList<Point3D>();
		Ray r11 = new Ray(new Point3D(0, 0, -5), new Vector(0, 0, -1));
		Vector u8 = new Vector(0, 0, 5);
		Tm = new Vector(0, 0, -1).normalization().dotProduct(u8);
		D = Math.pow(u8.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l11.add(new Point3D(r11.getP().addition(r11.getDirection().scaling(Tm - Th))));
		assertEquals(null, s.findIntersections(r11));

		// test14 ray is from the tangent to the sphere
		List<Point3D> l12 = new ArrayList<Point3D>();
		Ray r12 = new Ray(new Point3D(-5, 5, 0), new Vector(1, 0, 0));
		Vector u9 = new Vector(5, -5, 0);
		Tm = new Vector(1, 0, 0).normalization().dotProduct(u9);
		D = Math.pow(u9.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l12.add(new Point3D(r12.getP().addition(r12.getDirection().scaling(Tm + Th))));
		assertEquals(null, s.findIntersections(r12));

		// test15 ray is on the tangent line of the sphere
		List<Point3D> l13 = new ArrayList<Point3D>();
		Ray r13 = new Ray(new Point3D(4, 5, 0), new Vector(1, 0, 0));
		Vector u10 = new Vector(-4, -5, 0);
		Tm = new Vector(1, 0, 0).normalization().dotProduct(u10);
		D = Math.pow(u10.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l13.add(new Point3D(r13.getP().addition(r13.getDirection().scaling(Tm + Th))));
		assertEquals(null, s.findIntersections(r13));

		// test16 ray is orthogonal to the radius but from outside the sphere
		List<Point3D> l14 = new ArrayList<Point3D>();
		Ray r14 = new Ray(new Point3D(0, 6, 0), new Vector(1, 0, 0));
		Vector u11 = new Vector(0, -6, 0);
		Tm = new Vector(1, 0, 0).normalization().dotProduct(u11);
		D = Math.pow(u11.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l14.add(new Point3D(r14.getP().addition(r14.getDirection().scaling(Tm + Th))));
		assertEquals(null, s.findIntersections(r14));

	}

}
