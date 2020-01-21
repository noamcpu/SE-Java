package geometries;
 
import java.util.List;
 
import primitives.*;
 
public class Triangle extends Polygon {
	
	// ***************** Constructors ********************** //
	/**
     * constructs a triangle from three points
     *
     * @param p1, first point
     * @param p2, second point
     * @param p3, third point
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }
    @Override
	public List<Point3D> findIntersections(Ray r) {
		 List<Point3D> l = super.findIntersections(r);
		if (l.isEmpty())
			return null;
		 Point3D p1 = this._points.get(0);
	     Point3D p2 = this._points.get(1);
	     Point3D p3 = this._points.get(2);
		Point3D headRay = new Point3D(r.getP());

		Point3D firstP = l.get(0);

		if (firstP.equals(p1) || firstP.equals(p2) || firstP.equals(p3)) {
			return null;
		}
		Vector v1 = p1.sub(headRay);
		Vector v2 = p2.sub(headRay);
		Vector v3 = p3.sub(headRay);
		try {
			Vector n1 = v1.crossProduct(v2);
			Vector n2 = v2.crossProduct(v3);
			Vector n3 = v3.crossProduct(v1);

			try {
				Vector vSub = firstP.sub(headRay);
				double x1 = vSub.dotProduct(n1);
				double x2 = vSub.dotProduct(n2);
				double x3 = vSub.dotProduct(n3);

				if (!(x1 > 0 && x2 > 0 && x3 > 0 || x1 < 0 && x2 < 0 && x3 < 0))
					return null;
			} catch (Exception e) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return l;
	}
}

