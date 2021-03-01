package retrofit_product_test.service;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import retrofit_product_test.dto.Category;
import retrofit_product_test.dto.Product;

public interface ProductService {
	@POST("products")
	Call<Product> createProduct(@Body Product createProductRequest);

	@DELETE("products/{id}")
	Call<ResponseBody> deleteProduct(@Path("id") int id);

//
	@GET("products")
	Call<Product[]> getProduct();

	@GET("products-negativ")
	Call<Product[]> getProductNegative();

	@GET("products/{id}")
	Call<Product> getProductById(@Path("id") int id);

	@PUT("products")
	Call<Product> modifyProduct(@Body Product modifyProductRequest);

}
