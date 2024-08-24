package test;

import org.testng.annotations.Test;

import PageObject.MerchantLoginPageObject;

public class merchantTest{

	@Test
	public void main() throws InterruptedException {
		MerchantLoginPageObject.merchantLogin();
		
		
	}
}
