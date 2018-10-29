package com.amartinho.data;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.amartinho.model.User;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class UserData {
	static UserData ud = null;
	public static MongoCollection<User> colUser;
	
	//DATABASE CONNECTION
	public static UserData getInstance() {
		if(ud == null) {
			ud = new UserData();			
			
			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
			MongoDatabase dbUser = mongoClient.getDatabase("ShowIT");
			colUser = dbUser.getCollection("Users", User.class);
		}
		return ud;
	}
	
	
	public void insertData(User user) {
		if(colUser.find(eq("name", user.getUsername())).first() == null) {
			
			colUser.insertOne(user);
		}
	}	
	
	public void updateData(User user,User usernew) {
		colUser.deleteOne(eq("username", user));		

		if(colUser.find(eq("name", usernew.getUsername())).first() == null) {
			colUser.insertOne(usernew);
		}
	}	

	public List<User> getData() {
		List<User> users = new ArrayList<User>();
		
		Block<User> printBlock = new Block<User>() {
		    public void apply(final User user) {
		    	users.add(user);
		    }
		};
		colUser.find().forEach(printBlock);
		return users;
	}
	
	public List<User> getData(String username) {		
		final List<User> users = new ArrayList<User>();
		Block<User> printBlock = new Block<User>() {
		    public void apply(final User user) {
		    	users.add(user);
		    }
		};
		colUser.find(eq("username",username)).forEach(printBlock);;		
		return users;
	}
	
	public void removeData(String username) {
		colUser.deleteOne(eq("username", username));		
	}
}
