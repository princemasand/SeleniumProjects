package test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import CommonLibs.Implementation.Base;

public class WindowHandle_Demo extends Base {

	@Test
	public void set() {

		openDriver();
		navigateToURL("https://omayo.blogspot.com/");

		driver.findElement(By.xpath("//a[text()='Open a popup window']")).click();
		String parent = getWindowHandle();
		switchToWindow();
		System.out.println(driver.findElement(By.xpath("//h3[text()='New Window']")).getText());
//		switchToWindow();
		closeDriver();switchToWindow();
//		
		closeDriver(); 
	}
}