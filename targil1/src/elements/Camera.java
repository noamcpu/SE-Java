package elements;

import primitives.*;
import static primitives.Util.*;
/*
 * class for element camera 
 */

public class Camera {
	protected Point3D _p0;
	protected Vector _vUp;
	protected Vector _vTo;
	protected Vector _vRight;

// ***************** Constructors ********************** //
	/**
	 * Constructor Camera
	 * 
	 * @param p0  - the location of camera
	 * @param vUp - vector up of camera
	 * @param vTo - vector to of camera
	 */
	public Camera(Point3D p0, Vector vUp, Vector vTo) {
		_p0 = p0;
		_vUp = vUp.normalize();
		_vTo = vTo.normalize();
		if (!isZero(_vUp.dotProduct(_vTo)))
			throw new IllegalArgumentException("Vup and Vto must be orthogonal");
		_vRight = vTo.crossProduct(vUp).normalization();
	}

// ***************** Getters********************** //

	/**
	 * Getter of camera's location
	 *
	 * @return point of location
	 */
	public Point3D get_p0() {
		return _p0;
	}

	/**
	 * Getter of _vup 0f the camera
	 *
	 * @return _vup
	 */
	public Vector get_vUp() {
		return _vUp;
	}

	/**
	 * Getter of _vto 0f the camera
	 *
	 * @return _vto
	 */
	public Vector get_vTo() {
		return _vTo;
	}

	/**
	 * Getter of _vright 0f the camera
	 *
	 * @return _vright
	 */
	public Vector get_vRight() {
		return _vRight;
	}

// ***************** Operations ******************** //
	/**
	 * construct ray through pixel
	 * 
	 * @param nx
	 * @param ny
	 * @param i
	 * @param j
	 * @param screenDistance
	 * @param screenWidth
	 * @param screenHeight
	 * @return ray from the camera through the pixel
	 */
	public Ray constructRayThroughPixel(int nx, int ny, int i, int j, double screenDistance, double screenWidth,
			double screenHeight) {

		Point3D pC = new Point3D(get_p0().addition(get_vTo().scaling(screenDistance)));
		double rx = screenWidth / nx;
		double ry = screenHeight / ny;
		double xi = ((i - (double) nx / 2) * rx + (rx / 2));
		double yj = ((j - (double) ny / 2) * ry) + (ry / 2);
		Point3D pIJ = pC;
		if (xi != 0)
			pIJ = pIJ.addition(_vRight.scaling(xi));
		if (yj != 0)
			pIJ = pIJ.addition(_vUp.scaling(-yj));
		return new Ray(_p0, pIJ.sub(_p0));

	}

}
