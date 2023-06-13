package utilities;

import java.util.Locale;

import com.github.javafaker.Faker;

public class DataHelper {
	Faker faker = new Faker(new Locale("en"));

	public static DataHelper getDataHelper() {
		return new DataHelper();
	}

	public String getFirstName() {
		return faker.address().firstName();
	}

	public String getLastName() {
		return faker.address().lastName();
	}

	public String getEmail() {
		return faker.internet().emailAddress();
	}

	public String getPassword() {
		return faker.internet().password(6, 8);
	}
}
