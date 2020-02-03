package primitives;

/**
 * class for implementing material
 *
 */
public class Material {
	double _kD;
	double _kS;
	int _nShininess;
	double _kR;
    double _kT;

	// *****************constructors**********
    /**
     * creates a material for a geometry
     *
     * @param kd        - diffusion attenuation coefficient
     * @param ks        - specular attenuation coefficient
     * @param shininess - shininess power
     */
	public Material(double _Kd, double _Ks, int nShininess) {
		this._kD = _Kd;
		this._kS = _Ks;
		this._nShininess = nShininess;
		_kR = 0;
        _kT = 0;
	}
	/**
     * creates a material for a geometry
     *
     * @param kd        - diffusion attenuation coefficient
     * @param ks        - specular attenuation coefficient
     * @param shininess - shininess power
     * @param kr        - reflection coefficient
     * @param kt        - transparency coefficient
     */
    public Material(double kd, double ks, int shininess, double kr, double kt) {
        this(kd, ks, shininess);
        _kR = kr;
        _kT = kt;
    }
	// ****************getters and setters******
	/**
	 * getter of diffusion attenuation coefficient
	 *
	 * @return diffusion attenuation coefficient
	 */
	public double get_Kd() {
		return _kD;
	}

	/**
	 * getter of specular attenuation coefficient
	 *
	 * @return specular attenuation coefficient
	 */
	public double get_Ks() {
		return _kS;
	}

	/**
	 * getter of shininess power
	 *
	 * @return shininess power
	 */
	public int get_nShininess() {
		return _nShininess;
	}
	/**
     * getter of reflection coefficient
     *
     * @return reflection coefficient
     */
    public double getKr() {
        return _kR;
    }

    /**
     * getter of transparency coefficient
     *
     * @return transparency coefficient
     */
    public double getKt() {
        return _kT;
    }
}

