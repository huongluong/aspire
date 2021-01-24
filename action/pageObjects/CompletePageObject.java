package pageObjects;

import org.openqa.selenium.WebDriver;

import common.AbstractPages;
import common.PageGeneratorManager;
import pageUIs.CompletePageUI;


public class CompletePageObject extends AbstractPages{
	private WebDriver driver;
    
    public CompletePageObject(WebDriver _driver) {
    	driver = _driver;
    }

	public OnBoardingPageObject clickOnContinueButton() {
		waitToElementVisible(driver,CompletePageUI.CONTINUE_BUTTON);
		clickToElement(driver, CompletePageUI.CONTINUE_BUTTON);
		return PageGeneratorManager.getOnBoardingPage(driver);
	}
}
