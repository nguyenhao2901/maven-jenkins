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
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;
import reportConfigs.ExtentTestManager;
import utilities.DataHelper;

public class MyAccount extends BaseTest {
	WebDriver driver;
	DataHelper data;
	HomePageObject homePage;
	LoginPageObject loginPage;
	MyAccountPageObject myAccountPage;
	String firstNameUpdate, lastNameUpdate, emailUpdate;

	@Parameters("browserName")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		data = DataHelper.getDataHelper();
		loginPage = homePage.clickToLoginLink(driver);
		loginPage.setCookie(driver, Common_01_Register_Login_Global.loggedCookies);
		loginPage.refreshCurrentPage(driver);
		sleepInSecond(2);
		loginPage.clickCloseSuccessLoginNotify();
		Assert.assertTrue(homePage.isMyAccountLinkDisplay(driver));
		firstNameUpdate = data.getFirstName();
		lastNameUpdate = data.getLastName();
		emailUpdate = data.getEmail();

	}

	@Test
	public void MyAccount_01_Update_Customer_Info(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_01_Update_Customer_Info_In_MyAccount");
		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 01 - Click to 'MyAccount' link and navigate to MyAccount page");
		myAccountPage = homePage.clickToMyAccountLink(driver);

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 02 - Click to 'Male' radio button");
		myAccountPage.clickToGenderByID("gender-female");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 03 - Update first name with value: " + firstNameUpdate);
		myAccountPage.enterToTextboxByID("FirstName", firstNameUpdate);

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 04 - Update last name with value: " + lastNameUpdate);
		myAccountPage.enterToTextboxByID("LastName", lastNameUpdate);

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 05 - Select day - month - year at 'Date of birth' field with value: " + "20/2/1990");
		myAccountPage.selectItemInDropdownByName("DateOfBirthDay", "20");
		myAccountPage.selectItemInDropdownByName("DateOfBirthMonth", "May");
		myAccountPage.selectItemInDropdownByName("DateOfBirthYear", "1990");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 06 - Update email with value: " + emailUpdate);
		myAccountPage.enterToTextboxByID("Email", emailUpdate);

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 07 - Click to 'Save' button");
		myAccountPage.clickToSaveButton();

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 08 - Verify successfully update notification is display");
		Assert.assertEquals(myAccountPage.getUpdateResultMessage(), "The customer info has been updated successfully.");

	}

	@Test
	public void MyAccount_02_Add_Address_Info() {

	}

	@Test
	public void MyAccount_03_Change_Password() {

	}

	@Test
	public void MyAccount_04_My_Product_Review() {

	}

	@AfterClass
	public void afterClass() {

	}
}
