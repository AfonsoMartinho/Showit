package com.amartinho.impl;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import com.amartinho.data.UserData;
import com.amartinho.model.User;

import io.jsonwebtoken.impl.crypto.MacProvider;

public class UsersManager {
static List<User> users = new ArrayList<User>();
	

	static UsersManager um = null;
	
	static Key key;

	public static UsersManager getInstance() {
		if(um == null) {
			um = new UsersManager();	
			key = MacProvider.generateKey();

		}
		return um;
	}
	
	public Key getKey() {
		return key;
	}
	

	//GET todos os Users
	public List<User> getUsers() {
		UserData userdata = UserData.getInstance();				
		return userdata.getData();
	}

	//GET todos os users com um determinado parametro 
	public List<User> getUsers(String param) {
		UserData userdata = UserData.getInstance();				
		return userdata.getData(param);
	}	

	//DELETE Remover Utilizador
	public void removeUser(String username) {
		UserData userdata = UserData.getInstance();				
		userdata.removeData(username);
	}

	//POST Adicionar Utilizador
	public void createUser(String name,
			String username, String password, String email) {
		UserData userdata = UserData.getInstance();
		userdata.insertData(new User(name,username,password,email));		
	}
	
	public void updateUser(String oldname, String name,
			String username, String password, String email) {
		UserData userdata = UserData.getInstance();
		userdata.updateData(new User(name,oldname,password,email),new User(name,username,password,email));		
	}
}
