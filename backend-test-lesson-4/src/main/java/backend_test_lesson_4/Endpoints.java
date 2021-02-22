package backend_test_lesson_4;

public class Endpoints {
	public static final String GET_ACCOUNT_REQUEST = "/account/{username}";
	public static final String POST_IMAGE_REQUEST = "/image";
	public static final String GET_IMAGE_REQUEST = "/image/{id}";
	public static final String DELETE_IMAGE_REQUEST = "/account/{username}/image/{deleteHash}";
	public static final String GET_IMAGE_USERNAME = "https://api.imgur.com/3/account/{username}";
}
