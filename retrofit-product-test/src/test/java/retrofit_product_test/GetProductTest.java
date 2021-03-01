package retrofit_product_test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import retrofit2.Response;
import retrofit_product_test.dto.Product;
import retrofit_product_test.service.ProductService;
import retrofit_product_test.util.RetrofitUtils;

public class GetProductTest {
	Random random = new Random();
	static ProductService productService;
	private static int countProducts;
	Product product;
	private static int expectedId;
	private static String expectedTitle;
	private static int expectedPrice;
	private static String expectedCategoryTitle;

	@SneakyThrows
	@BeforeAll
	static void beforeAll() {
		productService = RetrofitUtils.getRetrofit().create(ProductService.class);
		System.out.println("----------------------------------------------------");
	}

	@DisplayName("Получить продукты позитивный тест")
	@SneakyThrows
	@BeforeEach
	@Test
	void getProductsPositiveTest() {
		Response<Product[]> response = productService.getProduct().execute();
		assertThat(response.code(), is(200));
		assertThat(response.body().length, not(equalTo(0)));
		countProducts = response.body().length;
		int randomProduct = random.nextInt((response.body().length));
		System.out.println("-----------------------------------------------------");
		System.out.println(countProducts);
		System.out.println(randomProduct);
		product = response.body()[randomProduct];
		expectedId = product.getId();
		expectedTitle = product.getTitle();
		expectedPrice = product.getPrice();
		expectedCategoryTitle = product.getCategoryTitle();
		System.out.println("----------------------------------------------------------");
		System.out.println(expectedId);
		System.out.println(expectedTitle);
		System.out.println(expectedPrice);
		System.out.println(expectedCategoryTitle);
		System.out.println("-----------------------------------------------------------");
	}

	@DisplayName("Получить продукт с случайным Id - позитивный тест")
	@SneakyThrows
	@Test
	void getProductsById() {
		Response<Product> response = productService.getProductById(expectedId).execute();
//		Response<Product> response = productService.getProductById((response.body().length)).execute();
//		assertThat("Id doesn't match", response.body().getId(), equalTo(expectedId));
		assertThat(response.code(), is(200));
		System.out.println(response);
	}

	@DisplayName("Получить продукты - негативный тест")
	@SneakyThrows
	@Test
	void getProductsNegativeTest() {
		Response<Product[]> response = productService.getProductNegative().execute();
		assertThat(response.code(), is(404));
	}

	@DisplayName("Получить продукты c не коректным id - негативный тест")
	@SneakyThrows
	@Test
	public void getProductWithNotCorrectIdNegativeTest() {
		Response<Product> response = productService.getProductById(0).execute();
		assertThat(response.code(), is(404));
	}
//	ok
}
