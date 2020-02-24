package elements;

import primitives.*;

public class SpotLight extends PointLight implements LightSource {
	Vector _direction;

	// ***************** Constructors ********************** //

	/**
	 * constructs a spot light in a scene
	 *
	 * @param color     - color of the light
	 * @param position  - position of the light in the space
	 * @param direction - direction of the spot light
	 * @param kC
	 * @param kL
	 * @param kQ
	 */
	public SpotLight(Color color, Point3D position, Vector direction, double kC, double kL, double kQ) {
		super(color, position, kC, kL, kQ);
		this._direction = direction.normalization();
	}

	// *****************Getters/Setters******************//

	public Color gerIntensity(Point3D p) {
		Vector l = getL(p);
		double dl = _direction.dotProduct(l);
		if (dl <= 0)
			return Color.BLACK;
		else
			return super.getIntensity(p).scale(dl);
	}
}
