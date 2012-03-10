package controllers;

import java.io.File;
import java.io.FileNotFoundException;

import javax.inject.Inject;

import play.mvc.Controller;
import storage.FileStorage;
import storage.s3.S3FileStorage;

public class Upload extends Controller {
	
	@Inject
	public static FileStorage fileStorage;
	
	public static void index() {
        render();
    }

	public static void uploadPhoto(String title, File photo) {
		redirect(fileStorage.save(photo));
	}
}
