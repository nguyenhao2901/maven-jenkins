package nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;
import reportConfigs.ExtentTestManager;
import utilities.DataHelper;

public class Register extends BaseTest {
	WebDriver driver;
	String firstName, lastName, emailAddress, email, password, invalidEmail;
	DataHelper data;
	HomePageObject homePage;
	RegisterPageObject registerPage;

	@Parameters("browserName")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		data = DataHelper.getDataHelper();
		firstName = data.getFirstName();
		lastName = data.getLastName();
		emailAddress = data.getEmail();
		email = data.getEmail();
		password = data.getPassword();
		invalidEmail = "abc#@gmail@net";

	}

	@Test
	public void Register_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_01_Register_With_Empty_Data");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01 - Open application url and navigate to home page");
		homePage = PageGeneratorManager.getHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02 - Click to 'Register' link");
		registerPage = homePage.clickToRegisterLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03 - Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04 - Verify error message at 'First name' textbox is displayed");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("FirstName-error"), "First name is required.");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05 - Verify error message at 'Last name' textbox is displayed");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("LastName-error"), "Last name is required.");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06 - Verify error message at 'Email' textbox is displayed");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("Email-error"), "Email is required.");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07 - Verify error message at 'Password' textbox is displayed");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("Password-error"), "Password is required.");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08 - Verify error message at 'Confirm password' textbox is displayed");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("ConfirmPassword-error"), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_02_Register_With_Invalid_Email");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01 - Open application url and navigate to home page");
		registerPage.openPageUrl(driver, GlobalConstants.APP_URL);
		homePage = PageGeneratorManager.getHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02 - Click to 'Register' link");
		registerPage = homePage.clickToRegisterLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03 - Enter to 'First name' textbox with value: " + firstName);
		registerPage.enterToTextboxByID("FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04 - Enter to 'Last name' textbox with value: " + lastName);
		registerPage.enterToTextboxByID("LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05 - Enter to 'Email' textbox with value: " + invalidEmail);
		registerPage.enterToTextboxByID("Email", invalidEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06 - Enter to 'Password' textbox with value: " + password);
		registerPage.enterToTextboxByID("Password", password);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07 - Enter to 'Confirm password' textbox with value: " + password);
		registerPage.enterToTextboxByID("ConfirmPassword", password);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08 - Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09 - Verify email error message is displayed");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("Email-error"), "Wrong email");

	}

	@Test
	public void Register_03_Success(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_03_Register_Success_With_Correct_Data");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01 - Open application url and navigate to home page");
		registerPage.openPageUrl(driver, GlobalConstants.APP_URL);
		homePage = PageGeneratorManager.getHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02 - Click to 'Register' link");
		registerPage = homePage.clickToRegisterLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03 - Enter to 'First name' textbox with value: " + firstName);
		registerPage.enterToTextboxByID("FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04 - Enter to 'Last name' textbox with value: " + lastName);
		registerPage.enterToTextboxByID("LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05 - Enter to 'Email' textbox with value: " + emailAddress);
		registerPage.enterToTextboxByID("Email", emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06 - Enter to 'Password' textbox with value: " + password);
		registerPage.enterToTextboxByID("Password", password);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07 - Enter to 'Confirm password' textbox with value: " + password);
		registerPage.enterToTextboxByID("ConfirmPassword", password);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08 - Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09 - Verify successful registration message is display");
		Assert.assertTrue(registerPage.isSuccessRegisterMessageDisplay());
	}

	@Test
	public void Register_04_Exist_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_04_Register_With_Exist_Email");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01 - Open application url and navigate to home page");
		registerPage.openPageUrl(driver, GlobalConstants.APP_URL);
		homePage = PageGeneratorManager.getHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02 - Click to 'Register' link");
		registerPage = homePage.clickToRegisterLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03 - Enter to 'First name' textbox with value: " + firstName);
		registerPage.enterToTextboxByID("FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04 - Enter to 'Last name' textbox with value: " + lastName);
		registerPage.enterToTextboxByID("LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05 - Enter to 'Email' textbox with value: " + emailAddress);
		registerPage.enterToTextboxByID("Email", emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06 - Enter to 'Password' textbox with value: " + password);
		registerPage.enterToTextboxByID("Password", password);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07 - Enter to 'Confirm password' textbox with value: " + password);
		registerPage.enterToTextboxByID("ConfirmPassword", password);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08 - Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09 - Verify 'The specified email already exists' notification is display");
		Assert.assertEquals(registerPage.getExistEmailMessage(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Than_6_Chars(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_05_Register_With_Password_Less_Than_6_Chars");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01 - Open application url and navigate to home page");
		registerPage.openPageUrl(driver, GlobalConstants.APP_URL);
		homePage = PageGeneratorManager.getHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02 - Click to 'Register' link");
		registerPage = homePage.clickToRegisterLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03 - Enter to 'First name' textbox with value: " + firstName);
		registerPage.enterToTextboxByID("FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04 - Enter to 'Last name' textbox with value: " + lastName);
		registerPage.enterToTextboxByID("LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05 - Enter to 'Email' textbox with value: " + email);
		registerPage.enterToTextboxByID("Email", email);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06 - Enter to 'Password' textbox with value: " + "123");
		registerPage.enterToTextboxByID("Password", "123");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07 - Enter to 'Confirm password' textbox with value: " + "123");
		registerPage.enterToTextboxByID("ConfirmPassword", "123");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08 - Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09 - Verify password error message is display");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("Password-error"),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_06_Register_With_Invalid_Confirm_Password");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01 - Open application url and navigate to home page");
		registerPage.openPageUrl(driver, GlobalConstants.APP_URL);
		homePage = PageGeneratorManager.getHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02 - Click to 'Register' link");
		registerPage = homePage.clickToRegisterLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03 - Enter to 'First name' textbox with value: " + firstName);
		registerPage.enterToTextboxByID("FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04 - Enter to 'Last name' textbox with value: " + lastName);
		registerPage.enterToTextboxByID("LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05 - Enter to 'Email' textbox with value: " + email);
		registerPage.enterToTextboxByID("Email", email);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06 - Enter to 'Password' textbox with value: " + "123456");
		registerPage.enterToTextboxByID("Password", "123456");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07 - Enter to 'Confirm password' textbox with value: " + "123123");
		registerPage.enterToTextboxByID("ConfirmPassword", "123123");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08 - Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09 - Verify confirm password error message is display");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("ConfirmPassword-error"), "The password and confirmation password do not match.");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
