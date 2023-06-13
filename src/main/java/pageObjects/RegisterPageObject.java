package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);

	}

	public String getTextboxErrorMessageByID(String id) {
		waitForElementInVisible(driver, RegisterPageUI.TEXTBOX_ERROR_MESSAGE_BY_ID, id);
		return getTextElement(driver, RegisterPageUI.TEXTBOX_ERROR_MESSAGE_BY_ID, id);
	}

	public void enterToTextboxByID(String id, String textValue) {
		waitForElementVisible(driver, RegisterPageUI.TEXTBOX_BY_ID, id);
		sendkeyToElement(driver, RegisterPageUI.TEXTBOX_BY_ID, textValue, id);
	}

	public boolean isSuccessRegisterMessageDisplay() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getExistEmailMessage() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_EXIST_ERROR_MESSAGE);
		return getTextElement(driver, RegisterPageUI.EMAIL_EXIST_ERROR_MESSAGE);
	}
}
