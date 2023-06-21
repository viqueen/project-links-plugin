package org.viqueen.ide.links;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "org.viqueen.ide.links.JiraLinksAppSettingsState",
        storages = @Storage("JiraLinksSettings.xml")
)
public class JiraLinksAppSettingsState implements PersistentStateComponent<JiraLinksAppSettingsState> {

    public boolean openSource = true;

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
