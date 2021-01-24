package common;

import org.openqa.selenium.WebDriver;

import pageObjects.BusinessViewPageObject;
import pageObjects.CompletePageObject;
import pageObjects.EmailVerificationPageObject;
import pageObjects.IncorporateSelectorPageObject;
import pageObjects.MobileVerificationPageObject;
import pageObjects.OnBoardingPageObject;
import pageObjects.PersonalEditPageObject;
import pageObjects.PersonalViewPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.RegisterSelectMethodPageObject;
import pageObjects.LoginPageObject;
import pageObjects.BusinessEditPageObject;

public class PageGeneratorManager {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static EmailVerificationPageObject getEmailVerificationPage(WebDriver driver){
		return new EmailVerificationPageObject(driver);
	}
	public static MobileVerificationPageObject getMobileVerificationPage(WebDriver driver) {
		return new MobileVerificationPageObject(driver);
	}
	public static PersonalEditPageObject getPersonalEditPage(WebDriver driver) {
		return new PersonalEditPageObject(driver);
	}
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	public static CompletePageObject getCompletePage(WebDriver driver) {
		return new CompletePageObject(driver);
	}
	public static OnBoardingPageObject getOnBoardingPage(WebDriver driver) {
		return new OnBoardingPageObject(driver);
	}
	public static IncorporateSelectorPageObject getIncorporateSelectorPage(WebDriver driver) {
		return new IncorporateSelectorPageObject(driver);
	}
	public static RegisterSelectMethodPageObject getRegisterSelectMethodPage(WebDriver driver) {
		return new RegisterSelectMethodPageObject(driver);
	}
	public static PersonalViewPageObject getPersonalViewPage(WebDriver driver) {
		return new PersonalViewPageObject(driver);
	}	
	public static BusinessViewPageObject getBusinessViewPage(WebDriver driver) {
		return new BusinessViewPageObject(driver);
	}
	public static BusinessEditPageObject getBusinessEditPage(WebDriver driver) {
		return new BusinessEditPageObject(driver);
	}
}
