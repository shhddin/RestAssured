package api.test;


import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import utils.Configuration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PUT_Test {

	Configuration config = Configuration.getInstance();

	Map<String, Object> map = new HashMap<String , Object>();
	
	public Map<String, Object> getMap(){
		map.put("id", 5);
		map.put("title", "Be Mindful");
		map.put("body", "watch your surroundings");
		map.put("userId", 5);
		return map;
	}
	
	@Test
	public void putCall() {
		getMap();
		RestAssured
			.given()
			.baseUri(config.get("baseUrl"))
			.contentType(ContentType.JSON)
			.body(map)
			.log().all()
			.put(config.get("postsEndpoint")+"/"+ map.get("id"))
			.then()
			.log().all();
	}
}
