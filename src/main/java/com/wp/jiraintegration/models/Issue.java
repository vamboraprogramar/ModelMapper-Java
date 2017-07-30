package com.wp.jiraintegration.models;

public class Issue {
	private String key;
	private String summary;
	private String description;
	private String type;
	private String project;
	private String status;
	private long issueTypeId;
	
	public long getissueTypeId() {
		return issueTypeId;
	}
	public void setissueTypeId(long typeId) {
		this.issueTypeId = typeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
}
