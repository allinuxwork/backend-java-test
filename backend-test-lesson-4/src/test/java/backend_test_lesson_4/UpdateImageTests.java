package backend_test_lesson_4;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import backend_test_lesson_4.utils.PropertiesUtils;
import io.restassured.specification.RequestSpecification;

public class UpdateImageTests extends BaseTest {
	static RequestSpecification withoutAuthReqSpec;
	static RequestSpecification withAuthReqSpec;
	String json;

	@DisplayName("Загрузка файла по url")
	@BeforeEach
	void setUp() {
		json = given().headers("Authorization", token).body(PropertiesUtils.IMG_URL).when().post("/image").then()
				.statusCode(200).extract().asString();
		imgid = from(json).get("data.id");
		deletehash = from(json).get("data.deletehash");
	}

	@DisplayName("Обновление загруженного файла")
	@Test
	void updateImgTest() {
		given().headers("Authorization", token).when().get(Endpoints.GET_IMAGE_REQUEST, imgid).prettyPeek().then()
				.spec(responseSpecification);
	}

	@DisplayName("Удаление файла")
	@AfterEach
	void tearDown() {
		given().headers("Authorization", token).when().delete(Endpoints.DELETE_IMAGE_REQUEST, username, deletehash)
				.prettyPeek().then().spec(responseSpecification);
	}
}
