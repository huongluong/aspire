package pageObjects;

import org.openqa.selenium.WebDriver;

import common.AbstractPages;
import common.PageGeneratorManager;
import pageUIs.RegisterSelectMethodPageUI;

public class RegisterSelectMethodPageObject extends AbstractPages {
	private WebDriver driver;
    
    public RegisterSelectMethodPageObject(WebDriver _driver) {
    	driver = _driver;
    }

	public PersonalViewPageObject clickOnGetStartButton(String string) {
		
		waitToElementVisible(driver,RegisterSelectMethodPageUI.GET_START_BUTTON,string);
		clickToElement(driver, RegisterSelectMethodPageUI.GET_START_BUTTON,string);
		return PageGeneratorManager.getPersonalViewPage(driver);
	}

}
