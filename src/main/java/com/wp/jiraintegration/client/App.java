package com.wp.jiraintegration.client;

import com.wp.jiraintegration.models.Issue;
import com.wp.jiraintegration.models.JIRAInstance;
import com.wp.jiraintegration.services.ISubtaskService;
import com.wp.jiraintegration.services.SubtaskService;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String JIRA_URL = "https://hostJira.atlassian.net";
    private static final String JIRA_ADMIN_USERNAME = "usuario@usuario.com";
    private static final String JIRA_ADMIN_PASSWORD = "senha";
    
	public static void main(String[] args) throws Exception {
		JIRAInstance.CreateInstance(JIRA_URL,JIRA_ADMIN_USERNAME, JIRA_ADMIN_PASSWORD);
		ISubtaskService service = new SubtaskService(JIRAInstance.GetInstance());
		Issue parentIssue = new Issue();
		parentIssue.setKey("PT-32");
		
/*		for(Issue issue : service.GetSubtasks(parentIssue)) {
			System.out.println(issue.getKey());
		}*/
		
		Issue parent = new Issue();
		parent.setKey("PT-71");		
		
		Issue newSubtask = new Issue();
		newSubtask.setProject("PT");
		newSubtask.setissueTypeId(10003);
		newSubtask.setSummary("implementando mapper model");
		newSubtask.setDescription("implementação e demonstração da biblioteca mapper model");
		
		service.CreateSubtask(parent, newSubtask);		
	}
}
