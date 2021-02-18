package backend_test_lesson_3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	static Properties prop = new Properties();
	static String token;
	static String username;

	@BeforeAll
	static void beforeAll() {

		LoadProperties();
		token = prop.getProperty("token");
		username = prop.getProperty("username");

//		Выводет ифромцию при ошбках - это логирование вместо 200 поставить 400 в методе ниже
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.baseURI = prop.getProperty("base.url");
		RestAssured.filters(new AllureRestAssured());
	}

	private static void LoadProperties() {
		try (InputStream file = new FileInputStream("src/test/resources/application.properties")) {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
