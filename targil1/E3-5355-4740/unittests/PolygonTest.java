package unittests;

import static org.junit.Assert.*;
import java.lang.Math;
import org.junit.Test;
import primitives.*;
import geometries.*;

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

}
