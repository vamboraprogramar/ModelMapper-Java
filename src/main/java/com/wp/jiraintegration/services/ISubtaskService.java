package com.wp.jiraintegration.services;

import java.util.List;
import com.wp.jiraintegration.models.*;

public interface ISubtaskService {
    List<Issue> GetSubtasks(Issue parentIssue);
    void CreateSubtask(Issue parentIssue, Issue subtask);
}
