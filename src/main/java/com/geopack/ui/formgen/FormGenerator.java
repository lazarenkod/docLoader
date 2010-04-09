package com.geopack.ui.formgen;

import com.geopack.models.DictColumnModel;
import com.geopack.models.DictRow;
import com.geopack.models.DictTableModel;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import java.util.List;

/**
 * User: Lazarenko.Dmitry
 * Date: 07.04.2010
 * Time: 15:38:29
 */
public class FormGenerator {
	private DictTableModel tableModel;

	public FormGenerator(DictTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JPanel makeEditForm() {
		JPanel rootPanel=new JPanel();
		rootPanel.setLayout(new VerticalLayout(10));
		for (DictColumnModel columnModel : tableModel.getColumnModels()) {
			JPanel rowPanel=new JPanel();
			rowPanel.setLayout(new HorizontalLayout(10));
			JLabel label=new JLabel(columnModel.getDescription());
			JTextField textField=new JTextField(15);
			textField.setName(columnModel.getName());
			textField.setToolTipText(columnModel.getDescription());
			rowPanel.add(label);
			rowPanel.add(textField);
			rootPanel.add(rowPanel);
		}
		return rootPanel;
	}

	public void setDataRow(DictRow row,DictTableModel tableModel, JPanel panel) {
		final List<DictColumnModel> columnModels = tableModel.getColumnModels();
		
	}

}
