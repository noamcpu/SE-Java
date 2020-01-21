package unittests;

import primitives.*;
import geometries.*;
import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SphereTest {

	/**
	 * return normal of sphere
	 */

	@Test
	public void testGetNormal() {
		Point3D p = new Point3D(0, 0, 0);
		Sphere b = new Sphere(5, p);
		assertEquals(new Vector(0, 0, 1), b.getNormal(new Point3D(0, 0, 5)));
	}

	@Test
	public void testFindIntersections() {
		// sphere
		Point3D p = new Point3D(0, 0, 0);
		Sphere s = new Sphere(5, p);
		/****************** EP ******************/
		// the line have two intersection points
       assertEquals( null, s.findIntersections(new Ray(new Point3D(-6, 0, -1), new Vector(-5, 0, -5))));
		// check regular case of 1 intersection
		List<Point3D> l = new ArrayList<Point3D>();
		Ray r = new Ray(new Point3D(-15, 0, 5), new Vector(1, 0, 0));
		assertEquals(l, s.findIntersections(r));
		
		// check regular case of 2 intersections
		List<Point3D> l1 = new ArrayList<Point3D>();
		Ray r1 = new Ray(new Point3D(-5, 0, -1), new Vector(1, 1, 1));
		Vector u = new Vector(5, 0, 1);
		double Tm = new Vector(1, 1, 1).normalization().dotProduct(u);
		double D = Math.pow(u.length(), 2) - Math.pow(Tm, 2);
		double d = Math.sqrt(D);
		double Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		Point3D p1 = new Point3D(r1.getP().addition(r1.getDirection().scaling(Tm + Th)));
		Point3D p2 = new Point3D(r1.getP().addition(r1.getDirection().scaling(Tm - Th)));

		l1.add(p2);
		l1.add(p1);
		assertEquals(l1, s.findIntersections(r1));

        //regular case of 0 intersections
		List<Point3D> l2 = new ArrayList<Point3D>();
		Ray r2 = new Ray(new Point3D(5, 6, 7), new Vector(1, 1, 0));
		assertEquals(null, s.findIntersections(r2));
		/****************** BVA ******************/
		// the Point3D is inside the Sphere
		List<Point3D> l3 = new ArrayList<Point3D>();
		Ray r3 = new Ray(new Point3D(0, 0, 4), new Vector(1, 1, 1));
		Vector u1 = new Vector(0, 0, -4);
		Tm = r3.getDirection().dotProduct(u1);
		D = Math.pow(u1.length(), 2) - Math.pow(Tm, 2);
		d = Math.sqrt(D);
		Th = Math.sqrt(Math.pow(5, 2) - Math.pow(d, 2));
		l3.add(new Point3D(r3.getP().addition(r3.getDirection().scaling(Tm + Th))));
		assertEquals(l3, s.findIntersections(r3));

//test5, check case of ray that the direction is when the sphere
		List<Point3D> l4 = new ArrayList<Point3D>();
		Ray r4 = new Ray(new Point3D(5, 0, 2), new Vector(8, 2, 2));
		assertEquals(null, s.findIntersections(r4));

//test6,check case if p00 is on the frame and the vector direction is inside the sphere
		List<Point3D> l5 = new ArrayList<Point3D>();
		Ray r5 = new Ray(new Point3D(0, 0, -5), new Vector(1, 1, 1));
		double t = 5 / Math.sqrt(3);
		Point3D p5 = new Point3D(r5.getP().addition(r5.getDirection().scaling(2 * t)));
		l5.add(r5.getP());
		l5.add(p5);
		assertEquals(l5, s.findIntersections(r5));

//test7,like test6 but the direction is no get inside the sphere
		List<Point3D> l6 = new ArrayList<Point3D>();
		Ray r6 = new Ray(new Point3D(0, 0, -5), new Vector(-1, 1, -1));
		l6.add(new Point3D(0, 0, -5));
		assertEquals(l6, s.findIntersections(r6));

//test8,if p00 is on the frame , but the ray orthogonal to radius
		List<Point3D> l7 = new ArrayList<Point3D>();
		Ray r7 = new Ray(new Point3D(0, 0, -5), new Vector(1, 0, 0));
		l7.add(r7.getP());
		assertEquals(l7, s.findIntersections(r7));

//test9, like test8 but here p00 is not on the frame , but is continue behind orthogonal to radius
		List<Point3D> l8 = new ArrayList<Point3D>();
		Ray r8 = new Ray(new Point3D(1, 0, 5), new Vector(1, 0, 0));
		assertEquals(null, s.findIntersections(r8));

//test10, when the direction of ray ant Vector u orthogonal , and p00 is outside the sphere
		List<Point3D> l9 = new ArrayList<Point3D>();
		Ray r9 = new Ray(new Point3D(0, 0, -6), new Vector(1, 0, 0));
		assertEquals(null, s.findIntersections(r9));

//test11, when p00 is outside the sphere but is the continue of radius, and the direction is also in that way
		List<Point3D> l10 = new ArrayList<Point3D>();
		Ray r10 = new Ray(new Point3D(0, 0, -6), new Vector(0, 0, -1));
		assertEquals(null, s.findIntersections(r10));

//test12, like test11 but p00 is on the frame
		List<Point3D> l11 = new ArrayList<Point3D>();
		Ray r11 = new Ray(new Point3D(0, 0, -5), new Vector(0, 0, -1));
		l11.add(r11.getP());
		assertEquals(l11, s.findIntersections(r11));

//test13, when p00 in on the frame and the ray passes on Point O
		List<Point3D> l12 = new ArrayList<Point3D>();
		Ray r12 = new Ray(new Point3D(0, 0, -5), new Vector(0, 0, 1));
		l12.add(new Point3D(0, 0, -5));
		l12.add(new Point3D(0, 0, 5));
		assertEquals(l12, s.findIntersections(r12));

//test14, like test13 but p00 is outside the sphere
		List<Point3D> l13 = new ArrayList<Point3D>();
		Ray r13 = new Ray(new Point3D(0, 0, -5), new Vector(0, 0, 1));
		l13.add(new Point3D(0, 0, -5));
		l13.add(new Point3D(0, 0, 5));
		assertEquals(l13, s.findIntersections(r13));

//test15, when p00 is on the center of sphere
		List<Point3D> l14 = new ArrayList<Point3D>();
		Ray r14 = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1));
		l14.add(r14.getP().addition(r14.getDirection().scaling(5)));
		assertEquals(l14, s.findIntersections(r14));

	}

}
