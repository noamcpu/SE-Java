package primitives;

import java.lang.Math;

/**
 * class Vector with point3D head
 */
public class Vector {
	protected Point3D head;

	// ***************** Constructors ********************** //

	/**
	 * getting point3D head for initialization the vector (error for Vector (0,0,0))
	 * 
	 * @param direction the given point3D
	 */
	public Vector(Point3D head) {
		if (Point3D.ZERO.equals(head))
			throw new IllegalArgumentException("vector 0 is wrong");
		this.head = head;
	}

	/**
	 * Enters the value of the existing vector to other
	 * 
	 * @param testP the given Vector
	 */
	public Vector(Vector other) {
		head = new Point3D(other.head);
	}

	/**
	 * getting 3 double numbers for initialization of coordinates' head
	 * 
	 * @param x first coordinate
	 * @param y second coordinate
	 * @param z third coordinate
	 */
	public Vector(double x, double y, double z) {
		Point3D head1 = new Point3D(x, y, z);
		this.head = new Point3D(head1);
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

	/**
	 * print the head of vector
	 */
	@Override
	public String toString() {

		return "Vector: head=" + head;
	}

	// ***************** Operations ******************** //

	/**
	 * Vector Addition
	 * 
	 * @param vec the given Vector
	 * @return
	 */
	public Vector add(Vector vec) {
		Point3D A = new Point3D(this.head.x.add(vec.head.x), this.head.y.add(vec.head.y), this.head.z.add(vec.head.z));
		return new Vector(A);
	}

	/**
	 * Vector subtraction
	 * 
	 * @param vec the given Vector
	 * @return
	 */
	public Vector sub(Vector vec) {
		Point3D A = new Point3D(this.head.x.subtract(vec.head.x), this.head.y.subtract(vec.head.y),
				this.head.z.subtract(vec.head.z));
		return new Vector(A);
	}

	/**
	 * Vector scaling
	 * 
	 * @param scal the given number
	 * @return
	 */
	public Vector scaling(double scal) {
		Point3D A = new Point3D(this.head.x.scale(scal), this.head.y.scale(scal), this.head.z.scale(scal));
		return new Vector(A);
	}

	/**
	 * Dot product between the given vector and the exist vector gives a scalar
	 * 
	 * @param vec the given vector
	 * @return double number
	 */
	public double dotProduct(Vector vec) {
		return (this.head.x.multiply(vec.head.x).get() + this.head.y.multiply(vec.head.y).get()
				+ this.head.z.multiply(vec.head.z).get());
	}

	/**
	 * Cross product between vector v and vector u gives: u x v 
	 * 
	 * @param vec the given vector
	 * @return new vector
	 */
	public Vector crossProduct(Vector vec) {

		Point3D A = new Point3D((this.head.y.multiply(vec.head.z).subtract(this.head.z.multiply(vec.head.y))),
				(this.head.z.multiply(vec.head.x).subtract(this.head.x.multiply(vec.head.z))),
				(this.head.x.multiply(vec.head.y).subtract(this.head.y.multiply(vec.head.x))));
		return new Vector(A);
	}

	/**
	 * calculate the length of vector in square for the func length
	 * 
	 * @return double number
	 */
	public double length2() {
		double x = head.getX().get();
		double y = head.getY().get();
		double z = head.getZ().get();

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
		return scaling(1 / length());
	}

	/**
	 * make to the vector normalization
	 * 
	 * @return the normal vector
	*/
	public Vector normalization() {
		double l = length();
		double x = head.getX().get();
		double y = head.getY().get();
		double z = head.getZ().get();
		head = new Point3D(x / l, y / l, z / l);
		return this;
	}
}
