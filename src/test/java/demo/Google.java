package demo;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import CommonLibs.Implementation.Base;
import CommonLibs.Implementation.DataReader;
import PageObject.GooglePageObject;

public class Google extends Base{
	
	@BeforeMethod
	public void beforeMethod() {
		startTest("Google", "Test");
		openDriver();
	}

	@DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
		return DataReader.getSheetTCData(".\\src\\main\\resources\\Test Data\\New Microsoft Excel Worksheet.xlsx", "DataReaderSheet", "Google");
    }    
	
	@Test (dataProvider = "data-provider")
    public void testMethod(String merID, String custID, String Amount, String dc, String date) throws IOException, InterruptedException {
		GooglePageObject.openGoogleURL();
		GooglePageObject.clickOnGoogleButton("Google Search");
		GooglePageObject.searchTest(merID);
	}
	
	@AfterMethod
	public void AfterMethod(ITestResult result) {
		endTest(result);
		quitDriver();
	}

}