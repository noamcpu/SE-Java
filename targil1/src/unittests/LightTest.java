package unittests;

import elements.*;
import geometries.*;
import org.junit.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import _scene.Scene;
import primitives.Material;

import java.util.ArrayList;
import java.util.List;

public class LightTest {
	    public Scene createScene(String name) {
	        Scene scene = new Scene(name);
	        scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
	        scene.set_distance(200);
	        scene.set_ambietLight(new AmbientLight(new Color(255, 255, 255), 0));
	        scene.set_background(Color.BLACK);
	        return scene;
	    }
	@Test
    public void lightingTest() {
        Scene scene = createScene("Basic Light Tests");
        Geometries geometries = new Geometries();
        scene.set_geometries(geometries);
        Triangle triangle1 = new Triangle(new Color(0, 0, 0), new Material(0.5, 0.5, 300), new Point3D(-250, 250, 90),
                new Point3D(-250, -250, 120), new Point3D(250, 250, 90));
        Triangle triangle2 = new Triangle(new Color(0, 0, 0), new Material(0.5, 0.5, 300), new Point3D(248, 248, 90),
                new Point3D(248, -248, 120), new Point3D(-248, -248, 120));
        geometries.add(triangle1, triangle2);
        Point3D pos = new Point3D(0, -30, 90);
        Color color = new Color(java.awt.Color.pink);

        // test 1 - 2 triangles with point light
        ImageWriter imageWriter = new ImageWriter("PL triangles", 500, 500, 600, 600);
        List<LightSource> lights = new ArrayList<LightSource>();
        PointLight light1 = new PointLight(color, pos, 1, 0.00005, 0.0000005);
        lights.add(light1);
        scene.setLights(lights);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test 2 - 2 triangles with spot light
        imageWriter = new ImageWriter("SP triangles", 500, 500, 600, 600);
        lights.clear();
        SpotLight light2 = new SpotLight(color, pos, 0.0000005, 1, 0.00005, new Vector(0, 0.7, 0.3));
        lights.add(light2);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test 3 - 2 triangles with directional light
        imageWriter = new ImageWriter("DL triangles", 500, 500, 600, 600);
        lights.clear();
        DirectionalLight light3 = new DirectionalLight(new Color(400, 300, 300), new Vector(0, 0, 1));
        lights.add(light3);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        geometries = new Geometries();
        scene.set_geometries(geometries);
        Sphere sphere = new Sphere(new Color(0, 0, 100), new Material(0.5, 0.5, 300), 50, new Point3D(0, 0, 100));
        geometries.add(sphere);
        color = new Color(400, 300, 300);
        pos = new Point3D(-50, 50, 0);

        // test 1 - sphere with point light
        imageWriter = new ImageWriter("PL sphere", 500, 500, 600, 600);
        lights = new ArrayList<LightSource>();
        PointLight pointLight2 = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(pointLight2);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test 2 - sphere with spot light
        imageWriter = new ImageWriter("SL sphere", 500, 500, 600, 600);
        lights.clear();
        SpotLight spotLight2 = new SpotLight(color, pos, 0.000005, 1, 0.0005, new Vector(1, -1, 2));
        lights.add(spotLight2);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test 3 - sphere with directional light
        imageWriter = new ImageWriter("DL sphere", 500, 500, 600, 600);
        lights.clear();
        DirectionalLight Directional2 = new DirectionalLight(new Color(400, 300, 300), new Vector(1, -1, 2));
        lights.add(Directional2);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }
	@Test
    public void shadowTest1() {
	Scene scene = new Scene("Test scene");
	scene.set_camera(new Camera(new Point3D(0, 0, 5000), new Vector(0, -1, 0), new Vector(0, 0, -1)));
	scene.set_distance(5000);
	scene.set_background(new Color(0, 0, 10));
	scene.set_ambietLight(new AmbientLight(new Color(0, 100, 0), 0.1));

	Geometries geometries = new Geometries();
	geometries.add(
		new Sphere(new Color(50, 100, 0),new Material(0.5, 0.5, 70) ,  150,new Point3D(0, 0, -200)));
	geometries.add(new Triangle(new Color(255, 0, 0), new Material(0.5, 0.5, 70) ,new Point3D(100, 100, -50), new Point3D(25, 100, -50),
		new Point3D(100, 25, -50)));
	scene.set_geometries(geometries);

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
	scene.set_camera(new Camera(new Point3D(0, 0, 5000), new Vector(0, -1, 0), new Vector(0, 0, -1)));
	scene.set_distance(5000);
	scene.set_background(new Color(0, 0, 10));
	scene.set_ambietLight(new AmbientLight(new Color(0, 100, 0), 0.1));

	Geometries geometries = new Geometries();
	geometries.add(
		new Sphere(new Color(50, 100, 0),new Material(0.5, 0.5, 70) ,150, new Point3D(0, 0, -200)));
	geometries.add(new Triangle(new Color(255, 0, 0),  new Material(0.5, 0.5, 70),new Point3D(125, 125, -50), new Point3D(50, 125, -50),
		new Point3D(125, 50, -50)));
	scene.set_geometries(geometries);

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
	scene.set_camera(new Camera(new Point3D(0, 0, 5000), new Vector(0, -1, 0), new Vector(0, 0, -1)));
	scene.set_distance(5000);
	scene.set_background(new Color(0, 0, 10));
	scene.set_ambietLight(new AmbientLight(new Color(0, 100, 0), 0.1));

	Geometries geometries = new Geometries();
	geometries.add(
		new Sphere(new Color(50, 100, 0),new Material(0.5, 0.5, 70), 150 , new Point3D(0, 0, -300)));
	geometries.add(new Triangle(new Color(255, 0, 0),new Material(0.5, 0.5, 70) ,new Point3D(150, 150, -100), new Point3D(75, 150, -100),
		new Point3D(150, 75, -100)));
	scene.set_geometries(geometries);

	List<LightSource> lights = new ArrayList<LightSource>();
	lights.add(new SpotLight(new Color(255,255,255), new Point3D(170,170,-20), 1, 0.0001, 0.000001, new Vector(-1,0,-1)));
//	lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(-1, -1, -1)));
	scene.setLights(lights);

	ImageWriter imageWriter = new ImageWriter("shadow3, traingle", 500, 500, 500, 500);
	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
    }

    @Test
    public void shadowTest4() {
	Scene scene = new Scene("Test scene");
	scene.set_camera(new Camera(new Point3D(0, 0, 5000), new Vector(0, -1, 0), new Vector(0, 0, -1)));
	scene.set_distance(5000);
	scene.set_background(new Color(0, 0, 10));
	scene.set_ambietLight(new AmbientLight(new Color(0, 100, 0), 0.1));
	
	Geometries geometries = new Geometries();
	scene.set_geometries(geometries);
	geometries.add(new Triangle(new Color(255, 138, 22), new Material(0.6, 0.38, 72), new Point3D(-1, -1, -150),
		new Point3D(-240, -1, -150), new Point3D(-1, -240, -150)));
	geometries.add(new Triangle(new Color(255, 138, 22), new Material(0.6, 0.38, 72), new Point3D(1, 1, -150), new Point3D(240, 1, -150),
		new Point3D(1, 240, -150)));
	geometries.add(new Triangle(new Color(255, 138, 22), new Material(0.6, 0.38, 72), new Point3D(-1, 1, -150), new Point3D(-240, 1, -150),
		new Point3D(-1, 240, -150)));
	geometries.add(new Triangle(new Color(255, 138, 22), new Material(0.6, 0.38, 72), new Point3D(1, -1, -150), new Point3D(240, -1, -150),
		new Point3D(1, -240, -150)));
	geometries.add(
		new Sphere(new Color(16, 20, 167), new Material(0.6, 0.38, 80), 50, new Point3D(0, 0, -120)));
	geometries.add(
		new Sphere(new Color(230, 23, 29), new Material(0.6, 0.38, 80) ,40, new Point3D(-80, -80, -160)));

	ArrayList<LightSource> lights = new ArrayList<LightSource>();
	scene.setLights(lights);
	lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(-1, -1, -1)));

	ImageWriter imageWriter = new ImageWriter("shaddows on spheres", 500, 500, 500, 500);
	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
    }

}
