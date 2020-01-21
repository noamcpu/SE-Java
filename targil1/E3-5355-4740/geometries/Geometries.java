package geometries;

import primitives.Point3D;
import primitives.Ray;
import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {

	private List<Intersectable> _intersectable = new ArrayList<Intersectable>();

	// ***************** Constructors ********************** //

	/**
	 * constructor
	 * 
	 * @param intersectable
	 */
	public Geometries(List<Intersectable> intersectable) {

		this._intersectable = intersectable;
	}

	public Geometries() {
		List<Intersectable> empty = new ArrayList<Intersectable>();
		this._intersectable = empty;
	}
	public Geometries(Intersectable... geometries) {
        for (int i = 0; i < geometries.length; ++i) {
        	_intersectable.add(geometries[i]);
        }
    }
	// ***************** Operations ******************** //

	/**
	 * function for adding geometry
	 * 
	 * @param a
	 */
	 public void add(Intersectable... geometries){
		 for (int i = 0; i < geometries.length; ++i) {
			 _intersectable.add(geometries[i]);
	        }
	 }

	@Override
	public List<Point3D> findIntersections(Ray r) {
		// TODO Auto-generated method stub
		return null;
	}
}

