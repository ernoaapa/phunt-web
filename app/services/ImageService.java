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

	public FileStorage fileStorage;
	
	public ImageService() {
		super();
	}

	public String save(File image) {
		ImageProcessor processor = Spring.getBeanOfType(ImageProcessor.class);
		processor.resize(image, 100, 100);
		
		return fileStorage.save(image);
	}

}
