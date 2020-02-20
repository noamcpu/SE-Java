package unittests;

import elements.*;
import geometries.*;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class LightTest {

    @Test
    public void directionalLightTest() {
	Scene scene = new Scene("Test scene");
	scene.setCamera(new Camera(new Point3D(0, 0, 5000), new Vector(0, -1, 0), new Vector(0, 0, -1)));
	scene.setDistance(5000);
	scene.setBackground(new Color(0, 0, 10));
	scene.setAmbient(new AmbientLight(new Color(0, 100, 0), 0.1));

	Geometries geometries = new Geometries();
	geometries.add(
		new Sphere(new Color(50, 100, 0), new Material(0.5, 0.5, 100), 150, new Point3D(0, 0, 200)));
	scene.setGeometries(geometries);

	List<LightSource> lights = new ArrayList<LightSource>();
	lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(1, 1, -1)));
	scene.setLights(lights);

	ImageWriter imageWriter = new ImageWriter("directionalTest", 500, 500, 500, 500);
	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
    }

    @Test
    public void pointLightTest() {
	Scene scene = new Scene("Test scene");
	scene.setCamera(new Camera(new Point3D(0, 0, 5000), new Vector(0, -1, 0), new Vector(0, 0, -1)));
	scene.setDistance(5000);
	scene.setBackground(new Color(0, 0, 10));
	scene.setAmbient(new AmbientLight(new Color(0, 100, 0), 0.1));

	Geometries geometries = new Geometries();
	scene.setGeometries(geometries);

	geometries.add(
		new Sphere(new Color(89, 32, 77), new Material(0.7, 0.3, 100), 150, new Point3D(0, 0, -200)));

	List<LightSource> lights = new ArrayList<LightSource>();
	lights.add(new PointLight(new Color(255, 255, 255), new Point3D(100, 100, -50), 1, 0.0005, 0.000005));
	scene.setLights(lights);

	ImageWriter imageWriter = new ImageWriter("pointTest", 500, 500, 500, 500);
	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
    }
    @Test
    public void TrianglesPointLightTest() {
	Scene scene = new Scene("Test scene");
	scene.setCamera(new Camera(new Point3D(0, 0, -5000), new Vector(0, -1, 0), new Vector(0, 0, 1)));
	scene.setDistance(5000);
	scene.setBackground(Color.BLACK);
	scene.setAmbient(new AmbientLight(new Color(255, 255, 255), 0));

	Geometries geometries = new Geometries();
	scene.setGeometries(geometries);
	geometries.add( new Triangle(new Color(0, 0, 0), new Material(0.5, 0.5, 300), new Point3D(-250, 250, 90),
			new Point3D(-250, -250, 120), new Point3D(250, 250, 90)));
	geometries.add(new Triangle(new Color(0, 0, 0), new Material(0.5, 0.5, 300), new Point3D(248, 248, 90),
			new Point3D(248, -248, 120), new Point3D(-248, -248, 120)));
	scene.setGeometries(geometries);
	List<LightSource> lights = new ArrayList<LightSource>();
	lights.add(new PointLight(new Color(java.awt.Color.pink), new Point3D(0, -30, 90), 1, 0.00005, 0.0000005));
	scene.setLights(lights);

	ImageWriter imageWriter = new ImageWriter("TrianglesPointLightTest", 500, 500, 600, 600);
	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
    }

    @Test
    public void spotLightTest() {
	Scene scene = new Scene("Test scene");
	scene.setCamera(new Camera(new Point3D(0, 0, 5000), new Vector(0, -1, 0), new Vector(0, 0, -1)));
	scene.setDistance(5000);
	scene.setBackground(new Color(0, 0, 10));
	scene.setAmbient(new AmbientLight(new Color(0, 100, 0), 0.1));

	Geometries geometries = new Geometries();
	scene.setGeometries(geometries);
	geometries.add(
		new Sphere(new Color(0, 0, 255), new Material(0.5, 0.5, 100), 150, new Point3D(0, 0, -200)));

	List<LightSource> lights = new ArrayList<LightSource>();
	lights.add(new SpotLight(new Color(255, 255, 255), new Point3D(200, 200, -100), new Vector(-1, -1, -1),1, 0.001,
		0.000001));
	scene.setLights(lights);

	ImageWriter imageWriter = new ImageWriter("spotTest", 500, 500, 500, 500);
	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
    }

    @Test
    public void TrianglesSpotLightTest() {
	Scene scene = new Scene("Test scene");
	scene.setCamera(new Camera(new Point3D(0, 0, 5000), new Vector(0, -1, 0), new Vector(0, 0, -1)));
	scene.setDistance(5000);
	scene.setBackground(new Color(0, 0, 10));
	scene.setAmbient(new AmbientLight(new Color(0, 0, 0), 0.1));

	Geometries geometries = new Geometries();
	scene.setGeometries(geometries);
	geometries.add(new Triangle(new Color(255, 0, 0), new Material(0.5, 0.4, 70), new Point3D(0, -200, -200),
		new Point3D(0, 0, -200), new Point3D(200, 0, -100)));
	geometries.add(new Triangle(new Color(0, 0, 255), new Material(0.5, 0.4, 70),
		new Point3D(0, -200, -200), new Point3D(0, 0, -200), new Point3D(-200, 0, -100)));

	List<LightSource> lights = new ArrayList<LightSource>();
	lights.add(new SpotLight(new Color(255, 255, 255), new Point3D(0, -50, -50), new Vector(0, 0, -1),1, 0.0001,
		0.000001));
	scene.setLights(lights);

	ImageWriter imageWriter = new ImageWriter("TrianglesSpotLightTest", 500, 500, 500, 500);
	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
    }

//ShadowTest 
    @Test
    public void shadowTest1() {
	Scene scene = new Scene("Test scene");
	scene.setCamera(new Camera(new Point3D(0, 0, 5000), new Vector(0, -1, 0), new Vector(0, 0, -1)));
	scene.setDistance(5000);
	scene.setBackground(new Color(0, 0, 10));
	scene.setAmbient(new AmbientLight(new Color(0, 100, 0), 0.1));

	Geometries geometries = new Geometries();
	geometries.add(
		new Sphere(new Color(50, 100, 0), new Material(0.5, 0.5, 70), 150, new Point3D(0, 0, -200)));
	geometries.add(new Triangle(new Color(255, 0, 0), new Material(0.5, 0.5, 70), new Point3D(100, 100, -50), new Point3D(25, 100, -50),
		new Point3D(100, 25, -50)));
	scene.setGeometries(geometries);

	List<LightSource> lights = new ArrayList<LightSource>();
	lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(-1, -1, -1)));
	scene.setLights(lights);

	ImageWriter imageWriter = new ImageWriter("shadow1, traingle", 500, 500, 500, 500);
	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
    }

    @Test
    public void shadowTest2() {
	Scene scene = new Scene("Test scene");
	scene.setCamera(new Camera(new Point3D(0, 0, 5000), new Vector(0, -1, 0), new Vector(0, 0, -1)));
	scene.setDistance(5000);
	scene.setBackground(new Color(0, 0, 10));
	scene.setAmbient(new AmbientLight(new Color(0, 100, 0), 0.1));

	Geometries geometries = new Geometries();
	geometries.add(
		new Sphere(new Color(50, 100, 0), new Material(0.5, 0.5, 70), 150, new Point3D(0, 0, -200)));
	geometries.add(new Triangle(new Color(255, 0, 0), new Material(0.5, 0.5, 70), new Point3D(125, 125, -50), new Point3D(50, 125, -50),
		new Point3D(125, 50, -50)));
	scene.setGeometries(geometries);

	List<LightSource> lights = new ArrayList<LightSource>();
	lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(-1, -1, -1)));
	scene.setLights(lights);

	ImageWriter imageWriter = new ImageWriter("shadow2, traingle", 500, 500, 500, 500);
	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
    }

    @Test
    public void shadowTest3() {
	Scene scene = new Scene("Test scene");
	scene.setCamera(new Camera(new Point3D(0, 0, 5000), new Vector(0, -1, 0), new Vector(0, 0, -1)));
	scene.setDistance(5000);
	scene.setBackground(new Color(0, 0, 10));
	scene.setAmbient(new AmbientLight(new Color(0, 100, 0), 0.1));

	Geometries geometries = new Geometries();
	geometries.add(
		new Sphere(new Color(50, 100, 0), new Material(0.5, 0.5, 70), 150, new Point3D(0, 0, -300)));
	geometries.add(new Triangle(new Color(255, 0, 0), new Material(0.5, 0.5, 70), new Point3D(150, 150, -100), new Point3D(75, 150, -100),
		new Point3D(150, 75, -100)));
	scene.setGeometries(geometries);

	List<LightSource> lights = new ArrayList<LightSource>();
	lights.add(new SpotLight(new Color(255,255,255), new Point3D(170,170,-20), new Vector(-1,0,-1), 1, 0.0001, 0.000001));
	scene.setLights(lights);

	ImageWriter imageWriter = new ImageWriter("shadow3, traingle", 500, 500, 500, 500);
	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
    }

    @Test
    public void shadowTest4() {
	Scene scene = new Scene("Test scene");
	scene.setCamera(new Camera(new Point3D(0, 0, 5000), new Vector(0, -1, 0), new Vector(0, 0, -1)));
	scene.setDistance(5000);
	scene.setBackground(new Color(0, 0, 10));
	scene.setAmbient(new AmbientLight(new Color(0, 100, 0), 0.1));
	
	Geometries geometries = new Geometries();
	scene.setGeometries(geometries);
	geometries.add(new Triangle(new Color(255, 138, 22), new Material(0.6, 0.38, 72), new Point3D(-1, -1, -150),
		new Point3D(-240, -1, -150), new Point3D(-1, -240, -150)));
	geometries.add(new Triangle(new Color(255, 138, 22), new Material(0.6, 0.38, 72), new Point3D(1, 1, -150), new Point3D(240, 1, -150),
		new Point3D(1, 240, -150)));
	geometries.add(new Triangle(new Color(255, 138, 22), new Material(0.6, 0.38, 72), new Point3D(-1, 1, -150), new Point3D(-240, 1, -150),
		new Point3D(-1, 240, -150)));
	geometries.add(new Triangle(new Color(255, 138, 22), new Material(0.6, 0.38, 72), new Point3D(1, -1, -150), new Point3D(240, -1, -150),
		new Point3D(1, -240, -150)));
	geometries.add(
		new Sphere(new Color(16, 20, 167), new Material(0.6, 0.38, 80),50, new Point3D(0, 0, -120) ));
	geometries.add(
		new Sphere(new Color(230, 23, 29), new Material(0.6, 0.38, 80), 40, new Point3D(-80, -80, -160)));

	ArrayList<LightSource> lights = new ArrayList<LightSource>();
	scene.setLights(lights);
	lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(-1, -1, -1)));

	ImageWriter imageWriter = new ImageWriter("shaddows on spheres", 500, 500, 500, 500);
	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
    }
}


