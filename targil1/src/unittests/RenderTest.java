/*
package unittests;

import org.junit.Test;

import _scene.Scene;
import elements.AmbientLight;
import elements.Camera;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;

public class RenderTest {
	@Test
	public void basicRendering(){
		Scene scene = new Scene("Test scene");
		scene.set_camara(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.set_distance(150);
		scene. set_ambietLight(new AmbientLight(new Color(java.awt.Color.white), 1));
		scene.set_background(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.set_geometries(geometries);
		 geometries.add(new Sphere(50, new Point3D(0, 0, 150)));

	        geometries.add(new Triangle(new Color(255,0,0), new Point3D( 100, 0, 149),
	                new Point3D(  0, 100, 149),
	                new Point3D( 100, 100, 149)));

	        geometries.add(new Triangle(new Color(255,0,0),new Point3D( 100, 0, 149),
	                new Point3D(  0, -100, 149),
	                new Point3D( 100,-100, 149)));

	        geometries.add(new Triangle(new Point3D(-100, 0, 149),
	                new Point3D(  0, 100, 149),
	                new Point3D(-100, 100, 149)));

	        geometries.add(new Triangle( new Point3D(-100, 0, 149),
	                new Point3D(  0,  -100, 149),
	                new Point3D(-100, -100, 149)));

		ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.printGrid(50 ,new Color(java.awt.Color.blue));
		render.writeToImage();
	}
}
*/
package unittests;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import _scene.Scene;

public class RenderTest {
	@Test
	public void basicRendering() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.set_distance(150);
		scene.set_background(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.set_geometries(geometries);
		scene.set_ambietLight(new AmbientLight(new Color(java.awt.Color.white)));
		geometries.add(new Sphere(new Color(0, 0, 0), 50, new Point3D(0, 0, 150)));

		geometries.add(new Triangle(new Color(0, 0, 0), new Point3D(100, 0, 149), new Point3D(0, 100, 149),
				new Point3D(100, 100, 149)));

		geometries.add(new Triangle(new Color(0, 0, 0), new Point3D(100, 0, 149), new Point3D(0, -100, 149),
				new Point3D(100, -100, 149)));

		geometries.add(new Triangle(new Color(100, 100, 100), new Point3D(-100, 0, 149), new Point3D(0, 100, 149),
				new Point3D(-100, 100, 149)));

		geometries.add(new Triangle(new Color(100, 100, 100), new Point3D(-100, 0, 149), new Point3D(0, -100, 149),
				new Point3D(-100, -100, 149)));

		ImageWriter imageWriter = new ImageWriter("test1", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.printGrid(50, new Color(java.awt.Color.blue));
		render.writeToImage();
	}
}