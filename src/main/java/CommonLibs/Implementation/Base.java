package CommonLibs.Implementation;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static WebDriver driver;
	public static ExtentTest test = null;
	public static ExtentReports extentReports = null;
	public static ExtentSparkReporter spark = null;
	public static String startTime = null;
	public static String filePath = null;

	/**
	 * Click on Element
	 * @param element
	 */
	public static void click(By element) {
		Actions action = new Actions(driver);
		try {
			webElement(element).click();
		} catch (ElementNotInteractableException e) {
			scrollToElement(element);
			driver.findElement(element).click();
		} catch (StaleElementReferenceException e) {
			scrollToElement(element);
			action.moveToElement(webElement(element)).click().perform();
		} catch (Exception e) {
			scrollToElement(element);
			WebElement webElement = webElement(element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", webElement);
		}
	}

	/**
	 * click on Element by JavaScript
	 * @param element
	 */
	public static void javaScriptClick(By element) {
		try {
			WebElement webElement = webElement(element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", webElement);
		} catch (ElementNotInteractableException e) {
			scrollToElement(element);
			WebElement webElement = webElement(element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", webElement);
		}
	}

	/**
	 * click on Element by Action Class
	 * @param element
	 */
	public static void clickByActionClass(By element) {
		Actions action = new Actions(driver);
		try {
			action.moveToElement(webElement(element)).click().perform();
		} catch (ElementNotInteractableException e) {
			scrollToElement(element);
			action.moveToElement(webElement(element)).click().perform();
		}
	}

	/**
	 * Scroll To Element
	 * @param element
	 */
	public static void scrollToElement(By element) {
		Actions action = new Actions(driver);
		WebElement targetElement = webElement(element);
		action.moveToElement(targetElement).perform();
	}

	/**
	 * Select Dropdown Element by Name
	 * @param element
	 * @param visibleName
	 */
	public static void selectByName(By element, String visibleName) {
		WebElement selectElement = webElement(element);
		Select select = new Select(selectElement);
		select.selectByVisibleText(visibleName);
	}

	/**
	 * Select dropdown element by Value
	 * @param element
	 * @param value
	 */
	public static void selectByValue(By element, String value) {
		WebElement selectElement = webElement(element);
		Select select = new Select(selectElement);
		select.selectByValue(value);
	}

	/**
	 * Select dropdown element by Index
	 * @param element
	 * @param index
	 */
	public static void selectByIndex(By element, int index) {
		WebElement selectElement = webElement(element);
		Select select = new Select(selectElement);
		select.selectByIndex(index);
	}

	/**
	 * Switch To Frame by Element
	 * @param element
	 */
	public static void switchToFrameByElement(By element) {
		WebElement iframeElement = webElement(element);
		driver.switchTo().frame(iframeElement);
	}

	/**
	 * Switch To Frame by Index
	 * @param index
	 */
	public static void switchToFrameByIndex(int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * Switch To Frame by Name and ID
	 * @param name
	 */
	public static void switchToFrameByName(String name) {
		driver.switchTo().frame(name);
	}

	/**
	 * Switch To Main Frame
	 */
	public static void switchToMainFrame() {
		driver.switchTo().defaultContent();
	}

	/**
	 * Send text in field
	 * @param element
	 * @param message
	 */
	public static void sendkeys(By element, String message) {
		webElement(element).sendKeys(message);
	}

	/**
	 * Send single and multiple keys function on element
	 * @param element
	 * @param keys
	 */
	public static void sendkeys(By element, CharSequence... keysToSend) {
		webElement(element).sendKeys(keysToSend);
	}

	/**
	 * clear text field
	 * @param element
	 */
	public static void clear(By element) {
		webElement(element).clear();
	}
	
	/**
	 * Send text in field by javaScript
	 * @param element
	 * @param message
	 */
	public static void javaScriptSendkeys(By element, String message) {
		WebElement webele = webElement(element);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+message+"';", webele);
	}
	
	/**
	 * Type on given element
	 * @param element
	 * @param value
	 */
	public static void type(By element, CharSequence... value) {
		webElement(element).sendKeys(value);
	}

	/**
	 * Navigate to URL
	 * @param URL
	 */
	public static void navigateToURL() {
		String environment = getConfigProperty.getProperty("env");
		if (environment.equals("uat_url")) {
			driver.get(getConfigProperty.getProperty("uat_url"));
		} else if (environment.equals("prepod_url")) {
			driver.get(getConfigProperty.getProperty("prepod_url"));
		} else if (environment.equals("uat_transaction")) {
			driver.get(getConfigProperty.getProperty("uat_transaction"));
		} else if (environment.equals("uat_admin")) {
			driver.get(getConfigProperty.getProperty("uat_admin"));
		} else if (environment.equals("uat_merchant")) {
			driver.get(getConfigProperty.getProperty("uat_merchant"));
		}
	}

	/**
	 * Navigate to URL
	 * @param URL
	 * @throws IOException
	 */
	public static void navigateToURL(String URL) {
		driver.get(URL);
	}

	/**
	 * Double click on Element
	 * @param element
	 */
	public static void doubleClick(By element) {
		Actions actions = new Actions(driver);
		WebElement elementLocator = webElement(element);

		try {
			actions.moveToElement(elementLocator).doubleClick(elementLocator).perform();
		} catch (ElementNotInteractableException e) {
			scrollToElement(element);
			actions.moveToElement(elementLocator).doubleClick(elementLocator).perform();
		}
	}

	/**
	 * Get Title of the page
	 * @return
	 */
	public static String getTitle() {
		return driver.getTitle();
	}
	
	/**
	 * Get Current URL of the page
	 * @return
	 */
	public static String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Get Text of the Element
	 * @param element
	 * @return
	 */
	public static String getText(By element) {
		return webElement(element).getText();
	}

	/**
	 * Get Text of the Element using JavaScript
	 * @param element
	 * @return
	 */
	public static String getTextUsingJavaScript(By element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return arguments[0].innerText;", webElement(element));
	}

	/**
	 * Refresh the page
	 */
	public static void refresh() {
		driver.navigate().refresh();
	}

	/**
	 * Get WebElement of element
	 * @param element
	 * @return
	 */
	public static WebElement webElement(By element) {
		return driver.findElement(element);
	}

	/**
	 * Get all list of WebElements
	 * @param elements
	 * @return
	 */
	public static List<WebElement> getListOfWebElements(By elements) {
		return driver.findElements(elements);
	}

	/**
	 * Mouse Over on the Element
	 * @param element
	 */
	public static void mouseOver(By element) {
		Actions actions = new Actions(driver);
		WebElement elementLocator = webElement(element);
		try {
			actions.moveToElement(elementLocator).perform();
		} catch (ElementNotInteractableException e) {
			scrollToElement(element);
			actions.moveToElement(elementLocator).perform();
		}
	}

	/**
	 * Object Assertion check actual and expected
	 * @param actualObject
	 * @param expectedObject
	 */
	public static void verifyEquals(Object actualObject, Object expectedObject) {
		Assert.assertEquals(actualObject, expectedObject);
	}

	/**
	 * Element is Enabled or Not
	 * @param element
	 * @return
	 */
	public static boolean isEnabled(By element) {
		try {
			return webElement(element).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Element is Displayed or Not
	 * @param element
	 * @return
	 */
	public static boolean isDisplayed(By element) {
		try {
			return webElement(element).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Element is Selected or Not
	 * @param element
	 * @return
	 */
	public static boolean isSelected(By element) {
		try {
			return webElement(element).isSelected();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Wait Until Element Is Visible
	 * @param element
	 * @param i
	 */
	public static void waitUntilElementIsVisible(By element, int i) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(i)).until(ExpectedConditions.visibilityOfElementLocated(element));
		
		} catch (Exception e) {
		}
	}

	/**
	 * Wait Until Element Supress
	 * @param element
	 * @param waitTime
	 */
	public static void waitUntilElementSupress(By element, Duration waitTime) {
		try {
			new WebDriverWait(driver, waitTime).until(ExpectedConditions.invisibilityOfElementLocated(element));
		} catch (Exception e) {
		}
	}

	/**
	 * Wait Until Presence Of Element
	 * @param element
	 * @param waitTime
	 */
	public static void waitUntilPresenceOfElement(By element, Duration waitTime) {
		try {
			new WebDriverWait(driver, waitTime).until(ExpectedConditions.presenceOfElementLocated(element));
		} catch (Exception e) {
		}
	}

	/**
	 * Get Alert text
	 * @return
	 */
	public static String getAlertText() {
		return driver.switchTo().alert().getText();
	}

	/**
	 * Accept the Alert
	 */
	public static void acceptAlert() {
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
		}
	}

	/**
	 * Dismiss the Alert
	 */
	public static void dismissAlert() {
		try {
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {
		}
	}

	/**
	 * Send text in Alert
	 * @param message
	 */
	public static void alertSendkeys(String message) {
		driver.switchTo().alert().sendKeys(message);
	}

	/**
	 * driver sleep
	 * @param timeOut
	 */
	public static void hardPause(int timeOut) {
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get window handle
	 * @return
	 */
	public static String getWindowHandle() {
		return driver.getWindowHandle();
	}
	
	/**
	 * get window handles
	 * @return
	 */
	public static Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	/**
	 * Switch between two window only
	 */
	public static void switchToWindow() {
		Set<String> handleSet = driver.getWindowHandles();

		for (String handleString : handleSet) {
			if (!handleString.equals(getWindowHandle())) {
				driver.switchTo().window(handleString);
			}
		}
	}

	/**
	 * Drag and Drop by Element
	 * @param fromElement
	 * @param toElement
	 */
	public static void dragAndDrop(By fromElement, By toElement) {
		Actions action = new Actions(driver);

		WebElement FromElement = webElement(fromElement);
		WebElement ToElement = webElement(toElement);

		Action dragAndDrop = action.clickAndHold(FromElement).moveToElement(ToElement).release(ToElement).build();

		dragAndDrop.perform();
	}

	/**
	 * Drag And Drop by X and Y (Co-ordinates) offset
	 * @param element
	 * @param xOffset
	 * @param yOffset
	 */
	public static void dragAndDropOffset(By element, int xOffset, int yOffset) {
		Actions action = new Actions(driver);
		WebElement Element = webElement(element);
		action.dragAndDropBy(Element, xOffset, yOffset).build().perform();
	}
	
	/**
	 * Open headless browser using Value mentioned in property file
	 */
	public static void openHeadlessDriver() {
		String browserName = getConfigProperty.getProperty("browser");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(chromeOptions);
		} else if (browserName.equals("edge")) {  
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--headless");
			edgeOptions.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(edgeOptions);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	/**
	 * Open browser using Value mentioned in property file
	 */
	public static void openDriver() {
		String browserName = getConfigProperty.getProperty("browser");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
		} else if (browserName.equals("edge")) {  
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	/**
	 * Close driver
	 */
	public static void closeDriver() {
		driver.close();
	}
	
	/**
	 * Quit driver
	 */
	public static void quitDriver() {
		driver.quit();
	}

	/**
	 * Generate report environment BeforeSuite
	 */
	@BeforeSuite
	public static void generateReport() {
		String timeString = "_"+DateTimeFormatter.ofPattern("dd_MM_YYYY_HH_mm_ss").format(LocalDateTime.now());
		filePath = System.getProperty("user.dir") + "/Reports/executionReport"+timeString;
		
		new File(filePath).mkdir();
		new File(filePath + "/screenshots").mkdir();
		new File(filePath + "/files").mkdir();
	
		extentReports = new ExtentReports();
		spark = new ExtentSparkReporter(filePath+ "/Report.html");
		extentReports.attachReporter(spark);
		spark.config().setReportName("Automation Test Result");		
		startTime = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a").format(LocalDateTime.now());
	}

	/**
	 * Start Test
	 * @param testCaseName
	 * @param category
	 */
	public static void startTest(String testCaseName, String category) {
		test = extentReports.createTest(testCaseName).assignCategory(category).
				assignAuthor(Thread.currentThread().getStackTrace()[2].getClassName());
	}
	
	/**
	 * Take a screenshot
	 * @return
	 * @throws IOException
	 */
	public static String takeScreenShot() {
		String FileName = UUID.randomUUID().toString();
		String path = filePath + "/screenshots/" + FileName + ".png";
		String retPath = "./screenshots/" + FileName + ".png";

		try {
			File File = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(File, new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retPath;
	}
	
    /**
     * getREsult for API
     * @param result
     */
//	@AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
        extentReports.flush();
    }
	
	/**
	 * End Test
	 * @param result
	 */
	public static void endTest(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				test.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot()).build());
				logReport(Status.FAIL, "Test Failed");
			} catch (Exception e) {
				logReport(Status.FAIL, result.getThrowable());
				e.printStackTrace();
			}
		}

		if (result.getStatus() == ITestResult.SKIP) {
			try {
				test.fail("Test Skipped", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot()).build());
				logReport(Status.SKIP, "Test Skipped");
			} catch (Exception e) {
				logReport(Status.FAIL, result.getThrowable());
				e.printStackTrace();
			}
		}

		if (result.getStatus() == ITestResult.SUCCESS) {
			logReport(Status.PASS, "Test Passed");
		}
		
		extentReports.flush();
	}
	
	/**
	 * Log Report Status status, String message, boolean screenshot
	 * @param status
	 * @param message
	 * @param screenshot
	 * @throws IOException
	 */
	public static void logReport(Status status, String message, boolean screenshot) {
		if (screenshot) {
			test.log(status, message, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot()).build());

		} else {
			test.log(status, message);
		}
	}

	/**
	 * logReport by Status and String message
	 * @param status
	 * @param message
	 */
	public static void logReport(Status status, String message) {
		test.log(status, message);
		logStatus(status, message);

	}

	/**
	 * logReport by Status and Throwable
	 * @param status
	 * @param throwable
	 */
	public static void logReport(Status status, Throwable throwable) {
		test.log(status, throwable);
		logStatus(status, throwable);
	}
	
	/**
	 * logReport by Status and Markup
	 * @param status
	 * @param markup
	 */
	public static void logReport(Status status, Markup markup) {
		test.log(status, markup);
		logStatus(status, markup);
	}
	
	/**
	 * @param status
	 * @param message
	 */
	private static void logStatus(Status status, Object message) {
		if(status == Status.PASS || status == Status.INFO){
			System.out.println("["+Common.getTodaysDateTime()+"] "+message);
		}else{
			System.err.println("["+Common.getTodaysDateTime()+"] "+message);
		}
	}
	
}