package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import geometries.*;
import primitives.*;

public class TubeTest {
	/**
	 * return normal of tube
	 */

	@Test
	public void testGetNormal() {
		Ray r = new Ray(new Point3D(0, 0, 0), new Vector(0, 1, 0));
		Tube tu = new Tube(r, 5);
		Point3D p = new Point3D(1, 2, 0);
		Vector vec = new Vector(1, 0, 0);
		assertEquals(tu.getNormal(p), vec);

	}

}
