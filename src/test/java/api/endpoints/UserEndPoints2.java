package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//has  CRUD operations



//here the url are fetched from properties file

public class UserEndPoints2 {
	
	static ResourceBundle fetchURL() 
	{
		ResourceBundle url = ResourceBundle.getBundle("routes");
		return url;
	}
	
	public static Response createUser(User payload)
	{
		
		String post_url=fetchURL().getString("post_url");
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(post_url);
		
		return response;
		
	}
	
	public static Response readUser(String userName)
	{
		String get_url=fetchURL().getString("get_url");
		Response response=given()
		.pathParam("username", userName)
		.when()
		.get(get_url);
		return response;
		
	}
	
	public static Response updateUser(String userName,User payload)
	{
		String update_url=fetchURL().getString("update_url");
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
		.when()
		.put(update_url);
		
		return response;
		
	}
	
	public static Response deleteUser(String userName)
	{
		
		String delete_url=fetchURL().getString("delete_url");
		Response response=given()
		.pathParam("username", userName)
		.when()
		.delete(delete_url);
		return response;
		
	}

}
