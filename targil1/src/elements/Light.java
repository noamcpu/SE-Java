package elements;

import primitives.*;

public abstract class Light {
	Color _intensity;
	// ***************** Getters/Setters ********************** //

	/**
	 * getter for original intensity of a color
	 *
	 * @return intensity color
	 */
	public Color getIntensity() {
		return _intensity;
	}

}
