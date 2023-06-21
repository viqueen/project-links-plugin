package org.viqueen.ide.links;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;

public class JiraLinksAppSettingsComponent {
    private final JPanel mainPanel;
    private final JBCheckBox openSource = new JBCheckBox("With open source projects");

    public JiraLinksAppSettingsComponent() {
        this.mainPanel = FormBuilder.createFormBuilder()
                .addComponent(openSource, 1)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public boolean getOpenSource() {
        return this.openSource.isSelected();
    }

    public void setOpenSource(boolean value) {
        this.openSource.setSelected(value);
    }
}
