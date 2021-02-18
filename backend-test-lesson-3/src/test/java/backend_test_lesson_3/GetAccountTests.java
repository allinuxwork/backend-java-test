package backend_test_lesson_3;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;

public class GetAccountTests extends BaseTest {

	@Test
	void getAccountOkTest() {
		given().log().all().headers("Authorization", token).when().get("/account/{username}", username).prettyPeek()
				.then().statusCode(200);
	}

	@Test
	void getAccountOkTestMany() {
		given().log().all().headers("Authorization", token).expect().body(CoreMatchers.containsString(username))
				.body("success", is(true)).when().get("/account/{username}", username).prettyPeek().then()
				.statusCode(200);
	}

	@Test
	void getAccount1Test404() {
		given().log().all().headers("Authorization", token).when().get("/account/all").then().statusCode(404);
	}

	@Test
	void getAccount2TestImgpost21() {
		given().log().all().headers("Authorization", token).expect().body(CoreMatchers.containsString("imgpost21"))
				.when().get("/account/{username}", username);
	}

	@Test
	void getAccount3TestNeutral() {
		given().log().all().headers("Authorization", token).expect().body(CoreMatchers.containsString("Neutral")).when()
				.get("/account/{username}", username);

	}

	@Test
	void getAccount4TestId() {
		given().headers("Authorization", token).expect().body(CoreMatchers.containsString("145269175")).when()
				.get("/account/{username}", username);
	}

	@Test
	void getAccount5TestAvatar() {
		given().log().all().headers("Authorization", token).expect()
				.body("data.avatar", is("https://imgur.com/user/imgpost21/avatar?maxwidth=290")).when()
				.get("/account/{username}", username);
	}

	@Test
	void getAccount6TestSuccess() {
		given().log().all().headers("Authorization", token).expect().body("success", is(true)).when()
				.get("/account/{username}", username);
	}

	@Test
	void getAccount7TestProExpiration() {
		given().log().all().headers("Authorization", token).expect().body("data.pro_expiration", is(false)).when()
				.get("/account/{username}", username);
	}

	@Test
	void getAccount9TestUrl() {
		given().headers("Authorization", token).expect().body("data.url", is("imgpost21")).when()
				.get("/account/{username}", username);
	}

	@Test
	void getAccount10TestIsBlocked() {
		given().headers("Authorization", token).expect().body("data.is_blocked", is(false)).when()
				.get("/account/{username}", username);
	}

	@Test
	void getAccount11TestFollowStatus() {
		Response response = given().headers("Authorization", token).expect().body("data.user_follow.status", is(false))
				.when().get("/account/{username}", username);
		System.out.println("-------------------------------------------------");
		System.out.println("Ответ：" + response.asString());
	}

	@Test
	void getAccount12Тest() {
		given().headers("Authorization", token).expect().when().get("/account/{username}", username).then()
				.contentType("application/json");
	}

}
