	package reportUtilities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import excelUtilities.ExcelUtilities;


/**
 *Listner class used to generate Extent Report
 */
public class ReportingUtility extends TestListenerAdapter {
	ExcelUtilities excelUtil = new ExcelUtilities();

	int browserConfigCount = 0;

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent; 

	public ExtentTest test;

	String htmlReportName;

	LocalDateTime currentDateTime = LocalDateTime.now();  

	DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");  
	String formatDateTime = currentDateTime.format(format1).toString(); 

	DateTimeFormatter format2 = DateTimeFormatter.ofPattern("k-mm-ss a");
	String currentTime = currentDateTime.format(format2).toString(); 

	private static ThreadLocal<ExtentTest> methodTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> dataProviderTest = new ThreadLocal<>();

	/**
	 *On start of test suite or group of test execution, this method gets called & empty report gets created
	 */
	public void onStart() {

		String timeStamp = new SimpleDateFormat("dd_MMM_yyyy-k_mm_ss a").format(new Date());// Time Stamp
		String nameOfReportRequired = System.getProperty("Report Name");//Report Name : needs to be set
		String reportName = nameOfReportRequired+" "+ timeStamp + ".html";
		String  outputFolder = "\\Reports\\"+formatDateTime+"\\";
		Path path = Paths.get(System.getProperty("user.dir") + outputFolder);

		if(!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		htmlReportName = System.getProperty("user.dir") +outputFolder+reportName;
		sparkReporter =  new ExtentSparkReporter(htmlReportName).viewConfigurer().viewOrder().as(new ViewName[] { ViewName.TEST,ViewName.DASHBOARD, ViewName.DEVICE, ViewName.LOG,ViewName.EXCEPTION,ViewName.AUTHOR}).apply();

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Application",System.getProperty("AppName Details"));//Application Name : needs to be set
		extent.setSystemInfo("Environment", System.getProperty("Environment Details"));//Environment Details : needs to be set
		extent.setSystemInfo("URL", System.getProperty("url"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("OS Version", System.getProperty("os.version"));
		extent.setSystemInfo("OS Arch", System.getProperty("os.arch"));

		sparkReporter.config().setCss("css-string");
		sparkReporter.config().setDocumentTitle(System.getProperty("Application Title"));//Application Title : needs to be set
		sparkReporter.config().setReportName(System.getProperty("Report Name"));//Report Name : needs to be set
		sparkReporter.config().setTheme(Theme.DARK);  //Theme
		sparkReporter.config().setTimelineEnabled(true);
		sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss a");
	}

	/**
	 *On start test execution, this method gets called & node for particular test is created in report
	 */
	@Override
	public void onTestStart(ITestResult result) {    

		if(browserConfigCount==0) {
			extent.setSystemInfo("Browser", getBrowser(result));
			extent.setSystemInfo("Browser Version", getBrowserVersion(result));
			browserConfigCount++;
		}

		String methodName = result.getMethod().getMethodName();

		if (result.getParameters().length>0) {
			if (methodTest.get() != null && methodTest.get().getModel().getName().equals(methodName)) { } 
			else {
				createTest(result);
			}
			String paramName = Arrays.asList(result.getParameters()).toString();
			ExtentTest paramTest = methodTest.get().createNode(paramName);
			dataProviderTest.set(paramTest);
		} else {
			createTest(result);
		}
	}

	private void createTest(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		ExtentTest test = extent.createTest(methodName);

		methodTest.set(test);

		String[] groups = result.getMethod().getGroups();
		if (groups.length > 0) {
			Arrays.asList(groups)
			.forEach(x -> methodTest.get().assignCategory(x));
		}
	}

	private ExtentTest getTest(ITestResult result) {
		ExtentTest t = result.getParameters() != null && result.getParameters().length>0
				? dataProviderTest.get()
						: methodTest.get();
		return t;
	}

	/**
	 *On test script being passed, this method is called
	 */
	public void onTestSuccess(ITestResult result) {		
		getTest(result).pass(MarkupHelper.createLabel(result.getName()+ " - Test Case Passed", ExtentColor.GREEN));
		getTest(result).pass("Test passed");
		try {
			setTestResult( result);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 *On test script being failed, this method is called
	 */
	public void onTestFailure(ITestResult result ) {
		ITestContext textContext = result.getTestContext();	
		WebDriver driver = (WebDriver) textContext.getAttribute("driver");

		getTest(result).fail(MarkupHelper.createLabel(result.getName()+ " - Test Case Failed", ExtentColor.RED));
		getTest(result).fail(result.getThrowable());

		LocalDateTime currentDateTime = LocalDateTime.now();  
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");  
		String formatDateTime = currentDateTime.format(format1).toString(); 

		DateTimeFormatter format2 = DateTimeFormatter.ofPattern("hh-mm-ss a");
		String currentTime = currentDateTime.format(format2).toString(); 
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\"+formatDateTime +"\\"+ result.getName()+" "+currentTime + ".png";
		File f = new File(screenshotPath);

		if(!f.exists()) {
			try {
				captureScreenshotAsBase64(result.getMethod().getMethodName(), driver);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		getTest(result).fail(result.getMethod().getMethodName() +" "+currentTime, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
		try {
			setTestResult( result);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 *On test script being failed within pass percentage, this method is called
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ITestContext textContext = result.getTestContext();	
		WebDriver driver = (WebDriver) textContext.getAttribute("driver");

		getTest(result).fail(MarkupHelper.createLabel(result.getName()+ " - Test Case Failed", ExtentColor.RED));
		getTest(result).fail(result.getThrowable());

		LocalDateTime currentDateTime = LocalDateTime.now();  
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");  
		String formatDateTime = currentDateTime.format(format1).toString(); 

		DateTimeFormatter format2 = DateTimeFormatter.ofPattern("hh-mm-ss a");
		String currentTime = currentDateTime.format(format2).toString(); 
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\"+formatDateTime +"\\"+ result.getName()+" "+currentTime + ".png";
		File f = new File(screenshotPath);

		if(!f.exists()) {
			try {
				captureScreenshotAsBase64(result.getMethod().getMethodName(), driver);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		getTest(result).fail(result.getMethod().getMethodName() +" "+currentTime, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());

		try {
			setTestResult( result);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 *On test script being skipped, this method is called
	 */
	public void onTestSkipped(ITestResult result) {
		getTest(result).skip(MarkupHelper.createLabel(result.getName()+" - Test Case Skipped", ExtentColor.ORANGE));
		getTest(result).skip(result.getThrowable());
	}

	/**
	 *On complete test script execution, flush  all result and saves into report
	 *Opens report on default after testscript completion
	 */
	public void onFinish(ITestContext textContext) {
		extent.flush();
		methodTest.remove();
		try { //preload report on default browser of the system
			Desktop.getDesktop().browse(new File(htmlReportName).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Captures screenshot as png file
	 * @param testMethodName
	 * @param driver
	 * @return
	 * @throws Throwable
	 */
	public String captureScreenshotAsFile( String testMethodName, WebDriver driver) throws Throwable {
		File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String captureScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" +formatDateTime+"\\"+currentTime+  testMethodName+" "+".png";
		try {
			FileUtils.copyFile(srcfile, new File(captureScreenshotPath));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return captureScreenshotPath;
	}

	/**
	 * Capture screenshot as Base64 file
	 * @param testMethodName
	 * @param driver
	 * @return
	 * @throws Throwable
	 * @throws Throwable
	 */
	public String captureScreenshotAsBase64(String testMethodName,  WebDriver driver) throws Throwable, Throwable {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String captureScreenshotBase64Path = System.getProperty("user.dir") + "\\Screenshots\\" +formatDateTime+"\\"+testMethodName+" "+currentTime+".png";
		try {
			FileUtils.copyFile(source, new File(captureScreenshotBase64Path));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		byte[] imageBytes = IOUtils.toByteArray(new FileInputStream (captureScreenshotBase64Path));
		return Base64.getEncoder().encodeToString(imageBytes);
	}

	/**
	 * Convert screenshot into base64 string
	 * @param driver
	 * @return base64
	 */
	public String getBase64(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}

	/**
	 * Set result in the data sheet againt its respective testCaseID
	 * @param result
	 * @throws Throwable
	 */
	public void setTestResult(ITestResult result) throws Throwable {
		String filePath= System.getProperty("driverFilePath");
		String testScriptName = result.getMethod().getMethodName();
		String sheetName = excelUtil.getSheetName(testScriptName,filePath);
		Workbook book = excelUtil.getWorkbook(filePath);
		Sheet sheetObj = excelUtil.getSheetObject(book,sheetName);

		Object[] parametersArray = result.getParameters();
		Object testCaseIDValue= parametersArray[1];
		String testCaseIDStr = testCaseIDValue.toString();

		String[][]srchCriteria={{"TestCaseID",testCaseIDStr}};
		int matchedRow = ExcelUtilities.getRowNumber(sheetObj, srchCriteria);

		int col_Num = -1;
		Row rowObj = sheetObj.getRow(0);
		for(int i = 0; i < rowObj.getLastCellNum(); i++)
		{
			if(rowObj.getCell(i).getStringCellValue().trim().equals("Result"))
			{
				col_Num = i;
			}
		}
		rowObj = sheetObj.getRow(matchedRow);
		if(rowObj == null)
			rowObj = sheetObj.createRow(matchedRow);

		Cell cellObj = rowObj.getCell(col_Num);
		if(cellObj == null)
			cellObj = rowObj.createCell(col_Num);

		if(result.isSuccess() ) {
			cellObj.setCellValue("PASS");
			FileOutputStream fo=new FileOutputStream(filePath);				
			book.write(fo);	
			book.close();
			excelUtil.fillGreenColor(filePath,sheetName,matchedRow ,col_Num);
		}
		else {
			cellObj.setCellValue("FAIL");
			FileOutputStream fo=new FileOutputStream(filePath);				
			book.write(fo);	
			book.close();
			excelUtil.fillRedColor(filePath,sheetName,matchedRow ,col_Num);
		}
	}	

	/**
	 * Returns Browser Name of the Test Environment
	 * @param result
	 * @return browserName
	 */
	public String getBrowser(ITestResult result) {
		ITestContext textContext = result.getTestContext();	
		WebDriver driver = (WebDriver) textContext.getAttribute("driver");

		Capabilities browserCap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = browserCap.getBrowserName();
		return browserName ;
	}

	/**
	 * Returns Browser version of the Test Environment
	 * @param result
	 * @return browserVersion
	 */
	public String getBrowserVersion(ITestResult result) {
		ITestContext textContext = result.getTestContext();	
		WebDriver driver = (WebDriver) textContext.getAttribute("driver");

		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String version = cap.getBrowserVersion().toString();
		return version ;
	}
}