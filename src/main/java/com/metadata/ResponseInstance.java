package com.metadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import static com.jayway.restassured.RestAssured.given;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;
import org.testng.Reporter;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ResponseInstance {

	protected static Response resp = null;
	public static String assetSubType = "Empty";
	static LoggingUtils logger = new LoggingUtils();
	public static String searchContentID = null;
	public static String pageName = "shows";
	public static boolean trailer = false;
	public static String newEmailID = null;
	public static String newPassword = null;
	
	public static String AdvertiseId = "7e128be0-8f02-4eb4-93e4-e7382eb01d82";
	public static String GuestLanguage = "en-kn";

	public static Response getResponse() {
		resp = RestAssured.given().urlEncodingEnabled(false).when().get("url");
		return resp;
	}

	public static Response getResponse(String URL) {
		resp = RestAssured.given().urlEncodingEnabled(false).when().get(URL);
		System.out.println(resp.statusCode());
		return resp;
	}

	public static void traysTitle() {
		int numberOfTrays = getResponse().jsonPath().getList("buckets").size();
		for (int i = 1; i < numberOfTrays; i++) {
			System.out.println(getResponse().jsonPath().getString("buckets[" + i + "].title"));
		}
	}

	public static ArrayList<String> traysIndiviualTags(String NameOfTheTray) {
		ArrayList<String> Arraytags = new ArrayList<String>();
		int numberOfTrays = getResponse().jsonPath().getList("buckets").size();
		for (int i = 1; i < numberOfTrays; i++) {
			String TrayName = getResponse().jsonPath().getString("buckets[" + i + "].title");
			int numberOfitmesTrays = getResponse().jsonPath().getList("buckets[" + i + "].items").size();
			if (TrayName.equals(NameOfTheTray)) {
				for (int j = 0; j < numberOfitmesTrays; j++) {
					Arraytags.add(getResponse().jsonPath().getString("buckets[" + i + "].items[" + j + "].title"));
				}
				break;
			}
			if (Arraytags.size() > 0) {
				break;
			}
		}
		return Arraytags;
	}

	public static ArrayList<String> ContentOfViewAll() {
		ArrayList<String> contentFromViewAll = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			String page1 = "url" + i + "url";
			resp = RestAssured.given().urlEncodingEnabled(false).when().get(page1);
			int sizeOfAnItem = resp.jsonPath().getList("buckets[0].items").size();
			if (sizeOfAnItem > 0) {
				for (int j = 0; j < sizeOfAnItem; j++) {
					contentFromViewAll.add(resp.jsonPath().getString("buckets[0].items[" + j + "].title"));
					System.out.println(contentFromViewAll);
				}
			}
		}
		return contentFromViewAll;
	}

	public static ArrayList<String> getCollectionContent(String CollectionName) {

		ArrayList<String> contentList = new ArrayList<String>();
		int sizeOfAnCollection = 0;
		int NumberOfCollection = resp.jsonPath().getList("buckets[0].items").size();
		for (int i = 0; i < NumberOfCollection; i++) {
			if (resp.jsonPath().getString("").equals(CollectionName)) {
				sizeOfAnCollection = resp.jsonPath().getList("buckets[0].items").size();
				for (int j = 0; j < sizeOfAnCollection; j++) {
					contentList.add(resp.jsonPath().getString(""));
				}
			}
			if (sizeOfAnCollection > 0) {
				break;
			}
		}
		return contentList;
	}

//	BASAVARAJ
	public static Response getRECOResponse(String URL, String username, String password) {
		Response respp = null;
		String Uri = URL;
		respp = RestAssured.given()
				.headers("X-ACCESS-TOKEN", getXAccessToken(), "Authorization", getBearerToken(username, password))
				.when().get(Uri);
		System.out.println(respp.getBody());
		return respp;
	}

	/**
	 * Function to return X-ACCESS TOKEN
	 * 
	 * @param page
	 * @return
	 */
	public static String getXAccessToken() {
		try {
			Response respToken = null, respForKey = null;
			// get APi-KEY
			String Uri = "url";
			respForKey = RestAssured.given().urlEncodingEnabled(false).when().get(Uri);
			String xAccessToken = respForKey.jsonPath().getString("token");
			return xAccessToken;
		} catch (Exception e) {
			return "";
		}

	}
	
	/**
	 * Function to return X-ACCESS TOKEN from gwapi platform token
	 * 
	 * @param page
	 * @return
	 */
	public static String getXAccessTokenFromPlatformToken() {
		Response resp = null;
		String xAccessToken = "";
		String url = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("url");
		resp = RestAssured.given().urlEncodingEnabled(false).when().get(url);
		String respString = resp.getBody().asString();
		respString = respString.replace("\"gwapiPlatformToken\":\"\"", "");
		respString = respString.split("\"gwapiPlatformToken\":\"")[1];
		xAccessToken = respString.split("\"")[0];
		return xAccessToken;
	}

	public static String getXAccessToken1() {
		Response respToken = null, respForKey = null;
		// get APi-KEY
		String Uri = "url";
		respForKey = RestAssured.given().urlEncodingEnabled(false).when().get(Uri);
		String rawApiKey = respForKey.getBody().asString();
		String apiKeyInResponse = rawApiKey.substring(0, rawApiKey.indexOf("<br>airtel "));
		String finalApiKey = apiKeyInResponse.replaceAll("<br>rel - API-KEY : ", "");
		String UriForToken = "url";
		respToken = RestAssured.given().headers("API-KEY", finalApiKey).when().get(UriForToken);
		String xAccessToken = respToken.jsonPath().getString("X-ACCESS-TOKEN");
		return xAccessToken;
	}

	/**
	 * Function to return Bearer token
	 * 
	 * @param email, password
	 * @return bearer token
	 */
	public static String getBearerToken(String email, String password) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", email);
		requestParams.put("password", password);
		RequestSpecification req = RestAssured.given();
		req.header("Content-Type", "application/json");
		req.config(io.restassured.RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig()
				.appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		req.body(requestParams.toString());
		Response resp = req.post("url");
		String accesstoken = resp.jsonPath().getString("access_token");
		String tokentype = resp.jsonPath().getString("token_type");
		String bearerToken = tokentype.replaceAll("Bearer", "bearer") + " " + accesstoken;
		return bearerToken;
	}

	
	/**
	 * Method to get content details passing content ID
	 * 
	 */
	public static Response getContentDetails(String contentID, String contentType) {
		Response respContentDetails = null;
		String Uri = "";
		if (contentType.equals("original")) {
			Uri = "url" + contentID + "url";
		} else if (contentType.contentEquals("Manual")) {
			Uri = "url" + contentID + "url";
		} else {
			Uri = "url" + contentID + "url";
		}
		respContentDetails = RestAssured.given().headers("X-ACCESS-TOKEN", getXAccessTokenWithApiKey()).when().get(Uri);
//		System.out.println("Content Details API Response: "+respContentDetails.getBody().asString());
		return respContentDetails;
	}


	public static String getXAccessTokenWithApiKey() {
		try {
			Response respToken = null, respForKey = null;
			// get APi-KEY
//		String Uri = "https://gwapi.zee5.com/user/getKey?=aaa"; //partner 
			String Uri = "url";
			respForKey = RestAssured.given().urlEncodingEnabled(false).when().get(Uri);
//		respForKey.print();
			String xAccessToken = respForKey.jsonPath().getString("token");
			System.out.println("Toke : " + xAccessToken);
//		String rawApiKey = respForKey.getBody().asString();
//		String apiKeyInResponse = rawApiKey.substring(0, rawApiKey.indexOf("<br>airtel "));
//		String finalApiKey = apiKeyInResponse.replaceAll("<br>rel - API-KEY : ", "");
//		String UriForToken = "http://gwapi.zee5.com/user/getToken";
//		respToken = RestAssured.given().headers("API-KEY", finalApiKey).when().get(UriForToken);
//		String xAccessToken = respToken.jsonPath().getString("X-ACCESS-TOKEN");
			return xAccessToken;
		} catch (Exception e) {
			return "";
		}
	}

	public static String getRegion() {
		Response regionResponse = RestAssured.given().urlEncodingEnabled(false).when().get("url");
		return regionResponse.getBody().jsonPath().getString("state");
	}

	public static String getresponse(String searchText) {
		Response resp = RestAssured.given().urlEncodingEnabled(false).when().get("url" + searchText + "url");
		return resp.jsonPath().getString("results[0].total");
	}

	public static String getXAccessTokenAMD() {
		Response respToken = null, respForKey = null;
		// get APi-KEY
		String Uri = "url";
		respForKey = RestAssured.given().urlEncodingEnabled(false).when().get(Uri);
		String rawApiKey = respForKey.getBody().asString();
		String apiKeyInResponse = rawApiKey.substring(0, rawApiKey.indexOf("<br>airtel "));
		String finalApiKey = apiKeyInResponse.replaceAll("<br>rel - API-KEY : ", "");
		String UriForToken = "url";
		respToken = RestAssured.given().headers("API-KEY", finalApiKey).when().get(UriForToken);
		String xAccessToken = respToken.jsonPath().getString("X-ACCESS-TOKEN");
		return xAccessToken;
	}
	
	
	public static String getParameterFromXML(String parameter) {
		return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter(parameter);
	}

	
	public static Response getSettingsDetails(String username, String password) {
		String url = "url";
		Response response = null;
		String b = getBearerToken(username, password);
		// System.out.println("\nbearer token :"+b+"\n");
		response = RestAssured.given().headers("Authorization", b).when().get(url);
		// System.out.println("\nresponse body: "+response.getBody().asString());
		return response;
	}

	public static Response getUserSettingDetails(String pUsername, String pPassword) {
		Response response = null;
		String xAccessToken = getXAccessTokenWithApiKey();
		String bearerToken = getBearerToken(pUsername, pPassword);
		String url = "url";
		response = RestAssured.given().headers("x-access-token", xAccessToken).header("authorization", bearerToken)
				.when().get(url);
		System.out.println("\nresponse body: " + response.getBody().asString());
		return response;
	}


	public static String getAuthorization(String email, String password) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", email);
		requestParams.put("password", password);
		RequestSpecification req = RestAssured.given();
		req.header("Content-Type", "application/json");
		req.config(io.restassured.RestAssured.config().encoderConfig(io.restassured.config.EncoderConfig.encoderConfig()
				.appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		req.body(requestParams.toString());
		Response resp = req.post("url");
		String accesstoken = resp.jsonPath().getString("access_token");
		return accesstoken;
	}


	public static Response getUserDetails(String username, String password) {
		Response response = null;
		String bearerToken = getBearerToken(username, password);
		String url = "url";
		response = RestAssured.given().headers("Authorization", bearerToken).when().get(url);
		// System.out.println("\nresponse body: " + response.prettyPrint());
		return response;
	}

}
