package retrofit_product_test;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit_product_test.base.enums.CategoryType;
import retrofit_product_test.dto.Product;
import retrofit_product_test.service.ProductService;
import retrofit_product_test.util.RetrofitUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateProductTests {
	Integer productId;
	Faker faker = new Faker();
	static ProductService productService;
	Product product;
	List<Product> products = new ArrayList<>();

	@SneakyThrows
	@BeforeAll
	static void beforeAll() {
		productService = RetrofitUtils.getRetrofit().create(ProductService.class);
	}

	@DisplayName("Создать новый продукт позитивный тест")
	@SneakyThrows
	@Test
	void createNewProductTest() {
		product = new Product().withCategoryTitle(CategoryType.FOOD.getTitle())
				.withPrice((int) (Math.random() * 1000 + 1)).withTitle(faker.food().ingredient());
		retrofit2.Response<Product> response = productService.createProduct(product).execute();
		productId = response.body().getId();
		System.out.println("-------------------------------------------------");
		System.out.println(response.body().getId());
		System.out.println(response.body().getTitle());
		System.out.println(response.body().getPrice());
		System.out.println(response.body().getCategoryTitle());
		System.out.println("-------------------------------------------------");
		assertThat(response.isSuccessful()).isTrue();
		assertThat(response.code()).isEqualTo(201);
	}

	@DisplayName("Создать новый продукт негативный тест")
	@SneakyThrows
	@Test
	void createNewProductNegativeTest() {
		product = new Product().withCategoryTitle("").withPrice(0).withTitle("");
		retrofit2.Response<Product> response = productService.createProduct(product).execute();
		assertThat(response.code()).isEqualTo(500);
	}

	@AfterEach
	void tearDown() {
		if (productId != null)
			try {
				retrofit2.Response<ResponseBody> response = productService.deleteProduct(productId).execute();
				assertThat(response.isSuccessful()).isTrue();
				System.out.println(productId);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
//	ok
}
