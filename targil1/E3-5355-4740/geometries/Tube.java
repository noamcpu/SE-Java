package geometries;

import java.util.List;

import primitives.*;

/**
 * class Tube with radius and _axisRay Ray
 */
public class Tube extends RadialGeometry {
	Ray _axisRay;

	// ***************** Constructors ********************** //

	/**
	 * getting point3D and radius for initialization the Sphere
	 * 
	 * @param radius double number
	 * @param center Ray _axisRay
	 */
	public Tube(double _radius, Ray axisR) {
		super(_radius);
		this._axisRay = axisR;
	}

	// ***************** Getters ********************** //
	/**
	 * giving the Ray _axisRay
	 * 
	 * @return the point
	 */
	public Ray get_axisRay() {
		return _axisRay;
	}
	// ***************** operations ********************** //
	@Override
	public String toString() {
		return "Tube:_axisRay=" + _axisRay + ", radius=" + _radius;

	}
	/**
	 * Gets the normal of the tube to the point
	 * 
	 * @param p the point of the normal
	 * @return the normal of the tube
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Point3D p0 = _axisRay.getP();
		Vector v = _axisRay.getDirection();
		Vector u = p.sub(p0); // vector from p0 to p
		double t = v.dotProduct(u); // size of projection of vector u on the ray
		// point on the ray and plane crossing P and orthogonal to the ray
		Point3D o;
		if (t == 0)
			o = p0;
		else
			o = p0.addition(v.scaling(t));

		return p.sub(o).normalization();
	}

	@Override
	public List<Point3D> findIntersections(Ray r) {
		// TODO Auto-generated method stub
		return null;
	}
}
