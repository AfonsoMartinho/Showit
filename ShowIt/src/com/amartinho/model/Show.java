package com.amartinho.model;

public class Show {
	private String name;
	private String desc;
	private String gender;
	private String release;
	private int seasons;
	private int episodes;
	private int imdb;
	private int cusers;

	public Show() {
		super();
	}
	public Show(String name,String desc, String gender, String release, int seasons, int episodes, int imdb, int cusers
) {
		super();
		this.name=name;
		this.desc = desc;
		this.gender = gender;
		this.release = release;
		this.seasons = seasons;
		this.episodes = episodes;
		this.imdb = imdb;
		this.cusers = cusers;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public int getSeasons() {
		return seasons;
	}
	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}
	public int getEpisodes() {
		return episodes;
	}
	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}
	public int getImdb() {
		return imdb;
	}
	public void setImdb(int imdb) {
		this.imdb = imdb;
	}
	public int getCusers() {
		return cusers;
	}
	public void setCusers(int cusers) {
		this.cusers = cusers;
	}
	
}
