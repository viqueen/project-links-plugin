/**
 * Copyright 2023 Hasnae Rehioui
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.viqueen.ide.links;

import com.intellij.openapi.paths.WebReference;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.util.ProcessingContext;
import org.apache.groovy.util.Arrays;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class JiraLinksReferenceProvider extends PsiReferenceProvider {

    private static final Pattern openSourcePattern = Pattern.compile("(?<jiraIssue>[a-zA-Z]+-[0-9]+)");

    @Override
    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
        if (element instanceof PsiComment) {
            PsiComment comment = (PsiComment) element;
            JiraLinksAppSettingsState state = JiraLinksAppSettingsState.getInstance();
            state.projectKeyToUrl.remove("");
            WebReference[] fromOpenSource = state.openSource ? this.handleOpenSource(comment) : new WebReference[]{};
            WebReference[] fromInternal = this.handleInternalProjects(comment, state.projectKeyToUrl);
            return Arrays.concat(fromOpenSource, fromInternal);
        }
        return new PsiReference[0];
    }

    private WebReference @NotNull[] handleOpenSource(@NotNull PsiComment comment) {
        String text = comment.getText();
        Matcher matcher = openSourcePattern.matcher(text);
        List<WebReference> webReferences = new ArrayList<>();
        while (matcher.find()) {
            String jiraIssue = matcher.group("jiraIssue");
            int start = matcher.start("jiraIssue");
            int end = matcher.end("jiraIssue");
            TextRange range = new TextRange(start, end);
            webReferences.add(new WebReference(comment, range, format("https://project-links-navigator.web.app/#/jira/%s", jiraIssue)));
        }
        return webReferences.toArray(new WebReference[0]);
    }

    private WebReference @NotNull[] handleInternalProjects(@NotNull PsiComment comment, Map<String, String> projects) {
        String projectsFilter = String.join("|", projects.keySet());
        String regex = String.format("(?<jiraIssue>(?<jiraProject>%s)-(?<issueNumber>[0-9])+)", projectsFilter);
        Pattern projectPattern = Pattern.compile(regex);

        String text = comment.getText();
        Matcher matcher = projectPattern.matcher(text);

        List<WebReference> webReferences = new ArrayList<>();
        while (matcher.find()) {
            String jiraIssue = matcher.group("jiraIssue");
            int start = matcher.start("jiraIssue");
            int end = matcher.end("jiraIssue");
            TextRange range = new TextRange(start, end);

            String jiraProject = matcher.group("jiraProject");
            String jiraProjectUrl = projects.get(jiraProject);
            if (jiraProjectUrl != null) {
                webReferences.add(new WebReference(comment, range, format("%s/browse/%s", jiraProjectUrl, jiraIssue)));
            }
        }
        return webReferences.toArray(new WebReference[0]);
    }
}
