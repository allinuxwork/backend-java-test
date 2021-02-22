package backend_test_lesson_4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import backend_test_lesson_4.utils.FileEncodingUtils;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;

@Feature("")
public class PostImageTests extends BaseTest {
	private String uploadedImageId;
	MultiPartSpecification multiPartSpec;
	RequestSpecification uploadReqSpec;
	Faker faker = new Faker();
	static final String INPUT_IMAGE_FILE_PATH = Images.POSITIVE.path;

	@BeforeEach
	void setUp() {
		byte[] fileContent = FileEncodingUtils.getFileContent(INPUT_IMAGE_FILE_PATH);
		multiPartSpec = new MultiPartSpecBuilder(fileContent).controlName("image").build();
		uploadReqSpec = reqSpec.multiPart(multiPartSpec).formParam("title", faker.chuckNorris().fact())
				.formParam("description", faker.harryPotter().quote());
	}

	@Test
	void uploadFileTest() {
		uploadedImageId = given().spec(uploadReqSpec).expect().body("success", is(true))
				.body("data.id", is(notNullValue())).when().post("/image").prettyPeek().then().extract().response()
				.jsonPath().getString("data.deletehash");
	}

	@AfterEach
	@Step("Удалить файл после теста")
	void tearDown() {
		given().headers("Authorization", token).when().delete(Endpoints.DELETE_IMAGE_REQUEST, username, uploadedImageId)
				.prettyPeek().then().statusCode(200);
	}
}
