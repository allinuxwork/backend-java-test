package backend_test_lesson_4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import backend_test_lesson_4.utils.FileEncodingUtils;
import backend_test_lesson_4.utils.PropertiesUtils;

public class LoadImgNegativeTest extends BaseTest {

	@BeforeEach
	void setUp() {
//		FileEncodingUtils.getFileContent(Images.TOO_BIG.path);

	}

	@DisplayName("Загрузка файла размером больше 10mb base 64")
	@Test
	void LoadFileSizeOver10Base64Test() {
		given().headers("Authorization", token)
				.multiPart("image", FileEncodingUtils.getFileContent(Images.TOO_BIG.path)).expect()
				.body("success", is(false)).body("data.error", is("File is over the size limit")).when().post("/image")
				.prettyPeek().then().statusCode(400);
	}

	@DisplayName("Загрузка не валидного файла base 64")
	@Test
	void LoadInvalidFileBase64Test() {
		given().headers("Authorization", token).multiPart("image", PropertiesUtils.IMG_64_INVALID).expect()
				.body("success", is(false)).when().post("/image").prettyPeek().then().statusCode(400);
	}

	@DisplayName("Загрузка file.txt")
	@Test
	void uploadFileTxtTest() {
		given().headers("Authorization", token).multiPart("plain", FileEncodingUtils.getFileContent(Images.TXT.path))
				.expect().body("success", is(false)).when().post("/image").prettyPeek().then().statusCode(400);
	}

	@DisplayName("Загрузка файла менее 1 кбайта")
	@Test
	void imageUploadFile0ByteTest() {
		given().headers("Authorization", token)
				.multiPart("image", FileEncodingUtils.getFileContent(Images.ZERO_IMG.path)).expect()
				.body("success", is(true)).when().post("/image").prettyPeek().then().statusCode(200);
	}

	@DisplayName("Загрузка пустого файла")
	@Test
	void EmptyUploadDataTest() {
		given().headers("Authorization", token).param("image", "").expect().body("success", is(false))
				.body("status", is(400)).when().post("/image").prettyPeek().then().statusCode(400);
	}
}
