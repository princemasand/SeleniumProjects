package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import CommonLibs.Implementation.Base;
import CommonLibs.Implementation.Utilities;

public class com_Chhattisgarh_Transport_Settlement_MIS extends Base {

	// Xpath:
	public static By selectionType(String toolLink) {
		return By.xpath("//a[normalize-space(text())='" + toolLink + "']");
	}
	
	public static By dateClick(String dateClick) {
		return By.xpath("//label[text()='" + dateClick + "']/parent::td/following-sibling::td[1]/img");
	}
	
	public static By specificDate(String dateSelected) {
		return By.xpath("//td/a[text()='" + dateSelected + "']");
	}	
	
	private static By toolText = By.xpath("//font[text()='Chhattisgarh Transport Settlement MIS']");
	
	private static By year = By.className("ui-datepicker-year");
	
	private static By month = By.className("ui-datepicker-month");
	
	private static By submitbtn = By.xpath("//input[@id='Submit']");
	
	private static By resetbtn = By.xpath("//input[@id='reset']");
	
	private static By xlsclick = By.xpath("(//td[@class='zebraStrip_lastTD']/a[1])[1]");
	
	private static By zipclick = By.xpath("(//td[@class='zebraStrip_lastTD']/a[1])[2]");
	
	public static List<String> dateSelected;

	public static void chhattisgarhTransportSettlementMISTool(String toolLink) throws InterruptedException {
		switchToFrameByIndex(1);
		click(selectionType(toolLink));
		switchToMainFrame();
		switchToFrameByIndex(2);
		System.out.println(getText(toolText));
		
		click(dateClick("From Date"));
		selectDate("-12");
		
		click(dateClick("To Date"));
		selectDate("-12");
		click(submitbtn);
		click(resetbtn);
//		click(xlsclick);
//		click(zipclick);
	}
	
	public static void selectDate(String date) {
		dateSelected = Utilities.dateAdded(date);
		selectByName(year, dateSelected.get(0));
		selectByName(month, dateSelected.get(1));
		click(specificDate(dateSelected.get(2)));
	}

}
