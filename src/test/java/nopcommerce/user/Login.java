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
import commons.Common_01_Register_Login_Global;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Global;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import reportConfigs.ExtentTestManager;
import utilities.DataHelper;

public class Login extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	String emailAddress, password;
	LoginPageObject loginPage;
	DataHelper data;

	@Parameters("browserName")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		emailAddress = Common_01_Register_Login_Global.emailAddress;
		password = Common_01_Register_Login_Global.password;
		data = DataHelper.getDataHelper();

	}

	@Test
	public void Login_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_01_Login_With_Empty_Data");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01 - Click to 'Login' link");
		loginPage = homePage.clickToLoginLink(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02 - Click to 'Login' button");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03 - Verify email error message display");
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter your email");

	}

	@Test
	public void Login_02_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_02_Login_With_Invalid_Email");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01 - Open app url and navigate to home page");
		loginPage.openPageUrl(driver, GlobalConstants.APP_URL);
		homePage = PageGeneratorManager.getHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02 - Click to 'Login' link");
		loginPage = homePage.clickToLoginLink(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03 - Enter to email textbox with value: " + "abc#@gmail@com");
		loginPage.enterToEmailTextbox("abc#@gmail@com");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04 - Click to 'Login' button");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05 - Verify email error message display");
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Wrong email");

	}

	@Test
	public void Login_03_Unregistered_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_03_Login_With_Unregistered_Email");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01 - Open app url and navigate to home page");
		loginPage.openPageUrl(driver, GlobalConstants.APP_URL);
		homePage = PageGeneratorManager.getHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02 - Click to 'Login' link");
		loginPage = homePage.clickToLoginLink(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03 - Enter to email textbox with value: " + data.getEmail() );
		loginPage.enterToEmailTextbox(data.getEmail());

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04 - Click to 'Login' button");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05 - Verify login error message display");
		Assert.assertEquals(loginPage.getLoginSummaryErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	@Test
	public void Login_04_Registered_Email_And_Password_Blank(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_04_Login_With_Registered_Email_And_Password_Blank");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01 - Open app url and navigate to home page");
		loginPage.openPageUrl(driver, GlobalConstants.APP_URL);
		homePage = PageGeneratorManager.getHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02 - Click to 'Login' link");
		loginPage = homePage.clickToLoginLink(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03 - Enter to email textbox with value: " + emailAddress);
		loginPage.enterToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04 - Click to 'Login' button");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05 - Verify login error message display");
		Assert.assertEquals(loginPage.getLoginSummaryErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Registered_Email_And_Incorrect_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_05_Login_With_Registered_Email_And_Incorrect_Password");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01 - Open app url and navigate to home page");
		loginPage.openPageUrl(driver, GlobalConstants.APP_URL);
		homePage = PageGeneratorManager.getHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02 - Click to 'Login' link");
		loginPage = homePage.clickToLoginLink(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03 - Enter to email textbox with value: " + emailAddress);
		loginPage.enterToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04 - Enter to password textbox with value: " + "11111");
		loginPage.enterToPasswordTextbox("11111");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05 - Click to 'Login' button");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 06 - Verify login error message display");
		Assert.assertEquals(loginPage.getLoginSummaryErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_06_Registered_Email_And_Correct_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_06_Login_With_Registered_Email_And_Correct_Password");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01 - Open app url and navigate to home page");
		loginPage.openPageUrl(driver, GlobalConstants.APP_URL);
		homePage = PageGeneratorManager.getHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02 - Click to 'Login' link");
		loginPage = homePage.clickToLoginLink(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03 - Enter to email textbox with value: " + emailAddress);
		loginPage.enterToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04 - Enter to password textbox with value: " + password);
		loginPage.enterToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05 - Click to 'Login' button");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 06 - Verify 'My Account' link display");
		Assert.assertTrue(loginPage.isMyAccountLinkDisplay(driver));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
