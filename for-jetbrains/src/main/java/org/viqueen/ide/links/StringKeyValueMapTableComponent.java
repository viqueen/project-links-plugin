package org.viqueen.ide.links;

import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;
import java.awt.*;
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
