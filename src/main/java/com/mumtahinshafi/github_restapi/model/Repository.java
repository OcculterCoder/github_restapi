package com.mumtahinshafi.github_restapi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Repository {
	
	private String repository_html_url;
	private int repository_stars;
	private int repository_watchers;
	
	public Repository() {
		
	}
	
	public Repository(String repository_html_url, int repository_stars, int repository_watchers) {
		this.repository_html_url = repository_html_url;
		this.repository_stars = repository_stars;
		this.repository_watchers = repository_watchers;
	}
	
	public String getRepository_html_url() {
		return repository_html_url;
	}
	public void setRepository_html_url(String repository_html_url) {
		this.repository_html_url = repository_html_url;
	}
	public int getRepository_stars() {
		return repository_stars;
	}
	public void setRepository_stars(int repository_stars) {
		this.repository_stars = repository_stars;
	}
	public int getRepository_watchers() {
		return repository_watchers;
	}
	public void setRepository_watchers(int repository_watchers) {
		this.repository_watchers = repository_watchers;
	}
	
}
