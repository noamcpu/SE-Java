package geometries;

import primitives.*;
import java.util.Arrays;
import java.util.List;
import static primitives.Util.*;

/**
 * class plane with normal Vector and Point3D
 */
public class Plane extends Geometry {
	private Vector _normal;
	private Point3D _p;

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
	/**
    * Constructs a plane using three points in the space
    *
    * @param p1 is first point
    * @param p2 is second point
    * @param p3 is third point
    */
	public Plane(Point3D _p1, Point3D _p2, Point3D _p3) {
		this._p = _p1;
		this._normal = _p2.sub(_p1).crossProduct(_p3.sub(_p1)).normalization();
	}
	/**
     * Constructs a plane using three points in the space and a color
     *
     * @param emission the color of the plane
     * @param p1       is first point
     * @param p2       is second point
     * @param p3       is third point
     */
    public Plane(Color emission, Point3D p1, Point3D p2, Point3D p3) {
        this(p1, p2, p3);
        setEmission(emission);
    }

    /**
     * Constructs a plane with a point, normal vector and a color
     *
     * @param emission the color of the plane
     * @param _p,      the base point of the normal
     * @param _normal, the normal vector to the plane
     */
    public Plane(Color emission, Point3D _p, Vector _normal) {
        this(_normal, _p);
        setEmission(emission);
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

	/**
	 * finds intersections of the ray with the plane
	 *
	 * @param ray
	 * @return intersection point
	 */
	public List<GeoPoint> findIntersections(Ray ray) {
		Point3D p0 = ray.getP();
		Vector v = ray.getDirection();

		double s = alignZero(this._normal.dotProduct(v));
		if (s == 0)
			return null;

		Vector pp0 = null;
		try {
			pp0 = this._p.sub(p0);
		} catch (Exception e) {
	
			return null;
		}

		double t = alignZero(this._normal.dotProduct(pp0) / s);
		return (t <= 0) ? null : Arrays.asList(new GeoPoint(this, p0.addition(v.scaling(t)))); 
	}
}
