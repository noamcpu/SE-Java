package primitives;

import java.lang.Math;

/**
 * class Vector with point3D head
 */
public class Vector {
	private Point3D head;

	// ***************** Constructors ********************** //
	/**
	 * Constructs a vector with head point
	 *
	 * @param head point of the vector
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Point3D head) {
		if (head.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Zero Vector");
		this.head = head;
	}

	/**
	 * Constructs a vector by values of three coordinates of vector's head
	 *
	 * @param x coordinate value
	 * @param y coordinate value
	 * @param z coordinate value
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(double x, double y, double z) {
		this.head = new Point3D(x, y, z);
		if (this.head.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Zero Vector");
	}

	/**
	 * Constructs a vector with three coordinates.
	 *
	 * @param x coordinate of head point
	 * @param y coordinate of head point
	 * @param z coordinate of head point
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D headPoint = new Point3D(x, y, z);
		if (headPoint.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Zero Vector");
		this.head = headPoint;
	}

	/**
	 * Construct head point of vector class with a vector
	 *
	 * @param other vector
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Vector other) {
		this.head = other.head;
	}

	// ***************** Getters/Setters ********************** //

	/**
	 * giving the head of Vector
	 * 
	 * @return the head
	 */
	public Point3D getHead() {
		return head;
	}

	// ***************** Administration ******************** //

	/**
	 * checking if Vector equal to other Vector
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		return head.equals(other.head);
	}

	@Override
	public String toString() {

		return "Vector: head=" + head;
	}

	// ***************** Operations ******************** //

	/**
	 * Vector addition operation.
	 *
	 * @param other vector
	 * @return equivalent vector
	 */
	public Vector add(Vector other) {
		return new Vector(this.head.add(other));
	}

	/**
	 * Vector subtraction operation.
	 *
	 * @param other Vector
	 * @return equivalent vector.
	 */
	public Vector sub(Vector other) {
		return this.head.sub(other.getHead());
	}

	/**
	 * Scale vector by num.
	 *
	 * @param num scale size
	 * @return scaled vector by num
	 */
	public Vector scale(double num) {
		double x = head.getX();
		double y = head.getY();
		double z = head.getZ();
		return new Vector(num * x, num * y, num * z);
	}

	/**
	 * The dot-product function takes two 3D space vectors and return a number as
	 * the formula (a,b,c) * (h,y,k) = a*h+b*y+c*k
	 *
	 * @param other Vector
	 * @return Outcome of the formula below.
	 */
	public double dotProduct(Vector other) {
		double x1 = head.getX();
		double y1 = head.getY();
		double z1 = head.getZ();
		double x2 = other.head.getX();
		double y2 = other.head.getY();
		double z2 = other.head.getZ();
		return (x1 * x2 + y1 * y2 + z1 * z2);
	}

	/**
	 * cross product multiplication define as the result of (a,b,c) cross(h,y,f) =
	 * (b*f-c*y,c*h-a*f,a*y-b*h).
	 *
	 * @param other vector .
	 * @return vector orthogonal to each one of the two vectors.
	 */
	public Vector crossProduct(Vector other) {
		double x1 = head.getX();
		double y1 = head.getY();
		double z1 = head.getZ();
		double x2 = other.head.getX();
		double y2 = other.head.getY();
		double z2 = other.head.getZ();
		return new Vector(y1 * z2 - y2 * z1, z1 * x2 - z2 * x1, x1 * y2 - x2 * y1);
	}

	/**
	 * calculate the length of vector in square for the func length
	 * 
	 * @return double number
	 */
	public double length2() {
		double x = head.getX();
		double y = head.getY();
		double z = head.getZ();
		return x * x + y * y + z * z;
	}

	/**
	 * gives the length of the vector
	 * 
	 * @return double number
	 */
	public double length() {
		return Math.sqrt(length2());
	}

	/**
	 * make to the vector normalize
	 * 
	 * @return the normal vector
	 */
	public Vector normalize() {
		return scale(1 / length());
	}

	/**
	 * make to the vector normalization
	 * 
	 * @return the normal vector
	 */
	public Vector normalization() {
		double l = length();
		double x = head.getX();
		double y = head.getY();
		double z = head.getZ();
		head = new Point3D(x / l, y / l, z / l);
		return this;
	}
}
