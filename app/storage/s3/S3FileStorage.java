package storage.s3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;

import play.Logger;
import play.libs.Codec;
import play.libs.MimeTypes;
import storage.FileStorage;
import storage.FileUploadException;

public class S3FileStorage implements FileStorage{

	public static String s3Bucket;
	public static AmazonS3 s3Client;
	
	public String save(File file) {
	    String key = Codec.UUID();
	    
		ObjectMetadata metadata = new ObjectMetadata();
	    String contentType = MimeTypes.getContentType(file.getName());
	    metadata.setContentType(contentType);
	    
		try {
			long start = System.currentTimeMillis();
			Logger.info("Saving file to s3");
		    s3Client.putObject(s3Bucket, key, new FileInputStream(file), metadata);
			Logger.info("File transferred to s3 in "+(System.currentTimeMillis()-start)+"ms");

		} catch (FileNotFoundException e) {
			throw new FileUploadException();
		}
	    
	    return "http://"+s3Bucket+".s3.amazonaws.com/"+key;
	}
}
