package view;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.dto.CategoryDTO;

public interface View<T> {
	void showData(List<T> list, DefaultTableModel model);

}
