package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

/**
 * Class tests vector class operations.
 */
public class VectorTest {
	/**
	 * check function add
	 */
	@Test
	 public void testAdd() {
		Vector vec1 = new Vector(1, 2, 3);
		Vector vec2 = new Vector(3, 2, 1);
		assertEquals(new Vector(4, 4, 4), vec1.add(vec2));
	}
	/**
	 * check function sub
	 */
	@Test
	 public void testSubtract() {
		Vector vec1 = new Vector(1, 2, 3);
		Vector vec2 = new Vector(3, 2, 1);
		assertEquals(new Vector(-2, 0, 2), vec1.sub(vec2));
	}
	/**
	 * check function scale
	 */
	@Test
	 public void testScailing() {
		Vector vec1 = new Vector(1, 2, 3);
		assertEquals(new Vector(2, 4, 6), vec1.scale(2));
	}
	/**
	 * check function dot product
	 */
	@Test
	public void testDotProduct() {
		Vector vec1 = new Vector(0, 0, 1);
		Vector vec2 = new Vector(1, 1, 0);
		double dot = vec1.dotProduct(vec2);
		assertEquals("Dot product function error", 0, dot, 0);
		vec1 = new Vector(1, 0, 0);
		vec2 = new Vector(1, 1, 0);
		dot = vec1.dotProduct(vec2);
		assertEquals("Dot product function error", 1, dot, 0);
		vec1 = new Vector(1, 0, 0);
		vec2 = new Vector(5, 0, 0);
		dot = vec1.dotProduct(vec2);
		assertEquals("Dot product function error", 5, dot, 0);
	}

	/**
	 * check function cross product
	 */
	@Test
	 public void testCrossProduct() {
		Vector vec1 = new Vector(0, 0, 1);
		Vector vec2 = new Vector(0, 1, 0);
		assertEquals(new Vector(-1, 0, 0), vec1.crossProduct(vec2));
	}
	/**
	 * check function length
	 */
	@Test
	public void testlength() {
		Vector vec1 = new Vector(0, 3, 4);
		assertEquals(5, vec1.length(), 0.1);
	}

	/**
	 * check function normal
	 */

	@Test
	 public void testNormalize() {
		Vector vec1 = new Vector(1, 1, 1);
		assertEquals(new Vector(Math.sqrt(1.0 / 3), Math.sqrt(1.0 / 3), Math.sqrt(1.0 / 3)), vec1.normalization());
				
	}

}
