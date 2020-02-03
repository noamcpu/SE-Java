package elements;

import primitives.*;

public class PointLight extends Light implements LightSource {
	Point3D _position;

	double _Kc, _Kl, _Kq;

	// ***************** Constructors ********************** //
	/**
	 * constructs a point light
	 *
	 * @param color    - color of the light
	 * @param position - position of the light in the space
	 * @param kC
	 * @param kL
	 * @param kQ
	 */
	public PointLight(Color _color, Point3D _position, double _Kc, double _Kl, double _Kq) {
		super();
		this._position = _position;
		this._Kc = _Kc;
		this._Kl = _Kl;
		this._Kq = _Kq;
	}

	// *****************Getters/Setters******************//

	public Point3D getPosition() {
		return _position;
	}


	@Override
	public Color getIntensity(Point3D p) {
		double d = _position.distance(p);
		double divisionFactor = _Kc + _Kl * d + _Kq * d * d;
		return getIntensity().reduce(divisionFactor);
	}

	@Override
	public Vector getL(Point3D p) {
		return p.sub(_position).normalization();
	}

}
