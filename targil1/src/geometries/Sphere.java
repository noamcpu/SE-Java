package geometries;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Class represents a sphere in 3D
 */
public class Sphere extends RadialGeometry {
	Point3D _center;
	// ***************** Constructors ********************** //

	/**
	 * constructs a sphere with a radius and center point
	 *
	 * @param _radius, the radius of the sphere
	 * @param _center, the center point of the sphere
	 */
	public Sphere(double _radius, Point3D _center) {
		this(Color.BLACK, _radius, _center);
	}

	/**
	 * constructs a sphere with a radius, center point and a color
	 *
	 * @param emission the color of the sphere
	 * @param _radius, the radius of the sphere
	 * @param _center, the center point of the sphere
	 */
	public Sphere(Color emission, double _radius, Point3D _center) {
		this(emission, new Material(0, 0, 1), _radius, _center);
	}

	/**
	 * constructs a sphere with a radius, center point and a color
	 *
	 * @param emission the color of the sphere
	 * @param material the material of the sphere
	 * @param _radius, the radius of the sphere
	 * @param _center, the center point of the sphere
	 */
	public Sphere(Color emission, Material material, double _radius, Point3D _center) {
		super(emission, material, _radius);
		this._center = _center;
	}
	// ***************** Operations ******************** //

	/**
	 * Gets the normal of the sphere at a certain point
	 *
	 * @param p, the point of the normal on the sphere
	 * @return the normal of the sphere at p
	 */
	public Vector getNormal(Point3D p) {
		return p.sub(_center).normalize();
	}

	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> l = new ArrayList<GeoPoint>();
		Point3D headRay = ray.getP();
		Vector vectorRay = ray.getDirection();
		if (headRay.equals(_center)) {
			l.add(new GeoPoint(this, headRay.add(vectorRay.scale(_radius))));
		}
		Vector u = new Vector(_center.sub(headRay));
		double tm = vectorRay.dotProduct(u);
		double s = u.length() * u.length() - tm * tm;
		double d = Math.sqrt(s);
		double th = alignZero(Math.sqrt(_radius * _radius - d * d));
		if (alignZero(d - _radius) > 0)
			return null;
		double t1 = tm + th;
		double t2 = tm - th;
		if (t1 > 0 || t2 > 0) {
			if (t1 > 0)
				l.add(new GeoPoint(this, headRay.add(vectorRay.scale(t1))));
			if (t2 > 0)
				l.add(new GeoPoint(this, headRay.add(vectorRay.scale(t2))));
			return l;
		}
		return null;
	}
}
