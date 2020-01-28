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
	@Test
	public void lightTesttringle() {
		Scene scene = new Scene("Test tringle");
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.set_distance(500);
		scene.set_ambietLight(new AmbientLight(new Color(0, 0, 20), 0));
		scene.set_background(new Color(java.awt.Color.green));
		Geometries geometries = new Geometries();
		scene.set_geometries(geometries);
		Triangle triangle1 = new Triangle(new Color(java.awt.Color.black), new Point3D(50, 50, 100),
				new Point3D(-50, 50, 101), new Point3D(-50, -50, 100));
		Triangle triangle2 = new Triangle(new Color(java.awt.Color.black), new Point3D(-50, -50, 100),
				new Point3D(50, -50, 101), new Point3D(50, 50, 100));
		triangle1.setMaterial(0.5, 0.5, 300);
		triangle2.setMaterial(0.5, 0.5, 300);
		geometries.add(triangle1, triangle2);
		Point3D pos = new Point3D(0, 5, 95);
		Color color = new Color(400, 200, 200);

		ImageWriter imageWriter = new ImageWriter("triangle-point", 500, 500, 600, 600);
		List<LightSource> lights = new ArrayList<LightSource>();
		PointLight light1 = new PointLight(color, pos, 1, 0.004, 0.0004);
		lights.add(light1);
		scene.setLights(lights);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();

		imageWriter = new ImageWriter("triangle-spot", 500, 500, 600, 600);
		lights.clear();
		SpotLight light2 = new SpotLight(color, pos, 0.0000005, 1, 0.00005, new Vector(0, 1, 0));
		lights.add(light2);
		scene.setLights(lights);
		render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();

		imageWriter = new ImageWriter("triangle-direction", 500, 500, 600, 600);
		lights.clear();
		DirectionalLight light3 = new DirectionalLight(new Color(400, 300, 300), new Vector(0, 0, 1));
		lights.add(light3);
		scene.setLights(lights);
		render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}

	@Test
	public void lightTestsphere() {
		Scene scene = new Scene("Test sphere");
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.set_distance(200);
		scene.set_ambietLight(new AmbientLight(new Color(0, 0, 20), 0));
		scene.set_background(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.set_geometries(geometries);
		Sphere sphere = new Sphere(new Color(0, 0, 100), 50, new Point3D(0, 0, 100));
		sphere.setMaterial(0.5, 0.5, 300);
		geometries.add(sphere);
		Color color = new Color(400, 300, 300);
		Point3D pos = new Point3D(-50, 50, 0);

		ImageWriter imageWriter = new ImageWriter("sphere-point", 500, 500, 600, 600);
		List<LightSource> lights = new ArrayList<LightSource>();
		PointLight light1 = new PointLight(color, pos, 1, 0.0005, 0.000005);
		lights.add(light1);
		scene.setLights(lights);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();

		imageWriter = new ImageWriter("sphere-spot", 500, 500, 600, 600);
		lights.clear();
		SpotLight light2 = new SpotLight(color, pos, 0.000005, 1, 0.0005, new Vector(1, -1, 2));
		lights.add(light2);
		scene.setLights(lights);
		render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();

		imageWriter = new ImageWriter("sphere-directional", 500, 500, 600, 600);
		lights.clear();
		DirectionalLight light3 = new DirectionalLight(new Color(400, 300, 300), new Vector(1, -1, 2));
		lights.add(light3);
		scene.setLights(lights);
		render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}

	}