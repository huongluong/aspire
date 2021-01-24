package common;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
public class AbstractTest {
	private WebDriver driver;

	//constructor
	protected AbstractTest() {

	}
	//public String email = "huong.luong2@abc.com";
	//public String password = "1286801";
	protected WebDriver getBrowserDriver(String browserName) {
		//System.out.println("Browser name = " + browserName);
		//System.out.println("URL = " + autUrl);
		if(browserName.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "./browserDriver/chromedriver.exe");
			//WebDriverManager.chromedriver().setup();
			WebDriverManager.chromedriver().version("78.0.3904.11").setup();
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
		    //System.setProperty("webdriver.gecko.driver", "./browserDriver/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("headless_chrome")) {
		    //setProperty("ebdriver.chrome.driver", "./browserDriver/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
		    options.addArguments("headless");
		    options.addArguments("window-size=1920x1080");
		    driver = new ChromeDriver(options);
		}else if(browserName.equalsIgnoreCase("headless_firefox")) {
		    //System.setProperty("ebdriver.gecko.driver", "./browserDriver/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
		    options.addArguments("--headless");
		    options.addArguments("window-size=1920x1080");
		    driver = new FirefoxDriver(options);
		
		}
		
		// Open Url
		driver.get(GlobalConstants.TEST_URL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	protected WebDriver getBrowserDriver(String browserName, String autUrl) {
		//System.out.println("Browser name = " + browserName);
		//System.out.println("URL = " + autUrl);
		if(browserName.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "./browserDriver/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			//WebDriverManager.chromedriver().version("78.0.3904.11").setup();
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
		    //System.setProperty("webdriver.gecko.driver", "./browserDriver/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("headless_chrome")) {
		    //setProperty("ebdriver.chrome.driver", "./browserDriver/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
		    options.addArguments("headless");
		    options.addArguments("window-size=1920x1080");
		    driver = new ChromeDriver(options);
		}else if(browserName.equalsIgnoreCase("headless_firefox")) {
		    //System.setProperty("ebdriver.gecko.driver", "./browserDriver/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
		    options.addArguments("--headless");
		    options.addArguments("window-size=1920x1080");
		    driver = new FirefoxDriver(options);
		
		}else if(browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}else if(browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
			
		}else if(browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		// Open Url
		driver.get(autUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	protected int randomNumber(int numbers) {
		//number of digit need to generate random
			Random rand = new Random();
			switch(numbers) {
			case 1: return rand.nextInt(9);
			case 2: return rand.nextInt(99);
			case 3: return rand.nextInt(999);
			case 4: return rand.nextInt(9999);
			case 5: return rand.nextInt(99999);
			case 6: return rand.nextInt(999999);
			case 7: return rand.nextInt(9999999);
			case 8: return rand.nextInt(99999999);
			case 9: return rand.nextInt(999999999);
			
			}
		    return rand.nextInt(999999);
		}
		protected int randomNumberInRange(int number)
		{
			//Range : 0 -  number
			Random rand = new Random();
			return rand.nextInt(number);
		}
	
	protected char randomChar() {
		Random rand = new Random();
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		char letter = abc.charAt(rand.nextInt(abc.length()));
		return letter;
	}

}
