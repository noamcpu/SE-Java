package _scene;

import java.util.ArrayList;
import java.util.List;

import elements.*;
import geometries.*;
import primitives.*;

public class Scene {

	String _sceneName;
	Color _background;
	AmbientLight _ambietLight;
	Geometries _geometries;
	Camera _camera;
	double _distance;;
	List<LightSource> _lights = new ArrayList<>();
	// ***************** Constructors ********************** //

	/**
	 * Constructor
	 * 
	 * @param sceneName
	 * @param _background
	 * @param _ambietLight
	 * @param _geometries
	 * @param _camera
	 * @param _distance
	 */
	public Scene(String _sceneName, Color _background, AmbientLight _ambietLight, Geometries _geometries, Camera _camera,
			double _distance) {
		this._sceneName = _sceneName;
		this._background = _background;
		this._ambietLight = _ambietLight;
		this._geometries = _geometries;
		this._camera = _camera;
		this._distance = _distance;
	}
	 /**
     * creates an empty scene with a name
     *
     * @param name
     */
	public Scene(String name) {
		this._sceneName = name;
	}

	// ***************** Getters/Setters ********************** //

	/**
     * getter of lights in the scene
     *
     * @return lights
     */
    public List<LightSource> getLights() {
        return _lights;
    }
    /**
     * setter of the lights in the scene
     *
     * @param _lights
     */
    public void setLights(List<LightSource> _lights) {
        this._lights = _lights;
    }

   /**
    * getter of the scene name
    *
    * @return sceneName
    */
	public String getSceneName() {
		return _sceneName;
	}
	 /**
     * getter of the background color
     *
     * @return background
     */
	public Color get_background() {
		return _background;
	}
	 /**
     * setter of the background color
     *
     * @param background
     */
	public void set_background(Color _background) {
		this._background = _background;
	}
	/**
     * getter of the ambient light
     *
     * @return ambient
     */
	public AmbientLight get_ambietLight() {
		return _ambietLight;
	}

    /**
     * setter of the ambient light
     *
     * @param ambient
     */
	public void set_ambietLight(AmbientLight _ambietLight) {
		this._ambietLight = _ambietLight;
	}
	/**
     * getter of geometries list
     *
     * @return geometries
     */
	public Geometries get_geometries() {
		return _geometries;
	}
	/**
     * setter of geometries list
     *
     * @return geometries
     */
	public void set_geometries(Geometries _geometries) {
		this._geometries = _geometries;
	}
	 /**
     * getter of camera
     *
     * @return camera
     */
	public Camera get_camera() {
		return _camera;
	}
	/**
     * setter of camera
     *
     * @param camera
     */
	public void set_camera(Camera _camera) {
		this._camera = _camera;
	}
	 /**
     * getter of distance
     *
     * @return distance
     */
	public double get_distance() {
		return _distance;
	}
	 /**
     * setter of distance
     *
     * @param distance
     */
	public void set_distance(double _distance) {
		this._distance = _distance;
	}

	// ***************** Operations ******************** //

	/**
	 * changes the camera location and distance from view plane
	 *
	 * @param cam
	 * @param dist
	 */
	public void updateCamera(Camera c, double d) {
		_camera = c;
		_distance = d;
	}

	/**
	 * changes the background color
	 *
	 * @param color
	 */
	public void updateBackground(Color color) {
		_background = color;
	}

	/**
	 * changes the ambient light
	 *
	 * @param amb
	 */
	public void updateAmbient(AmbientLight amb) {
		_ambietLight = amb;
	}

	 /**
     * adds shapes to the 3D model
     *
     * @param shapes
     */
    public void addIntersectable(Intersectable... shapes) {
        _geometries.add(shapes);
    }

    /**
     * adds lights to the scene
     *
     * @param lights
     */
    public void addLight(LightSource... lights) {
        for (int i = 0; i < lights.length; ++i)
            _lights.add(lights[i]);
    }
}
