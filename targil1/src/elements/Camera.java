package elements;

import primitives.*;

import static primitives.Util.*;

/**
 * class Camera represents a view point in the 3D space
 */
public class Camera {
	Point3D _p0; // location of the camera in the space
	Vector _vUp; // Y axis vector
	Vector _vTo; // Z axis vector
	Vector _vRight; // X axis vector
	// ***************** Constructors ********************** //

	/**
	 * Constructor Camera
	 * 
	 * @param p0  - the locition of camera
	 * @param vUp - vector up of camera
	 * @param vTo - vector to of camera
	 */
	public Camera(Point3D p0, Vector vUp, Vector vTo) {
		_p0 = p0;
		_vUp = vUp.normalize();
		_vTo = vTo.normalize();
		_vRight = vTo.crossProduct(vUp).normalization();
	}

	// ***************** Getters/Setters ********************** //

	/**
	 * Getter of camera's location
	 *
	 * @return point of location
	 */
	public Point3D getP0() {
		return _p0;
	}

	/**
	 * Getter of Y axis in relation to the camera
	 *
	 * @return Y axis vector
	 */
	public Vector get_vUp() {
		return _vUp;
	}

	/**
	 * Getter of Z axis in relation to the camera
	 *
	 * @return Z axis vector
	 */
	public Vector get_vTo() {
		return _vTo;
	}

	/**
	 * Getter of X axis in relation to the camera
	 *
	 * @return X axis vector
	 */
	public Vector get_vRight() {
		return _vRight;
	}
	// ***************** Operations ******************** //

	/**
	 * Creates a ray though a certain pixel in the view plane
	 *
	 * @param nX             number of X pixels
	 * @param nY             number of Y pixels
	 * @param j              index of the X pixel
	 * @param i              index of the Y pixel
	 * @param screenDistance the distance from the camera to the view plane
	 * @param screenWidth    view plane length (Y axis)
	 * @param screenHeight   view plane length (X axis)
	 * @return the ray through the pixel
	 */
	public Ray constructRayThroughPixel(int nX, int nY, double j, double i, //
			double screenDistance, double screenWidth, double screenHeight) {
		Point3D pC = _p0.add(_vTo.scale(screenDistance));
		double rX = screenWidth / nX;
		double rY = screenHeight / nY;
		double yi = (i - (nY / 2.0)) * rY + (rY / 2.0);
		double xj = (j - (nX / 2.0)) * rX + (rX / 2.0);
		Point3D pIJ = pC;
		if (xj != 0)
			pIJ = pIJ.add(_vRight.scale(xj));
		if (yi != 0)
			pIJ = pIJ.add(_vUp.scale(-yi));
		return new Ray(_p0, pIJ.sub(_p0));
	}
}
