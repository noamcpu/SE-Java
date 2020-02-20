package primitives;

import java.lang.Math;

import primitives.Coordinate;
import primitives.Vector;

/**
 * class that getting 3 coordinates
 */
public class Point3D {
	private Coordinate x;
	private Coordinate y;
	private Coordinate z;

	public static final Point3D ZERO = new Point3D(Coordinate.ZERO, Coordinate.ZERO, Coordinate.ZERO);

	// ***************** Constructors ********************** //

	/**
	 * Insert values into the coordinates of point 3D
	 * 
	 * @param x first coordinate
	 * @param y second coordinate
	 * @param z third coordinate
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Construct three decimal numbers.
	 *
	 * @param x coordinate by decimal number.
	 *
	 * @param y coordinate by decimal number.
	 *
	 * @param z coordinate by decimal number.
	 */
	public Point3D(double x, double y, double z) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);

	}

	// ***************** Getters ********************** //

	/**
	 * giving the value of coordinate x
	 * 
	 * @return x
	 */
	public double getX() {
		return x._coord;
	}

	/**
	 * giving the value of coordinate y
	 * 
	 * @return y
	 */
	public double getY() {
		return y._coord;
	}

	/**
	 * giving the value of coordinate z
	 * 
	 * @return z
	 */
	public double getZ() {
		return z._coord;
	}

	// ***************** Administration ******************** //

	/**
	 * checking if point 3D equal to other point 3D
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return x.equals(other.x) && (y.equals(other.y) && z.equals(other.z));
	}

	@Override
	public String toString() {
		return "point 3D: x=" + x + ", y=" + y + ", z=" + z;
	}

	// ***************** Operations ******************** //

	/**
	 * 3D point subtraction operation by coordinates .
	 *
	 * @param other initial 3D space point.
	 * @return the vector which ends in the point and begins in other .
	 */
	public Vector sub(Point3D other) {
		double x1 = x.get();
		double y1 = y.get();
		double z1 = z.get();
		double x2 = other.x.get();
		double y2 = other.y.get();
		double z2 = other.z.get();
		return new Vector(new Point3D(x1 - x2, y1 - y2, z1 - z2));
	}

	/**
	 * 3D point addition operation by coordinates .
	 *
	 * @param vector Vector
	 * @return the head point .
	 */
	public Point3D add(Vector vector) {
		double x1 = x.get();
		double y1 = y.get();
		double z1 = z.get();
		double x2 = vector.getHead().x.get();
		double y2 = vector.getHead().y.get();
		double z2 = vector.getHead().z.get();
		return new Point3D(x1 + x2, y1 + y2, z1 + z2);
	}

	/**
	 * getting point 3D and calculate the distance squared between the given point
	 * 
	 * @param other The point you get
	 * @return distance squared
	 */
	public double distance2(Point3D other) {
		double dx = this.x.subtract(other.x).get();
		double dy = this.y.subtract(other.y).get();
		double dz = this.z.subtract(other.z).get();
		return dx * dx + dy * dy + dz * dz;
	}

	/**
	 * getting point 3D and calculate the distance between the given point
	 * 
	 * @param other The point you get
	 * @return distance
	 */

	public double distance(Point3D other) {
		return Math.sqrt(distance2(other));

	}
}
