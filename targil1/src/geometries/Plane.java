package geometries;

import primitives.*;

import java.util.ArrayList;
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
	public Plane(Vector n, Point3D p) {

		_normal = n.normalize();
		_p = p;
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
		this.emission = emission;
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
		this.emission = emission;
	}

	/**
	 * Constructs a plane with a point, normal vector and a color
	 *
	 * @param emission the color of the plane
	 * @param material the material of the plane
	 * @param _p,      the base point of the normal
	 * @param _normal, the normal vector to the plane
	 */
	public Plane(Color emission, Material material, Point3D _p, Vector _normal) {
		this(emission, _p, _normal);
		this.material = material;
	}

	/**
	 * Constructs a plane using three points in the space and a color
	 *
	 * @param emission the color of the plane
	 * @param material the material of the plane
	 * @param p1       is first point
	 * @param p2       is second point
	 * @param p3       is third point
	 */
	public Plane(Color emission, Material material, Point3D p1, Point3D p2, Point3D p3) {
		this(emission, p1, p2, p3);
		this.material = material;
	}

	// ***************** Getters/Setters ********************** //

	/**
	 * Gets the normal of the plane at a certain point
	 *
	 * @param p is the point of the normal
	 * @return the normal of the plane
	 */
	public Vector getNormal(Point3D p) {
		return _normal;
	}

	/**
	 * Gets the normal of the plane
	 *
	 * @return the normal of the plane
	 */
	public Vector getNormal() {
		return _normal;
	}

	// ***************** operations ********************** //
	@Override
	public String toString() {
		return "Plane: " + _p + ", normal: " + _normal;

	}

	/**
	 * get ray and return list of the intersections between the ray and the plane
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		Vector v = ray.getDirection();
		double d = v.dotProduct(this._normal);
		if (d == 0)
			return null;
		Point3D p0 = ray.getP();
		Vector u = null;
		try {
			u = this._p.sub(p0);
		} catch (Exception e) {
			return null;
		}
		double t = u.dotProduct(this._normal) / d;
		if (t <= 0)
			return null;

		return List.of(new GeoPoint(this, p0.add(v.scale(t))));
	}
}
