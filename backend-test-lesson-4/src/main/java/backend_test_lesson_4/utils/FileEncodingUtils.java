package backend_test_lesson_4.utils;

import java.io.File;
import java.io.IOException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FileEncodingUtils {

	public byte[] getFileContent(String str) {
		File inputFile = new File(str);

		byte[] fileContent = new byte[0];
		try {
			fileContent = org.apache.commons.io.FileUtils.readFileToByteArray(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}
}
