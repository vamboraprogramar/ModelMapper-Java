package com.wp.jiraintegration.models;

public class JIRAInstance {
	private static JIRAInstance instance = null;
	private String host;
	private String user;
	private String password;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	private JIRAInstance(String host, String user, String password) {
		this.setHost(host);
		this.setUser(user);
		this.setPassword(password);		
	}
	
	public static void CreateInstance(String host, String user, String password) {
		if(instance == null)
			instance = new JIRAInstance(host, user, password);
	}
	
	public static JIRAInstance GetInstance() {
		return instance;
	}
}
