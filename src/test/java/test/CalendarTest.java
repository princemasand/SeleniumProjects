package test;

import java.util.Calendar;

import org.testng.annotations.Test;

import CommonLibs.Implementation.Base;
import CommonLibs.Implementation.Utilities;

public class CalendarTest extends Base{
	@Test
	public void testt() {
		
		String date = Utilities.getAdjustedData("MMMMMMMMMMMMMMMMM", Calendar.DAY_OF_MONTH, Integer.parseInt("3"));
		System.out.println(date);
	}

}

