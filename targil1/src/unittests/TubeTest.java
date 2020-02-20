package unittests;

import static org.junit.Assert.*;

import primitives.*;
import geometries.*;
import org.junit.Test;

/**
 * Test class of Tube 
 */
public class TubeTest {
    public static Point3D p = new Point3D(0, 0, 0);
    public static Vector v = new Vector(0, 1, 0);
    public static Ray r= new Ray(p, v);

    /**
     * test Method for fint intersection of tube
     */
    @Test
    public void testGetNormal() {
        // Test of normal on the tube
        Tube tube = new Tube(r, 1);
        Point3D p = new Point3D(1, 2, 0);
        Vector vec = new Vector(1, 0, 0);
        assertEquals("Normal function error", tube.getNormal(p), vec);
    }
}