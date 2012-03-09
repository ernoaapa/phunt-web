import models.Location;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Boostrap extends Job {

	@Override
	public void doJob() throws Exception {
		if (Location.count() == 0) {
			Fixtures.loadModels("initial_data.yml");
		}
	}
}
