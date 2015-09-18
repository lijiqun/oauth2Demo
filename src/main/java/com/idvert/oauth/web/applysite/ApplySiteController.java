package com.idvert.oauth.web.applysite;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.idvert.oauth.pojo.Client;
import com.idvert.oauth.pojo.WebApp;
import com.idvert.oauth.service.IClientService;
import com.idvert.oauth.service.IWebAppService;

@Controller
@RequestMapping("applysite")
public class ApplySiteController {
	
	@Autowired
	IWebAppService webAppService;
	@Autowired
	IClientService clientService;
	
	@RequestMapping("initapplysite")
	public ModelAndView initApplySite(){
		ModelAndView mav = new ModelAndView();
		String webKey = UUID.randomUUID().toString();
		mav.addObject("webKey", webKey);
		mav.setViewName("applysite/site_apply");
		
		return mav;
	}
	
	@RequestMapping("initapprovesite")
	public ModelAndView initApproveSite(){
		ModelAndView mav = new ModelAndView();
		List<WebApp> websiteList =  webAppService.findAll();
		mav.addObject("websiteList", websiteList);
		mav.setViewName("applysite/site_approval");
		
		return mav;
	}
	
	@RequestMapping("approvewebsite")
	public ModelAndView approveWebSite(String webId, String approveState){
		ModelAndView mav = new ModelAndView();
		WebApp favWebApp = webAppService.findOne(Long.valueOf(webId));
		if(favWebApp!=null){
			favWebApp.setWebState(approveState);
			webAppService.updateFavWebApp(favWebApp);
		}
		return mav;
	}

	
	@RequestMapping("/addwebsite")
	@ResponseBody
	public String addWebSite(WebApp favWebApp){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webapply/web_application");
		webAppService.createFavWebApp(favWebApp);
		Client favClient = new Client();
		favClient.setClientName("test");
		clientService.createFavClient(favClient);
		return "";
	}
	
	
}
