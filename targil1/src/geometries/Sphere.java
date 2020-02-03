package geometries;

import primitives.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import geometries.Intersectable.GeoPoint;

import java.lang.Math;
import static primitives.Util.*;

public class Sphere extends RadialGeometry {
	Point3D _center;

	// ***************** Constructors ********************** //

	/**
	 * getting point3D and radius for initialization the Sphere
	 * 
	 * @param radius double number
	 * @param center point3D center
	 */
	public Sphere(double _radius, Point3D _center) {
		super(_radius);
		this._center = _center;
	}

	/**
	 * constructs a sphere with a radius, center point and a color
	 *
	 * @param emission the color of the sphere
	 * @param _radius, the radius of the sphere
	 * @param _center, the center point of the sphere
	 */
	public Sphere(Color emission, double _radius, Point3D _center) {
		this(_radius, _center);
		 this.emission = emission;
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
        this(emission, _radius, _center);
        this.material = material;
    }

	// ***************** Getters ********************** //
	/**
	 * giving the point3D center
	 * 
	 * @return the point
	 */
	public Point3D getCenter() {
		return _center;
	}

	// ***************** operations ********************** //
	@Override
	public String toString() {
		return "Sphere: center=" + _center + ", radius=" + _radius;

	}

	/**
	 * @param point p
	 * 
	 * @return the normal vector to the point
	 */
	public Vector getNormal(Point3D p) {
		return new Vector(p.sub(_center).normalization());
	}

	/**
	 * giving the intersection
	 * 
	 * @param r
	 * @return intersections
	 */
	public List<GeoPoint> findIntersections(Ray r) {
		Point3D p0 = r.getP();
		Vector v = r.getDirection();
		Vector u;
		try {
			u = _center.sub(p0); // O - P0
		} catch (Exception e) {

			return Arrays.asList(new GeoPoint(this, p0.addition(v.scaling(this._radius))));
		}

		double tm = alignZero(v.dotProduct(u)); //

		double d = alignZero(Math.sqrt(u.length2() - tm * tm));
		if (alignZero(d - _radius) > 0)
			return null;

		double th = alignZero(Math.sqrt(_radius * _radius - d * d));
		if (th == 0)
			return null;

		double t1 = alignZero(tm + th);
		double t2 = alignZero(tm - th);
		if (t1 > 0 || t2 > 0) {
			List<GeoPoint> intersections = new ArrayList<>();
			if (t1 > 0)
				intersections.add(new GeoPoint(this, p0.addition(v.scaling(t1))));
			if (t2 > 0)
				intersections.add(new GeoPoint(this, p0.addition(v.scaling(t2))));
			return intersections;
		}

		return null;
	}
}
