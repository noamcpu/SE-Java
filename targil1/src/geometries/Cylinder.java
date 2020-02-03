package geometries;

import primitives.*;
import static primitives.Util.isZero;

public class Cylinder extends Tube {
	double _height;

	// ***************** Constructors ********************** //

	/**
	 * getting the radius and height
	 * 
	 * @param radius
	 * @param height
	 */
	public Cylinder(double _radius, double _height, Ray _axisR) {
		super(_axisR, _radius);
		if (_height > 0)
			this._height = _height;
		else {
		this._height = _height;
		}
	}
	// ***************** Getters ********************** //

	/**
	 * giving the height
	 * 
	 * @return the height
	 */
	public double get_height() {
		return _height;
	}

	// ***************** operations ********************** //
	@Override
	public String toString() {
		return "Cylinder:height=" + _height + " Radius" + _radius;
	}

	/**
	 * Gets the direction of the normal vector of the cylinder at a certain point
	 *
	 * @param p, the point on the cylinder
	 * @return the direction of the normal vector
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Vector v = _axisRay.getDirection();
		Point3D p0 = _axisRay.getP();
		if (p.equals(p0))
			return v;
		Vector u = p.sub(p0); // vector from p0 to p
		double t = v.dotProduct(u); // size of projection of vector u on the ray
		// point on the ray and plane crossing P and orthogonal to the ray
		if (isZero(t) || isZero(t - this._height)) {
			return v;
		}
		return p.sub(p0.addition(v.scaling(t))).normalize();
	}
}
