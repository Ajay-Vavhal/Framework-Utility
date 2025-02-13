package webElementUtilitiesTest;

import org.testng.annotations.Test;

import BasePackage.BaseTest;

/**
 * Webelement Utility Test Script
 */
public class WebElementUtilitiesTest extends BaseTest {

//    public static WebDriver driver = null;
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
	
	@Test
	public void TestWebelementUtilities(){
		System.out.println("WebElement Utility Test");
		
		//WebDriver driver = null;
//		try {
//			String projectPath = System.getProperty("user.dir");
//			System.setProperty("webdriver.chrome.driver", projectPath + "/resource/ChromeDriver/chromedriver.exe");
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("start-maximized");
//			driver = new ChromeDriver(options);
			
			
//
//			WebElementUtlities elementUtil = new WebElementUtlities();
//
//			// Enter URL
//			driver.get("https://demoqa.com/text-box");
//
//			// Enter full name
//			WebElement fullname = driver.findElement(By.xpath("//div[@id='userName-wrapper']//input"));
//			String attributeIdValue = elementUtil.getAttributeUsingValue(driver, fullname, "id");
//			WebElement fullnameText = driver.findElement(By.xpath("//div[@id='userName-wrapper']//input[@id='" + attributeIdValue + "']"));
//			elementUtil.setText(driver, fullnameText, "Testing");
//
//			// Email
//			WebElement email=driver.findElement(By.xpath("//input[@id='userEmail']"));
//			elementUtil.setTextAndTab(driver, email, "xyz@automation.com");
//
//			// Current Adds
//			WebElement currentAdds = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
//			elementUtil.setTextUsingActionClass(driver, currentAdds, "Automation Test");
//
//			// Permanent Adds2
//			WebElement permanentAdds = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
//			elementUtil.setTextUsingJavascript(driver, permanentAdds, "Test the Application");
//
//			// Submit
//			WebElement submitBtn = driver.findElement(By.xpath("//button[text()='Submit']"));
//			elementUtil.scrollTillElement(driver, submitBtn);
//			elementUtil.click(driver,submitBtn);
//
//			// Observed Values
//			String observedNameValue=elementUtil.getText(driver, driver.findElement(By.xpath("//div[@id='output']//p[@id='name']")));
//			String observedEmailValue=elementUtil.getText(driver, driver.findElement(By.xpath("//div[@id='output']//p[@id='email']")));
//			String observedCurrAddsValue=elementUtil.getText(driver, driver.findElement(By.xpath("//div[@id='output']//p[@id='currentAddress']")));
//			String observedPermAddsValue=elementUtil.getText(driver, driver.findElement(By.xpath("//div[@id='output']//p[@id='permanentAddress']")));
//			equals(observedNameValue.contains("Testing"));
//			equals(observedEmailValue.contains("xyz@automation.com"));
//			equals(observedCurrAddsValue.contains("Automation Test"));
//			equals(observedPermAddsValue.contains("Test the Application"));
//
//			// Clear Text
//			elementUtil.clearText(driver, fullnameText);
//			elementUtil.clearTextUsingActionClass(driver, currentAdds);
//			elementUtil.clearTextUsingJavaScript(driver, permanentAdds);
//			elementUtil.clearTextUsingJavaScript(driver, email);
//			elementUtil.scrollTillElement(driver, submitBtn);
//			elementUtil.click(driver,submitBtn);
//			By output=By.xpath("//div[@id='output']//p");
//			boolean outputCount=elementUtil.elementIsVisible(driver, output);
//			assertFalse(outputCount);
//			//int outputCount=driver.findElements(By.xpath("//div[@id='output']//p")).size();
//
//			// Radio button
//			WebElement radioBtnMenu=driver.findElement(By.xpath("//span[text()='Radio Button']"));
//			elementUtil.scrollTillElement(driver, radioBtnMenu);
//			elementUtil.click(driver, radioBtnMenu);
//			WebElement yesRadioBtn=driver.findElement(By.xpath("//input[@id='yesRadio']"));
//			boolean isYesRadioBtnSelect=elementUtil.elementISelected(yesRadioBtn);
//			assertFalse(isYesRadioBtnSelect);
//			elementUtil.clickUsingJavaScript(driver, yesRadioBtn);
//			boolean isYesRadioBtnSelected=elementUtil.elementISelected(yesRadioBtn);
//			assertTrue(isYesRadioBtnSelected);
//
//			// Web tables
//			WebElement webTables=driver.findElement(By.xpath("//span[text()='Web Tables']"));
//			elementUtil.scrollTillElement(driver, webTables);
//			elementUtil.click(driver,webTables );
//			By tableColumn=By.xpath("//div[@role='columnheader']");
//			List<WebElement> table = driver.findElements(tableColumn);
//			int columnCount=elementUtil.getElementsCount(driver, table);
//			assertEquals(7, columnCount);
//			int columnElementCount =elementUtil.getElementCount(driver, tableColumn);
//			assertEquals(7, columnElementCount);
//			boolean isAgeColumnExist=elementUtil.isValueExist(table, "Age");
//			assertTrue(isAgeColumnExist);
//			List<WebElement> columnName=driver.findElements(By.xpath("//div[@role='columnheader']"));
//			elementUtil.explicitWaitForAllElementToBeVisible(driver, columnName);
//			List<String>observedColumnNames = elementUtil.getElementsText(driver, columnName);
//			String[] list={"First Name","Last Name","Age","Email","Salary","Department","Action"};
//			observedColumnNames.equals(list);
//
//			// Double click
//			WebElement buttonsMenu =  driver.findElement(By.xpath("//span[text()='Buttons']"));
//			elementUtil.scrollTillElement(driver, buttonsMenu);
//			elementUtil.click(driver,buttonsMenu);
//			WebElement doubleClkBtn= driver.findElement(By.xpath("//button[@id='doubleClickBtn']"));
//			elementUtil.doubleClick(driver, doubleClkBtn);
//			String doubleClickMsg=elementUtil.getText(driver, driver.findElement(By.xpath("//p[@id='doubleClickMessage']")));
//			equals(doubleClickMsg.contains("double click"));
//
//			// Right click
//			elementUtil.rightClickOnWebElement(driver, driver.findElement(By.xpath("//button[@id='rightClickBtn']")));
//			String rightClickMsg=elementUtil.getText(driver, driver.findElement(By.xpath("//p[@id='rightClickMessage']")));
//			equals(rightClickMsg.contains("right click"));
//
//			// Enabled & displayed check
//			WebElement dynamicPropMenu=driver.findElement(By.xpath("//span[text()='Dynamic Properties']"));
//			elementUtil.scrollTillElement(driver, dynamicPropMenu);
//			elementUtil.click(driver, dynamicPropMenu);
//			WebElement enableButton=driver.findElement(By.xpath("//button[@id='enableAfter']"));
//			By visibleButton=By.xpath("//button[@id='visibleAfter']");
//			boolean isDisableButton=elementUtil.elementIsEnabled(enableButton);
//			assertFalse(isDisableButton);
//			boolean isVisibleButton=elementUtil.elementIsVisible(driver,visibleButton);
//			assertFalse(isVisibleButton);
//			boolean isDisplayed=elementUtil.elementIsDisplayed(enableButton);
//			assertTrue(isDisplayed);
//			elementUtil.explicitWaitForElementToBeVisible(driver, visibleButton);
//			boolean isEnabledButton=elementUtil.elementIsEnabled(enableButton);
//			assertTrue(isEnabledButton);
//			boolean isVisibleButton1=elementUtil.elementIsVisible(driver,visibleButton);
//			assertTrue(isVisibleButton1);
//
//			// Forms
//			WebElement forms=driver.findElement(By.xpath("//div[text()='Forms']"));
//			elementUtil.scrollTillElement(driver, forms);
//			elementUtil.click(driver,forms);
//			WebElement practiceForm=driver.findElement(By.xpath("//span[text()='Practice Form']"));
//			elementUtil.click(driver, practiceForm);
//			WebElement firstName=driver.findElement(By.xpath("//input[@id='firstName']"));
//			elementUtil.setText(driver, firstName, "WebElement Utility");
//			String observedFirstName = elementUtil.getAttributeByValue(driver, firstName);
//			assertEquals("WebElement Utility",observedFirstName);
//
//			// CheckBox
//			WebElement sportsCheckBox = driver.findElement(By.xpath("//input[@value='1']"));
//			elementUtil.scrollTillElement(driver, sportsCheckBox);
//			boolean ischkboxDisplayed1=elementUtil.elementISelected(sportsCheckBox);
//			assertFalse(ischkboxDisplayed1);
//			elementUtil.clickUsingActionClass(driver, sportsCheckBox);
//			boolean ischkboxDisplayed=elementUtil.elementISelected(sportsCheckBox);
//			assertTrue(ischkboxDisplayed);
//
//			// Menu
//			WebElement widgets=driver.findElement(By.xpath("//div[text()='Widgets']"));
//			elementUtil.scrollTillElement(driver, widgets);
//			elementUtil.click(driver,widgets);
//			WebElement menu=driver.findElement(By.xpath("//span[text()='Menu']"));
//			elementUtil.explicitWaitForElementToBeVisible(driver,menu );
//			elementUtil.scrollToBottom(driver);
//			elementUtil.click(driver, menu);
//			elementUtil.moveToElement(driver, driver.findElement(By.xpath("//a[text()='Main Item 1']")));
//			WebElement elementToMove=driver.findElement(By.xpath("//a[text()='SUB SUB LIST �']"));
//			WebElement elementToClick=driver.findElement(By.xpath("//a[text()='Sub Sub Item 1']"));
//			elementUtil.moveToElement(driver, driver.findElement(By.xpath("//a[text()='Main Item 2']")));
//			elementUtil.moveToElementAndClick(driver, elementToMove, elementToClick);
//			elementUtil.scrollToBottom(driver);
//
//			// Select dropdown
//			elementUtil.click(driver, driver.findElement(By.xpath("//span[text()='Select Menu']")));
//			WebElement selectDrpdwn=driver.findElement(By.xpath("//select[@id='oldSelectMenu']"));
//			List<String>observeddropdownValues =elementUtil.getDropDownValues(selectDrpdwn);
//			elementUtil.selectDropDownByValue(driver,driver.findElement(By.xpath("//select[@id='cars']")),"audi");
//			String dropdownValue=elementUtil.getSelectedDropDownValue(driver, driver.findElement(By.xpath("//select[@id='cars']")));
//			equals(observeddropdownValues.contains(dropdownValue));
//			elementUtil.selectDropDownByIndex(driver,selectDrpdwn,9);
//			String dropdownValue1=elementUtil.getSelectedDropDownValue(driver, selectDrpdwn);
//			equals(observeddropdownValues.contains(dropdownValue1));
//			elementUtil.selectDropDownByVisibleText(driver,selectDrpdwn,"Black");
//			String dropdownValue2=elementUtil.getSelectedDropDownValue(driver, selectDrpdwn);
//			equals(observeddropdownValues.contains(dropdownValue2));
//
//			// Drag and drop
//			elementUtil.scrollTillElement(driver, driver.findElement(By.xpath("//div[text()='Interactions']")));
//			elementUtil.click(driver, driver.findElement(By.xpath("//div[text()='Interactions']")));
//			WebElement droppableMenu=driver.findElement(By.xpath("//span[text()='Droppable']"));
//			elementUtil.explicitWaitForElementToBeVisible(driver,droppableMenu );
//			elementUtil.click(driver, droppableMenu);
//			WebElement dragElement=driver.findElement(By.xpath("//div[@id='simpleDropContainer']//div[@id='draggable']"));
//			WebElement dropElement=driver.findElement(By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']"));
//			boolean isDragElementVisible=elementUtil.elementIsVisible(driver,By.xpath("//div[@id='simpleDropContainer']//div[@id='draggable']"));
//			assertTrue(isDragElementVisible);
//			elementUtil.dragAndDrop(driver, dragElement, dropElement);
//			By dropHereBy =  By.xpath("//div[@id='simpleDropContainer']//p[text()='Drop here']");
//			elementUtil.explicitWaitForElementToBeInVisible(driver, dropHereBy);
//			elementUtil.scrollToBottom(driver);
//			elementUtil.scrollToTop(driver);
//			String title=elementUtil.getTitle(driver);
//			assertEquals("ToolsQA",title);
//
//			driver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
//			By chkboxDemo=By.xpath("//input[@id='isAgeSelected']");
//			WebElement checkBoxDemo = driver.findElement(chkboxDemo);
//			elementUtil.scrollTillElement(driver, checkBoxDemo);
//			boolean ischkboxDemoDisplayed=elementUtil.elementISelected(checkBoxDemo);
//			assertFalse(ischkboxDemoDisplayed);
//			elementUtil.selectCheckBox(driver,checkBoxDemo);
//			Thread.sleep(1000);
//			boolean ischeckboxDemoDisplayed=elementUtil.elementISelected(checkBoxDemo);
//			assertTrue(ischeckboxDemoDisplayed);
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		finally {
//			driver.close();
//		}
	}
}
