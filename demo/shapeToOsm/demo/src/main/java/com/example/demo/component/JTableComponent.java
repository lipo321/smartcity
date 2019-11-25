//package com.shape.tool.view.component;
package com.example.demo.component;

import com.example.demo.component.style.ComponentStyle;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class JTableComponent extends AbstractDefaultComponent {

    public static JTable initTable() {
        /** 初始化数据内容 */
        DefaultTableModel tableModel = new DefaultTableModel(ROW_DATA, COLUMN_NAME);
        return initTable(tableModel);
    }

    public static JTable initTable(Object[][] rowData, Object[] columnName) {
        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnName);
        return initTable(tableModel);
    }

    public static JTable initTable(DefaultTableModel tableModel) {
        /** 禁用表格编辑 */
        @SuppressWarnings("serial")
        JTable jTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.setAutoCreateColumnsFromModel(true);
        /** 表头字体 */
        jTable.getTableHeader().setFont(ComponentStyle.DEFAULT_FONT);
        /** 禁止整列拖动 */
        jTable.getTableHeader().setReorderingAllowed(false);
        /** 禁止调整列宽 */
        jTable.getTableHeader().setResizingAllowed(false);
        /** 表格字体 */
        jTable.setFont(ComponentStyle.DEFAULT_FONT);
        /** 选中背景色 */
        jTable.setSelectionBackground(ComponentStyle.DEFAULT_COLOR);
        /** 行高 */
        jTable.setRowHeight(ComponentStyle.TEXT_HEIGHT);
        /** 行距 */
        jTable.setRowMargin(ComponentStyle.CONTENT_BORDER_DISTANCE);
        /** 允许整行选中 */
        jTable.setRowSelectionAllowed(true);
        /** 显示网格线 */
        jTable.setShowGrid(true);

        jTable.doLayout();
        jTable.setBackground(Color.white);

        /** 表格居中 */
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable.setDefaultRenderer(Object.class, tableCellRenderer);
        /** 第一列（序号）宽度 */
        TableColumn column = jTable.getColumnModel().getColumn(0);
        column.setMinWidth(fontWidth(2));
        column.setMaxWidth(fontWidth(2));
        column.setPreferredWidth(fontWidth(2));

        return jTable;
    }

    public static JPanel initTablePanel(JPanel wrapPanel, JTable jTable) {
        JPanel panel = new JPanel();

        panel.setSize(wrapPanel.getSize());
        int tableWidth = wrapPanel.getWidth() - ComponentStyle.TABLE_DISTANCE_WIDTH;
        int tableHeight = wrapPanel.getHeight() - ComponentStyle.TABLE_DISTANCE_HEIGHT;
        jTable.setPreferredScrollableViewportSize(new Dimension(tableWidth, tableHeight));

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        wrapPanel.setLayout(gridBagLayout);

        JScrollPane scrollPane = new JScrollPane(jTable);
        panel.add(scrollPane);

        addComponent(wrapPanel, panel, gridBagLayout, constraints);
        return panel;
    }

    public static void addComponent(JFrame jFrame, JComponent jComponent, GridBagLayout gridBagLayout, GridBagConstraints constraints) {
        gridBagLayout.setConstraints(jComponent, constraints);
        jFrame.add(jComponent);
    }

    public static void addComponent(JPanel jPanel, JComponent jComponent, GridBagLayout gridBagLayout, GridBagConstraints constraints) {
        gridBagLayout.setConstraints(jComponent, constraints);
        jPanel.add(jComponent);
    }


}
