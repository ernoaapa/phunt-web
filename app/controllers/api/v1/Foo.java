package controllers.api.v1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import models.Location;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import play.Logger;
import play.db.DB;

public class Foo extends AuthenticatedController {

	public static void show() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(DB.datasource);
		final AtomicInteger cnt = new AtomicInteger();
		
		Logger.info("Datasource: "+DB.datasource);
		List locations = jdbcTemplate.query("SELECT *, earth_distance(ll_to_earth(lat, lon), ll_to_earth(60.233312, 25.1)) AS distance FROM location", new RowMapper() {
			@Override public Object mapRow(ResultSet resultSet, int arg1) throws SQLException {
				Logger.info("Fetching "+cnt.incrementAndGet());
				
				Location loc = new Location();
				loc.roughDistance = resultSet.getDouble("distance")+"";
				loc.id = resultSet.getLong("id");
				return loc;
			}
			
		});
				
		renderJSON(locations);
	}
	
	public static void dbVersion() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(DB.datasource);
		
		List result = jdbcTemplate.query("SELECT version() as version", new RowMapper() {
			@Override public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
				return arg0.getString("version");
			}		
		});
		
		Logger.info("psql version: "+result.get(0));
		renderJSON(result.get(0));
	}
	
}