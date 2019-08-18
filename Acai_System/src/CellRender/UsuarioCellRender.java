package CellRender;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class UsuarioCellRender extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		
		 setForeground(Color.DARK_GRAY);	
		 setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
		 // setBackground(Color.YELLOW);
				
		if(row % 2 == 0){
			setBackground(Color.WHITE);
		}else{
			setBackground(Color.LIGHT_GRAY);
		}
		
		if (isSelected){
			setBackground(Color.DARK_GRAY);
			setForeground(Color.WHITE);	
		}
	
		UsuarioCellRender esquerda = new UsuarioCellRender();  
		UsuarioCellRender centralizado = new UsuarioCellRender();  
		UsuarioCellRender direita = new UsuarioCellRender(); 
		
		esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
		direita.setHorizontalAlignment(SwingConstants.RIGHT);
		
		table.getColumnModel().getColumn(0).setMinWidth(100);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(0).setCellRenderer(centralizado); 
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setMinWidth(441);
		table.getColumnModel().getColumn(1).setMaxWidth(441);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado); 
		table.getColumnModel().getColumn(1).setResizable(false);
	
	
		return this;
		}

		
	
}
