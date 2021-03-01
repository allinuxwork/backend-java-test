package retrofit_product_test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit_product_test.dto.Category;
import retrofit_product_test.service.CategoryService;
import retrofit_product_test.util.RetrofitUtils;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static retrofit_product_test.base.enums.CategoryType.FOOD;
import static retrofit_product_test.base.enums.CategoryType.DOOD;

public class CategoryTests {
	static CategoryService categoryService;

	@BeforeAll
	static void beforeAll() throws IOException {
		categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
	}

	@DisplayName("Получение существующий катергории")
	@Test
	void getFoodCategoryPositiveTest() throws IOException {
		Response<Category> response = categoryService.getCategory(FOOD.getId()).execute();
		assertThat(response.code(), is(200));
		assertThat(response.isSuccessful()).isTrue();
		assertThat(response.body().getId()).as("Id is not equal to 1!").isEqualTo(1);
		assertThat(response.body().getTitle()).isEqualTo(FOOD.getTitle());
		System.out.println("-------------------------------------------------");
		System.out.println(response.body().getId());
		System.out.println(response.body().getTitle());
	}

	@DisplayName("Получение не существующий катергории")
	@Test
	void getDoodCategoryNegativeTest() throws IOException {
		Response<Category> response = categoryService.getCategory(DOOD.getId()).execute();
		assertThat(response.isSuccessful()).isFalse();
	}
//ok
}
