package backend_test_lesson_3;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostImageTests extends BaseTest {
	static final String KARIBS = "karibs.jpg";
	private String uploadedImageId;
	private String fileString;

	@BeforeEach
	void setUp() {
		byte[] fileContent = getFileContent();
		fileString = Base64.getEncoder().encodeToString(fileContent);
	}

	@Test
	void uploadFileTest() {
		// R0lGODlAQABAIAAAAAAAP///yH5BEAAAAALAAAAAABAAEAAAIBRAA7
		uploadedImageId = given().headers("Authorization", token).multiPart("image", fileString).expect()
				.body("success", is(true)).body("data.id", is(notNullValue())).when().post("/image").prettyPeek().then()
				.extract().response().jsonPath().getString("data.deletehash");
	}

	@AfterEach
	void tearDown() {
		given().headers("Authorization", token).when()
				.delete("/account/{username}/image/{deleteHash}", username, uploadedImageId).prettyPeek().then()
				.statusCode(200);
	}

	private byte[] getFileContent() {
		ClassLoader classLoader = getClass().getClassLoader();
		File inputFile = new File(classLoader.getResource(KARIBS).getFile());
		byte[] fileContent = new byte[0];
		try {
			fileContent = FileUtils.readFileToByteArray(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}
}
