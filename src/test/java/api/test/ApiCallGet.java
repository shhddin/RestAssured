package api.test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiCallGet {
	
	@Test(enabled = false)
	public void printResponse() {
		RestAssured
		.when()
		.get("https://jsonplaceholder.typicode.com/posts")
		.then()
		.extract()
		.response()
		.prettyPrint();
		}
	
	@Test(enabled = false)
	public void assertStatusCodeFailed() {
		RestAssured
			.when()
			.get("https://jsonplaceholder.typicode.com/posts")
			.then()
			.statusCode(203);
	}
	
	@Test(enabled = false)
	public void validatingStatusCode() {
		RestAssured
			.when()
			.get("https://jsonplaceholder.typicode.com/posts")
			.then()
			.statusCode(200);
			
	}
	
	@Test(enabled = false)
	public void logRequestResponse() {
		RestAssured
		.given().log().all()
		.when()
		.get("https://jsonplaceholder.typicode.com/posts")
		.then().log().all();
	}
	
	@Test(enabled = false)
	public void assertion() {
		RestAssured
		.when()
		.get("https://jsonplaceholder.typicode.com/posts/1")
		.then()
		.log().all()
		.body("userId", equalTo(1))
		.body("id", equalTo(1))
		.body("title", containsString("sunt aut facere"));
	}
	
	@Test(enabled = false)
	public void assertContentType() {
		RestAssured
		.when()
		.get("https://jsonplaceholder.typicode.com/posts")
		.then()
		.contentType(ContentType.XML);
	}
	@Test(enabled = false)
	public void validatingContentType() {
		RestAssured
		.when()
		.get("https://jsonplaceholder.typicode.com/posts")
		.then()
		.contentType(ContentType.JSON);
	}

	@Test(enabled = false)
	public void printHeaders() {
		Headers headers =
		RestAssured
		.when()
		.get("https://jsonplaceholder.typicode.com/posts")
		.then()
		.extract().headers();
		
		System.out.println(headers.toString());
	}
	
	@Test(enabled = false)
	public void printHeader() {
		String header =
		RestAssured
		.when()
		.get("https://jsonplaceholder.typicode.com/posts")
		.then()
		.extract()
		.header("Server");
		
		System.out.println(header);
	}
	
	@Test(enabled = false)
	public void assertAHeader() {
		RestAssured
		.when()
		.get("https://jsonplaceholder.typicode.com/posts")
		.then()
		.header("Server", equalTo("apple"));
	}
	
	@Test(enabled = false)
	public void assertQuilting() {
		Response response = 
		RestAssured
		.when()
		.get("https://www.googleapis.com/books/v1/volumes?q=quilting")
		.then()
		.extract().response();
		
		JsonPath path = new JsonPath(response.asInputStream());
		List<String> list = path.get("items.volumeInfo.title");
		assertThat(list, hasItem(containsStringIgnoringCase("quilting")));
		
		System.out.println(list);
	}
	
	@Test(enabled = false)
	public void assertShortQuilting() {
		RestAssured
		.when()
		.get("https://www.googleapis.com/books/v1/volumes?q=quilting")
		.then()
		.log().all()
		.body("items.volumeInfo.title", hasItem(containsStringIgnoringCase("quilting")));
	}
	
	@Test(enabled=false)
	public void jsonPathList() {
		List<String> list = 
		RestAssured
		.when()
		.get("https://www.googleapis.com/books/v1/volumes?q=quilting")
		.then()
		.extract()
		.path("items.volumeInfo.title");
		System.out.println(list);
	}
	
	@Test(enabled=true)
	public void jsonPathSingleEntity() {
		String entity = 
		RestAssured
		.when()
		.get("https://www.googleapis.com/books/v1/volumes?q=quilting")
		.then()
		.extract()
		.path("items[2].volumeInfo.title");
		System.out.println(entity);
	}
	
}
