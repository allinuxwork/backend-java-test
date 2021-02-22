package backend_test_lesson_4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import backend_test_lesson_4.utils.PropertiesUtils;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest {
	static Properties prop = new Properties();
	static String token;
	static String username;
	static ResponseSpecification responseSpecification = null;
	static RequestSpecification reqSpec;
	String deletehash;
	String imgid;
	static RequestSpecification withoutAuthReqSpec;
	static RequestSpecification withAuthReqSpec;

	@BeforeAll
	static void beforeAll() {
		loadProperties();

		token = prop.getProperty("token");
		username = prop.getProperty("username");

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.baseURI = prop.getProperty("base.url");
		RestAssured.filters(new AllureRestAssured());

		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectStatusLine("HTTP/1.1 200 OK")
				.expectContentType(ContentType.JSON).expectResponseTime(Matchers.lessThan(5000L))
				.expectHeader("Access-Control-Allow-Credentials", "true").build();

		reqSpec = new RequestSpecBuilder().addHeader("Authorization", token).setAccept(ContentType.ANY).build();

//        RestAssured.responseSpecification = responseSpecification;
//        RestAssured.requestSpecification = reqSpec;

	}

	static void loadProperties() {
		try (InputStream file = new FileInputStream(PropertiesUtils.PROPERTIES_FILE)) {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
