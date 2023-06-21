import {openSourceProjects} from "./oss-projects";

const fragment = window.location.hash;

const getJiraIssueLinkFromFragment = () => {
    if (!fragment.startsWith('#/jira/')) {
        return undefined;
    }
    const jiraIssue = fragment.replace('#/jira/', '');
    const [projectKey, issueNumber] = jiraIssue.split('-');
    if (!projectKey) {
        return undefined;
    }
    const projectFound = openSourceProjects[projectKey];
    if (!projectFound) {
        return undefined;
    }

    return `${projectFound}/browse/${jiraIssue}`;
}

const jiraIssueLink = getJiraIssueLinkFromFragment();
if (jiraIssueLink) {
    window.location.href = jiraIssueLink;
}
