package controllers.api.v1;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class CommentTest extends FunctionalTest {

    @Test
    public void saveCommentShouldRequireLocationIdAndComment() throws Exception {
		Response response = POST("/api/v1/comments/create", getCommentParameters("uuid", "", "comment"));
        assertStatus(500, response);
	}
    
    @Test
    public void saveComment() throws Exception {
		Response response = POST("/api/v1/comments/create", getCommentParameters("uuid", "1", "comment"));
        assertStatus(200, response);
	}    

	private Map<String, String> getCommentParameters(String uuid, String locationId, String comment) {
		Map<String, String> parameters = new HashMap<String, String>();
    	parameters.put("uuid", uuid);
    	parameters.put("locationId", locationId);
    	parameters.put("comment", comment);
		return parameters;
	}    
}