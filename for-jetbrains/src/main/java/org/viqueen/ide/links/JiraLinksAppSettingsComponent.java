package org.viqueen.ide.links;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;
import java.util.Map;

public class JiraLinksAppSettingsComponent {
    private final JPanel mainPanel;
    private final JBCheckBox openSource;
    private final StringKeyValueMapTableComponent projectKeyToUrlTable;

    public JiraLinksAppSettingsComponent(
            final boolean isOpenSource,
            final Map<String, String> projectKeyToUrl
    ) {

        this.openSource = new JBCheckBox("With open source projects", isOpenSource);
        this.projectKeyToUrlTable = new StringKeyValueMapTableComponent(
                projectKeyToUrl,
                new String[]{"Jira Project Key", "Jira Instance Url"}
        );


        this.mainPanel = FormBuilder.createFormBuilder()
                .addComponent(openSource, 1)
                .addComponent(this.projectKeyToUrlTable.getMainPanel())
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public boolean getOpenSource() {
        return this.openSource.isSelected();
    }

    public Map<String, String> getProjectKeyToUrl() {
        return this.projectKeyToUrlTable.getData();
    }
}
