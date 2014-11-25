package org.isima.tweeter.services;

import java.util.List;

import javax.ejb.Local;

import org.isima.twitter.model.User;

@Local
public interface UserServiceLocal {
	
	User login(String password, String email);
	
	User create(User user);
	
	void update(int id,String email, String nom, byte[] photo);
	
	User getById(int id);
	
	User getByLogin(String login);
	
	User getByNom(String nom);

	User getByEmail(String email);

	List<User> listAllUser(int id);
	
	List<User> getAllUser();


}
