package CellRender;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TabelaDireitosCellRender  extends DefaultTableCellRenderer{
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		if(row % 2 == 0){
			setBackground(Color.WHITE);
		}else{
			setBackground(Color.LIGHT_GRAY);
		}
		
		if (isSelected){
			setBackground(Color.DARK_GRAY);
		}
	
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.getColumnModel().getColumn(1).setMaxWidth(400);
		return this;
		}


}
