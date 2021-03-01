package retrofit_product_test.base.enums;

import lombok.Getter;

public enum CategoryType {
	FOOD(1, "Food"), ELECTRONICS(2, "Electronics"), DOOD(3, "Dood");

	@Getter
	private final Integer id;
	@Getter
	private final String title;

	CategoryType(int id, String title) {
		this.id = id;
		this.title = title;
	}
}
