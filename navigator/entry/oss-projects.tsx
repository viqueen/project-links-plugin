interface OpenSourceProjects {
    JDK: string;
}
const openSourceProjects: OpenSourceProjects & Record<string, string> = {
    JDK: 'https://bugs.openjdk.org',
};
export { openSourceProjects };
