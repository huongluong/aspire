package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import common.AbstractPages;
import common.PageGeneratorManager;
import pageUIs.MobileVerificationPageUI;
import pageObjects.CompletePageObject;

public class MobileVerificationPageObject  extends AbstractPages {
	private WebDriver driver;
    
    public MobileVerificationPageObject(WebDriver _driver) {
    	driver = _driver;
    }
    
	public CompletePageObject inputOTP(String OTPString) throws InterruptedException {
		waitToElementVisible(driver, MobileVerificationPageUI.OTP_RESEND_LNK);
		waitToElementVisible(driver,MobileVerificationPageUI.OTP_TEXTBOX_1);
		sendKeyBoardToElement(driver,MobileVerificationPageUI.OTP_TEXTBOX_1,Keys.NUMPAD1);
		waitToElementVisible(driver,MobileVerificationPageUI.OTP_TEXTBOX_2);
		sendKeyBoardToElement(driver,MobileVerificationPageUI.OTP_TEXTBOX_2,Keys.NUMPAD2);
		waitToElementVisible(driver,MobileVerificationPageUI.OTP_TEXTBOX_3);
		sendKeyBoardToElement(driver,MobileVerificationPageUI.OTP_TEXTBOX_3,Keys.NUMPAD3);
		waitToElementVisible(driver,MobileVerificationPageUI.OTP_TEXTBOX_4);
		sendKeyBoardToElement(driver,MobileVerificationPageUI.OTP_TEXTBOX_4,Keys.NUMPAD4);
		//waitToElementVisible(driver,MobileVerificationPageUI.OTP_TEXTBOX_3);
		//sendkeyToElementByJS(driver,MobileVerificationPageUI.OTP_TEXTBOX_3, OTPString.charAt(2)+"");
		//waitToElementVisible(driver,MobileVerificationPageUI.OTP_TEXTBOX_4);
		//sendkeyToElementByJS(driver,MobileVerificationPageUI.OTP_TEXTBOX_4, OTPString.charAt(3)+"");
		return PageGeneratorManager.getCompletePage(driver);
	}

}
