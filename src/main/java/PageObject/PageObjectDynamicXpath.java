package PageObject;

import org.openqa.selenium.By;

import CommonLibs.Implementation.Base;

public class PageObjectDynamicXpath extends Base {
	
	public static By linksClick(String links) {
		return By.xpath("(//ul[@class='navigation clearfix']/li/a[text()='" + links + "'])[1]");
	}
	
	public static void practiceDynamicXpath(String links) throws InterruptedException {
		openDriver();
		navigateToURL("https://rahulshettyacademy.com/AutomationPractice/");
		switchToFrameByIndex(0);
		Thread.sleep(5000);
		click(linksClick(links));
	}
	

}
