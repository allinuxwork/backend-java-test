package retrofit_product_test.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit_product_test.dto.Category;

public interface CategoryService {
	@GET("categories/{id}")
	Call<Category> getCategory(@Path("id") Integer id);
}
