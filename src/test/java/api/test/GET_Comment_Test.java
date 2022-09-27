package api.test;

import org.testng.annotations.Test;

import utils.Configuration;
import io.restassured.RestAssured;

public class GET_Comment_Test {

	Configuration config = Configuration.getInstance();
	
	@Test
	public void getWithParameter() {
		RestAssured
			.given()
			.baseUri(config.get("baseUrl"))
			.param("postId", 1)
			.param("id", 1)
			.get(config.get("commentEndpoint"))
			.prettyPrint();
	}
	
	@Test
	public void getWithParameter2() {
		RestAssured
			.given()
			.baseUri(config.get("baseUrl"))
			.param("postId", 1)
			.get(config.get("commentEndpoint"))
			.prettyPrint();
	}
	
}
