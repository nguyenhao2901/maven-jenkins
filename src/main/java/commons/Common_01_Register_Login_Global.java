package commons;

import java.lang.reflect.Method;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import reportConfigs.ExtentTestManager;
import utilities.DataHelper;

public class Common_01_Register_Login_Global extends BaseTest {
	WebDriver driver;
	String firstName, lastName;
	public static String emailAddress, password;
	DataHelper data;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	LoginPageObject loginPage;
	public static Set<Cookie> loggedCookies;

	@Parameters("browserName")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		data = DataHelper.getDataHelper();
		firstName = data.getFirstName();
		lastName = data.getLastName();
		emailAddress = data.getEmail();
		password = data.getPassword();

//		ExtentTestManager.startTest(method.getName(), "Pre-conditon: Create user - pass to login global");
//		ExtentTestManager.getTest().log(Status.INFO, "Pre-conditon - Step 01 - Click to 'Register' link");
		registerPage = homePage.clickToRegisterLink();

//		ExtentTestManager.getTest().log(Status.INFO, "Pre-conditon - Step 02 - Enter to 'First name' textbox with value: " + firstName);
		registerPage.enterToTextboxByID("FirstName", firstName);

//		ExtentTestManager.getTest().log(Status.INFO, "Pre-conditon - Step 03 - Enter to 'Last name' textbox with value: " + lastName);
		registerPage.enterToTextboxByID("LastName", lastName);

//		ExtentTestManager.getTest().log(Status.INFO, "Pre-conditon - Step 04 - Enter to 'Email' textbox with value: " + emailAddress);
		registerPage.enterToTextboxByID("Email", emailAddress);

//		ExtentTestManager.getTest().log(Status.INFO, "Pre-conditon - Step 05 - Enter to 'Password' textbox with value: " + password);
		registerPage.enterToTextboxByID("Password", password);

//		ExtentTestManager.getTest().log(Status.INFO, "Pre-conditon - Step 06 - Enter to 'Confirm password' textbox with value: " + password);
		registerPage.enterToTextboxByID("ConfirmPassword", password);

//		ExtentTestManager.getTest().log(Status.INFO, "Pre-conditon - Step 07 - Click to 'Register' button");
		registerPage.clickToRegisterButton();

//		ExtentTestManager.getTest().log(Status.INFO, "Pre-conditon - Step 08 - Verify successful registration message is display");
		Assert.assertTrue(registerPage.isSuccessRegisterMessageDisplay());

		loginPage = registerPage.clickToLoginLink(driver);
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertTrue(loginPage.isMyAccountLinkDisplay(driver));

		loggedCookies = loginPage.getCookie(driver);

	}

	@AfterTest
	public void afterTest() {

	}

}
