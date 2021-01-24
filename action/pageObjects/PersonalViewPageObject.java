package pageObjects;

import org.openqa.selenium.WebDriver;

import common.AbstractPages;
import common.PageGeneratorManager;
import pageUIs.PersonalViewPageUI;

public class PersonalViewPageObject extends AbstractPages {
	private WebDriver driver;
    
    public PersonalViewPageObject(WebDriver _driver) {
    	driver = _driver;
    }

	public PersonalEditPageObject clickonGetStartButton() {
		waitToElementVisible(driver,PersonalViewPageUI.GET_START_BUTTON);
		clickToElement(driver, PersonalViewPageUI.GET_START_BUTTON);
		return PageGeneratorManager.getPersonalEditPage(driver);
	} 

}
