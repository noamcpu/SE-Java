package elements;

import primitives.*;

public interface LightSource {
	// ***************** Getters/Setters ********************** //

	/**
     * getter of a color intensity in a certain point
     *
     * @param p - the point
     * @return the color at the point
     */
	public  Color getIntensity(Point3D p);
	/**
     * getter of vector between light position and point
     *
     * @param p - the point
     * @return the distance
     */
    public Vector getL(Point3D p);
 
}

