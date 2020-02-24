package unittests;

import static org.junit.Assert.*;
import static geometries.Intersectable.GeoPoint;

import primitives.*;
import geometries.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class of Polygon
 */
public class PolygonTest {

	/**
	 * test Method for find-intersections of polygon
	 */
	@Test
	public void testFindIntersections() {
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(1, 0, 0);
		Point3D p3 = new Point3D(1, 1, 0);
		Point3D p4 = new Point3D(0, 1, 0);
		List<GeoPoint> list = new ArrayList<GeoPoint>();
		Polygon square = new Polygon(p1, p2, p3, p4);
		Ray ray = new Ray(new Point3D(0.25, 0.25, -1), new Vector(0, 0, 1));
		list.add(new GeoPoint(square, new Point3D(0.25, 0.25, 0)));
		assertEquals("Polygon findInte=rsection error ", list, square.findIntersections(ray)); // inside square
		ray = new Ray(new Point3D(-1, -1, -1), new Vector(0, 0, 1));
		assertEquals("Polygon findIntersection error ", null, square.findIntersections(ray)); // outside square
	}
}
