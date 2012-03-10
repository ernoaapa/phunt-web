package image.marvin;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import services.ImageService;
import storage.FileStorage;

public class MarvinImageProcessorTest {

	private MarvinImageProcessor imageProcessor;
	
	@Before
	public void setup() {
		imageProcessor = new MarvinImageProcessor();
	}
	
	@Test
	public void shouldResizeImage() {
		File image = new File("ari_kukkahippi.jpg");
		image = imageProcessor.resize(image, 100, 100);
		imageProcessor.crop(image, 100, 100);//336, 457
	}
}
