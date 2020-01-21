package primitives;

/**
 * class Ray with point head and direction from vector
 */
public class Ray {
	protected Point3D p;
	protected Vector direction;

	// ***************** Constructors ********************** //

	/**
	 * getting the point3D and vector for initialization of values
	 * 
	 * @param p         the given point 3D
	 * @param direction the given vector
	 */
	public Ray(Point3D p, Vector direction) {
		this.p = p;
		this.direction = direction.normalize();// work with normal vectors
	}

	public Ray(Ray k) {
		this.p = k.p;
		this.direction = k.direction;
	}

	// ***************** Getters/Setters ********************** //

	/**
	 * giving the point3D of Ray
	 * 
	 * @return the point3D
	 */
	public Point3D getP() {
		return p;
	}

	/**
	 * giving the direction vector of Ray
	 * 
	 * @return the vector
	 */
	public Vector getDirection() {
		return direction;
	}

	// ***************** Administration ******************** //

	/**
	 * checking if Ray equal to other Ray
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return p.equals(other.p) && (direction.equals(other.direction));
	}

	@Override
	public String toString() {
		return "Ray: p=" + p + ",direction vector " + direction;
	}
}
