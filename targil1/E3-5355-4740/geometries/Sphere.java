package geometries;

import primitives.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * class sphere with radius and center point3D
 */
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
	@Override
	public  List<Point3D> findIntersections(Ray r) {
		List<Point3D> l = new ArrayList<Point3D>();
		Point3D headRay = new Point3D(r.getP());
		Vector vectoRay = new Vector(r.getDirection());
		if (headRay.equals(_center))
			l.add(headRay.addition(vectoRay.scaling(_radius)));
		else {
			Vector u = new Vector(_center.sub(headRay));
			double Tm = vectoRay.dotProduct(u);
			double D = u.length() * u.length() - Tm * Tm;
			double d = Math.sqrt(D);
			Vector vec = new Vector(_center.sub(headRay));
			double check = vec.length2();
			if (d < _radius) {
				double Th = Math.sqrt(_radius * _radius - d * d);
				double t1 = Util.usubtract(Tm, Th);
				if (t1 == 0)
					l.add(headRay);
				if (t1 > 0) {
					Point3D p1 = new Point3D(headRay.addition(vectoRay.scaling(t1)));
					l.add(p1);
				}
				double t2 = Util.uadd(Tm, Th);
				if (t2 == 0)
					l.add(headRay);
				if (t2 > 0) {
					Point3D p2 = new Point3D(headRay.addition(vectoRay.scaling(t2)));
					l.add(p2);
				}
				if (check < _radius * _radius)
					l.remove(new Point3D(headRay.addition(vectoRay.scaling(t1))));
			} else if (d == _radius) {
				if (Tm == 0)
					l.add(headRay);
				if (Tm > 0) {
					Point3D p = new Point3D(headRay.addition(vectoRay.scaling(Tm)));
					l.add(p);
				}
			}
		}
		return l;
}
}

