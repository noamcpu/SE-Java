package geometries;

import primitives.*;

/**
 * class Cylinder with radius,height
 */
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
		super(_radius, _axisR);
		this._height = _height;

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
	 * getting Point3D and giving the normal Vector in this point
	 * 
	 * @param point the Point3D
	 * @return the normal Vector
	 */
	 @Override
	    public Vector getNormal(Point3D p) {
	        Point3D p0 = _axisRay.getP();
	        Vector v = _axisRay.getDirection();
	        Vector u = p.sub(p0); // vector from p0 to p
	        double t = v.dotProduct(u); // size of projection of vector u on the ray
	        // point on the ray and plane crossing P and orthogonal to the ray
	        if (t==0 || (t - this._height)==0)
	            return v;
	        return p.sub(p0.addition(v.scaling(t))).normalization();
	}
}
