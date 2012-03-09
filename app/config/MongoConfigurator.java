package config;

import java.net.URI;

import com.mongodb.MongoURI;

import play.Logger;
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class MongoConfigurator extends Job {

	@Override
	public void doJob() throws Exception {
		String mongoUrl = System.getenv("MONGOHQ_URL");

		Logger.info("MONGOHQ_URL is "+mongoUrl);
		
		if (mongoUrl != null) {
			MongoURI mongoURI = new MongoURI(mongoUrl);
			
			URI uri = new URI(mongoUrl);
			
			Play.configuration.setProperty("mongo.host", uri.getHost());
			Logger.info("mongo.host "+uri.getHost());
			
			Play.configuration.setProperty("mongo.username", mongoURI.getUsername());
			Logger.info("mongo.username "+mongoURI.getUsername());
			
			Play.configuration.setProperty("mongo.password", mongoURI.getPassword().toString());
			Logger.info("mongo.password "+mongoURI.getPassword().toString());
			Play.configuration.setProperty("mongo.database", mongoURI.getDatabase());
			

		}
	}
	
}
