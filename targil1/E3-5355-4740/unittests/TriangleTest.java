package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import geometries.*;
import primitives.*;
import java.util.ArrayList;
public class TriangleTest {
	/**
	 * return triangle
	 */
	@Test
	public void testTriangle() {
		Point3D p1 = new Point3D(2, 0, 0);
		Point3D p2 = new Point3D(0, 4, 0);
		Point3D p3 = new Point3D(0, 0, 0);
		Triangle triangle = new Triangle(p1, p2, p3);
	}

	/**
	 * return normal of triangle
	 */
	@Test
	public void testGetNormal() {
		Polygon py = new Polygon(new Point3D(2, 0, 0), new Point3D(0, 4, 0), new Point3D(0, 0, 0));
		Vector vec = new Vector(0, 0, 1);
		assertEquals(vec, py.getNormal(new Point3D(1, 1, 0)));

	}
	@Test
	 public void testFindIntersections() {
		ArrayList<Point3D> list = new ArrayList<Point3D>();
		Triangle tri = new Triangle(new Point3D(2, 0, 0), new Point3D(0, 0, 2), new Point3D(-1, 0, 0));

		// the point is inside the Triangle
		Ray r = new Ray(new Point3D(0, -1, 0), new Vector(0, 1, 1));
		Point3D p = new Point3D(0, 0, 1);
		list.add(p);
		assertEquals(list, tri.findIntersections(r));

		// ray intersects outside triangle
		list.clear();
		r= new Ray(new Point3D(0, 0, 3), new Vector(0, -2, 3));
		assertEquals(null, tri.findIntersections(r));

		// checking if one Intersects is one of triangle 
		r= new Ray(new Point3D(0, 2, 0), new Vector(0, -2, 2));
		assertEquals(list, tri.findIntersections(r));

		// the point between the two sides of the triangle
		r= new Ray(new Point3D(0, 0, 3), new Vector(0, 1, 1));
		assertEquals(null, tri.findIntersections(r));

		// the point on one side of the triangle
		list.clear();
		r = new Ray(new Point3D(0, 0, 0), new Vector(0, 1, 1));
		assertEquals(null, tri.findIntersections(r));

		// point on one of the triangular ribs
		list.clear();
		r = new Ray(new Point3D(-2, 0, 0), new Vector(0, 1, 1));
		assertEquals(null, tri.findIntersections(r));

		// the point is close to one of the triangle
		list.clear();
		r = new Ray(new Point3D(-1, 0, 1), new Vector(0, 1, 1));
		assertEquals(null, tri.findIntersections(r));

	}

}

