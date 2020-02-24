package unittests;

import static org.junit.Assert.*;

import elements.Camera;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * test class for Camera
 */
public class CameraTest {
	/**
	 * test Method for camera
	 */
	@Test
	public void testConstructRayThroughPixel() {
		Camera cam = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));

		// 3x3 view plane
		// the Ray passes Inside pixel
		Ray ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1));
		assertEquals(ray, cam.constructRayThroughPixel(3, 3, 1, 1, 1, 3, 3));

		// the Ray passes through the corner pixel3
		ray = new Ray(new Point3D(0, 0, 0), new Vector(1, -1, -1));
		assertEquals(ray, cam.constructRayThroughPixel(3, 3, 0, 0, 1, 3, 3));

		// the Ray passes through side pixels
		ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 0, -1));
		assertEquals(ray, cam.constructRayThroughPixel(3, 3, 0, 1, 1, 3, 3));

		// 4x4
		// the Ray passes through the corner pixels (1,1)
		ray = new Ray(new Point3D(0, 0, 0), new Vector(1.5, -1.5, -1));
		assertEquals(ray, cam.constructRayThroughPixel(4, 4, 0, 0, 1, 4, 4));

		// the Ray passes through the inside pixels (1,3)
		ray = new Ray(new Point3D(0, 0, 0), new Vector(1.5, -0.5, -1));
		assertEquals(ray, cam.constructRayThroughPixel(4, 4, 0, 1, 1, 4, 4));

		// the Ray passes through side pixels (2,3)
		ray = new Ray(new Point3D(0, 0, 0), new Vector(0.5, -0.5, -1));
		assertEquals(ray, cam.constructRayThroughPixel(4, 4, 1, 1, 1, 4, 4));

	}
}
