package geometries;

import primitives.*;

public abstract class Geometry implements Intersectable {
	public Color emission;
	public Material material;

	// ***************** Constructor ********************** //
	/**
	 * constrctor for geomtery put the matirel at (0,0,00 and painter the color to
	 * black
	 */
	public Geometry() {
		this.emission = Color.BLACK;
		this.material = new Material(0, 0, 0);
	}

	/**
	 * constrctor for geomtery
	 * 
	 * @param matireal
	 * @param color    put the matirel at that he get and painter the color to whice
	 *                 color he get
	 */
	public Geometry(Material material, Color emmission) {
		this.material = material;
		this.emission = emmission;
	}

	// ***************** Getters/Setters ********************** //

	/**
	 * getter of the emission light of the geometry
	 *
	 * @return the emission light
	 */
	public Color getEmission() {
		return this.emission;
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
