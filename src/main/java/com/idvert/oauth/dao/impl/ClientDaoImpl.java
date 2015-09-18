package com.idvert.oauth.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.idvert.oauth.dao.IClientDao;
import com.idvert.oauth.pojo.Client;

@Repository("favClientDao")
public class ClientDaoImpl implements IClientDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Client createFavClient(final Client favClient) {
		final String sql = "insert into fav_client(client_name, client_id, client_secret) values(?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql,
						new String[] { "id" });
				int count = 1;
				psst.setString(count++, favClient.getClientName());
				psst.setString(count++, favClient.getClientId());
				psst.setString(count++, favClient.getClientSecret());
				return psst;
			}
		}, keyHolder);

		favClient.setId(keyHolder.getKey().longValue());
		return favClient;
	}

	@Override
	public Client updateFavClient(Client favClient) {
		String sql = "update fav_client set client_name=?, client_id=?, client_secret=? where id=?";
		jdbcTemplate.update(sql, favClient.getClientName(),
				favClient.getClientId(), favClient.getClientSecret(),
				favClient.getId());
		return favClient;
	}

	@Override
	public void deleteFavClient(Long clientId) {
		String sql = "delete from fav_client where id=?";
		jdbcTemplate.update(sql, clientId);
	}

	@Override
	public Client findOne(Long clientId) {
		String sql = "select id, client_name, client_id, client_secret from fav_client where id=?";
		List<Client> clientList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Client>(Client.class), clientId);
		if (clientList.size() == 0) {
			return null;
		}
		return clientList.get(0);
	}

	@Override
	public List<Client> findAll() {
		String sql = "select id, client_name, client_id, client_secret from fav_client";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Client>(Client.class));
	}

	@Override
	public Client findByClientId(String clientId) {
		String sql = "select id, client_name, client_id, client_secret from fav_client where client_id=?";
		List<Client> clientList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Client>(Client.class), clientId);
		if (clientList.size() == 0) {
			return null;
		}
		return clientList.get(0);
	}

	@Override
	public Client findByClientSecret(String clientSecret) {
		String sql = "select id, client_name, client_id, client_secret from fav_client where client_secret=?";
		List<Client> clientList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Client>(Client.class), clientSecret);
		if (clientList.size() == 0) {
			return null;
		}
		return clientList.get(0);
	}

}
