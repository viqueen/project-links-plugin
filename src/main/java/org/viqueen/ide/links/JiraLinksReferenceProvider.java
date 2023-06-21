package org.viqueen.ide.links;

import com.intellij.openapi.paths.WebReference;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class JiraLinksReferenceProvider extends PsiReferenceProvider {

    private static final Pattern issuePattern = Pattern.compile("(?<jiraIssue>[a-zA-Z]+-[0-9]+)");

    @Override
    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
        if (element instanceof PsiComment) {
            String text = element.getText();
            Matcher matcher = issuePattern.matcher(text);
            List<WebReference> webReferences = new ArrayList<>();
            while (matcher.find()) {
                String jiraIssue = matcher.group("jiraIssue");
                int start = matcher.start("jiraIssue");
                int end = matcher.end("jiraIssue");
                TextRange range = new TextRange(start, end);
                webReferences.add(new WebReference(element, range, format("https://viqueen.github.io/project-links-navigator/#jira/%s",jiraIssue)));
            }
            return webReferences.toArray(new PsiReference[0]);
        }
        return new PsiReference[0];
    }
}
