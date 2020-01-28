package geometries;

import primitives.*;

public abstract class Geometry implements Intersectable {
	Color emission;
	public Material material;

	// ***************** Getters/Setters ********************** //
	/**
	 * setter of the emission light of the geometry
	 *
	 * @param emission - the emission light color
	 */
	public void setEmission(Color emission) {
		this.emission = emission;
	}

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
		return material;
	}

	/**
	 * setter of the material of the geometry
	 *
	 * @param kd        - diffusion attenuation coefficient
	 * @param ks        - specular attenuation coefficient
	 * @param shininess - shininess power
	 */
	public void setMaterial(double kd, double ks, int shininess) {
		this.material = new Material(kd, ks, shininess);
	}

	/**
	 * Calculates vector orthogonal to the plane
	 * 
	 * @param p point of plane
	 * @return normal vector
	 */
	public abstract Vector getNormal(Point3D p);

}
