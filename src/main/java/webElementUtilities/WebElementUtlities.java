package webElementUtilities;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import exceptions.NoValueFoundInDropDown;

/**
 * Webelements generic methods
 *
 */
public class WebElementUtlities {

	private WebElementUtlities() {
		
	}
	
	/**
	 * Wait till the Webelement is visible Using Webelement
	 * @param driver
	 * @param element : Webelement
	 */
	public static void explicitWaitForElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void implicitWait(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
	}
	
	/**
	 * Wait till the element is visible Using By locator
	 * @param driver
	 * @param locator
	 */
	public static void explicitWaitForElementToBeVisible(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Wait till the element is visible By locator & adding external wait
	 * @param driver
	 * @param locator
	 * @param timeOut
	 */
	public static void explicitWaitForElementToBeVisible(WebDriver driver, By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Wait till the element is visible Using Webelement & adding external wait
	 * @param driver
	 * @param element : Webelement
	 * @param timeOut
	 */
	public static void explicitWaitForElementToBeVisible(WebDriver driver, WebElement element, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Wait till the element is invisible Using Webelement
	 * @param driver
	 * @param element : Webelement
	 */
	public static void explicitWaitForElementToBeInVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * Wait till the element is invisible Using By locator
	 * @param driver
	 * @param locator
	 */
	public static void explicitWaitForElementToBeInVisible(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	/**
	 * Wait till the element is invisible Using Webelement & adding external wait
	 * @param driver
	 * @param element : Webelement
	 * @param timeOut
	 */
	public static void explicitWaitForElementToBeInVisible(WebDriver driver, WebElement element, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * Wait till the element is invisible By locator & adding external wait
	 * @param driver
	 * @param locator
	 * @param timeOut
	 */
	public static void explicitWaitForElementToBeInVisible(WebDriver driver,  By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	/**
	 * Wait till the element is clickable
	 * @param driver
	 * @param element : Webelement
	 */
	public static void explicitWaitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wait till the all element is visible
	 * @param driver
	 * @param element : Webelement
	 */
	public static void explicitWaitForAllElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	/**
	 * Wait till the all element is visible
	 * @param driver
	 * @param element : Webelement
	 * @return 
	 * @return 
	 */
	public static void explicitWaitForAllElementToBeVisible(WebDriver driver, List<WebElement> element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	/**
	 * Set the text into the input field
	 * @param driver
	 * @param element : Webelement
	 * @param valueToBeEntered
	 */
	public static void setText(WebDriver driver, WebElement element, String valueToBeEntered) {
		explicitWaitForElementToBeVisible(driver, element);
		element.clear();
		element.sendKeys(valueToBeEntered);
	}

	/**
	 * Clear the text of input field
	 * @param driver
	 * @param element : Webelement
	 */
	public static void clearText(WebDriver driver, WebElement element) {
		explicitWaitForElementToBeVisible(driver, element);
		element.clear();
	}

	/**
	 * Click to the element
	 * @param driver
	 * @param element : Webelement
	 */
	public static void click(WebDriver driver, WebElement element) {
		explicitWaitForElementToBeVisible(driver, element);
		explicitWaitForElementToBeClickable(driver, element);
		element.click();
	}

	/**
	 * Get text from the input field
	 * @param driver
	 * @param element : Webelement
	 * @return
	 */
	public static String getText(WebDriver driver, WebElement element) {
		explicitWaitForElementToBeVisible(driver, element);
		String observedValue = element.getText();
		return observedValue;
	}

	/**
	 * Get value of the attribute.
	 * @param driver
	 * @param element : Webelement
	 * @return
	 */
	public static String getAttributeByValue(WebDriver driver, WebElement element) {
		explicitWaitForElementToBeVisible(driver, element);
		String observedValue = element.getAttribute("value");
		return observedValue;
	}

	/**
	 * Get attribute using the value
	 * @param driver
	 * @param element : Webelement
	 * @param value : The name of the attribute.
	 * @return
	 */
	public static String getAttributeUsingValue(WebDriver driver, WebElement element, String value) {
		explicitWaitForElementToBeVisible(driver, element);
		String observedValue = element.getAttribute(value);
		return observedValue;
	}

	/**
	 * Select the dropdown by value
	 * @param driver
	 * @param element : Webelement
	 * @param valueToBeSelect
	 */
	public static void selectDropDownByValue(WebDriver driver, WebElement element, String valueToBeSelect) {
		explicitWaitForElementToBeVisible(driver, element);
		Select select = new Select(element);
		select.selectByValue(valueToBeSelect);
	}

	/**
	 * Select the dropdown by visibility of text
	 * @param driver
	 * @param element : Webelement
	 * @param valueToBeSelect
	 */
	public static void selectDropDownByVisibleText(WebDriver driver, WebElement element, String valueToBeSelect) {
		explicitWaitForElementToBeVisible(driver, element);
		Select select = new Select(element);
		select.selectByVisibleText(valueToBeSelect);
	}

	/**
	 * Select the dropdown by Index
	 * @param driver
	 * @param element : Webelement
	 * @param index
	 */
	public static void selectDropDownByIndex(WebDriver driver, WebElement element, int index) {
		explicitWaitForElementToBeVisible(driver, element);
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public static void selectDropDownByInputDiv(WebDriver driver, WebElement drpDownElement, 
			WebElement textBox, WebElement txtElement, String value2Select) throws NoValueFoundInDropDown {
		explicitWaitForElementToBeVisible(driver, drpDownElement);

		click(driver, drpDownElement);
		setText(driver, textBox, value2Select);
		if (elementIsDisplayed(txtElement)) {
			String valueToBeSelectElement = txtElement.getText();
			if (value2Select.equals(valueToBeSelectElement))
				click(driver, txtElement);
		}else {
			throw new NoValueFoundInDropDown(value2Select+" is not found under the dropdown");
		}
	}

	public static void selectDropDownByVisibileValueDiv(WebDriver driver, WebElement drpDownElement, 
			List<WebElement> drpDownValues , String value2Select) throws NoValueFoundInDropDown {
		explicitWaitForElementToBeVisible(driver, drpDownElement);
		click(driver, drpDownElement);

		Iterator<WebElement> it = drpDownValues.iterator();
		boolean found=false;
		while (it.hasNext()) {
			WebElement wb = it.next();
			String option = wb.getText();
			if (option.equals(value2Select)) {
				wb.click();
				found=true;
				break;
			}
		}

		if (!found) {
			throw new NoValueFoundInDropDown(value2Select+" is not found under the dropdown");
		}
	}



	/**
	 * Get the selected value from dropdown
	 * @param driver
	 * @param element : Webelement
	 * @return
	 */
	public static String getSelectedDropDownValue(WebDriver driver, WebElement element) {
		explicitWaitForElementToBeVisible(driver, element);
		Select select = new Select(element);
		WebElement selectedOption = select.getFirstSelectedOption();
		String selectedValue = selectedOption.getText();
		return selectedValue;
	}

	/**
	 * Get all the values from dropdown
	 * @param element : Webelement
	 * @return
	 */
	public static List<String> getDropDownValues(WebElement element) {
		Select select = new Select(element);
		List<WebElement> dropDownvalues = select.getOptions();
		List<String> dropDownValues = dropDownvalues.stream().map(a -> a.getText().trim()).collect(Collectors.toList());
		return dropDownValues;
	}

	/**
	 * Move to element and click
	 * @param driver
	 * @param elementToHover
	 * @param elementToClick
	 */
	public static void moveToElementAndClick(WebDriver driver, WebElement elementToHover, WebElement elementToClick) {
		Actions action = new Actions(driver);
		action.moveToElement(elementToHover).click(elementToClick).build().perform();
	}

	/**
	 * Double click on element
	 * @param driver
	 * @param element : Webelement
	 */
	public static void doubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
	}

	/**
	 * Right click on element
	 * @param driver
	 * @param element : Webelement
	 */
	public static void rightClickOnWebElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
	}

	/**
	 * Drag a element and Drop to a target element
	 * @param driver
	 * @param dragElement
	 * @param dropElement
	 */
	public static void dragAndDrop(WebDriver driver, WebElement dragElement, WebElement dropElement) {
		Actions action = new Actions(driver);
		// action.clickAndHold(dragElement).moveToElement(dropElement).release(dropElement).build().perform();
		action.dragAndDrop(dragElement, dropElement).build().perform();
	}

	/**
	 * Move to element
	 * @param driver
	 * @param element : Webelement
	 */
	public static void moveToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	/**
	 * Select checkbox
	 * @param driver
	 * @param element : Webelement
	 */
	public static void selectCheckBox(WebDriver driver, WebElement element) {
		explicitWaitForElementToBeVisible(driver, element);
		boolean isCheckBoxSelected = element.isSelected();
		if (!isCheckBoxSelected)
			element.click();
	}

	/**
	 * Check if the element is displayed or not
	 * @param element : Webelement
	 * @return
	 */
	public static boolean elementIsDisplayed(WebElement element) {
		boolean isElementDisplayed = element.isDisplayed();
		return isElementDisplayed;
	}

	/**
	 * Check if the element is displayed or not
	 * @param element : Webelement
	 * @return
	 */
	public static boolean elementISelected(WebElement element) {
		boolean isElementDisplayed = element.isSelected();
		return isElementDisplayed;
	}

	/**
	 * Check if the element is enabled or not
	 * @param element : Webelement
	 * @return
	 */
	public static boolean elementIsEnabled(WebElement element) {
		boolean isElementEnabled = element.isEnabled();
		return isElementEnabled;
	}

	/**
	 * Check if the element is Visible or not
	 * @param driver
	 * @param locator
	 * @return
	 */

	public static boolean elementIsVisible(WebDriver driver,By locator) {
		boolean isElementVisible=false;	
		int elementCount = driver.findElements(locator).size();
		if (elementCount>=1)
			isElementVisible=true;

		return isElementVisible;
	}

	/**
	 * Get elements count
	 * @param driver
	 * @param element : Webelement
	 * @return
	 */
	public static int getElementsCount(WebDriver driver, List<WebElement> element) {
		int elementCount = element.size();
		return elementCount;
	}

	/**
	 * Get element count
	 * @param driver
	 * @param locator
	 * @return
	 */
	public static int getElementCount(WebDriver driver,By locator) {
		int elementCount = driver.findElements(locator).size();
		return elementCount;
	}

	/**
	 * Get the text of All elements
	 * @param driver
	 * @param element :List<Webelement>
	 * @return
	 */
	public static List<String> getElementsText(WebDriver driver, List<WebElement> element) {
		List<String> elementValues = element.stream().map(a -> a.getText().trim()).collect(Collectors.toList());
		return elementValues;
	}

	/**
	 * Check Whether the element is Exist or not
	 * @param element :List<Webelement>
	 * @param value
	 * @return
	 */
	public static boolean isValueExist(List<WebElement> element, String value) {
		List<String> elementValues = element.stream().map(a -> a.getText().trim()).collect(Collectors.toList());
		boolean isValueExist = elementValues.contains(value);

		return isValueExist;
	}

	/**
	 * Set the text into input field and Press tab
	 * @param driver
	 * @param element : Webelement
	 * @param valueToBeEntered
	 */
	public static void setTextAndTab(WebDriver driver, WebElement element, String valueToBeEntered) {
		explicitWaitForElementToBeVisible(driver, element);
		element.clear();
		element.sendKeys(valueToBeEntered);
		keyPress(element, "tab");
	}

	/**
	 * Set the text into input field and Press Escape
	 * @param driver
	 * @param element: Webelement
	 * @param valueToBeEntered
	 */
	public static void setTextAndEscape(WebDriver driver, WebElement element, String valueToBeEntered) {
		explicitWaitForElementToBeVisible(driver, element);
		element.clear();
		element.sendKeys(valueToBeEntered);
		keyPress(element, "esc");
	}

	/**
	 * press keys that aren't text
	 * @param element
	 * @param key
	 */
	public static void keyPress(WebElement element, String key) {
		Keys keyPress;
		key = key.trim().toUpperCase();
		switch (key) {
		case "ESCAPE":
			keyPress = Keys.ESCAPE;
			break;
		case "ESC":
			keyPress = Keys.ESCAPE;
			break;
		case "TAB":
			keyPress = Keys.TAB;
			break;
		case "DELETE":
			keyPress = Keys.DELETE;
			break;
		case "DEL":
			keyPress = Keys.DELETE;
			break;
		case "ARROWLEFT":
			keyPress = Keys.ARROW_LEFT;
			break;
		case "ARROWRIGHT":
			keyPress = Keys.ARROW_RIGHT;
			break;
		case "ARROWUP":
			keyPress = Keys.ARROW_UP;
			break;
		case "ARROWDOWN":
			keyPress = Keys.ARROW_DOWN;
			break;
		case "UP":
			keyPress = Keys.UP;
			break;
		case "DOWN":
			keyPress = Keys.DOWN;
			break;
		case "CLEAR":
			keyPress = Keys.CLEAR;
			break;
		case "CLR":
			keyPress = Keys.CLEAR;
			break;
		case "ENTER":
			keyPress = Keys.ENTER;
			break;
		case "CANCEL":
			keyPress = Keys.CANCEL;
			break;
		default:
			keyPress = null;
			break;
		}
		element.sendKeys(keyPress);
	}

	/**
	 * Set text into input field using Action class
	 * @param driver
	 * @param element: Webelement
	 * @param valueToBeEntered
	 */
	public static void setTextUsingActionClass(WebDriver driver, WebElement element, String valueToBeEntered) {
		Actions action = new Actions(driver);
		action.click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build()
		.perform();
		action.sendKeys(element, valueToBeEntered).sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * Clear text into input field using action class
	 * @param driver
	 * @param element: Webelement
	 */
	public static void clearTextUsingActionClass(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.click(element).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).build()
		.perform();
	}

	/**
	 * Click on element using Action Class
	 * @param driver
	 * @param element
	 */
	public static void clickUsingActionClass(WebDriver driver, WebElement element) {
		Actions act =  new Actions(driver);
		act.moveToElement(element).click().perform();
	}

	/**
	 * Set text of input field using Javascript
	 * @param driver
	 * @param element: Webelement
	 * @param valueToBeEntered
	 */
	public static void setTextUsingJavascript(WebDriver driver, WebElement element, String valueToBeEntered) {
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].value='" + valueToBeEntered + "' ;", element);
	}

	/**
	 * Clear text of input field using javascript
	 * @param driver
	 * @param element: Webelement
	 */
	public static void clearTextUsingJavaScript(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].value = '';", element);
	}

	/**
	 * Click on element using javascript
	 * @param driver
	 * @param element: Webelement
	 */
	public static void clickUsingJavaScript(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	/**
	 * Scroll till the element
	 * @param driver
	 * @param element: Webelement
	 */
	public static void scrollTillElement(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Get title of web page
	 * @param driver
	 * @return
	 */
	public static String getTitle(WebDriver driver) {
		String title = driver.getTitle();
		return title;
	}

	/**
	 * Scroll to top
	 * @param driver
	 */
	public static void scrollToTop(WebDriver driver) {
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollTo(0, 0)");
	}

	/**
	 * Scroll to bottom
	 * @param driver
	 */
	public static void scrollToBottom(WebDriver driver) {
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * Select Drop down value By entering text under auto suggestion text box & click visible entered text option
	 * @param driver
	 * @param drpDownElement
	 * @param textBox
	 * @param txtElement
	 * @param value2Select
	 * @throws NoValueFoundInDropDown
	 */
	public static void selectDropDownByInputDiv(WebDriver driver,WebElement drpDownElement, WebElement textBox, By txtElement, String value2Select) throws NoValueFoundInDropDown {
		explicitWaitForElementToBeVisible(driver, drpDownElement);
		click(driver, drpDownElement);
		setText(driver, textBox, value2Select);
		WebElement element = driver.findElement(txtElement);

		if (elementIsDisplayed(element)) {
			String valueToBeSelectElement = element.getText();
			if (value2Select.equals(valueToBeSelectElement))
				click(driver, element);
		} else {
			throw new NoValueFoundInDropDown(value2Select + " is not found under the dropdown");
		}

	}
}
