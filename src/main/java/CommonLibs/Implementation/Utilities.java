	package CommonLibs.Implementation;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Utilities {
	public static PropertiesConfiguration config = null;

	public static String getProperty(String key) throws IOException {
		FileReader reader = new FileReader(".\\src\\main\\resources\\Config.properties");
		Properties props = new Properties();
		props.load(reader);
		return props.getProperty(key);
	}
	
	public static void setConfigProperty(String key, String value) {
		try {
			config = new PropertiesConfiguration(".\\src\\main\\resources\\Config.properties");
			config.setProperty(key, value);
			config.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static String getAdjustedData(String format, int unit, int count) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date currentDate = new Date();

		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(unit, count);

		Date currentDatesOne = c.getTime();
		return sdf.format(currentDatesOne);
	}

	public static List<String> dateAdded(String count) {
		String year = getAdjustedData("YYYY", Calendar.DAY_OF_MONTH, Integer.parseInt(count));
		String month = getAdjustedData("MMM", Calendar.DAY_OF_MONTH, Integer.parseInt(count));
		String date = getAdjustedData("d", Calendar.DAY_OF_MONTH, Integer.parseInt(count));

		List<String> givenDateList = new ArrayList<String>();

		givenDateList.add(year);
		givenDateList.add(month);
		givenDateList.add(date);

		return givenDateList;
	
	
		/*
		 * ******************* NEED TO RUN IN MAIN TEST CLASS TO SELECT DATE USE THIS FORMAT ***************
		 * 
		 * public void testData() {
		 * 
		 * click(on calendar icon);
		 * 
		 * List<String> dateSelected = dateAdded("-2");
		 * 
		 * String YEAR= dateSelected.get(0); // year String 
		 * MONTH =dateSelected.get(1);       // month String
		 * Month String DATE = dateSelected.get(2); // date string
		 * 
		 * selectByName(element, YEAR); 
		 * selectByName(element, Month); 
		 * ClickAction(date);
		 */
	
	}

}