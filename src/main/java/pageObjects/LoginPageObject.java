package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);

	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);

	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getTextElement(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getLoginSummaryErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_SUMMARY_ERROR_MESSAGE);
		return getTextElement(driver, LoginPageUI.LOGIN_SUMMARY_ERROR_MESSAGE);
	}

	public void clickCloseSuccessLoginNotify() {
		waitForElementClickable(driver, LoginPageUI.ICON_CLOSE_SUCCESS_LOGIN_NOTIFY);
		clickToElementByJS(driver, LoginPageUI.ICON_CLOSE_SUCCESS_LOGIN_NOTIFY);
		
	}

}
