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
		//imageProcessor.resize(new File("ari_kukkahippi.jpg"), 100, 100);
		imageProcessor.crop(new File("ari_kukkahippi.jpg"), 336, 457);
	}
}
