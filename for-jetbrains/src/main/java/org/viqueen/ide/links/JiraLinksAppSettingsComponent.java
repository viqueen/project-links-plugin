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

import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.FormBuilder;

import javax.swing.JPanel;
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
