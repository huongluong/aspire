package pageObjects;

import org.openqa.selenium.WebDriver;

import common.AbstractPages;
import common.PageGeneratorManager;

import pageUIs.IncorporateSelectorPageUI;

public class IncorporateSelectorPageObject extends AbstractPages{
private WebDriver driver;
    
    public IncorporateSelectorPageObject(WebDriver _driver) {
    	driver = _driver;
    }

	public RegisterSelectMethodPageObject clickOnAlreadyLink() {
		waitToElementVisible(driver,IncorporateSelectorPageUI.ALREADY_LINK);
		clickToElement(driver, IncorporateSelectorPageUI.ALREADY_LINK);
		return PageGeneratorManager.getRegisterSelectMethodPage(driver);
		
	}



}
