package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class represents directional light in a scene
 */
public class DirectionalLight extends Light implements LightSource {
	private Vector _direction;
	// ***************** Constructors ********************** //

	/**
	 * constructs a directional light
	 *
	 * @param color - color of the light
	 * @param dir   - direction of the light
	 */
	public DirectionalLight(Color color, Vector dir) {
		_intensity = color;
		_direction = dir.normalization();
	}

	// ***************** Getters/Setters ********************** //
	@Override
	public Color getIntensity(Point3D p) {
		return _intensity;
	}

	@Override
	public Vector getL(Point3D p) {
		return _direction;
	}

	public double getDistance(Point3D p) {
		return Double.POSITIVE_INFINITY;
	}
}
