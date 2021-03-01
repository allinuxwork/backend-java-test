package retrofit_product_test;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit_product_test.dto.Product;
import retrofit_product_test.service.ProductService;
import retrofit_product_test.util.RetrofitUtils;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Это вспомогатльный скрипт для очиски и добвление продуктов, для его работы
 * необходимо раскомментировать необходимые блоки кода.
 *
 */
public class AddClearProduct {
	static ProductService productService;
	List<Product> products = new ArrayList<>();
	private static int id;
	private static int idn;

	@SneakyThrows
	@BeforeAll
	static void beforeAll() {
//		productService = RetrofitUtils.getRetrofit().create(ProductService.class);
//		Response<Product[]> response = productService.getProduct().execute();
//		idn = response.body()[0].getId();
//		id = response.body()[response.body().length].getId();
//		System.out.println(idn);
//		System.out.println(id);
	}

	@DisplayName("Удаление продукта с существующими Id продукта")
	@SneakyThrows
	@Test
	void deleteProductByIdTest() {
//		System.out.println("---------------------------------------------------");
//		for (int i = idn; i <= id; i++) {
//			try {
//				Response<ResponseBody> response = productService.deleteProduct(i).execute();
//				System.out.println("---------------------------------------------------------");
//				System.out.println(response);
//				System.out.println("Удалили продукт");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}

	@DisplayName("Добавление начальных продуктов продуктов")
	@SneakyThrows
	@Test
	void addProductTest() {
//		System.out.println("-------------------------------------------------");
//		products.add(new Product(null, "Milk", 95, "Food"));
//		products.add(new Product(null, "Bread", 25, "Food"));
//		products.add(new Product(null, "Cheese", 360, "Food"));
//		products.add(new Product(null, "Samsung Watch X1000", 20000, "Electronic"));
//		products.add(new Product(null, "LG TV 1", 50000, "Electronic"));
//		products.add(new Product(null, "Basil Basmati rice", 7187, "Food"));
//		products.add(new Product(null, "Blue Cheese", 2413, "Food"));
//		products.forEach(product -> {
//			try {
//				Response<Product> response = productService.createProduct(product).execute();
//				assertThat(response.body()).isNotNull();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		});
	}
}
