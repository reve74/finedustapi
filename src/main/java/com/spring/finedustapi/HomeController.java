package com.spring.finedustapi;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import util.ApiExplorer;
import util.DustVO;
import util.Myutils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(DustVO dustVO, Model model) throws Exception {
		Map<String, String> result = Myutils.getPlaceID();
		String pm25 = result.get(dustVO.getPm25());
		System.out.println("pm25 :"+pm25);
		String pm10 = result.get(dustVO.getPm10());
		System.out.println("pm10 :"+pm10);
		String no2 = result.get(dustVO.getNo2());
		System.out.println("no2 :"+no2);
		String o3 = result.get(dustVO.getO3());
		System.out.println("o3 :"+o3);
		
		//List<DustVO> zone = ApiExplorer.getDustJSON();
		//model.addAttribute("zone", zone);
		
		
		return "home";
	}
	
	@RequestMapping(value = "/place.do", method = RequestMethod.GET)
	public String place(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "placeForm";
	}	
	
}
