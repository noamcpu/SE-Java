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
	/**
	 * getter of diffusion attenuation coefficient
	 *
	 * @return diffusion attenuation coefficient
	 */
	public double get_Kd() {
		return _Kd;
	}
	/**
	 * getter of specular attenuation coefficient
	 *
	 * @return specular attenuation coefficient
	 */
	public double get_Ks() {
		return _Ks;
	}
	/**
	 * getter of shininess power
	 *
	 * @return shininess power
	 */
	public int get_nShininess() {
		return _nShininess;
	}
}
