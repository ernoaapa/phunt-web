package services;

import image.ImageProcessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import marvin.image.MarvinImage;
import marvin.image.MarvinImageMask;
import marvin.io.MarvinImageIO;
import marvin.plugin.MarvinImagePlugin;
import marvin.util.MarvinPluginLoader;

import org.springframework.beans.factory.annotation.Autowired;

import play.modules.spring.Spring;
import storage.FileStorage;

public class ImageService {

	public ImageService() {
		super();
	}

	public String save(File image) {
		ImageProcessor processor = Spring.getBeanOfType(ImageProcessor.class);
		FileStorage fileStorage = Spring.getBeanOfType(FileStorage.class);
		
		//image = processor.resize(image, 336, 457);
		//image = processor.crop(image, 336, 457);

		image = processor.resize(image, 420, 571);
		image = processor.crop(image, 420, 571);
		
		return fileStorage.save(image);
	}

	public String saveGrid(File image) {
		ImageProcessor processor = Spring.getBeanOfType(ImageProcessor.class);
		FileStorage fileStorage = Spring.getBeanOfType(FileStorage.class);
		
		image = processor.crop(image, 336, 457);
		
		return fileStorage.save(image);
	}

}
