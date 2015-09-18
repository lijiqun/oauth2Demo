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

import com.idvert.oauth.dao.IUserDao;
import com.idvert.oauth.pojo.User;

@Repository("favUserDao")
public class UserDaoImpl implements IUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User createFavUser(final User favUser) {
		final String sql = "insert into fav_user(username, password, salt) values(?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql,
						new String[] { "id" });
				int count = 1;
				psst.setString(count++, favUser.getUsername());
				psst.setString(count++, favUser.getPassword());
				psst.setString(count++, favUser.getSalt());
				return psst;
			}
		}, keyHolder);

		favUser.setId(keyHolder.getKey().longValue());
		return favUser;
	}

	@Override
	public User updateFavUser(User favUser) {
		String sql = "update fav_user set username=?, password=?, salt=? where id=?";
		jdbcTemplate.update(sql, favUser.getUsername(), favUser.getPassword(),
				favUser.getSalt(), favUser.getId());
		return favUser;
	}

	@Override
	public void deleteFavUser(Long userId) {
		String sql = "delete from fav_user where id=?";
		jdbcTemplate.update(sql, userId);
	}

	@Override
	public User findOne(Long userId) {
		String sql = "select id, username, password, salt from fav_user where id=?";
		List<User> userList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<User>(User.class), userId);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

	@Override
	public List<User> findAll() {
		String sql = "select id, username, password, salt from fav_user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public User findByUsername(String username) {
		String sql = "select id, username, password, salt from fav_user where username=?";
		List<User> userList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<User>(User.class), username);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

}
