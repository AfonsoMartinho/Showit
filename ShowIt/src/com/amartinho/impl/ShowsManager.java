package com.amartinho.impl;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import com.amartinho.data.ShowData;
import com.amartinho.model.Show;

import io.jsonwebtoken.impl.crypto.MacProvider;

public class ShowsManager {

static List<Show> shows = new ArrayList<Show>();
	

	static ShowsManager sm = null;
	
	static Key key;

	public static ShowsManager getInstance() {
		if(sm == null) {
			sm = new ShowsManager();	
			key = MacProvider.generateKey();

		}
		return sm;
	}
	
	public Key getKey() {
		return key;
	}
	

	//GET todos os Users
	public List<Show> getShows() {
		ShowData showdata = ShowData.getInstance();				
		return showdata.getData();
	}

	//GET todos os users com um determinado parametro Exemplo : Age,Height,Weight,Sex ou u utilizador pois o username é unico
	public List<Show> getShows(String param) {
		ShowData showdata = ShowData.getInstance();				
		return showdata.getData(param);
	}	

	//DELETE Remover Utilizador
	public void removeShow(String name) {
		ShowData userdata = ShowData.getInstance();				
		userdata.removeData(name);
	}

	//POST Adicionar Show
	public void createShow(String name, String desc, String gender, String release, int seasons, int episodes, int imdb, int cusers) {
		ShowData showdata = ShowData.getInstance();
		showdata.insertData(new Show(name,desc,gender,release,seasons,episodes,imdb,cusers));		
	}
	
	public void updateShow(String oldname,String name, String desc, String gender, String release, int seasons, int episodes, int imdb, int cusers) {
		ShowData showdata = ShowData.getInstance();
		showdata.updateData(new Show(name,desc,gender,oldname,seasons,episodes,imdb,cusers),new Show(name,desc,gender,release,seasons,episodes,imdb,cusers));		
	}
	
}
