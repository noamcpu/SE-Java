package primitives;

import java.lang.Math;

import geometries.Intersectable.GeoPoint;
import primitives.Coordinate;
import primitives.Vector;

/**
 * class that getting 3 coordinates
 */
public class Point3D {
	protected Coordinate x;
	protected Coordinate y;
	protected Coordinate z;

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
	 * Enters the value of the existing point 3D to other
	 * 
	 * @param other existing point 3D
	 */
	public Point3D(Point3D other) {
		x = new Coordinate(other.x);
		y = new Coordinate(other.y);
		z = new Coordinate(other.z);
	}

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
	public Coordinate getX() {
		return x;
	}

	/**
	 * giving the value of coordinate y
	 * 
	 * @return y
	 */
	public Coordinate getY() {
		return y;
	}

	/**
	 * giving the value of coordinate z
	 * 
	 * @return z
	 */
	public Coordinate getZ() {
		return z;
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
	 * getting point 3D and make vector from the 2 points
	 * 
	 * @param obj The point you get
	 * @return sub vector
	 */
	public Vector sub(Point3D po) {
		Point3D temp;
		temp = new Point3D(this.getX().subtract(po.getX()).get(), this.getY().subtract(po.getY()).get(),
				this.getZ().subtract(po.getZ()).get());
		return new Vector(temp);
	}

	/**
	 * getting point 3D and make addition with the given point
	 * 
	 * @param obj The point you get
	 * @return add vector
	 */
	public GeoPoint addition(Vector a) {
		Point3D b = new Point3D(this.getX().add(a.getHead().getX()).get(), this.getY().add(a.getHead().getY()).get(),
				this.getZ().add(a.getHead().getZ()).get());
		return b;
	}

	/**
	 * /** getting point 3D and calculate the distance squared between the given
	 * point
	 * 
	 * @param obj The point you get
	 * @return distance squared
	 */
	public double distance2(Point3D obj) {
		Coordinate dx = this.x.subtract(obj.x);
		Coordinate dy = this.y.subtract(obj.y);
		Coordinate dz = this.z.subtract(obj.z);
		return dx.get() * dx.get() + dy.get() * dy.get() + dz.get() * dz.get();
	}

	/**
	 * /** getting point 3D and calculate the distance between the given point
	 * 
	 * @param obj The point you get
	 * @return distance
	 */

	public double distance(Point3D obj) {
		return Math.sqrt(distance2(obj));

	}
}
