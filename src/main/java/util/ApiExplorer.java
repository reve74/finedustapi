package util;

import java.io.InputStreamReader;
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
	public static String getFinedustData(String place, String time) throws IOException {
		
		//String place = "221251"; 
		//String time = "2020111715"; 
		
	    StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/AirQualityInfoService/getAirQualityInfoClassifiedByStation"); 
	    urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=QVWDM9ZUKGDJ%2BZ46qFzKBatWi3Kc8Fp1WdNpIaERUzVjt0Xq4ha9p7BYowPw079DWuvSKjQrXlIFSSI8%2FhvE0w%3D%3D"); 
	    urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); 
	    urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("5", "UTF-8")); 
	    urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); 
	    urlBuilder.append("&" + URLEncoder.encode("areaIndex","UTF-8") + "=" + URLEncoder.encode(place, "UTF-8")); 
	    urlBuilder.append("&" + URLEncoder.encode("controlnumber","UTF-8") + "=" + URLEncoder.encode(time, "UTF-8")); 
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
		
		gson.fromJson(j_item.toString(), DustVO.class);
		DustVO[] dustVOarr = gson.fromJson(j_item.toString(), DustVO[].class);
		
		return dustVOarr;
		
		/*List<DustVO> list = gson.fromJson(j_item.toString(), new TypeToken<List<DustVO>>(){}.getType());
		
		for(DustVO dustVO: list) {
			System.out.println(dustVO.getPm10());
		} 
		return list;
		*/
	}
}
	

