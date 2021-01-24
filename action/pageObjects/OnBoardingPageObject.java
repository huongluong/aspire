package pageObjects;
import org.openqa.selenium.WebDriver;

import common.AbstractPages;
import common.PageGeneratorManager;
import pageUIs.OnBoardingPageUI;

public class OnBoardingPageObject extends AbstractPages{
private WebDriver driver;
    
    public OnBoardingPageObject(WebDriver _driver) {
    	driver = _driver;
    }

	public IncorporateSelectorPageObject clickToContinueButton(String string) {
		waitToElementVisible(driver,OnBoardingPageUI.CONTINUE_BUTTON,string);
		clickToElement(driver, OnBoardingPageUI.CONTINUE_BUTTON,string);
		return PageGeneratorManager.getIncorporateSelectorPage(driver);
	}
}
