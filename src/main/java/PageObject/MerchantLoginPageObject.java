package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import CommonLibs.Implementation.Base;

public class MerchantLoginPageObject extends Base {

	// Xpaths:
	private static By username = By.xpath("//input[@id='tlogin']");
	private static By password = By.xpath("//input[@id='tpassword']");
	private static By captcha = By.id("captcha");
	private static By btn1 = By.xpath("//*[@id=\"Button2\"]");

	private static By otpclick = By.xpath("//input[@id='totp']");
	private static By btn2 = By.xpath("//input[@id='vldbtn']");
	private static By logout = By.xpath("//a[text()='Logout']");

	public static void merchantLogin() throws InterruptedException {
		openDriver();
		navigateToURL("https://uat.sbiepay.sbi/secure/home");
		click(By.linkText("Login"));
		switchToFrameByIndex(2);
		
		sendkeys(username, "merchant@sbiepay.com");			//User 1
//		sendkeys(username, "merchant1@sbi.co.in");			//User 2
		sendkeys(password, "Pass@123");
		click(captcha);
		Thread.sleep(10000);
		click(btn1);
		switchToMainFrame();
		Thread.sleep(5000);
		click(otpclick);
		sendkeys((otpclick), Keys.BACK_SPACE, Keys.CONTROL + "z");
		click(btn2);
//		switchToFrameByIndex(0);
//		click(logout);
	}
}