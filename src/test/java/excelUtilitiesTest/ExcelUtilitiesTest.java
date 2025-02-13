package excelUtilitiesTest;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import BasePackage.BaseTest;
import excelUtilities.ExcelUtilities;

/**
 * This class is used the verify Excel Utilities
 */
public class ExcelUtilitiesTest extends BaseTest {
	
//	public static WebDriver driver = null;
//	
//	public void openURL( )
//	{
//		//launchBrowser();
//		driver.get("https://www.google.com");
//	}
//
//	public void TearDown() {
//		driver.close();
//		System.out.println("Chrome Browser Closed successfully");
//	}
//
//	public static WebDriver launchBrowser() {
//
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("start-maximized");
//		String userPath = System.getProperty("user.dir");
//		System.setProperty("webdriver.chrome.driver", userPath + "/resource/ChromeDriver/chromedriver.exe");
//		driver = new ChromeDriver(options);
//		driver.manage().window().maximize();
//	
//		return (driver);
//	}
//
//
//	@BeforeSuite(alwaysRun=true)
//	public void beforeSuite() throws Throwable {
//
//		
//		System.setProperty("url","https://www.google.com");
//
//		System.setProperty("AppName Details","Stacks DMDA");
//
//		System.setProperty("Environment Details","Staging");
//
//		String applicationTittle = "Stacks";
//		System.setProperty("Application Tittle",applicationTittle+" Automation");
//
//		System.setProperty("Report Name",applicationTittle+" AutomationTest-Report");
//
//	}
//	
//	@BeforeClass(alwaysRun = true)
//	public void beforeClass(ITestContext testcontext) throws Throwable {
//		
//		ChromeOptions options = new ChromeOptions();
//		String userPath = System.getProperty("user.dir");
//		System.setProperty("webdriver.chrome.driver", userPath + "/resource/ChromeDriver/chromedriver.exe");
//
//		options = new ChromeOptions();
//		options.addArguments("start-maximized");
//
//		driver = new ChromeDriver(options);
//		testcontext.setAttribute("driver", driver);
//		
//		openURL();
//	}
//
//	@AfterClass
//	public void afterClass() throws Throwable {
//		TearDown();
//	}
	
	String excelPath = System.getProperty("user.dir") + "\\excelTestData\\data.xlsx";
	@Test
	public void ExcelDemo() throws Throwable{
		ExcelUtilities obj=new ExcelUtilities();
		Workbook workbook=obj.getWorkbook(excelPath);

		String sheetName=obj.getSheetname(workbook, 0);
		int rowCount=obj.getRowCount(workbook,sheetName);
		System.out.println(rowCount);

		int columnName=obj.getColumnCount(workbook,sheetName, 5);
		System.out.println(columnName);	

		String getData=obj.getCellData(workbook,sheetName,12 , 1);
		System.out.println(getData);

		ArrayList<String> getRowData=obj.getRowData(workbook,sheetName, 13);
		System.out.println(getRowData);

		List<String> getSheetNames=obj.getAllSheetNames(workbook);
		System.out.println(getSheetNames);

		ArrayList<String> getColumnData=obj.getColumnDataByIndex(workbook,sheetName, 0);
		System.out.println(getColumnData);

		obj.setOrReplaceCellData(excelPath,"Radhika",13,0,"Ajay");
		System.out.println(rowCount);

		boolean status=obj.isSheetPresent(workbook,sheetName);
		System.out.println(status);

		boolean status123=obj.isSheetPresent(workbook,"AjayPage");
		System.out.println(status123);
	}
}