package retrofit_product_test.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class ConfigUtils {
	static Properties prop = new Properties();
	private static InputStream configFile;

	static {
		try {
			configFile = new FileInputStream("src/test/resources/application.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SneakyThrows
	public static String getBaseUrl() throws IOException {
		prop.load(configFile);
		return prop.getProperty("url");
	}

}
