package pageObjects;

import org.openqa.selenium.WebDriver;

import common.AbstractPages;
import pageUIs.BusinessEditPageUI;


public class BusinessEditPageObject extends AbstractPages {
	private WebDriver driver;
    
    public BusinessEditPageObject(WebDriver _driver) {
    	driver = _driver;
    }

	public void inputBusinessName(String string) {
		waitToElementVisible(driver,BusinessEditPageUI.BUSSINESS_NAME_TEXTBOX);
		sendkeyToElementByJS(driver, BusinessEditPageUI.BUSSINESS_NAME_TEXTBOX, string);
		
	}

	public void selectRegistrationTypeDropDown(String string) {
		waitToElementVisible(driver, BusinessEditPageUI.REGISTRATION_TYPE_DROPDOWN_ICON);
		selectItemInCustomDropDown(driver, BusinessEditPageUI.REGISTRATION_TYPE_DROPDOWN_ICON, BusinessEditPageUI.REGISTRATION_TYPE_LISTITEM,string);
		
	}

	public void selectIndustryDropDown(String string) {
		waitToElementVisible(driver, BusinessEditPageUI.INDUSTRY_DROPDOWN_ICON);
		selectItemInCustomDropDown(driver, BusinessEditPageUI.INDUSTRY_DROPDOWN_ICON, BusinessEditPageUI.INDUSTRY_LISTITEM,string);

		
	}

	public void selectSubIndustryDropDown(String string) {
		waitToElementVisible(driver, BusinessEditPageUI.SUB_INDUSTRY_DROPDOWN_ICON);
		selectItemInCustomDropDown(driver, BusinessEditPageUI.SUB_INDUSTRY_DROPDOWN_ICON, BusinessEditPageUI.SUB_INDUSTRY_LISTITEM,string);

		
	}

	public void clickOnSubmitButton() {
		waitToElementVisible(driver,BusinessEditPageUI.SUBMIT_BUTTON);
		clickToElement(driver, BusinessEditPageUI.SUBMIT_BUTTON);
		
		
	}

	public void inputUENNumber(String uENNumber) {
		waitToElementVisible(driver,BusinessEditPageUI.UEN_NUMBER_TEXTBOX);
		sendkeyToElementByJS(driver, BusinessEditPageUI.UEN_NUMBER_TEXTBOX, uENNumber);
		
	}

}
