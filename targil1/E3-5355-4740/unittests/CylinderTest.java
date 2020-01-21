package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import geometries.*;
import primitives.*;

public class CylinderTest {

	/**
	 * return normal of cylinder
	 */
	@Test
	public void testGetNormal() {
		Ray k = new Ray(new Point3D(0, 0, 0), new Vector(0, 1, 0));
		Cylinder cy = new Cylinder(1, 5, k);
		Point3D p = new Point3D(1, 2, 0);
		Vector vec = new Vector(1, 0, 0);
		assertEquals(vec, cy.getNormal(p));
		Vector actual = new Vector(0, 1, 0);
		assertEquals(cy.getNormal(p), actual); // Test of normal on the base of the cylinder
		p = new Point3D(0, 5, 0);
		assertEquals(cy.getNormal(p), actual); // Test of normal on the top base of the cylinder
	}

}
