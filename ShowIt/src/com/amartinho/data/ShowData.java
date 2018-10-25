package com.amartinho.data;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.amartinho.model.Show;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class ShowData {
	static ShowData sd = null;
	public static MongoCollection<Show> colShow;
	
	public static ShowData getInstance() {
		if(sd == null) {
			sd = new ShowData();			
			
			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
			MongoDatabase dbShows = mongoClient.getDatabase("ShowIT");
			colShow = dbShows.getCollection("Shows", Show.class);
		}
		return sd;
	}
	
	
	public void insertData(Show show) {
		if(colShow.find(eq("name", show.getName())).first() == null) {
			
			colShow.insertOne(show);
		}
	}	
	
	public void updateData(Show show,Show shownew) {
		colShow.deleteOne(eq("name", show));		

		if(colShow.find(eq("name", shownew.getName())).first() == null) {
			colShow.insertOne(shownew);
		}
	}	

	public List<Show> getData() {
		List<Show> shows = new ArrayList<Show>();
		
		Block<Show> printBlock = new Block<Show>() {
		    public void apply(final Show show) {
		    	shows.add(show);
		    }
		};
		colShow.find().forEach(printBlock);
		return shows;
	}
	
	public List<Show> getData(String name) {		
		final List<Show> shows = new ArrayList<Show>();
		Block<Show> printBlock = new Block<Show>() {
		    public void apply(final Show show) {
		    	shows.add(show);
		    }
		};
		colShow.find(eq("name",name)).forEach(printBlock);;		
		return shows;
	}
	
	public void removeData(String name) {
		colShow.deleteOne(eq("name", name));		
	}
}
