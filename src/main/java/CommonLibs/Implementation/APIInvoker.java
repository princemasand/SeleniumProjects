package CommonLibs.Implementation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIInvoker extends Base {

	/**
	 * get json file data for body
	 * @param path
	 * @param nodeName
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static JSONObject[] getJsonData(String path, String nodeName) {
		Object obj = null;
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		FileReader fileReader = null;

		JSONParser jsonParser = new JSONParser();

		try {
			fileReader = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			obj = jsonParser.parse(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		jsonObject = (JSONObject) obj;
		jsonArray = (JSONArray) jsonObject.get(nodeName);

		JSONObject[] arr = new JSONObject[jsonArray.size()];

		for (int a = 0; a < jsonArray.size(); a++) {
			arr[a] = (JSONObject) jsonArray.get(a);
		}

		return arr;
	}

	/*
	 * @DataProvider(name = "data-provider") public JSONObject[]
	 * dataProviderMethod() { return
	 * APIInvoker.getJsonData(".\\src\\main\\resources\\Test Data\\TestData.json",
	 * "createOrder"); }
	 * 
	 * @Test (dataProvider = "data-provider") public void testMethod(JSONObject
	 * object) throws IOException, ParseException { System.out.println(object);
	 * 
	 * }
	 */

	/**
	 * Read JSON file data
	 * @param filePath
	 * @param key
	 * @return
	 */
	public static String readJSONFile(String filePath, String key) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = null;
		try {
			jsonNode = objectMapper.readTree(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonNode.get(key).asText();
	}
	
	/**
	 * read JSON data
	 * @param object
	 * @param key
	 * @return
	 */
	public static String readJSONData(JSONObject object, String key) {
		JSONObject jsonobject = new JSONObject(object);
		return (String) jsonobject.get(key);  
	}
	
	/**
	 * update JSONObject value
	 * @param object
	 * @param key
	 * @param newValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject updateJSONData(JSONObject object, String key, String newValue) {
		JSONObject js = new JSONObject(object);
	    js.remove(key);
	    js.put(key, newValue);
	    return js;
	}

	/**
	 * PUT API method with token
	 * 
	 * @param jsonData
	 * @param uri
	 * @return
	 */
	public static Response putMethod(String token, JSONObject jsonData, String uri) {
		logReport(Status.INFO, MarkupHelper.createLabel("PUT METHOD", ExtentColor.BLUE));
		logReport(Status.INFO, "URL: " + uri);
//		logReport(Status.INFO, MarkupHelper.createCodeBlock(jsonData, CodeLanguage.JSON));

		Response response = RestAssured.given().header("Authorization", "Bearer" + token).relaxedHTTPSValidation()
				.contentType(ContentType.JSON).body(jsonData).when().put(uri);

		logReport(Status.INFO, MarkupHelper.createLabel("GET RESPONSE", ExtentColor.GREEN));
		logReport(Status.INFO, "Response Status Code: " + response.getStatusCode());
		saveResponse(response.asPrettyString());
		return response;
	}

	/**
	 * POST API method with token
	 * 
	 * @param jsonData
	 * @param uri
	 * @return
	 */
	public static Response postMethod(String token, JSONObject jsonData, String uri) {
		logReport(Status.INFO, MarkupHelper.createLabel("POST METHOD", ExtentColor.BLUE));
		logReport(Status.INFO, "URL: " + uri);
//		logReport(Status.INFO, MarkupHelper.createCodeBlock(jsonData, CodeLanguage.JSON));

		Response response = RestAssured.given().header("Authorization", "Bearer" + token).relaxedHTTPSValidation()
				.contentType(ContentType.JSON).body(jsonData).when().post(uri);

		logReport(Status.INFO, MarkupHelper.createLabel("GET RESPONSE", ExtentColor.GREEN));
		logReport(Status.INFO, "Response Status Code: " + response.getStatusCode());
		saveResponse(response.asPrettyString());
		return response;
	}
	
	/**
	 * GET API method with token
	 * 
	 * @param token
	 * @param uri
	 * @return
	 */
	public static String getMethod(String token, String uri) {
		logReport(Status.INFO, MarkupHelper.createLabel("GET METHOD", ExtentColor.BLUE));
		logReport(Status.INFO, "URL: " + uri);

		Response response = RestAssured.given().header("Authorization", "Bearer" + token).relaxedHTTPSValidation()
				.contentType(ContentType.JSON).when().get(uri);

		logReport(Status.INFO, MarkupHelper.createLabel("GET RESPONSE", ExtentColor.GREEN));
		logReport(Status.INFO, "Response Status Code: " + response.getStatusCode());
		saveResponse(response.asPrettyString());
		return response.asString();
	}

	/**
	 * GET API method
	 * 
	 * @param uri
	 * @return
	 */
	public static String getMethod(String uri) {
		logReport(Status.INFO, MarkupHelper.createLabel("GET METHOD", ExtentColor.BLUE));
		logReport(Status.INFO, "URL: " + uri);

		Response response = RestAssured.when().get(uri);

		logReport(Status.INFO, MarkupHelper.createLabel("GET RESPONSE", ExtentColor.GREEN));
		logReport(Status.INFO, "Response Status Code: " + response.getStatusCode());
		saveResponse(response.asPrettyString());
		return response.asString();
	}

	/**
	 * DELETE API method
	 * 
	 * @param uri
	 * @return
	 */
	public static Response deleteMethod(String uri) {
		logReport(Status.INFO, MarkupHelper.createLabel("DELETE METHOD", ExtentColor.BLUE));
		logReport(Status.INFO, "URL: " + uri);

		Response response = RestAssured.when().delete(uri);

		logReport(Status.INFO, MarkupHelper.createLabel("GET RESPONSE", ExtentColor.GREEN));
		logReport(Status.INFO, "Response Status Code: " + response.getStatusCode());
		return response;
	}

	/**
	 * Save API response in text file
	 * 
	 * @param response
	 * @throws IOException
	 */
	public static void saveResponse(String response) {
		String responseBody = response;
		String FileName = UUID.randomUUID().toString();
		String path = filePath + "/files/" + FileName + ".txt";
		try {
			Files.write(Paths.get(path), responseBody.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create JSOBBody from Map
	 * 
	 * @param map
	 * @return
	 */
	public static JSONObject requestBody(Map<String, Object> map) {
		JSONObject request = new JSONObject(map);
		return request;
	}

}
