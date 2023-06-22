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

import javax.swing.table.AbstractTableModel;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StringKeyValueTableModel extends AbstractTableModel {

    private final List<Map.Entry<String, String>> data;
    private final String[] columnNames;

    public StringKeyValueTableModel(Map<String, String> map, String[] columnNames) {
        this.data = new ArrayList<>();
        map.entrySet().forEach(this.data::add);
        this.columnNames = columnNames;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Map.Entry<String, String> row = this.data.get(rowIndex);
        return columnIndex == 0 ? row.getKey() : row.getValue();
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Map.Entry<String, String> entry = this.data.get(rowIndex);
        Map.Entry<String, String> updated = columnIndex == 0
                ? new AbstractMap.SimpleEntry<>(value.toString(), entry.getValue())
                : new AbstractMap.SimpleEntry<>(entry.getKey(), value.toString());
        this.data.set(rowIndex, updated);
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void addRow() {
        this.data.add(new AbstractMap.SimpleEntry<>("", ""));
        fireTableRowsInserted(this.data.size() - 1, this.data.size() - 1);
    }

    public void removeRow(int rowIndex) {
        this.data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Map<String, String> asMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        this.data.stream()
                .filter((entry) -> !entry.getKey().equals("") && !entry.getValue().equals(""))
                .forEach((entry) -> map.put(entry.getKey(), entry.getValue()));
        return map;
    }
}
