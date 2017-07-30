package com.wp.jiraintegration.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.wp.jiraintegration.helpers.ModelMapperHelper;
import com.wp.jiraintegration.models.Issue;
import com.wp.jiraintegration.models.JIRAInstance;

public class SubtaskService implements ISubtaskService {
	private JiraRestClient client;
	
	public SubtaskService(JIRAInstance jira) {
		
		JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();		
		try {
			URI uri = new URI(jira.getHost());
			client = factory.createWithBasicHttpAuthentication(uri, jira.getUser(), jira.getPassword());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Issue> GetSubtasks(Issue parentIssue) {
		List<Issue> issueList = new ArrayList<Issue>();
		
		com.atlassian.jira.rest.client.api.domain.Issue parent = client.getIssueClient().getIssue(parentIssue.getKey()).claim();
	    
		for(final com.atlassian.jira.rest.client.api.domain.Subtask subtask : parent.getSubtasks()) {
			Issue selectedIssue = new Issue();
			com.atlassian.jira.rest.client.api.domain.Issue currentSubtask = client.getIssueClient().getIssue(subtask.getIssueKey()).claim();
			
			selectedIssue = ModelMapperHelper.getInstance().map(currentSubtask, Issue.class);
			
			/*selectedIssue.setKey(currentSubtask.getKey());
			selectedIssue.setDescription(currentSubtask.getDescription());
			selectedIssue.setSummary(currentSubtask.getSummary());
			selectedIssue.setProject(currentSubtask.getProject().getName());
			selectedIssue.setType(currentSubtask.getIssueType().getId());*/
							
			issueList.add(selectedIssue);
		}
		
	    return issueList;
	}

	@Override
	public void CreateSubtask(Issue parentIssue, Issue subtask) {
		IssueInputBuilder builder = new IssueInputBuilder();
	
		builder = ModelMapperHelper.getInstance().map(subtask, IssueInputBuilder.class);
		
        Map<String, Object> parent = new HashMap<String, Object>();
        parent.put("key", parentIssue.getKey());
        FieldInput parentField = new FieldInput("parent", new ComplexIssueInputFieldValue(parent));
        builder.setFieldInput(parentField);
        IssueInput input = builder.build();        
        client.getIssueClient().createIssue(input).claim(); 		
	}

}
