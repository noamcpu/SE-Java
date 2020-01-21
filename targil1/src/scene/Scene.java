package scene;

import java.util.List;

import elements.*;
import geometries.*;
import primitives.*;

public class Scene {

	protected String sceneName;
	protected Color _background;
	protected AmbientLight _ambietLight;
	protected Geometries _geometries;
	protected  Camera _camera;
	protected double _distance;;

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
	public Scene(String sceneName, Color _background, AmbientLight _ambietLight, Geometries _geometries, Camera _camera,
			double _distance) {
		this.sceneName = sceneName;
		this._background = _background;
		this._ambietLight = _ambietLight;
		this._geometries = _geometries;
		this._camera = _camera;
		this._distance = _distance;
	}

	public Scene(String name) {
		this.sceneName = name;
	}

	// ***************** Getters/Setters ********************** //

	public void addGeometry( Geometry geo) {
		_geometries.add(geo);
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public Color get_background() {
		return _background;
	}

	public void set_background(Color _background) {
		this._background = _background;
	}

	public AmbientLight get_ambietLight() {
		return _ambietLight;
	}

	public void set_ambietLight(AmbientLight _ambietLight) {
		this._ambietLight = _ambietLight;
	}

	public Geometries get_geometries() {
		return _geometries;
	}

	public void set_geometries(Geometries _geometries) {
		this._geometries = _geometries;
	}

	public  Camera get_camera() {
		return _camera;
	}

	public void set_camara(Camera _camera) {
		this._camera = _camera;
	}

	public double get_distance() {
		return _distance;
	}

	public void set_distance(double _distance) {
		this._distance = _distance;
	}

	public void setCamera(Camera camera) {
		this._camera = camera;
	}
	
	// ***************** Operations ******************** //

    /**
     * changes the camera location and distance from view plane
     *
     * @param cam
     * @param dist
     */
    public void updateCamera(Camera cam, double dist) {
        _camera = cam;
        _distance = dist;
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
	}

