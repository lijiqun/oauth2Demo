package com.idvert.oauth.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idvert.oauth.dao.IClientDao;
import com.idvert.oauth.pojo.Client;
import com.idvert.oauth.service.IClientService;

@Service("favClientService")
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;

	@Override
	public Client createFavClient(Client favClient) {
		favClient.setClientId(UUID.randomUUID().toString());
		favClient.setClientSecret(UUID.randomUUID().toString());
		return clientDao.createFavClient(favClient);
	}

	@Override
	public Client updateFavClient(Client favClient) {
		return clientDao.updateFavClient(favClient);
	}

	@Override
	public void deleteFavClient(Long clientId) {
		clientDao.deleteFavClient(clientId);
	}

	@Override
	public Client findOne(Long clientId) {
		return clientDao.findOne(clientId);
	}

	@Override
	public List<Client> findAll() {
		return clientDao.findAll();
	}

	@Override
	public Client findByClientId(String clientId) {
		return clientDao.findByClientId(clientId);
	}

	@Override
	public Client findByClientSecret(String clientSecret) {
		return clientDao.findByClientSecret(clientSecret);
	}

}
