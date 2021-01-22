package commons;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetElementText;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageFactory.nopCommerce.PageGeneratorManager;
import pageObject.bankGuru.NewCustomerPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageUIs.bankGuru.AbstractBankGuruPageUI;
import pageUIs.nopCommerce.AbstractNopCommercePageUI;

public class AbstractPages {
	// Function dùng chung dành riêng cho package (layer) page Object
	// Bao gồm những function được wrapper lại từ selenium lib
	private Actions action;
	private WebElement element;
	private Select dropDown;
	private By byXpath;
	private WebDriverWait waitExplicit;
	private Date date;
	private JavascriptExecutor js;
	

	//Mở ra 1 url truyền tham số từ bên ngoài
	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public void back(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}	
	public void sendKeyToAlert(WebDriver driver,String value){
		driver.switchTo().alert().sendKeys(value);
	}
	
	public WebElement findElementByXpath(WebDriver driver,String locator) {
		return driver.findElement(byXpathLocator(locator));
	}
	public WebElement findElementByXpath(WebDriver driver,String locator, String ...values) {
		locator = String.format(locator, (Object[]) values);
		return driver.findElement(byXpathLocator(locator));
	}
	public List<WebElement> findElementsByXpath(WebDriver driver,String locator) {
		return driver.findElements(byXpathLocator(locator));
	}
	public List<WebElement> findElementsByXpath(WebDriver driver,String locator, String ...values) {
		locator = String.format(locator, (Object[]) values);
		return driver.findElements(byXpathLocator(locator));
	}
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public By byXpathLocator(String locator) {
		return By.xpath(locator);
	}
	public By byXpathLocator(String locator, String...values) {
		locator = String.format(locator, (Object[]) values);
		return By.xpath(locator);
	}
	public void clickToElement(WebDriver driver,String locator) {
		findElementByXpath(driver,locator).click();
	}
	public void clickToElement(WebDriver driver,String locator, String ...values) {
		findElementByXpath(driver, locator, values).click();
		//findElementByXpath(driver,locator).click();
	}
	public void sendkeyToElement(WebDriver driver,String locator, String value) {
		findElementByXpath(driver,locator).clear();
		findElementByXpath(driver,locator).sendKeys(value);
	}
	public void sendkeyToElement(WebDriver driver,String locator, String textInput, String ...values) {
		findElementByXpath(driver,locator, values).clear();
		findElementByXpath(driver,locator, values).sendKeys(textInput);
	}
	//Dropdown : select HTML dropdown/ custom dropdown
	public void selectItemInHtmlDropdown(WebDriver driver,String locator, String selectedText) {
		dropDown = new Select(findElementByXpath(driver,locator));
		dropDown.selectByVisibleText(selectedText);
	}
	public void selectItemInHtmlDropdown(WebDriver driver,String locator, String selectedText,String ...values) {
		dropDown = new Select(findElementByXpath(driver,locator,values));
		dropDown.selectByVisibleText(selectedText);
	}
	public String getSelectItemInHtmlDropdown(WebDriver driver,String locator) {
		dropDown = new Select(findElementByXpath(driver,locator));
		return dropDown.getFirstSelectedOption().getText();
	}
	
	public void selectItemInCustomDropDown(WebDriver driver,String xpathDropdown, String xpathAllItem, String selectItem) {
		clickToElement(driver,xpathDropdown);
		
		//2. Khai báo list webelement để chứa tất cả các item
		List <WebElement> allItems = findElementsByXpath(driver,xpathAllItem);
		
		//3. Wait cho tất cả item được xuất hiện ở trong DOM (không bắt buộc hiển thị ở UI)
		waitExplicit = new WebDriverWait(driver, 10);
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathAllItem)));
				
		//4. Get text từng item trong list và so sánh với item mình chọn
		for(WebElement item:allItems){
			System.out.println(item.getText());
			//5. Kiểm tra item nào đúng với item mình cần chọn thì click vào
			if(item.getText().equals(selectItem)) {
				item.click();
				break;
			}
		}
	}
	
	
	public String getAttributeElement(WebDriver driver,String locator, String attributeName) {
		return findElementByXpath(driver,locator).getAttribute(attributeName);
	}
	
	public String getTextElement(WebDriver driver,String locator) {
		return findElementByXpath(driver,locator).getText();
	}
	public String getTextElement(WebDriver driver,String locator, String...values) {
		return findElementByXpath(driver,locator,values).getText();
	}
	public int countElementNumber(WebDriver driver,String locator) {
		return findElementsByXpath(driver,locator).size();
	} 

	// Check/Uncheck checkbox
	public void checkToCheckbox(WebDriver driver,String locator) {
    	WebElement element = findElementByXpath(driver,locator);
		if(!element.isSelected()) {
    		element.click();
    	}
    }
    
    public void uncheckToCheckbox(WebDriver driver,String locator) {
    	WebElement element = findElementByXpath(driver,locator);
    	if(element.isSelected()) {
    		element.click();
    	}
    }
	//Check element isDisplay, isSelected, isEnabled
	public boolean isElementDisplayed(WebDriver driver,String locator) {
		overrideGlobalTimeOut(driver, GlobalConstants.SHORT_TIMEOUT);
		try{
			//happy path case 
		    element = findElementByXpath(driver,locator);
		    overrideGlobalTimeOut(driver, GlobalConstants.LONG_TIMEOUT);
		    return element.isDisplayed();
		} catch(Exception ex) {
			//throw exception -> catch sẽ bắt đưuọc những exception này ko đánh fail testcase tại thời điểm đang chạy.
			//ex.printStackTrace();
			overrideGlobalTimeOut(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}
	public boolean isElementDisplayed(WebDriver driver,String locator, String...values) {
		overrideGlobalTimeOut(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			element = findElementByXpath(driver,locator,values);
			overrideGlobalTimeOut(driver, GlobalConstants.LONG_TIMEOUT);
			return element.isDisplayed();
		}catch (Exception ex) {
			overrideGlobalTimeOut(driver, GlobalConstants.LONG_TIMEOUT);
			return false; 
	    }
	}
	
	public boolean Selected(WebDriver driver,String locator) {
		return findElementByXpath(driver,locator).isSelected();
	}
	public boolean isElementSelected(WebDriver driver,String locator, String...values) {
		return findElementByXpath(driver,locator,values).isSelected();
	}
	public boolean isElementEnabled(WebDriver driver,String locator) {
		try{
			element = findElementByXpath(driver,locator);
			return element.isEnabled();
			
		}catch(Exception ex) {
			return false;
		}
	}
	public boolean isElementEnabled(WebDriver driver,String locator,String...values) {
		try{
			element = findElementByXpath(driver,locator,values);
			return element.isEnabled();
			
		}catch(Exception ex) {
			return false;
		}
	}
	public void overrideGlobalTimeOut(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	//Switch to window by ID, Title
	public void switchToWindowByID(WebDriver driver,String parentID) {
    	//Lấy ra tất cả ID 
    	Set <String> allWindows =driver.getWindowHandles();
    	
    	//Dùng vòng lặp duyệt qua từng ID
    	for(String termID : allWindows) {
    	
    		//Kiểm tra cái ID nào khác với parent ID thì switch qua
    		if(!termID.equals(parentID)){
    		
    			//Swith cho that ID
    			driver.switchTo().window(termID);
    			break;
    		}
        }
    }
    public void switchToWindowByTitle(WebDriver driver,String expectedTitle){
    	//Lấy ra tất cả ID
    	Set <String> allWindows =driver.getWindowHandles();
    	//System.out.println("Có tất cả : " + allWindows.size());
    	
    	//Dùng vòng lặp duyệt qua từng ID
    	for(String termID : allWindows) {
    		//switch vào từng ID trước
    		//System.out.println("ID =" + termID);
    		driver.switchTo().window(termID);
    		
    		//get ra title đang có
    		String currentTitle = driver.getTitle();
    		//System.out.println("Title " + currentTitle);
    		
    		//Title nào bằng với title expected thì break khỏi vòng lặp
    		if(currentTitle.equals(expectedTitle)) {
    			//Thoát khỏi vòng lặp - Không duyệt những cái tiếp theo
    			break;
    		}
    			
    	}
    }
    public void closeAllWindowWithoutParent(WebDriver driver,String parentID) {
    	//Lấy ra tất cả ID
    	Set <String> allWindows =driver.getWindowHandles();
    	    	
    	//Dùng vòng lặp duyệt qua từng ID
    	for(String termID : allWindows) {
    		if(!termID.equals(parentID)) {
    			driver.switchTo().window(termID);
    			driver.close();
    		}
    		
    	}
    	driver.switchTo().window(parentID);
   
    }
    
    public void switchToFrame(WebDriver driver,String locator) {
    	driver.switchTo().frame(locator);
    }
    // User interaction : double click , hover, right click, draganddrop, sendkey
    
    public void doubleClickToElement(WebDriver driver,String locator) {
		action = new Actions(driver);
		element = findElementByXpath(driver,locator);
		action.doubleClick(element).perform();
	}
    
    
	public void hoverMouseToElement(WebDriver driver,String locator) {
		//Biến cục bộ
		action = new Actions(driver);
		element = findElementByXpath(driver,locator);
		action.moveToElement(element).perform();
	}
	public void hoverMouseToElement(WebDriver driver,String locator, String ...values) {
		//Biến cục bộ
		action = new Actions(driver);
		element = findElementByXpath(driver,locator,values);
		action.moveToElement(element).perform();
	}
	public void rightClick(WebDriver driver,String locator) {
		//Biến cục bộ
		action = new Actions(driver);
		element = findElementByXpath(driver,locator);
		action.contextClick().perform();
	}
	
	public void dragAndDrop(WebDriver driver,String source, String destination) {
		action = new Actions(driver);
		action.dragAndDrop(findElementByXpath(driver,source),findElementByXpath(driver,destination)).perform();
		
		
	}
	
	public void sendKeyBoardToElement(WebDriver driver,String locator, Keys key) {
		action = new Actions(driver);
		element = findElementByXpath(driver,locator);
		action.sendKeys(element,key).perform();
	}
	
	
	//Upload
	public void uploadFileSendKey(WebDriver driver,String upLoadTextBox, String pathFile, String uploadButton) {
		WebElement uploadFile = findElementByXpath(driver,upLoadTextBox);
		uploadFile.sendKeys(pathFile);
		findElementByXpath(driver,uploadButton).click();
	}
	
	public void uploadFileAutoIT(WebDriver driver,String uploadTextbox,String browserAutoIT, String pathFile, String uploadButton) throws IOException {
		WebElement uploadFile = findElementByXpath(driver,uploadTextbox);
		uploadFile.click();
		//execute runtime file (.exe/ .msi/ .bat/ .sh/)
	   	Runtime.getRuntime().exec(new String[] { browserAutoIT, pathFile});
	   	findElementByXpath(driver,uploadButton).click();
	}

	public void uploadFileRobot(WebDriver driver,String uploadTextbox, String uploadButton, String pathFile) throws Exception {
		
		WebElement uploadFile = findElementByXpath(driver,uploadTextbox);
		uploadFile.click();
			
		//Specify the file location with extension
		StringSelection select = new StringSelection(pathFile);
		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		/*if(driver.toString().contains("chrome") || driver.toString().contains("firefox")) {
			WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
			uploadFile.click();
			Thread.sleep(1000);
		} else {
			System.out.println("Go to IE");
			WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
			clickToElementByJS(uploadFile);
			Thread.sleep(1000);
		}*/
		Robot robot = new Robot();
		//Thread.sleep(1000);
		// Nhấn phím Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Nhấn xuống Ctrl - V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		//Nhã Ctrl - V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		//Thread.sleep(1000);
		//Nhấn Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		//Thread.sleep(5000);
		//Thread.sleep(4000);
		findElementByXpath(driver,uploadButton).click();
		//Thread.sleep(2000);
	}
	
	//Javascript Executor
	//Browser
	public Object executeForBrowser(WebDriver driver,String javaSript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaSript);
	}
	
	public boolean verifyTextInInnerText(WebDriver driver,String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver,String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	// Element
	public void highlightElement(WebDriver driver,String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		findElementByXpath(driver,locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(WebDriver driver,String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", findElementByXpath(driver,locator));
	}

	public void scrollToElement(WebDriver driver,String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver,locator));
	}

	public void sendkeyToElementByJS(WebDriver driver,String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", findElementByXpath(driver,locator));
	}

	public void removeAttributeInDOM(WebDriver driver,String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", findElementByXpath(driver,locator));
		sleepInSecond(1);
	}
	public void removeAttributeInDOM(WebDriver driver,String locator, String attributeRemove, String...values) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", findElementByXpath(driver, locator, values));
	}
	public boolean checkAnyImageLoaded(WebDriver driver,String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean)jsExecutor.executeScript("return arguments[0].complete && typeof argument[0].naturalWidth != \"undefined\" && argument[0].naturalWidth > 0",  element);
		if(status) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitToElementVisible(WebDriver driver,String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}
	
	public void waitToElementVisible(WebDriver driver,String locator, String...values) {
		byXpath = byXpathLocator(locator, values);
		waitExplicit = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}
	
	public void waitToElementInvisible(WebDriver driver, String locator) {
		date = new Date();
		By byLocator = By.xpath(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overrideGlobalTimeOut(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			System.out.println("Start time for wait invisible = " + date.toString());
			waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
				
		} catch(TimeoutException ex) {
			ex.printStackTrace();
		}
		System.out.println("End time for wait invisible = " + new Date().toString());
		overrideGlobalTimeOut(driver, GlobalConstants.LONG_TIMEOUT);
				
	}
	public void waitAlertPresence(WebDriver driver) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.alertIsPresent());
	}
	
	public boolean isElementUndisplay(WebDriver driver, String locator) {
		date = new Date();
		System.out.println("Start time = " + date.toString());
		overrideGlobalTimeOut(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if(elements.size()==0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			overrideGlobalTimeOut(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		}else if(elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/displayed");
			System.out.println("End time = " + new Date().toString());
			overrideGlobalTimeOut(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		}else {
			System.out.println("Element in DOM and visible");
			overrideGlobalTimeOut(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	
	}
	public boolean isElementUndisplay(WebDriver driver, String locator, String...value) {
		date = new Date();
		System.out.println("Start time = " + date.toString());
		overrideGlobalTimeOut(driver, GlobalConstants.SHORT_TIMEOUT);
		locator = String.format(locator, (Object[]) value);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if(elements.size()==0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			overrideGlobalTimeOut(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		}else if(elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/displayed");
			System.out.println("End time = " + new Date().toString());
			overrideGlobalTimeOut(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		}else {
			System.out.println("Element in DOM and visible");
			overrideGlobalTimeOut(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	
	}
    
	public void waitForElementPresence(WebDriver driver,String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byXpath));
	}
	
//	public void waitForElementVisible(WebDriver driver,String locator) {
//		byXpath = byXpathLocator(locator);
//		waitExplicit = new WebDriverWait(driver, longTimeout);
//		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
//	}
	
	public void waitToElementClickable(WebDriver driver,String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
	}
	public void waitToElementClickable(WebDriver driver,String locator, String...values) {
		byXpath = byXpathLocator(locator,values);
		waitExplicit = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
	}
	public void waitForElementInvisible(WebDriver driver,String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byXpath));
	}
	
	public void waitForAlertPresence(WebDriver driver,String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.alertIsPresent());
	}
	//Open Footer -> My Account page (Customer page)
//	public CustomerInfoPageObject openFooterCustomerInfo(WebDriver driver) {
//		waitToElementVisible(driver, AbstractPageUI.FOOTER_MY_ACCOUNT_LINK);
//		clickToElement(driver, AbstractPageUI.FOOTER_MY_ACCOUNT_LINK);
//		return PageGeneratorManager.getCustomerInfoPage(driver);
//	}
	
//	public SearchPageObject openFooterSearchPage(WebDriver driver) {
//		waitToElementVisible(driver, AbstractPageUI.FOOTER_SEARCH_LINK);
//		clickToElement(driver, AbstractPageUI.FOOTER_SEARCH_LINK);
//		return PageGeneratorManager.getSearchPage(driver);
//	}
	// Trong trường hợp mà cái application ít page (10-15)
	public AbstractPages openFooterPageByName(WebDriver driver, String pageName) {
		//factory pattern
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_FOOTER_LINK,pageName);
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_FOOTER_LINK,pageName);
		switch (pageName) {
		case "Search":
			return PageGeneratorManager.getSearchPage(driver);
		case "My account":
			return PageGeneratorManager.getCustomerInfoPage(driver);
		case "New products":
			return PageGeneratorManager.getNewProductPage(driver);
		case "Wishlist":
			return PageGeneratorManager.getWishListPage(driver);
		default:
			return PageGeneratorManager.getCustomerInfoPage(driver);
		}
	}
	// Trong trường hợp mà cái application ít page (10-15)
		public AbstractPages openHeaderPageByName(WebDriver driver, String pageName) {
			//factory pattern
			waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_LINK,pageName);
			clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_LINK,pageName);
			switch (pageName) {
			case "Register":
				return PageGeneratorManager.getRegisterPage(driver);
			case "Log in":
				return PageGeneratorManager.getLoginPage(driver);
			case "Log out":
				return PageGeneratorManager.getHomePage(driver);
			default:
				return PageGeneratorManager.getCustomerInfoPage(driver);
			}
		}
	// Trong trường hợp mà cái application nhiều page (n)
	public void openFooterPagesByName(WebDriver driver, String pageName) {
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_FOOTER_LINK,pageName);
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_FOOTER_LINK,pageName);
	}
	// Trong trường hợp mà cái application nhiều page (n)
	public void openHeaderPagesByName(WebDriver driver, String pageName) {
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_LINK,pageName);
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_LINK,pageName);
	}

	public AbstractPages openMyAccountPageByName(WebDriver driver, String pageName) {
		//factory pattern
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_MY_ACCOUNT_LINK,pageName);
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_MY_ACCOUNT_LINK,pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getCustomerInfoPage(driver);
		case "Addresses":
			return PageGeneratorManager.getAddressPage(driver);
		//case "customer-orders":
		//	return 
		//case "downloadable-products":
		//  return
		//case "back-in-stock-subscriptions":
		//  return
		//case "reward-points":
		//  return
		case "Change password":
		  return PageGeneratorManager.getChangePasswordPage(driver);
		case "My product reviews":
		  return PageGeneratorManager.getCustomerProductReviewPage(driver);
		default:
			return PageGeneratorManager.getCustomerInfoPage(driver);
		}
	}
	public void openHeaderMenuPagesByName(WebDriver driver, String pageMenuName) {
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_MENU_LINK,pageMenuName);
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_MENU_LINK,pageMenuName);
	}
	
	public void openHeaderSubMenuPagesByName(WebDriver driver, String pageMenuName, String subpageMenuName) {
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_MENU_LINK,pageMenuName);
		hoverMouseToElement(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_MENU_LINK,pageMenuName );
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_MENU_LINK,subpageMenuName);   
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_MENU_LINK,subpageMenuName);
	}
	
//	public SortPageObject openHeaderMenuPageByName(WebDriver driver, String pageName) {
//		//factory pattern
//		sleepInSecond(2);
//		waitToElementVisible(driver, String.format(AbstractPageUI.DYNAMIC_HEADER_MENU_LINK,pageName));
//		//clickToElement(driver, String.format(AbstractPageUI.DYNAMIC_TOP_MENU,pageName));
//		clickToElementByJS(driver, String.format(AbstractPageUI.DYNAMIC_HEADER_MENU_LINK,pageName));
//		return PageGeneratorManager.getSortPage(driver);
//		      
//	}
	//Open Menu My Account -> Customer Info page / Address Page// ....//Change Password Page
//	public CustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
//		waitToElementClickable(driver, AbstractPageUI.CUSTOMER_INFO_LINK);
//		clickToElement(driver, AbstractPageUI.CUSTOMER_INFO_LINK);
//		return PageGeneratorManager.getCustomerInfoPage(driver);
//	}
//
//	public AddressPageObject openAddressPage(WebDriver driver) {
//		waitToElementClickable(driver, AbstractPageUI.CUSTOMER_ADDRESSES_LINK);
//		clickToElement(driver, AbstractPageUI.CUSTOMER_ADDRESSES_LINK);
//		return PageGeneratorManager.getAddressPage(driver);
//		//waitToElementClickable(driver, AbstractPageUI.CUSTOMER_ADDRESSES_LINK);
//		//clickToElement(driver, AbstractPageUI.CUSTOMER_ADDRESSES_LINK);
//		//return PageGeneratorManager.getAddressPage(driver);
//		
//	}
//	
//	public ChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
//		waitToElementClickable(driver, AbstractPageUI.CUSTOMER_CHANGE_PASSWORD_LINK);
//		clickToElement(driver, AbstractPageUI.CUSTOMER_CHANGE_PASSWORD_LINK);
//		return PageGeneratorManager.getChangePasswordPage(driver);
//		
//	}
	// Open HomePage by click on Home Page image
	public HomePageObject openHomePage(WebDriver driver) {
		waitToElementClickable(driver, AbstractNopCommercePageUI.HOME_PAGE_IMAGE);
		clickToElement(driver, AbstractNopCommercePageUI.HOME_PAGE_IMAGE);
		return PageGeneratorManager.getHomePage(driver);
		
	}
	public void inputToNopCommerceTextboxByID(WebDriver driver, String textboxID, String textValue) {
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_TEXTBOX, textboxID);
		sendkeyToElement(driver, AbstractNopCommercePageUI.DYNAMIC_TEXTBOX, textValue, textboxID );
	}
	public void clickToNopCommerceRadioButtonByValue(WebDriver driver, String radioBtnValue) {
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_RADIO_BUTTON, radioBtnValue);
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_RADIO_BUTTON, radioBtnValue );
	}
	
	public void clickToNopCommerceCheckboxByID(WebDriver driver, String checkboxID, boolean status) {
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_CHECKBOX, checkboxID);
		
		boolean checkboxStatus = isElementSelected(driver, AbstractNopCommercePageUI.DYNAMIC_CHECKBOX, checkboxID);
	    System.out.println("Checkbox status  = " + checkboxStatus);
		if(!(checkboxStatus == status)) {
			clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_CHECKBOX, checkboxID );	
		}
		
	}
	public void clickToNopCommerceButtonByValue(WebDriver driver, String buttonValue) {
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_BUTTON, buttonValue);
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_BUTTON, buttonValue );
	}
	
	public void selectNopCommerceDropdownListByName(WebDriver driver, String dropdownName, String selectText) {
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_DROPDOWN_LIST, dropdownName);
		selectItemInHtmlDropdown(driver, AbstractNopCommercePageUI.DYNAMIC_DROPDOWN_LIST, selectText, dropdownName);
	}
	
	public boolean isDataSortedAscending(WebDriver driver,String locator) {
		//Khai bao 1 Array List
		ArrayList<String> arrayList = new ArrayList<>();
		//Tim tat ca element matching vs dieu kien (Name/Price/...)
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		//Lay text cua tung element add vao ArrayList
		for(WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("---------Du lieu tren UI --------");
		for(String name:arrayList) {
			System.out.println(name);
		}
		//Copy qua 1 array list moi de SORT trong CODE
		ArrayList<String> sortedList = new ArrayList<>();
		for(String child:arrayList) {
			sortedList.add(child);
		}
		//Thuc hien SORT ASC
		Collections.sort(arrayList);
		System.out.println("---------Du lieu da SORT ASC trong code: --------- ");
		for(String name:arrayList) {
			System.out.println(name);
		}
		//Verify 2 array bang nhau - neu du lieu sort tren UI khong chinh xac thi ket qua tra ve sai
		return sortedList.equals(arrayList);
	}
	
	public boolean isDataSortedDescending(WebDriver driver,String locator) {
		//Khai bao 1 Array List
		ArrayList<String> arrayList = new ArrayList<>();
		//Tim tat ca element matching vs dieu kien (Name/Price/...)
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		//Lay text cua tung element add vao ArrayList
		for(WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("---------Du lieu tren UI --------");
		for(String name:arrayList) {
			System.out.println(name);
		}
		//Copy qua 1 array list moi de SORT trong CODE
		ArrayList<String> sortedList = new ArrayList<>();
		for(String child:arrayList) {
			sortedList.add(child);
		}
		//Thuc hien SORT ASC
		Collections.sort(arrayList);
		System.out.println("---------Du lieu da SORT ASC trong code: --------- ");
		for(String name:arrayList) {
			System.out.println(name);
		}
		//Reverse data de sort DESC
		Collections.reverse(arrayList);
		//Collections.sort(arrayList,Collections.reverseOrder());
		System.out.println("--------Du lieu da SORT DESC trong code: -----------");
		for(String name:arrayList) {
			System.out.println(name);
		}
		
		//Verify 2 array bang nhau - neu du lieu sort tren UI khong chinh xac thi ket qua tra ve sai
		return sortedList.equals(arrayList);
	}
	public boolean isPriceSortedAscending(WebDriver driver,String locator) {
		//Khai bao 1 Array List
		ArrayList<Float> arrayList = new ArrayList<>();
		//Tim tat ca element matching vs dieu kien (Name/Price/...)
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		//Lay text cua tung element add vao ArrayList
		for(WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
			
		}
		System.out.println("---------Du lieu tren UI --------");
		for(Float name:arrayList) {
			System.out.println(name);
		}
		//Copy qua 1 array list moi de SORT trong CODE
		ArrayList<Float> sortedList = new ArrayList<>();
		for(Float child:arrayList) {
			sortedList.add(child);
		}
		//Thuc hien SORT ASC
		Collections.sort(arrayList);
		System.out.println("---------Du lieu da SORT ASC trong code: --------- ");
		for(Float name:arrayList) {
			System.out.println(name);
		}
		//Collections.sort(arrayList,Collections.reverseOrder());
		System.out.println("--------Du lieu da SORT DESC trong code: -----------");
		for(Float name:arrayList) {
			System.out.println(name);
		}
		
		//Verify 2 array bang nhau - neu du lieu sort tren UI khong chinh xac thi ket qua tra ve sai
		return sortedList.equals(arrayList);
	}
	public boolean isPriceSortedDescending(WebDriver driver,String locator) {
		//Khai bao 1 Array List
		ArrayList<Float> arrayList = new ArrayList<>();
		//Tim tat ca element matching vs dieu kien (Name/Price/...)
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		//Lay text cua tung element add vao ArrayList
		for(WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
			
		}
		System.out.println("---------Du lieu tren UI --------");
		for(Float name:arrayList) {
			System.out.println(name);
		}
		//Copy qua 1 array list moi de SORT trong CODE
		ArrayList<Float> sortedList = new ArrayList<>();
		for(Float child:arrayList) {
			sortedList.add(child);
		}
		//Thuc hien SORT ASC
		Collections.sort(arrayList);
		System.out.println("---------Du lieu da SORT ASC trong code: --------- ");
		for(Float name:arrayList) {
			System.out.println(name);
		}
		//Reverse data de sort DESC
		Collections.reverse(arrayList);
		//Collections.sort(arrayList,Collections.reverseOrder());
		System.out.println("--------Du lieu da SORT DESC trong code: -----------");
		for(Float name:arrayList) {
			System.out.println(name);
		}
		
		//Verify 2 array bang nhau - neu du lieu sort tren UI khong chinh xac thi ket qua tra ve sai
		return sortedList.equals(arrayList);
	}
	/** BANK GURU **/
	public void openBankGuruPagesByName(WebDriver driver, String pageName) {
		waitToElementVisible(driver, AbstractBankGuruPageUI.DYNAMIC_MENU_LINK,pageName);
		clickToElement(driver, AbstractBankGuruPageUI.DYNAMIC_MENU_LINK,pageName);
	}
	public void inputToBankGuruTextboxByName(WebDriver driver, String textboxName, String textValue) {
		waitToElementVisible(driver, AbstractBankGuruPageUI.DYNAMIC_TEXTBOX, textboxName);
		if(textboxName.contains("dob")) {
			removeAttributeInDOM(driver, AbstractBankGuruPageUI.DYNAMIC_TEXTBOX, "type", textboxName);
		}
		sendkeyToElement(driver, AbstractBankGuruPageUI.DYNAMIC_TEXTBOX, textValue, textboxName );
	}
	 
	public void inputToBankGuruTextAreaByName(WebDriver driver, String textareaName, String textValue) {
		waitToElementVisible(driver, AbstractBankGuruPageUI.DYNAMIC_TEXTAREA, textareaName);
		sendkeyToElement(driver, AbstractBankGuruPageUI.DYNAMIC_TEXTAREA, textValue, textareaName );
	}
	public void clickToBankGuruButtonByValue(WebDriver driver, String buttonValue) {
		waitToElementVisible(driver, AbstractBankGuruPageUI.DYNAMIC_BUTTON, buttonValue);
		clickToElement(driver, AbstractBankGuruPageUI.DYNAMIC_BUTTON, buttonValue);
	}
	public String getBankGuruHeaderText(WebDriver driver) {
		waitToElementVisible(driver, AbstractBankGuruPageUI.HEADER_TEXT);
		return getTextElement(driver, AbstractBankGuruPageUI.HEADER_TEXT);
	}
	
	public String getBankGuruRowValueByRowName(WebDriver driver, String rowName) {
		waitToElementVisible(driver, AbstractBankGuruPageUI.DYNAMIC_ROW_NAME,rowName);
		return getTextElement(driver, AbstractBankGuruPageUI.DYNAMIC_ROW_NAME,rowName);
				
	}
	
	 public boolean isBankGuruTextboxEnable(WebDriver driver, String textboxName) {
	    waitToElementVisible(driver, AbstractBankGuruPageUI.DYNAMIC_TEXTBOX, textboxName);
	    return isElementEnabled(driver, AbstractBankGuruPageUI.DYNAMIC_TEXTBOX, textboxName);
	 }
	 
	 public boolean isBankGuruTextAreaEnable(WebDriver driver, String textareaName) {
		    waitToElementVisible(driver, AbstractBankGuruPageUI.DYNAMIC_TEXTAREA, textareaName);
		    return isElementEnabled(driver, AbstractBankGuruPageUI.DYNAMIC_TEXTAREA, textareaName);
		 }
	public void selectBankGuruDropDownItemByName(WebDriver driver, String dropDownName, String selectItem) {
		waitToElementVisible(driver, AbstractBankGuruPageUI.DYNAMIC_DROPDOWN, dropDownName);
		selectItemInHtmlDropdown(driver, AbstractBankGuruPageUI.DYNAMIC_DROPDOWN, selectItem, dropDownName);
		
	}
	public boolean isAlertTextDisplayAndAccept(WebDriver driver, String alertText) {
		waitAlertPresence(driver);
		String actualText = getTextAlert(driver);
		sleepInSecond(1);
		acceptAlert(driver);
		sleepInSecond(1);
		return actualText.equals(alertText);
	}

}
