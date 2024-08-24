package test;

import org.json.simple.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static PageObject.encryptionJSP.*;
import CommonLibs.Implementation.APIInvoker;
import CommonLibs.Implementation.Base;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Refund_API extends Base {

	@BeforeMethod
	public void beforeMethod() {
		startTest("Validate Refund API", "Refunf API");
	}

	@DataProvider(name = "data-provider")
	public JSONObject[] dataProviderMethod() {
		return APIInvoker.getJsonData(".\\src\\test\\resources\\Test Data\\RefundAPI.txt", "refundRequest");
	}

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "data-provider")
	public void apirefund(JSONObject object) {
		String queryRequest = APIInvoker.readJSONData(object, "queryRequest");

		openHeadlessDriver();
		navigateToEncryptDecryptURL();

		String encQueryData = fillEncryptionField("1000003", "Encrypt", queryRequest);
		object = APIInvoker.updateJSONData(object, "queryRequest", encQueryData);

		Response response = RestAssured.given().auth().none()
				.contentType("application/x-www-form-urlencoded; charset=utf-8").params(object).when()
				.post("https://uat.sbiepay.sbi/payagg/RefundMISReport/refundEnquiryAPI");

		verifyResponse(response.getStatusCode(), 200);

		String body = response.getBody().asPrettyString();
		APIInvoker.saveResponse(body);

		fillEncryptionField("1000003", "Decrypt", body);
		verifyResult(
				"1000003|3353665819561|8091514937910|2187666|vtriL|REFUND|Refund Despatched|10|0|30-07-2022 18:24:21|30-07-2022 18:26:40");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		getResult(result);
		quitDriver();
	}
}