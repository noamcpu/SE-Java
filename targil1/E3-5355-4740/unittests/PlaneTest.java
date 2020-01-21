package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import primitives.*;
import geometries.Plane;

import java.util.ArrayList;
import java.util.List;


public class PlaneTest {
	/**
	 * check the class plane
	 */
	@Test
	public void test() {
		Point3D point1 = new Point3D(1, 1, 1);
		Point3D point2 = new Point3D(2, 0, 1);
		Vector normal = new Vector(2, 2, 3);
		Plane plane1 = new Plane(normal, point1);
		Plane plane2 = new Plane(normal, point2);
		assertFalse(plane1.equals(plane2));
	}

	/**
	 * return normal of plan
	 */
	@Test
	public void testGetNormal() {
		Point3D p = new Point3D(0, 0, 0);
		Vector vec = new Vector(0, 1, 0);
		Plane plane = new Plane(vec, p);
		assertEquals(vec, plane.getNormal());
	}
	@Test
	// ray intersects with plane
		 public void testFindIntersections() {
			List<Point3D> list = new ArrayList<Point3D>();
			Vector n = new Vector(1, 1, 1);
			Point3D q = new Point3D(1, 0, 0);
			Plane p = new Plane(n,q);

			// EP-ray intersects with the plane
			Vector N = new Vector(-2, -1, -1);
			Point3D Q = new Point3D(3, 1, 1);
			Ray T = new Ray(Q, N);
			Point3D t = new Point3D(1, 0, 0);
			list.add(t);
			assertEquals(list, p.findIntersections(T));

			/** EP-ray does not intersect with the plane
			Vector N0 = new Vector(-2, -1, -1);
		    Point3D Q0 = new Point3D(3, 1, 1);
			Ray T0 = new Ray(Q0, N0);
			Point3D t0 = new Point3D(1, 8, 0);
			list.add(t0);
			assertEquals(list, p.findIntersections(T0));
			*/
	//ray  doesn't intersects with plane
			list.clear();
			Vector N1 = new Vector(1, 0, 0);
			Point3D Q1 = new Point3D(2, 0, 0);
			Ray T1 = new Ray(Q1, N1);
			assertEquals(null, p.findIntersections(T1));

	//ray parallel to the plane in the plane 
			list.clear();
			Vector N2 = new Vector(-1, 0, 1);
			Point3D Q2 = new Point3D(2, 0, 0);
			Ray T2 = new Ray(Q2, N2);

			assertEquals(null, p.findIntersections(T2));

	//ray parallel to the plane  not in the plane 
			list.clear();
			Vector N3 = new Vector(-1, 0, 1);
			Point3D Q3 = new Point3D(2, 0, 0);
			Ray T3 = new Ray(Q3, N3);
			assertEquals(null, p.findIntersections(T3));

	//ray orthogonal to the plane and begins into the plane
			list.clear();
			Vector N4 = new Vector(1, 1, 1);
			Point3D Q4 = new Point3D(0, 1, 0);
			Ray T4 = new Ray(Q4, N4);
			assertEquals(null, p.findIntersections(T4));

	//ray orthogonal to the plane and begins before the plane
			list.clear();
			Vector N5 = new Vector(1, 1, 1);
			Point3D Q5 = new Point3D(0, 0, -2);
			Ray T5 = new Ray(Q5, N5);
			list.add(new Point3D(1,1,-1));
			assertEquals(list, p.findIntersections(T5));

	//ray orthogonal to the plane and begins after the plane
			list.clear();
			Vector N6 = new Vector(1, 1, 1);
			Point3D Q6 = new Point3D(0, 0, 2);
			Ray T6 = new Ray(Q6, N6);
			assertEquals(null, p.findIntersections(T6));

	//ray start in plane but not orthogonal or parallel
			list.clear();
			Vector N7 = new Vector(1, 0, 0);
			Point3D Q7 = new Point3D(0, 1, 0);
			Ray T7 = new Ray(Q7, N7);
			assertEquals(null, p.findIntersections(T7));
		}
	//BVA ray begins in the reference point of the plane
	}


