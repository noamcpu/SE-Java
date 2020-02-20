package geometries;

import static primitives.Util.isZero;

import primitives.*;

public class Cylinder extends Tube {
	private double _height;

	// ***************** Constructors ********************** //
	/**
	 * Constructs a cylinder with radius, height and axis ray
	 *
	 * @param double _radius, radius of the cylinder
	 * @param double _height, height of the cylinder
	 * @param Ray    _axis, axis of the cylinder
	 * @throws new IllegalException when radius is smaller than zero
	 */
	public Cylinder(double _radius, double _height, Ray _axis) {
		super(_axis, _radius);
		if (_height > 0)
			this._height = _height;
		else {
			throw new IllegalArgumentException("Radius is smaller than zero");
		}
	}

	// ***************** Operations ******************** //
/**
 * getting Point3D and giving the normal Vector in this point
 * 
 * @param point the Point3D
 * @return the normal Vector
 */
	@Override
	public Vector getNormal(Point3D p) {
		Vector v = _axisRay.getDirection();
		Point3D p0 = _axisRay.getP();
		if (p.equals(p0))
			return v;
		Vector u = p.sub(p0);
		double t = v.dotProduct(u); 
		if (isZero(t) || isZero(t - this._height)) {
			return v;
		}
		return p.sub(p0.add(v.scale(t))).normalization();
	}
}

