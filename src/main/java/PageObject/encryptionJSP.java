package PageObject;

import org.openqa.selenium.By;

import CommonLibs.Implementation.Base;
import CommonLibs.Implementation.getConfigProperty;

public class encryptionJSP extends Base {

	private static By Encryption_Algorithm = By.xpath("//option[.='AES256']");

	private static By merchantid = By.id("merchantid");

	private static By encryptionFlag = By.name("encryptionFlag");

	private static By encDecValue = By.id("encDecValue");

	private static By selectionType(String radioButton) {
		return By.xpath("//input[@value='" + radioButton + "']");
	}

	private static By encDecResult = By.id("encDecResult");

	public static void navigateToEncryptDecryptURL() {
		navigateToURL(getConfigProperty.getProperty("encryptDecryptChecksum"));
	}

//	--->
	
	public static String fillEncryptionField(String mid, String radioButton, String body) {
		sendkeys(merchantid, mid);
		click(encryptionFlag);
		click(Encryption_Algorithm);
		sendkeys(encDecValue, body);
		click(selectionType(radioButton));
		return getText(encDecResult);
	}

	public static void verifyResult(String expected) {
		verifyEquals(getText(encDecResult), expected);
	}

	public static void verifyResponse(int statusCode, int responseCode) {
		verifyEquals(statusCode, responseCode);
	}
}
