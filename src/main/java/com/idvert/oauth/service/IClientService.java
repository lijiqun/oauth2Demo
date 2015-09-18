package com.idvert.oauth.service;

import java.util.List;

import com.idvert.oauth.pojo.Client;

public interface IClientService {

	public Client createFavClient(Client favClient);
    public Client updateFavClient(Client favClient);
    public void deleteFavClient(Long clientId);

    Client findOne(Long clientId);

    List<Client> findAll();

    Client findByClientId(String clientId);
    Client findByClientSecret(String clientSecret);
	    
}
