package unittests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;
import renderer.ImageWriter;

public class ImageWriterTest {

	ImageWriter image = new ImageWriter("image", 1000, 1000, 500, 500);

	@Test
	public void renderTest() {
		for (int i = 0; i < image.getNx(); i++) {
			for (int j = 0; j < image.getNy(); j++)
				if (i % 50 == 0 || j % 50 == 0)
					image.writePixel(j, i, Color.green);
		}
		image.writeToimage();
	}
}
