package org.viqueen.ide.links;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class JiraLinksAppSettingsConfigurable implements Configurable {

    private JiraLinksAppSettingsComponent component;
    @Override
    public String getDisplayName() {
        return "Project Links Settings";
    }

    @Override
    public @Nullable JComponent createComponent() {
        JiraLinksAppSettingsState state = JiraLinksAppSettingsState.getInstance();
        this.component = new JiraLinksAppSettingsComponent(state.openSource, state.projectKeyToUrl);
        return this.component.getMainPanel();
    }

    @Override
    public boolean isModified() {
        JiraLinksAppSettingsState state = JiraLinksAppSettingsState.getInstance();
        return state.openSource != this.component.getOpenSource()
                || !state.projectKeyToUrl.equals(this.component.getProjectKeyToUrl());
    }

    @Override
    public void apply() throws ConfigurationException {
        JiraLinksAppSettingsState state = JiraLinksAppSettingsState.getInstance();
        state.openSource = this.component.getOpenSource();
        state.projectKeyToUrl = this.component.getProjectKeyToUrl();
    }

    @Override
    public void disposeUIResources() {
        this.component = null;
    }
}
