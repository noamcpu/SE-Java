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
		Triangle triangle = new Triangle( p1, p2, p3);
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

}
