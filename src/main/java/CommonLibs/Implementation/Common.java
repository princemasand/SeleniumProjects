package CommonLibs.Implementation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Common extends Base{
	
	public static String getDownloadedFileName(String basePath, String timeAfter, int waitSec, String fileExtension, String fileNameContains)
			throws ParseException {
		boolean fileFound = false;
		String filePath = "";

		Calendar waitTime = Calendar.getInstance();
		waitTime.add(Calendar.SECOND, waitSec);

		File folder = new File(basePath);
		File[] list = folder.listFiles();

		for (File file : list) {
			System.out.println(file);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
			Date fileDate = sdf.parse(sdf.format(file.lastModified()));
			Date dateFrom = sdf.parse(timeAfter);

			if (file.getName().endsWith(fileExtension) && file.getName().contains(fileNameContains) && fileDate.after(dateFrom) || fileDate.equals(dateFrom)) {
				fileFound = true;
				filePath = file.getPath();
			}
			if (fileFound) {
				break;
			}
		}
		return filePath;
		
		/*
		 * ******************* NEED TO RUN IN MAIN TEST CLASS TO SELCT DATE USE THIS FORMAT ***************
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

	public static String getDownloadedFileName(String basePath, String timeAfter, int waitSec, String fileExtension)
			throws ParseException {
		boolean fileFound = false;
		String filePath = "";

		Calendar waitTime = Calendar.getInstance();
		waitTime.add(Calendar.SECOND, waitSec);

		File folder = new File(basePath);
		File[] list = folder.listFiles();

		for (File file : list) {
			System.out.println(file);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
			Date fileDate = sdf.parse(sdf.format(file.lastModified()));
			Date dateFrom = sdf.parse(timeAfter);

			if (file.getName().endsWith(fileExtension) && fileDate.after(dateFrom) || fileDate.equals(dateFrom)) {
				fileFound = true;
				filePath = file.getPath();
			}
			if (fileFound) {
				
				System.out.println("File Found");
				break;
			}else {
				System.out.println("File Not Found");
			}
		}
		return filePath;
	}
	
	/*
	getDownloadedFileName("C:\\Users\\Admin\\Downloads", Common.getTodaysDateTime(), 1000, ".exe");
	*/

	/**
	 * get Todays Date and Time
	 * @return
	 */
	public static String getTodaysDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}
	
	public static void fileUploadByRobot(String filePath) {
		StringSelection s = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		Robot r;
		try {
			r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_V);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public static void fileUploadBySendKeys(By element, String filePath) {
		WebElement Ele = webElement(element);
		Ele.sendKeys(filePath);
	}
	
	public static void fileUploadByAutoit(String filePath) {
		try {
			Runtime.getRuntime().exec(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
