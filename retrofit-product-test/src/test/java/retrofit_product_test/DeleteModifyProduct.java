package retrofit_product_test;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit_product_test.base.enums.CategoryType;
import retrofit_product_test.dto.Product;
import retrofit_product_test.service.ProductService;
import retrofit_product_test.util.RetrofitUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static retrofit_product_test.base.enums.CategoryType.FOOD;

public class DeleteModifyProduct {
	static ProductService productService;
	static Product product;
	static Faker faker = new Faker();
	private static int id;
	private static String title;
	private static int price;
	private static String categoryTitle;
	Product modifyProduct;
	Product putProduct;

	@SneakyThrows
	@BeforeAll
	static void beforeAll() {
		productService = RetrofitUtils.getRetrofit().create(ProductService.class);

		product = new Product().withTitle(faker.food().ingredient()).withCategoryTitle(FOOD.getTitle())
				.withPrice((int) (Math.random() * 1000 + 1));
		Response<Product> response = productService.createProduct(product).execute();
		id = response.body().getId();
		title = response.body().getTitle();
		price = response.body().getPrice();
		categoryTitle = response.body().getCategoryTitle();
		System.out.println("-------------------------------------------------");
		System.out.println("Создали продукт");
		System.out.println(id);
		System.out.println(title);
		System.out.println(price);
		System.out.println(categoryTitle);
	}

	@DisplayName("Удаление продукта с не существующим Id продукта негативный тест")
	@SneakyThrows
	@Test
	void deleteProductNotCorrectId() {
		Response<ResponseBody> response = productService.deleteProduct(-id).execute();
		assertThat(response.code(), is(500));
	}

	@DisplayName("Получить по Id продукта позитивный тест")
	@SneakyThrows
	@Test
	void getProductTest() {
		Response<Product> response = productService.getProductById(id).execute();
//		assertThat("Id doesn't match", response.body().getId(), equalTo(expectedId));
		assertThat(response.code(), is(200));
		System.out.println("---------------------------------------------------------");
		System.out.println(response);
		System.out.println(id);
	}

	@DisplayName("Изменить продукт позитивный тест")
	@SneakyThrows
	@Test
	void modifyProductTest() {
		putProduct = new Product().withId(id).withTitle(faker.food().ingredient())
				.withCategoryTitle(CategoryType.FOOD.getTitle()).withPrice((int) (Math.random() * 10000 + 1));
		Response<Product> modifyResponse = productService.modifyProduct(putProduct).execute();
		assertThat(modifyResponse.isSuccessful(), CoreMatchers.is(true));
		System.out.println("---------------------------------------------------------");
		System.out.println("Изменили продукт");
		System.out.println(modifyResponse.body().getId());
		System.out.println(modifyResponse.body().getTitle());
		System.out.println(modifyResponse.body().getPrice());
		System.out.println(modifyResponse.body().getCategoryTitle());
	}

	@DisplayName("Удаление продукта с существующим Id позитивный тест")
	@SneakyThrows
	@Test
	void deleteProductByIdTest() {
		Response<ResponseBody> response = productService.deleteProduct(id).execute();
		assertThat(response.isSuccessful(), is(true));
		System.out.println("---------------------------------------------------------");
		System.out.println(response);
		System.out.println("Удалили продукт");
	}
//	ok
}
