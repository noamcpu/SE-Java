package elements;

import primitives.*;

public class DirectionalLight extends Light implements LightSource {
	Vector _direction;

	// ***************** Constructors ********************** //

	public DirectionalLight(Color intensity, Vector direction) {
		super();
		this._direction = direction.normalization();
	}

	// *****************Getters/Setters******************//

	public Vector get_Direction() {
		return _direction;
	}

	public Color getIntensity(Point3D p) {
		return getIntensity();
	}

	public Vector getL(Point3D p) {
		return this._direction;
	}

}
