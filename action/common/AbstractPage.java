package common;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
	// Function dùng chung dành riêng cho package (layer) page Object
	// Bao gồm những function được wrapper lại từ selenium lib
	private WebDriver driver;
	private Actions action;
	private long longTimeout = 30;
	private WebElement element;
	private Select dropDown;
	private By byXpath;
	private WebDriverWait waitExplicit;
	
	public AbstractPage(WebDriver localDriver) {
		// TODO Auto-generated constructor stub
		driver = localDriver;
	}

	//Mở ra 1 url truyền tham số từ bên ngoài
	public void openUrl(String urlValue) {
		driver.get(urlValue);
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getCurrentPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public void back() {
		driver.navigate().back();
	}
	
	public void refresh() {
		driver.navigate().refresh();
	}
	
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert() {
		driver.switchTo().alert().dismiss();
	}
	
	public String getTextAlert() {
		return driver.switchTo().alert().getText();
	}	
	public void sendKeyToAlert(String value){
		driver.switchTo().alert().sendKeys(value);
	}
	
	public WebElement findElementByXpath(String locator) {
		return driver.findElement(byXpathLocator(locator));
	}
	
	public List<WebElement> findElementsByXpath(String locator) {
		return driver.findElements(byXpathLocator(locator));
	}
	
	public By byXpathLocator(String locator) {
		return By.xpath(locator);
	}
	public void clickToElement(String locator) {
		findElementByXpath(locator).click();
	}
	
	public void sendkeyToElement(String locator, String value) {
		findElementByXpath(locator).sendKeys(value);
	}
	//Dropdown : select HTML dropdown/ custom dropdown
	public void selectItemInHtmlDropdown(String locator, String selectedText) {
		dropDown = new Select(findElementByXpath(locator));
		dropDown.selectByVisibleText(selectedText);
	}
	public String getSelectItemInHtmlDropdown(String locator) {
		dropDown = new Select(findElementByXpath(locator));
		return dropDown.getFirstSelectedOption().getText();
	}
	
	public void selectItemInCustomDropDonw(String xpathDropdown, String xpathAllItem, String selectItem) {
		clickToElement(xpathDropdown);
		
		//2. Khai báo list webelement để chứa tất cả các item
		List <WebElement> allItems = findElementsByXpath(xpathAllItem);
		
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
	
	
	public String getAttributeElement(String locator, String attributeName) {
		return findElementByXpath(locator).getAttribute(attributeName);
	}
	
	public String getTextElement(String locator) {
		return findElementByXpath(locator).getText();
	}
	
	public int countElementNumber(String locator) {
		return findElementsByXpath(locator).size();
	} 

	// Check/Uncheck checkbox
	public void checkToCheckbox(String locator) {
    	WebElement element = findElementByXpath(locator);
		if(!element.isSelected()) {
    		element.click();
    	}
    }
    
    public void uncheckToCheckbox(String locator) {
    	WebElement element = findElementByXpath(locator);
    	if(element.isSelected()) {
    		element.click();
    	}
    }
	//Check element isDisplay, isSelected, isEnabled
	public boolean isElementDisplayed(String locator) {
		return findElementByXpath(locator).isDisplayed();
	}
	
	public boolean isElementSelected(String locator) {
		return findElementByXpath(locator).isSelected();
	}
	
	public boolean isElementEnabled(String locator) {
		return findElementByXpath(locator).isEnabled();
	}
	
	
	//Switch to window by ID, Title
	public void switchToWindowByID(String parentID) {
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
    public void switchToWindowByTitle(String expectedTitle){
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
    public void closeAllWindowWithoutParent(String parentID) {
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
    
    public void switchToFrame(String locator) {
    	driver.switchTo().frame(locator);
    }
    // User interaction : double click , hover, right click, draganddrop, sendkey
    
    public void doubleClickToElement(String locator) {
		action = new Actions(driver);
		element = findElementByXpath(locator);
		action.doubleClick(element).perform();
	}
    
    
	public void hoverMouseToElement(String locator) {
		//Biến cục bộ
		action = new Actions(driver);
		element = findElementByXpath(locator);
		action.moveToElement(element).perform();
	}
	
	public void rightClick(String locator) {
		//Biến cục bộ
		action = new Actions(driver);
		element = findElementByXpath(locator);
		action.contextClick().perform();
	}
	
	public void dragAndDrop(String source, String destination) {
		action = new Actions(driver);
		action.dragAndDrop(findElementByXpath(source),findElementByXpath(destination)).perform();
		
		
	}
	
	public void sendKeyBoardToElement(String locator, Keys key) {
		action = new Actions(driver);
		element = findElementByXpath(locator);
		action.sendKeys(element,key).perform();
	}
	
	
	//Upload 1 file or multi files
	// 1 file : truyền tham số là path của file đó
	// multi file : truyền tham số pathfile1 + "\n" + pathfile2 + "\n" +...+ pathfilen
	public void uploadFileSendKey(String upLoadTextBox, String pathFile, String uploadButton) {
		WebElement uploadFile = findElementByXpath(upLoadTextBox);
		uploadFile.sendKeys(pathFile);
		findElementByXpath(uploadButton).click();
	}
	
	public void uploadFileAutoIT(String uploadTextbox,String browserAutoIT, String pathFile, String uploadButton) throws IOException {
		WebElement uploadFile = findElementByXpath(uploadTextbox);
		uploadFile.click();
		//execute runtime file (.exe/ .msi/ .bat/ .sh/)
	   	Runtime.getRuntime().exec(new String[] { browserAutoIT, pathFile});
	   	findElementByXpath(uploadButton).click();
	}

	public void uploadFileRobot(String uploadTextbox, String uploadButton, String pathFile) throws Exception {
		
		WebElement uploadFile = findElementByXpath(uploadTextbox);
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
		findElementByXpath(uploadButton).click();
		//Thread.sleep(2000);
	}
	
	//Javascript Executor
	//Browser
	public Object executeForBrowser(String javaSript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaSript);
	}
	
	public boolean verifyTextInInnerText(String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	// Element
	public void highlightElement(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		findElementByXpath(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", findElementByXpath(locator));
	}

	public void scrollToElement(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", findElementByXpath(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", findElementByXpath(locator));
	}
	
	public boolean checkAnyImageLoaded(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean)jsExecutor.executeScript("return arguments[0].complete && typeof argument[0].naturalWidth != \"undefined\" && argument[0].naturalWidth > 0",  element);
		if(status) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitToElementDisplayed(String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver,longTimeout);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}
	
	
	
	public void waitForElementPresence(String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byXpath));
	}
	
	public void waitForElementVisible(String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}
	
	public void waitToElementClickable(String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver,longTimeout);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
	}
	
	public void waitForElementInvisible(String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byXpath));
	}
	
	public void waitForAlertPresence(String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.alertIsPresent());
	}
	
}
