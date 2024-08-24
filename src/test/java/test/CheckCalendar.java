package test;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import CommonLibs.Implementation.Base;
import CommonLibs.Implementation.Utilities;

public class CheckCalendar extends Base {

	@Test
	public void checkCalendr() throws InterruptedException {
		openDriver();
		navigateToURL("https://uat.sbiepay.sbi/secure/admin/aggAdminLogin");
		driver.findElement(By.id("tlogin")).sendKeys("slsadmin@sbiepay.com");
		driver.findElement(By.id("tpassword")).sendKeys("Pass@123");
		Thread.sleep(5000);
		System.out.println("ok");
		Thread.sleep(5000);
		driver.findElement(By.id("Button2")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("vldbtn")).click();
		switchToFrameByIndex(1);
		driver.findElement(By.xpath("//a[text()='Merchant Registration']")).click();
//		driver.switchTo().frame("//frame[@name='showfrm']");
		driver.switchTo().defaultContent();
		switchToFrameByIndex(2);

		driver.findElement(By.xpath("//input[@id='dateSel']")).click();
		driver.findElement(By.xpath("//img[@class='ui-datepicker-trigger']")).click();
		
		List<String> dateSelected = Utilities.dateAdded("5");
		
		Select select = new Select(driver.findElement(By.className("ui-datepicker-year")));
		select.selectByVisibleText(dateSelected.get(0));
		
		Select select1 = new Select(driver.findElement(By.className("ui-datepicker-month")));
		select1.selectByVisibleText(dateSelected.get(1));
		
		driver.findElement(By.xpath("//td/a[text()='"+dateSelected.get(2)+"']")).click();
	}

}
