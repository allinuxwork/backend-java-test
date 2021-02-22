package backend_test_lesson_4.steps;

import static io.restassured.RestAssured.given;

import backend_test_lesson_4.Images;
import backend_test_lesson_4.dto.PostImageResponse;
import backend_test_lesson_4.utils.FileEncodingUtils;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonRequests {
	static final String INPUT_IMAGE_FILE_PATH = Images.POSITIVE.path;

	public static PostImageResponse uploadCommonImage(RequestSpecification spec) {
		RequestSpecification multiPart = spec
				.multiPart(new MultiPartSpecBuilder(FileEncodingUtils.getFileContent(INPUT_IMAGE_FILE_PATH))
						.controlName("image").build());
		return given().spec(multiPart).when().post("/image").prettyPeek().then().extract().body()
				.as(PostImageResponse.class);
	}
}
