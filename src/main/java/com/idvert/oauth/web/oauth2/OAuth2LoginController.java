package com.idvert.oauth.web.oauth2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.idvert.oauth.pojo.WebApp;
import com.idvert.oauth.service.IWebAppService;

@Controller
@RequestMapping("oauth2")
public class OAuth2LoginController {
	
	@Autowired
	IWebAppService webAppService;
	
	@RequestMapping(value={"getoAuth2loginapp"})
	public ModelAndView getOAuth2LoginApp(){
		ModelAndView mav = new ModelAndView();
		List<WebApp> websiteList =  webAppService.findAll();
		mav.addObject("websiteList", websiteList);
		mav.setViewName("oauth2/oAuth2_login_app");
		return mav;
	}
	
	
	@RequestMapping(value={"oauth2login"})
	public ModelAndView oauth2Login(String webId){
		ModelAndView mav = new ModelAndView();
		if(!StringUtils.isEmpty(webId)){
			WebApp favWebApp = webAppService.findOne(Long.valueOf(webId));
			mav.addObject("favWebApp", favWebApp);
		}		
		mav.setViewName("oauth2/oAuth2Login");
		return mav;
	}

}
