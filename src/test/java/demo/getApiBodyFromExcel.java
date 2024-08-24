package demo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CommonLibs.Implementation.DataReader;

public class getApiBodyFromExcel {
	
	@DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
		return DataReader.getSheetTCData(".\\src\\main\\resources\\Test Data\\New Microsoft Excel Worksheet.xlsx", "Sheet1", "APIBODY");
    }    
	
	@Test (dataProvider = "data-provider")
    public void testMethod(String merIDkey, String merIDval, String custkey, String custval) {
	
		Map<Object, Object> map = new HashMap<>();
		map.put(custkey, custval);
		map.put(merIDkey, merIDval);		
		System.out.println(map);
		map.clear();
		
		
	}

}