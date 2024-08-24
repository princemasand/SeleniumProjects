package test;

import org.testng.annotations.Test;

import CommonLibs.Implementation.Base;
import PageObject.MerchantLoginPageObject;
import PageObject.PageObjectDynamicXpath;
import PageObject.com_Chhattisgarh_Transport_Settlement_MIS;

public class DynamicXpathPractice{

	@Test
	public void main() throws InterruptedException {
		PageObjectDynamicXpath.practiceDynamicXpath("Learning paths");
	}

}
