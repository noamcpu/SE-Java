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

}