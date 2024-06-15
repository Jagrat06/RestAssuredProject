package api.test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userPayload;
	
		
@BeforeClass
 public void setupData()
 {
	faker = new Faker();
	userPayload = new User();
	
	userPayload.setId(faker.idNumber().hashCode());
	userPayload.setUsername(faker.name().username());
	userPayload.setFirstname(faker.name().firstName());
	userPayload.setLastName(faker.name().lastName());
	userPayload.setEmail(faker.internet().safeEmailAddress());
	userPayload.setPassword(faker.internet().password(5,10));
	userPayload.setPhone(faker.phoneNumber().cellPhone());
 }



@Test(priority=1)
public void testPostUser() {
	Response response=UserEndPoints.createUser(userPayload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(),200);
}



@Test(priority=2)
public void testGetUserByname() {
	Response response=UserEndPoints.readUser(this.userPayload.getUsername());
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	
}




@Test(priority=3)
public void testUpdateUserByname() {
	
	//update some data
	userPayload.setFirstname(faker.name().firstName());
	userPayload.setLastName(faker.name().lastName());

	Response response= UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
	response.then().log().body().statusCode(200); //Rest assured assertion
	Assert.assertEquals(response.getStatusCode(), 200); //testng assertion
	
	//checking data after update- so sending get user again
}



@Test(priority=4)
public void deleteUserByname() {
	Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
	Assert.assertEquals(response.getStatusCode(), 200);
}


}
