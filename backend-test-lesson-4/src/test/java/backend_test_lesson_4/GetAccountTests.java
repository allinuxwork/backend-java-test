package backend_test_lesson_4;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import backend_test_lesson_4.dto.GetAccountResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAccountTests extends BaseTest {
	static RequestSpecification withoutAuthReqSpec;

	@BeforeAll
	static void createSpecs() {
		withoutAuthReqSpec = new RequestSpecBuilder().addHeader("Authorization", "").build();
	}

	@Test
	void getAccountInfoPositiveTest() {
		given().spec(reqSpec).log().all().when().get(Endpoints.GET_ACCOUNT_REQUEST, username).prettyPeek().then()
				.spec(responseSpecification);
	}

	@DisplayName("Негативная проверка - без авторизации")
	@Test
	void getAccountInfoNegativeWithoutAuthTest() {
		given().spec(withoutAuthReqSpec).when().get(Endpoints.GET_ACCOUNT_REQUEST, username).prettyPeek().then()
				.statusCode(403);
	}

	@DisplayName("Позитивная проверка - содержит строка, успех и статус код")
	@Test
	void getAccountInfoPositiveWithManyChecksTest() {
		given().headers("Authorization", token).expect().body(CoreMatchers.containsString(username))
				.body("success", is(true)).when().get(Endpoints.GET_ACCOUNT_REQUEST, username).then().statusCode(200);
	}

	@DisplayName("Позитивная проверка - 200 и url data")
	@Test
	void getAccountInfoPositiveWithObjectTest() {
		GetAccountResponse response = given().headers("Authorization", token).when()
				.get(Endpoints.GET_IMAGE_USERNAME, username).prettyPeek().then().extract().body()
				.as(GetAccountResponse.class);
		assertThat(response.getData().getUrl()).isEqualTo("imgpost21");
		assertThat(response.getStatus()).isEqualTo(200);
//		System.out.println(response.getStatus().toString());
//		System.out.println(response.getStatus());
//		assertThat(response.getStatus(), equalTo(200));
//		assertThat(frodo).isNotEqualTo(sauron);
//		log.info(response.getStatus().toString());
	}

}
