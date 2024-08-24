package demo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CommonLibs.Implementation.DataReader;

public class DataReaderDemo {
	
	@DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
		return DataReader.getSheetTCData(".\\src\\main\\java\\Test Data\\New XLSX Worksheet.xlsx", "DataReaderSheet", "Auto Pay");
    }
	
	@Test(dataProvider = "data-provider")
    public void testMethod(String merID, String custID, String Amount, String dc, String date) {
        System.out.println("MerID is: " + merID);
        System.out.println("CustId is: " + custID);
        System.out.println("Amount is: " + Amount);
        System.out.println("DC is: " + dc);
        System.out.println("Date is: " + date);
    }

}