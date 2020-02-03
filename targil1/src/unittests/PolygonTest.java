package unittests;

import static org.junit.Assert.*;
import java.lang.Math;
import org.junit.Test;
import primitives.*;
import geometries.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static geometries.Intersectable.GeoPoint;
public class PolygonTest {

	/**
	 * return normal of polygon
	 */
	@Test
	public void testGetNormal() {
		Polygon py = new Polygon(new Point3D(2, 0, 0), new Point3D(0, 4, 0), new Point3D(0, 0, 0));
		Vector vec = new Vector(0, 0, 1);
		assertEquals(vec, py.getNormal(new Point3D(1, 1, 0)));

	}
	/**
	 * test Method for
	 * {@link geometries.Polygon#findIntersections (geomtries.Polygon)}
	 */
	@Test
	public void testFindIntersections() {
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(1, 0, 0);
		Point3D p3 = new Point3D(1, 1, 0);
		Point3D p4 = new Point3D(0, 1, 0);
		Polygon square = new Polygon(p1, p2, p3, p4);
		Ray ray = new Ray(new Point3D(0.25, 0.25, -1), new Vector(0, 0, 1));
		List<GeoPoint> intersections = Arrays.asList(new GeoPoint( square, new Point3D(0.25, 0.25, 0)));
		assertEquals("Polygon findIntersection error ", intersections, square.findIntersections(ray)); // inside square
		ray = new Ray(new Point3D(-1, -1, -1), new Vector(0, 0, 1));
		assertEquals("Polygon findIntersection error ", null, square.findIntersections(ray)); // outside square
		p1 = new Point3D(-2, 0, 0);
		p2 = new Point3D(-1, 2, 0);
		p3 = new Point3D(1, 2, 0);
		p4 = new Point3D(2, 0, 0);
		Point3D p5 = new Point3D(0, -2, 0);
		Polygon pentagon = new Polygon(p1, p2, p3, p4, p5);
		ray = new Ray(new Point3D(0, 0, -1), new Vector(0, 0, 1));
		intersections = Arrays.asList(new GeoPoint( square, new Point3D(0, 0, 0)));
		assertEquals("Polygon findIntersection error ", intersections, pentagon.findIntersections(ray)); // inside
		// pentagon
		ray = new Ray(new Point3D(-3, 3, -1), new Vector(0, 0, 1));
		assertEquals("Polygon findIntersection error ", null, pentagon.findIntersections(ray)); // outside pentagon
	}
}

