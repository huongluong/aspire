package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import common.AbstractPages;
import common.PageGeneratorManager;
import pageUIs.EmailVerficationPageUI;


public class EmailVerificationPageObject extends AbstractPages {
	private WebDriver driver;
    
    public EmailVerificationPageObject(WebDriver _driver) {
    	driver = _driver;
    }
    public BusinessViewPageObject inputOTP(String OTPString) throws InterruptedException {
	    waitToElementVisible(driver, EmailVerficationPageUI.OTP_RESEND_LNK);
		waitToElementVisible(driver,EmailVerficationPageUI.OTP_TEXTBOX_1);
		sendKeyBoardToElement(driver,EmailVerficationPageUI.OTP_TEXTBOX_1,Keys.NUMPAD1);
		waitToElementVisible(driver,EmailVerficationPageUI.OTP_TEXTBOX_2);
		sendKeyBoardToElement(driver,EmailVerficationPageUI.OTP_TEXTBOX_2,Keys.NUMPAD2);
		waitToElementVisible(driver,EmailVerficationPageUI.OTP_TEXTBOX_3);
		sendKeyBoardToElement(driver,EmailVerficationPageUI.OTP_TEXTBOX_3,Keys.NUMPAD3);
		waitToElementVisible(driver,EmailVerficationPageUI.OTP_TEXTBOX_4);
		sendKeyBoardToElement(driver,EmailVerficationPageUI.OTP_TEXTBOX_4,Keys.NUMPAD4);
		return PageGeneratorManager.getBusinessViewPage(driver);
    }
}
