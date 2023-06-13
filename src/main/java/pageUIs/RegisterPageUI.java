package pageUIs;

public class RegisterPageUI {
	public static final String TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String TEXTBOX_ERROR_MESSAGE_BY_ID = "xpath=//span[@id='%s']";
	public static final String REGISTER_BUTTON = "css=button#register-button";
	public static final String REGISTER_SUCCESS_MESSAGE = "xpath=//div[@class='result' and text()='Your registration completed']";
	public static final String EMAIL_EXIST_ERROR_MESSAGE = "css=div.validation-summary-errors li ";
}
