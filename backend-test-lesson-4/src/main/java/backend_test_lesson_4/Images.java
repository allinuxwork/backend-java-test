package backend_test_lesson_4;

public enum Images {

	POSITIVE("src/test/resources/karibs.jpg"), TOO_BIG("src/test/resources/img_big.jpg"),
	ZERO_IMG("src/test/resources/img_zero.jpg"), SMALL("src/test/resources/img_small.jpg"),
	TXT("src/test/resources/file.txt");

	public final String path;

	Images(String path) {
		this.path = path;
	}
}
