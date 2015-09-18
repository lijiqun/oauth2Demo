package com.idvert.oauth.service;

import java.util.List;

import com.idvert.oauth.pojo.WebApp;

public interface IWebAppService {

	public WebApp createFavWebApp(WebApp favWebApp);
	
	public WebApp updateFavWebApp(WebApp favWebApp);
	
	WebApp findOne(Long webId);
	
	List<WebApp> findAll();
	
	
	
}
