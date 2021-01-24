package pageObjects;

import org.openqa.selenium.WebDriver;

import common.AbstractPages;
import common.PageGeneratorManager;
import pageUIs.RegisterPageUI;



public class RegisterPageObject  extends AbstractPages {
	private WebDriver driver;
    
    public RegisterPageObject(WebDriver _driver) {
    	driver = _driver;
    }

	public void inputFullName(String fullName) {
		waitToElementVisible(driver,RegisterPageUI.FULL_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FULL_NAME_TEXTBOX, fullName);
		
	}

	public void inputEmailAddress(String emailAddress) {
		waitToElementVisible(driver,RegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
		
	}

	public void inputPhoneNumber(String phoneNumber) {
		waitToElementVisible(driver,RegisterPageUI.PHONE_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PHONE_TEXTBOX, phoneNumber);
		
	}

	public void selectRoleRadioButton(String selectOption) {
		if (selectOption == "Appointed director")
		{
			waitToElementVisible(driver,RegisterPageUI.DIRECTOR_ROLE_RADIOBUTTON);
			clickToElement(driver, RegisterPageUI.DIRECTOR_ROLE_RADIOBUTTON);
		}
		else if (selectOption == "Non-director")
		{
			waitToElementVisible(driver,RegisterPageUI.NON_DIRECTOR_ROLE_RADIOBUTTON);
			clickToElement(driver, RegisterPageUI.NON_DIRECTOR_ROLE_RADIOBUTTON);
		}
				
	}

	public void selectItemInSearchTypeDropDown(String selectedItem) {
		waitToElementVisible(driver, RegisterPageUI.SEARCH_TYPE_DROPDOWN);
		selectItemInCustomDropDown(driver, RegisterPageUI.SEARCH_TYPE_DROPDOWN, RegisterPageUI.SEARCH_TYPE_DROPDOWN_LISTITEM,selectedItem);
		
	}

	public void inputPromoCode(String promoCode) {
		
		waitToElementVisible(driver,RegisterPageUI.PROMO_CODE_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PROMO_CODE_TEXTBOX, promoCode);
	}

	public void checkAgreeCheckbox() {
		waitToElementVisible(driver,RegisterPageUI.AGREE_CHECKBOX);
		clickToElement(driver, RegisterPageUI.AGREE_CHECKBOX);
		
	}

	public MobileVerificationPageObject clickContinueButton() {
		waitToElementVisible(driver,RegisterPageUI.CONTINUE_BUTTON);
		clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
		return PageGeneratorManager.getMobileVerificationPage(driver);
	}

}
