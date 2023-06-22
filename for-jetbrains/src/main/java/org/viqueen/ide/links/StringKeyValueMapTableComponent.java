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

import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.FormBuilder;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.util.Map;

public class StringKeyValueMapTableComponent {

    private final JPanel mainPanel;
    private final StringKeyValueTableModel tableModel;

    public StringKeyValueMapTableComponent(Map<String, String> data, String[] columnNames) {
        this.tableModel = new StringKeyValueTableModel(data, columnNames);

        JBTable table = new JBTable(tableModel);
        table.setVisibleRowCount(15);

        JButton addRow = new JButton("Add");
        JButton removeRow = new JButton("Remove");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addRow);
        buttonPanel.add(removeRow);

        addRow.addActionListener((event) -> {
            tableModel.addRow();
        });

        removeRow.addActionListener((event) -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                tableModel.removeRow(selected);
            }
        });

        this.mainPanel = FormBuilder.createFormBuilder()
                .addComponent(new JScrollPane(table))
                .addComponent(buttonPanel)
                .getPanel();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Map<String, String> getData() {
        return this.tableModel.asMap();
    }
}
