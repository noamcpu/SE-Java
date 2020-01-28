package geometries;

import static primitives.Util.*;
import primitives.Color;


public abstract class RadialGeometry extends Geometry{

	protected double _radius;

	// ***************** Constructors ********************** //
	/**
	 * getting the radius for initialization the RadialGeometry
	 * 
	 * @param _radius
	 */
	public RadialGeometry(double _radius) {
		if (alignZero(_radius) <= 0)
			throw new IllegalArgumentException("logical wrong");

		this._radius = _radius;
	}
	// ***************** Getters/Setters ********************** //
	/**
	 * giving the value of the radius
	 * 
	 * @return the radius
	 */
	public double get_radius() {
		return _radius;
	}

}
