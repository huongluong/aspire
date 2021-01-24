package register;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.AbstractTest;
import common.GlobalConstants;
import common.PageGeneratorManager;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.RegisterSelectMethodPageObject;
import pageObjects.BusinessViewPageObject;
import pageObjects.CompletePageObject;
import pageObjects.EmailVerificationPageObject;
import pageObjects.IncorporateSelectorPageObject;
import pageObjects.MobileVerificationPageObject;
import pageObjects.OnBoardingPageObject;
import pageObjects.PersonalEditPageObject;
import pageObjects.PersonalViewPageObject;
import pageObjects.BusinessEditPageObject;

public class registerTest extends AbstractTest{
	private WebDriver driver;
	
	//private String registerSuccessMsg;
    private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private MobileVerificationPageObject mobileOTPPage;
	private CompletePageObject completePage;
	private OnBoardingPageObject onBoardingPage;
	private IncorporateSelectorPageObject inCorporateSelectorPage;
	private RegisterSelectMethodPageObject registerSelectMethodPage;
	private PersonalViewPageObject personViewPage;
	private PersonalEditPageObject personEditPage;
	private EmailVerificationPageObject emailVerifyPage;
	private BusinessViewPageObject businessViewPage;
	private BusinessEditPageObject businessEditPage;
    String phone_number = "" + randomNumber(9);
    String email_address = "email" + randomNumber(4) + "@gmail.com";
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "./browserDriver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//phóng to trình duyệt
		driver.manage().window().maximize();
		//mở ra trang web (AUT: Application under test)
		driver.get(GlobalConstants.TEST_URL);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
	}
  @Test
  public void Register() throws InterruptedException {
	    registerPage = loginPage.clickToRegisterLink();
	    registerPage.inputFullName("Huong Luong");
	    registerPage.inputEmailAddress(email_address);
	    Thread.sleep(2000);
	    registerPage.inputPhoneNumber(phone_number);
	    Thread.sleep(2000);
	    registerPage.selectRoleRadioButton("Appointed director");
	    registerPage.selectItemInSearchTypeDropDown("Google");
	    registerPage.inputPromoCode("abc123");
	    registerPage.checkAgreeCheckbox();
	    mobileOTPPage = registerPage.clickContinueButton();
	    Thread.sleep(15000);
	    completePage = mobileOTPPage.inputOTP("1234");
	    onBoardingPage = completePage.clickOnContinueButton();
	    inCorporateSelectorPage = onBoardingPage.clickToContinueButton("I don’t have a business yet");
//or    inCorporateSelectorPage = onBoardingPage.clickToContinueButton("Yes, my business");
	    registerSelectMethodPage = inCorporateSelectorPage.clickOnAlreadyLink();
	    personViewPage = registerSelectMethodPage.clickOnGetStartButton("Standard Registration");
	    personEditPage = personViewPage.clickonGetStartButton();
	    Thread.sleep(5000);
	    personEditPage.inputBirthday("Feb 21, 1984"); //Error , can not input date of birth by JS
	    personEditPage.selectItemInNationalityDropDown("Afghanistan");
	    personEditPage.selectItemInGenderDropDown("Female");
	    personEditPage.selectItemInInterestedInDropDown("Debit Account");
	    Thread.sleep(20000); //wait for input birthday by manual
	    emailVerifyPage = personEditPage.clickOnSubmitButton();
	    Thread.sleep(15000);
	    businessViewPage = emailVerifyPage.inputOTP("1234");
	    businessEditPage = businessViewPage.clickOnGetStartButton();
	    businessEditPage.inputBusinessName("TNHH Datalogic Viet Nam");
	    String UENNumber = randomNumber(8) + randomChar() + "";
	    businessEditPage.inputUENNumber(UENNumber);
	    businessEditPage.selectRegistrationTypeDropDown("Limited liability partnership");
	    businessEditPage.selectIndustryDropDown("Retail Goods");
	    businessEditPage.selectSubIndustryDropDown("Chemicals");
	    businessEditPage.clickOnSubmitButton();
	    
  }
}
