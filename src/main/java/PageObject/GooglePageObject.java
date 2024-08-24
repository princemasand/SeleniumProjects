package PageObject;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import CommonLibs.Implementation.Base;

public class GooglePageObject extends Base{
	
	private static By search = By.xpath("//textarea[@class='gLFyf']");
	
	private static By clickOnGoogleSearch(String buttonName) {
		return By.xpath("(//input[@value='"+buttonName+"'])[2]");
	}
	
	public static void openGoogleURL() {
		navigateToURL("https://www.google.co.in/");
	}
	public static void searchTest(String Search) throws IOException, InterruptedException {
		click(search);
		logReport(Status.PASS, "Clicked on searchBox",true);
		assertEquals(false, false);
		javaScriptSendkeys(search, Search);
		type(search, Search);
		logReport(Status.PASS, "Entered Text in SearchBox",true);
		logReport(Status.INFO, "Entered Text in SearchBox",true);
		assertEquals(false, false);
		logReport(Status.PASS, "Entered Text in SearchBox",true);
	}
	
	public static void clickOnGoogleButton(String searchButton) {
		waitUntilElementIsVisible(search, 2000);
		click(clickOnGoogleSearch(searchButton));
	}
	
}