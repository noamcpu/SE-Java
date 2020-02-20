package elements;

import primitives.Color;

public class AmbientLight extends Light {
	// ***************** Constructors ********************** //

	/**
	 * sets the ambient light of the picture by color and Attenuation coefficient
	 *
	 * @param i - Light color
	 * @param k - Attenuation coefficient
	 */
	public AmbientLight(Color i, double k) {
		_intensity = i.scale(k);
	}

	/**
	 * sets the ambient light of the picture by color
	 *
	 * @param i - Light color
	 */
	public AmbientLight(Color i) {
		_intensity = i;
	}
	// ***************** Getters/Setters ********************** //

	/**
	 * getter for the ambient lighting
	 *
	 * @return light intensity
	 */
	public Color getIntensity() {
		return _intensity;
	}
}
