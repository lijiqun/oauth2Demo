package com.idvert.oauth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idvert.oauth.dao.IWebAppDao;
import com.idvert.oauth.pojo.WebApp;
import com.idvert.oauth.service.IWebAppService;

@Service("favWebAppService")
public class WebAppServiceImpl implements IWebAppService {
	
	@Autowired
	private IWebAppDao webAppDao;

	@Override
	public WebApp createFavWebApp(WebApp favWebApp) {
		return webAppDao.createFavWebApp(favWebApp);
	}

	@Override
	public List<WebApp> findAll() {
		return webAppDao.findAll();
	}

	@Override
	public WebApp updateFavWebApp(WebApp favWebApp) {
		return webAppDao.updateFavWebApp(favWebApp);
	}

	@Override
	public WebApp findOne(Long webId) {
		return webAppDao.findOne(webId);
	}

}
