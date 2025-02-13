package BasePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import reportUtilities.ReportingUtility;

@Listeners(ReportingUtility.class)
public class BaseTest {
	
	public static WebDriver driver = null;

	public void openURL( )
	{
		//launchBrowser();
		driver.get("https://www.google.com");
	}

	public void TearDown() {
		driver.close();
		System.out.println("Chrome Browser Closed successfully");
	}

	public static WebDriver launchBrowser() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		String userPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", userPath + "/resource/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	
		return (driver);
	}


	@BeforeSuite(alwaysRun=true)
	public void beforeSuite() throws Throwable {

		System.setProperty("url","https://www.google.com");

		System.setProperty("AppName Details","Stacks DMDA");

		System.setProperty("Environment Details","Staging");

		String applicationTittle = "Stacks";
		System.setProperty("Application Tittle",applicationTittle+" Automation");

		System.setProperty("Report Name",applicationTittle+" AutomationTest-Report");
	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext testcontext) throws Throwable {
		
		ChromeOptions options = new ChromeOptions();
		String userPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", userPath + "/resource/ChromeDriver/chromedriver.exe");

		options = new ChromeOptions();
		options.addArguments("start-maximized");

		driver = new ChromeDriver(options);
		testcontext.setAttribute("driver", driver);
		
		openURL();
	}

	@AfterClass
	public void afterClass() throws Throwable {
		TearDown();
	}
}