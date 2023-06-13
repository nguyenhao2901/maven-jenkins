package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.MyAccountPageUI;

public class MyAccountPageObject extends BasePage {
	WebDriver driver;

	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToGenderByID(String genderID) {
		waitForElementClickable(driver, MyAccountPageUI.GENDER_RADIO_BUTTON_BY_ID, genderID);
		checkToDefaultCheckboxOrRadio(driver, MyAccountPageUI.GENDER_RADIO_BUTTON_BY_ID, genderID);

	}

	public void enterToTextboxByID(String textboxID, String textValue) {
		waitForElementVisible(driver, MyAccountPageUI.TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, MyAccountPageUI.TEXTBOX_BY_ID, textValue, textboxID);

	}

	public void selectItemInDropdownByName(String dropdownName, String textItem) {
		waitForElementClickable(driver, MyAccountPageUI.DATE_OF_BIRTH_DROPDOWN_BY_NAME, dropdownName);
		selectItemInDefaultDropdown(driver, MyAccountPageUI.DATE_OF_BIRTH_DROPDOWN_BY_NAME, textItem, dropdownName);

	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, MyAccountPageUI.SAVE_BUTTON);
		clickToElement(driver, MyAccountPageUI.SAVE_BUTTON);

	}

	public String getUpdateResultMessage() {
		waitForElementVisible(driver, MyAccountPageUI.UPDATE_RESULT_MESSAGE);
		return getTextElement(driver, MyAccountPageUI.UPDATE_RESULT_MESSAGE);
	}

}
