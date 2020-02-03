package unittests;

import primitives.*;

import static org.junit.Assert.*;
import java.lang.Math;
import org.junit.Test;

public class VectorTest {
	/**
	 * test Method for {@link primitives.Vector#add (primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Vector vec = new Vector(1, 1, 1);
		Vector vec2 = new Vector(2, 2, 2);
		try {
			vec.add(vec.scaling(-1));
			fail("didn't throw exception for Vector 0");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		assertEquals("Add function error", vec2, vec.add(vec));
	}

	/**
	 * check function sub
	 */
	@Test
	public void testSubtract() {
		Vector vec1 = new Vector(2, 2, 2);
		Vector vec2 = new Vector(4, 4, 4);
		try {
			vec1.sub(vec1);
			fail("didn't throw exception for Vector 0");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		assertEquals("Subtract function error", vec1, vec2.sub(vec1));
	}

	/**
	 * check function scale
	 */
	@Test
	public void testScailing() {
		Vector vec1 = new Vector(1, 2, 3);
		assertEquals(new Vector(2, 4, 6), vec1.scaling(2));
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
	public void testNormalization() {
		Vector vec1 = new Vector(0, 9, 0);
		Vector vec2 = new Vector(0, 1, 0);
		assertEquals(vec2, vec1.normalize());
	}

}
