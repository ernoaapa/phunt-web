package storage.s3;


import play.Play;
import play.PlayPlugin;
import play.exceptions.ConfigurationException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;

public class S3Plugin extends PlayPlugin {

	@Override
	public void onApplicationStart() {
		if (!Play.configuration.containsKey("aws.access.key")) {
			throw new ConfigurationException("Bad configuration for s3: no access key");
		} else if (!Play.configuration.containsKey("aws.secret.key")) {
			throw new ConfigurationException("Bad configuration for s3: no secret key");
		} else if (!Play.configuration.containsKey("s3.bucket")) {
			throw new ConfigurationException("Bad configuration for s3: no s3 bucket");
		}

		S3FileStorage.s3Bucket = Play.configuration.getProperty("s3.bucket");

		String accessKey = Play.configuration.getProperty("aws.access.key");
		String secretKey = Play.configuration.getProperty("aws.secret.key");
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		S3FileStorage.s3Client = new AmazonS3Client(awsCredentials);

	    if (!S3FileStorage.s3Client.doesBucketExist(S3FileStorage.s3Bucket)) {
	    	S3FileStorage.s3Client.createBucket(S3FileStorage.s3Bucket);
	    }
	}
}