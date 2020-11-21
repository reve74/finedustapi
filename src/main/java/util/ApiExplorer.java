package util;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
	
	public static void main(String[] args) throws IOException {
		
		String place = "221251"; 
		String time = "2020111920"; 
		
		System.out.println(getFinedustData(place, time));
	}
	
	public static String getFinedustData(String place, String time) throws IOException {
		
		//String place = "221251";
		
		//String time = "2020111715"; 
		
	    StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/AirQualityInfoService/getAirQualityInfoClassifiedByStation"); 
	    urlBuilder.append("?ServiceKey=QVWDM9ZUKGDJ%2BZ46qFzKBatWi3Kc8Fp1WdNpIaERUzVjt0Xq4ha9p7BYowPw079DWuvSKjQrXlIFSSI8%2FhvE0w%3D%3D"); 
	    urlBuilder.append("&pageNo=1"); 
	    urlBuilder.append("&numOfRows=5"); 
	    urlBuilder.append("&resultType=json"); 
	    urlBuilder.append("&areaIndex="+ place ); 
	    urlBuilder.append("&controlnumber="+ time); 
	    URL url = new URL(urlBuilder.toString());
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-type", "application/json");
	    System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	       return(sb.toString());
	}
	/*
	public static List<DustVO> getDustJSON(String place, String time) throws Exception {
		String result = getFinedustData(place, time);
		//JSON처럼 생긴 String을 JSON으로 만들기
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject)parser.parse(result); //리턴값이 오브젝트이기  떄문에 json오브젝트로 다운캐스팅
		
		//response
		JSONObject j_getAirQualityInfoClassifiedByStation = (JSONObject)jsonObj.get("getAirQualityInfoClassifiedByStation"); //리턴값이 오브젝트이기  떄문에 json오브젝트로 다운캐스팅	
		System.out.println("getAirQualityInfoClassifiedByStation : " + j_getAirQualityInfoClassifiedByStation);
		JSONArray j_item = (JSONArray)j_getAirQualityInfoClassifiedByStation.get("item");
		j_item.remove(0);
		System.out.println("item :" +j_item);
		
		Gson gson = new Gson();
		
		

		List<DustVO> list = gson.fromJson(j_item.toString(), new TypeToken<List<DustVO>>(){}.getType());
		
		for(DustVO dustVO: list) {
			System.out.println(dustVO.getPm10());
		} 
		return list;
	}
	 */
	
	
}
	

