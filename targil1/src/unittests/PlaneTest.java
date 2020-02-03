package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import static geometries.Intersectable.GeoPoint;
import org.junit.Test;

import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

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
	static Point3D p = new Point3D(1, 1, 0);
    static Vector dir = new Vector(0, 0, 1);

	/**
     * test Method for {@link geometries.Plane#findIntersections (geomtries.Plane)}
     */
   @Test
   public void testFindIntersections() {
       Plane plane = new Plane(dir, p);
       Ray ray = new Ray(new Point3D(0, 0, 1), new Vector(1, 1, -1));
       List<GeoPoint> intersections = Arrays.asList(new GeoPoint(plane, new Point3D(1, 1, 0)));
       assertEquals("Find intersections function error", intersections, plane.findIntersections(ray)); // EP ray
       // intersects
       // with the
       // plane
       ray = new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 1));
       assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // EP ray does not
       // intersect with the
       // plane
       ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 0));
       assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray is parallel
       // and included in the
       // plane
       ray = new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 0));
       assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray is parallel
       // and not included in
       // the plane
       ray = new Ray(new Point3D(0, 0, 1), new Vector(0, 0, 1));
       assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray is orthogonal
       // and after p0
       ray = new Ray(new Point3D(0, 0, -1), new Vector(0, 0, 1));
       intersections = Arrays.asList(new GeoPoint(plane,new Point3D(0, 0, 0)));
       assertEquals("Find intersections function error", intersections, plane.findIntersections(ray)); // BVA ray is
       // orthogonal
       // and before p0
       ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1));
       assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray is orthogonal
       // and in p0
       ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 1));
       assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray begins in the
       // plane
       ray = new Ray(new Point3D(1, 1, 0), new Vector(1, 1, 1));
       intersections = Arrays.asList(new GeoPoint(plane,new Point3D(1, 1, 0)));
       assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray begins in the
       // reference point of
       // the plane
   }
}