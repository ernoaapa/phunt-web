import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }
    
    @Test
    public void saveComment() throws Exception {
    	Map<String, String> parameters = new HashMap<String, String>();
    	parameters.put("uuid", "1");
    	parameters.put("locationId", "1");
    	parameters.put("comment", "comment");
		Response response = POST("/api/v1/comments", parameters);
        assertIsOk(response);
        
        // String comment = getComment("uuid");
	}

	private String getComment(String string) {
		Response response = GET("/comments/");
		return null;
	}
    
} 