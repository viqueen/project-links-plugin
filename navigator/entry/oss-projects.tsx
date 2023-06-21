interface JavaProjects {
    JDK: string;
}

interface MavenProjects {
    MDEP: string;
}

type OpenSourceProjects = JavaProjects & MavenProjects;

const APACHE_PROJECT_URL = 'https://issues.apache.org/jira';

const openSourceProjects: OpenSourceProjects & Record<string, string> = {
    JDK: 'https://bugs.openjdk.org',
    MDEP: APACHE_PROJECT_URL
};

export {openSourceProjects};
