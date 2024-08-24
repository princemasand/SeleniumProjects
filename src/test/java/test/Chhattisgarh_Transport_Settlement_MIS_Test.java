package test;

import org.testng.annotations.Test;

import PageObject.MerchantLoginPageObject;
import PageObject.com_Chhattisgarh_Transport_Settlement_MIS;

public class Chhattisgarh_Transport_Settlement_MIS_Test {
	@Test
	public void main() throws InterruptedException {
		MerchantLoginPageObject.merchantLogin();
		com_Chhattisgarh_Transport_Settlement_MIS.chhattisgarhTransportSettlementMISTool("Chhattisgarh Transport Settlement MIS");
	}

}
