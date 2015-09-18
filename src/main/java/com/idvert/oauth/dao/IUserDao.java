package com.idvert.oauth.dao;

import java.util.List;

import com.idvert.oauth.pojo.User;

public interface IUserDao {

	public User createFavUser(User favUser);

	public User updateFavUser(User favUser);

	public void deleteFavUser(Long userId);

	User findOne(Long userId);

	List<User> findAll();

	User findByUsername(String username);

}
