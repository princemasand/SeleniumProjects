package demo;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CommonLibs.Implementation.Base;
import CommonLibs.Implementation.DataReader;

public class demo extends Base{
	@DataProvider(name = "data-provider")
	public Object[][] dataProviderMethod() {
		return DataReader.getSheetTCData(".\\src\\test\\resources\\Test Data\\New Microsoft Excel Worksheet.xlsx","Sheet1", "tc1");
	}

	@Test (dataProvider = "data-provider") 
	public void testMethod(String sheet) throws IOException {
		System.out.println(sheet);
	}
}