package elements;


import primitives.*;

public class DirectionalLight extends Light implements LightSource  {
	 Vector direction;
	
	// ***************** Constructors ********************** //

	public DirectionalLight(Color intensity, Vector direction) {
		super();
		this.direction = direction.normalization();
	}

	// *****************Getters/Setters******************//

	public Vector getDirection() {
		return direction;
	}

	public void setDirection(Vector direction) {
		this.direction = direction;
	}

	public Color getIntensity(Point3D p) {
		return getIntensity();
	}

	public Vector getL(Point3D p) {
		return this.direction;
	}

}
