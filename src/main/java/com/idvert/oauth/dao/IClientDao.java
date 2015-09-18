package com.idvert.oauth.dao;

import java.util.List;

import com.idvert.oauth.pojo.Client;

public interface IClientDao {

	public Client createFavClient(Client favClient);
    public Client updateFavClient(Client favClient);
    public void deleteFavClient(Long clientId);

    Client findOne(Long clientId);

    List<Client> findAll();

    Client findByClientId(String clientId);
    Client findByClientSecret(String clientSecret);
    
}
