package pageObjects;

import org.openqa.selenium.WebDriver;

import common.AbstractPages;
import common.PageGeneratorManager;
import pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPages {
	  private WebDriver driver;
	    
	    public LoginPageObject(WebDriver _driver) {
	    	driver = _driver;
	    }

		public RegisterPageObject clickToRegisterLink() {
			waitToElementClickable(driver, LoginPageUI.REGISTER_LNK);
			clickToElement(driver, LoginPageUI.REGISTER_LNK);
			return PageGeneratorManager.getRegisterPage(driver);

		}
	    
}
