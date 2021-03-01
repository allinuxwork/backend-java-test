package retrofit_product_test.util;

import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@UtilityClass
public class RetrofitUtils {
	static HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new PrettyLogger());

	public static Retrofit getRetrofit() throws IOException {
		OkHttpClient client = new OkHttpClient.Builder().connectTimeout(Duration.ofMinutes(1l))
				.addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY)).build();

		return new Retrofit.Builder().baseUrl(ConfigUtils.getBaseUrl()).client(client)
				.addConverterFactory(JacksonConverterFactory.create()).build();
	}

}
