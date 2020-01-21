package primitives;

/**
 * class for implementing material
 *
 */
public class Material {
	protected double _Kd;
	protected double _Ks;
	protected int _nShininess;

    //*****************constructors**********
	public Material(double _Kd, double _Ks, int nShininess) {
		this._Kd = _Kd;
		this._Ks = _Ks;
		this._nShininess = nShininess;
	}

	// ****************getters and setters******
	public double get_Kd() {
		return _Kd;
	}

	public double get_Ks() {
		return _Ks;
	}

	public int get_nShininess() {
		return _nShininess;
	}
}
