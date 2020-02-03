package geometries;

import primitives.*;

public abstract class Geometry implements Intersectable {
	Color emission;
	public Material material;

	// ***************** Getters/Setters ********************** //

	/**
	 * getter of the emission light of the geometry
	 *
	 * @return the emission light
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * getter of the material of the geometry
	 *
	 * @return the material
	 */
	public Material getMaterial() {
		return this.material;
	}

	/**
	 * Calculates vector orthogonal to the plane
	 * 
	 * @param p point of plane
	 * @return normal vector
	 */
	public abstract Vector getNormal(Point3D p);

}
