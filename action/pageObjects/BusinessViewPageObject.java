package pageObjects;

import org.openqa.selenium.WebDriver;

import common.AbstractPages;
import common.PageGeneratorManager;
import pageUIs.BusinessViewPageUI;


public class BusinessViewPageObject extends AbstractPages {
	private WebDriver driver;
    
    public BusinessViewPageObject(WebDriver _driver) {
    	driver = _driver;
    }

	public BusinessEditPageObject clickOnGetStartButton() {
		waitToElementVisible(driver,BusinessViewPageUI.GET_START_BUTTON);
		clickToElement(driver, BusinessViewPageUI.GET_START_BUTTON);
		return PageGeneratorManager.getBusinessEditPage(driver);
		
	}

}
