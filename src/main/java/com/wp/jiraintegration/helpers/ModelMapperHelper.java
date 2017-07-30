package com.wp.jiraintegration.helpers;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;

public class ModelMapperHelper {

	private static ModelMapper instance;
	
	private static void initializeMapper() {
		if(instance == null)
			instance = new ModelMapper();
		
		instance.addMappings(new PropertyMap<com.atlassian.jira.rest.client.api.domain.Issue, com.wp.jiraintegration.models.Issue>() {
			@Override
			protected void configure() {
				map().setProject(source.getProject().getKey());
				map().setStatus(source.getStatus().getName());
			}
		});
		
		instance.addMappings(new PropertyMap<com.wp.jiraintegration.models.Issue, IssueInputBuilder>(){
			@Override
			protected void configure() {
				map().setProjectKey(source.getProject());
			}
		});
	}
	
	public static ModelMapper getInstance() {
		initializeMapper();
		return instance;
	}
}
