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

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;


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
