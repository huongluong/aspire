package pageObjects;

import org.openqa.selenium.WebDriver;

import common.AbstractPages;
import common.PageGeneratorManager;
import pageUIs.PersonalEditPageUI;


public class PersonalEditPageObject  extends AbstractPages {
	private WebDriver driver;
    
    public PersonalEditPageObject(WebDriver _driver) {
    	driver = _driver;
    }

	public void inputBirthday(String string) {
		waitToElementVisible(driver,PersonalEditPageUI.BIRTHDAY_TEXTBOX);
		sendkeyToElementByJS(driver, PersonalEditPageUI.BIRTHDAY_TEXTBOX, string);
		
	}
	public void selectItemInNationalityDropDown(String string) {
		waitToElementVisible(driver, PersonalEditPageUI.NATIONALITY_DROPDOWN_ICON);
		selectItemInCustomDropDown(driver, PersonalEditPageUI.NATIONALITY_DROPDOWN_ICON, PersonalEditPageUI.NATIONALITY_LISTITEM,string);
		
	}
	public void selectItemInGenderDropDown(String string) {
		waitToElementVisible(driver, PersonalEditPageUI.GENDER_DROPDOWN_ICON);
		selectItemInCustomDropDown(driver, PersonalEditPageUI.GENDER_DROPDOWN_ICON, PersonalEditPageUI.GENDER_LISTITEM,string);
		
	}

	public void selectItemInInterestedInDropDown(String string) {
		waitToElementVisible(driver, PersonalEditPageUI.INTERESTED_IN_DROPDOWN_ICON);
		selectItemInCustomDropDown(driver, PersonalEditPageUI.INTERESTED_IN_DROPDOWN_ICON, PersonalEditPageUI.INTERESTED_IN_LISTITEM,string);
		
	}

	public EmailVerificationPageObject clickOnSubmitButton() {
		waitToElementVisible(driver, PersonalEditPageUI.SUBMIT_BUTTON);
		clickToElement(driver, PersonalEditPageUI.SUBMIT_BUTTON);
	    return PageGeneratorManager.getEmailVerificationPage(driver);
		
	}

	
}
