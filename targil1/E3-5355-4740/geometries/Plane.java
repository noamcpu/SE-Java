package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * class plane with normal Vector and Point3D
 */
public class Plane implements Geometry {
	protected Vector _normal;
	protected Point3D _p;

	// ***************** Constructors ********************** //
	/**
	 * getting normal Vector and Point3D for initialization the Plane
	 * 
	 * @param n the vector
	 * @param p the point
	 */
	public Plane(Vector n, Point3D v) {

		_normal = n.normalize();
		_p = v;
	}

	public Plane(Point3D _p1, Point3D _p2, Point3D _p3) {
		this._p = _p1;
		this._normal = _p2.sub(_p1).crossProduct(_p3.sub(_p1)).normalization();
	}

	// ***************** Getters ********************** //

	/**
	 * giving the normal Vector
	 * 
	 * @return the Vector
	 */
	public Vector getNormal() {
		return _normal;
	}

	/**
	 * giving the Point3D
	 * 
	 * @return the point
	 */
	public Point3D get_P() {
		return _p;
	}

	// ***************** operations ********************** //
	@Override
	public String toString() {
		return "Plane: " + _p + ", normal: " + _normal;

	}
	/**
	 * giving the Point3D
	 * 
	 * @param p 
	 * @return the normal
	 */
	@Override
	public Vector getNormal(Point3D p) {
		return _normal;

	}
	@Override
	public List<Point3D> findIntersections(Ray r) {
		List<Point3D> l = new ArrayList<Point3D>();
		Point3D headRay = new Point3D(r.getP());
		Vector vectoRay = new Vector(r.getDirection());
		Vector u = new Vector(_p.sub(headRay));
		double numerator = _normal.dotProduct(u);
		double denominator = _normal.dotProduct(vectoRay);
		double t =numerator / denominator;
		if (!(t <= 0)) {
			Point3D p = new Point3D(headRay.addition(vectoRay.scaling(t)));
			l.add(p);
		}
		
		return l;
		

	}
}

