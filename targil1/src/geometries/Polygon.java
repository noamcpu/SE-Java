package geometries;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import static primitives.Util.*;

import primitives.*;

public class Polygon extends Geometry {
	List<Point3D> _points;
	Plane _plane;

	// ***************** Constructors ********************** //
	/**
	 * Regular constructor for polygon
	 * 
	 * @param points create a new polygon and painter the color to black and set the
	 *               matirel to (0,0,0)
	 */
	public Polygon(Point3D... points) {
		this(new Material(0, 0, 0), Color.BLACK, points);
	}

	/**
	 * Regular constructor
	 * 
	 * @param matirel represent the matirel of th polygon
	 * @param color   of th polygon
	 * @param points- orgnized list of the polygon points create new polygon with
	 *                that parmas
	 */
	public Polygon(Material material, Color emission, Point3D... points) {
		super(material, emission);
		if (points.length < 3)
			throw new IllegalArgumentException("Polygon must have at least 3 points");
		this._points = new ArrayList<>();
		Point3D p1 = points[0];
		Point3D p2 = points[1];
		Point3D p3 = points[2];
		this._plane = new Plane(emission, material, p1, p2, p3);
		this._points.add(p1);
		this._points.add(p2);
		this._points.add(p3);
		Vector n = _plane.getNormal();
		for (int i = 3; i < points.length; ++i) {
			Point3D p = points[i];
			if (!isZero(p1.sub(p).dotProduct(n)))
				throw new IllegalArgumentException("Polygon points must be in the same plane");
			this._points.add(p);
		}
	}

	// ***************** Operations ******************** //

	/**
	 * Gets the normal of the polygon at a certain point
	 *
	 * @return the normal of the plane of the polygon
	 */
	public Vector getNormal(Point3D p) {
		return _plane.getNormal(p);
	}

	/**
	 * finds intersections of the ray with the polygon
	 *
	 * @param ray
	 * @return intersection point
	 */
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> intersections = this._plane.findIntersections(ray);
		if (intersections == null) // there are no intersections with the plane
			return null;

		Point3D p0 = ray.getP();
		int size = this._points.size();
		Vector[] v = new Vector[size];
		Vector[] n = new Vector[size];
		double[] x = new double[size];
		for (int i = 0; i < size; ++i)
			v[i] = _points.get(i).sub(p0);

		for (int i = 0; i < size; ++i)
			n[i] = v[i].crossProduct(v[(i < size - 1) ? i + 1 : 0]).normalization();

		Vector u = intersections.get(0).getPoint().sub(p0);

		for (int i = 0; i < size; ++i)
			if ((x[i] = alignZero(u.dotProduct(n[i]))) == 0)
				return null;
		double t = x[0];
		for (int i = 1; i < size; ++i)

			if ((t < 0 && x[i] > 0) || (t > 0 && x[i] < 0))
				return null;

		return intersections;
	}
}
