package geometries;

import java.util.List;
import primitives.*;

public interface Intersectable {
	static class GeoPoint {
		public Geometry geometry;
		public Point3D point;
		// ***************** Constructors ********************** //

		/**
		 * constructor of a geoPoint
		 *
		 * @param geometry - the geometry
		 * @param point    - the point in the geometry
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			this.geometry = geometry;
			this.point = point;

		}
		// ***************** Getters/Setters ********************** //

		/**
		 * getter of the point
		 *
		 * @return the point
		 */
		public Point3D getPoint() {
			return point;
		}

		/**
		 * getter of the geometry associated with the point
		 *
		 * @return the geometry
		 */
		public Geometry getGeometry() {
			return geometry;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GeoPoint))
				return false;

			GeoPoint other = (GeoPoint) obj;

			return point.equals(other.point);
		}
	}

	/**
	 * Find the intersections with the geometry
	 * 
	 * @param r
	 * @return list of intersections points
	 */
	public List<GeoPoint> findIntersections(Ray r);

}
