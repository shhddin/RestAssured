package api.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import utils.Configuration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Post__Test {
	
	Configuration config = Configuration.getInstance();
	File file = new File("src\\test\\resources\\post.json");
	
	@Test(enabled = false)
	public void postTestPrint() {
		RestAssured
			.given()
			.body("{\"title\" : \"Be humble\", \"body\" : \"Stay in the ground,Be kind\", \"userId\" : 786}")
			.contentType(ContentType.JSON)
			.when()
			.post("https://jsonplaceholder.typicode.com/posts")
			.prettyPrint();
	}
	
	@Test(enabled = false)
	public void postTestLog() {
		RestAssured
			.given()
			.body("{\"title\" : \"Chemical\", \"body\" : \" Dopamine: Try something new. Serotonin: Sunlight or Excercise. Oxytocin: do something nice. Endorphins: Pray, Create art, Meditate\", \"userId\" : 786}")
			.contentType(ContentType.JSON)
			.log().all()
			.when()
			.post("https://jsonplaceholder.typicode.com/posts")
			.then()
			.statusCode(201)
			.log().all();	
	}
	
	@Test(enabled = false)
	public void postWithJsonFile() {
		File file = new File("src\\test\\resources\\post.json");
		RestAssured
		.given()
		.body(file)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode(201)
		.log().all();	
	}
	
	@Test(enabled = false)
	public void postWithJsonInputstream() throws FileNotFoundException {
		File file = new File("src\\test\\resources\\post.json");
		InputStream stream = new FileInputStream(file);
		RestAssured
		.given()
		.body(stream)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode(201)
		.log().all();	
	}
	
	@Test(enabled = false)
	public void postWithFileValidation() {
		File file = new File("src\\test\\resources\\post.json");
		
		RestAssured
		.given()
		.body(file)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode(201)
		.log().all()
	   .body("2.userId", equalTo(786)) 
	   .body("2.body", equalTo("Is life would be easier if you become a bad person?"))
		 ;
	}
	
	@Test(enabled = false)
	public void postWithFileFailedTest() {
		File file = new File("src\\test\\resources\\post.json");
		
		RestAssured
		.given()
		.body(file)
		.contentType(ContentType.JSON)
		
		.when()
		.post("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode(201)
		.log().all()
		.body("title", equalTo(""))
		.body("body", equalTo("Is life would be easier if you become a bad person?"))
		.body("id", equalTo(201));
	}
	
	@Test(enabled = false)
	public void postWithFileNegativeTestIncorrectURL() {
		File file = new File("src\\test\\resources\\post.json");
		
		RestAssured
		.given()
		.body(file)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("https://jsonplaceholder.typicode.com/pos")
		.then()
		.statusCode(404);
	}
	
	//Where is the incorrect method?
	@Test(enabled = false)
	public void postWithFileNegativeTestIncorrectMethod() {
		File file = new File("src\\test\\resources\\post.json");
		
		RestAssured
		.given()
		.body(file)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("https://jsonplaceholder.typicode.com/posts/1")
		.then()
		.statusCode(404);
	}
	
	@Test(enabled = false)
	public void postWithFileExtractResponse() {
		File file = new File("src\\test\\resources\\post.json");
		
		Response response = 
		RestAssured
		.given()
		.body(file)
		.contentType(ContentType.JSON)
		.when()
		.post("https://jsonplaceholder.typicode.com/posts")
		.then()
		.statusCode(201)
		.and()
		.extract()
		.response();
		
		System.out.println(response.asPrettyString());
	}
	
	@Test(enabled = true)
	public void postTestConfig() {
		RestAssured
			.given().baseUri(config.get("baseUrl"))
			.body(file)
			.contentType(ContentType.JSON)
			.log().all()
			.when()
			.post(config.get("postsEndpoint"))
			.then()
			.statusCode(201)
			.log().all();	
	}
}
