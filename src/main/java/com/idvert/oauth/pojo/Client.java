package com.idvert.oauth.pojo;

import java.io.Serializable;

public class Client implements Serializable {
	
	/**
     * @Fields serialVersionUID 用一句话描述这个变量表示什么
     */
    private static final long serialVersionUID = 3692752002590103389L;
    private Long id;
	private String clientId;
	private String clientName;
	private String clientSecret;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Client client = (Client) obj;

        if (id != null ? !id.equals(client.id) : client.id != null) return false;

        return true;
	}
	
	
	

}
