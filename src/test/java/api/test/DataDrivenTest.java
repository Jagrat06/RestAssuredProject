package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
//here the data are getting fetched from excel sheet

public class DataDrivenTest {
	
	/*here the test will execute, the same number of times as data, for example the excel sheet has 4 sets of data,
	test will execute 4 time)*/
	@Test(priority=1,dataProvider="data",dataProviderClass=DataProviders.class)
	public void testPostUser(String UserName,	String FirstName, String LastName,String Email,String Password,String Phone) 
	{
		User userPayload=new User();
		 
		userPayload.setUsername(UserName);
		userPayload.setFirstname(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByname(String UserName) {
		
		Response response=UserEndPoints.deleteUser(UserName);
		Assert.assertEquals(response.getStatusCode(),200);
	}

}
