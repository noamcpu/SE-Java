package unittests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import elements.*;
import primitives.*;
import static org.junit.Assert.*;

public class CameraTest {

	@Test
	public void testConstructRayThroughPixel() {
		Camera cam = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));

		// 3x3 view plane
		// the Ray passes through the corner pixels
		Ray r = new Ray(new Point3D(0, 0, 0), new Vector(1, -1, -1));
		Ray ray = cam.constructRayThroughPixel(3, 3, 0, 0, 1, 3, 3);
		assertEquals(r, ray);

		// the Ray passes through the center pixels
		r = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1));
		assertEquals(r, cam.constructRayThroughPixel(3, 3, 1, 1, 1, 3, 3));

		// the Ray passes through the remaining pixels
		r = new Ray(new Point3D(0, 0, 0), new Vector(1, 0, -1));
		assertEquals(r, cam.constructRayThroughPixel(3, 3, 0, 1, 1, 3, 3));

		// 4x4
		// the Ray passes through the corner pixels
		r = new Ray(new Point3D(0, 0, 0), new Vector(1.5, -1.5, -1));
		assertEquals(r, cam.constructRayThroughPixel(4, 4, 0, 0, 1, 4, 4));

		// the Ray passes through the center pixels
		r = new Ray(new Point3D(0, 0, 0), new Vector(1.5, -0.5, -1));
		assertEquals(r, cam.constructRayThroughPixel(4, 4, 0, 1, 1, 4, 4));

		// the Ray passes through the remaining pixels
		r = new Ray(new Point3D(0, 0, 0), new Vector(0.5, -0.5, -1));
		assertEquals(r, cam.constructRayThroughPixel(4, 4, 1, 1, 1, 4, 4));

	}
}
