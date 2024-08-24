package test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BrokenLinks {
	public static void main(String[] args) throws MalformedURLException, IOException {
		WebDriver driver = new ChromeDriver();

		//Step 1 - Is to get all urls tied up to the links using Selenium 
		//Step 2 - Java methods will call URL's and gets you the status code
		// if status code (is greater) > 400 then that url is not working
		// <a href="https://www.soapui.org/">SoapUI</a> | a[href*="soapui"]'

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		   //For Verifying Broken link for Single Element
//		String url1 = driver.findElement(By.cssSelector("a[href*=\"soapui\"]")).getAttribute("href");
//		String url1 = driver.findElement(By.cssSelector("a[href*='broken link']")).getAttribute("href");   - Broken Link
//			//CSSSelector - <a href="/favourites"> flex </a> | "<tagname>[href=’<href value>’]” - "a[href='/favourites']"
//		HttpURLConnection conn = (HttpURLConnection) new URL(url1).openConnection();
//			//openConnection() - It comes from URL(url) class which is Predefined class. We can open connection & send the url & get the response
//			//HttpURLConnection - Is returntype of openConnection. Also casting it to that method.
//		conn.setRequestMethod("HEAD");
//		conn.connect();
//		int respCode = conn.getResponseCode();
//		System.out.println(respCode); - It'll Give 200

		   //For Verifying Broken link for Multiple Links	
		List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));	////li[@class='gf-li']/child::a - xpath
		SoftAssert a = new SoftAssert();
		for (WebElement link : links)	//Out of all links, In every iteration We're using one link. Returntype is WebElement 
		{
			String url = link.getAttribute("href");
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
				//openConnection() - It comes from URL(url) class which is Predefined class. We can open connection & send the url & get the response
				//HttpURLConnection - Is returntype of openConnection. Also casting it to that method.
			conn.setRequestMethod("HEAD");
			conn.connect();
			int respCode = conn.getResponseCode();
			System.out.println(respCode);
			if(respCode > 400)
			{
				System.out.println("The link with Text" + link.getText() + " is broken with code" + respCode);
				//Assert.assertTrue(false); - This line will fail immediately when it finds an broken link 	
			}
			//OR
			a.assertTrue(respCode < 400, "The link with Text" + link.getText() + " is broken with code" + respCode);
				//a. - is soft assert SoftAssert a = new SoftAssert(). link.getText() - specifies which all links failed.
		}
		a.assertAll();	//Whatever failures we stored in asserTrue then ultimately this line will fail that script. If it is passed then assertAll will pass your script.
		//This way we can avoid stopping of execution
	}
	private static Object getReturnCode(WebElement link) {
		return null;
	}
}