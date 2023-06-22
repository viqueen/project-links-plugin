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

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

@State(
        name = "org.viqueen.ide.links.JiraLinksAppSettingsState",
        storages = @Storage("JiraLinksSettings.xml")
)
public class JiraLinksAppSettingsState implements PersistentStateComponent<JiraLinksAppSettingsState> {

    public boolean openSource = true;
    public Map<String, String> projectKeyToUrl = new HashMap<>();

    @Override
    public @Nullable JiraLinksAppSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull JiraLinksAppSettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public static JiraLinksAppSettingsState getInstance() {
        return ApplicationManager.getApplication().getService(JiraLinksAppSettingsState.class);
    }
}
