package elements;

import primitives.*;

public class SpotLight extends PointLight implements LightSource {
	protected Vector _direction;

	// ***************** Constructors ********************** //
	
	
	/**
	 * constructs a spot light in a scene
	 *
	 * @param color     - color of the light
	 * @param position  - position of the light
	 * @param kC
	 * @param kL
	 * @param kQ
	 * @param direction - direction of the spot light
	 */
	public SpotLight(Color _color, Point3D _position, double _Kc, double _Kl, double _Kq, Vector _direction) {
		super(_color, _position, _Kc, _Kl, _Kq);
		this._direction = _direction.normalize();
	}

	// *****************Getters/Setters******************//

	public Vector get_direction() {
		return _direction;
	}

	public void set_direction(Vector _direction) {
		this._direction = _direction;
	}

	public Color gerIntensity(Point3D p) {
		Vector l = getL(p);
		double dl = this._direction.dotProduct(l);
		if (dl <= 0)
			return Color.BLACK;
		else
			return super.getIntensity(p).scale(this._direction.dotProduct(l));
	}
}
