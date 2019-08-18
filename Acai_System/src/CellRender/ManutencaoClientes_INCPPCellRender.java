package CellRender;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ManutencaoClientes_INCPPCellRender extends DefaultTableCellRenderer {
	
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
					row, column);
			
			
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			   setForeground(Color.DARK_GRAY);
			     	//LINHAS
					if(row % 2 == 0)
						{
							setBackground(Color.WHITE);
							setForeground(Color.DARK_GRAY);	
							  setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
						}		
					else
						{
							setForeground(Color.DARK_GRAY);	
							setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
							setBackground(Color.LIGHT_GRAY);
						}
			
			if (isSelected)
			{
				setBackground(Color.DARK_GRAY);
				 setForeground(Color.WHITE);
			}
			
			ManutencaoClientes_INCPPCellRender esquerda = new ManutencaoClientes_INCPPCellRender();  
			ManutencaoClientes_INCPPCellRender centralizado = new ManutencaoClientes_INCPPCellRender();  
			ManutencaoClientes_INCPPCellRender direita = new ManutencaoClientes_INCPPCellRender(); 
			
			esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
			centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
			direita.setHorizontalAlignment(SwingConstants.RIGHT);
			
			table.getColumnModel().getColumn(0).setMinWidth(70);
			table.getColumnModel().getColumn(0).setMaxWidth(70);
			table.getColumnModel().getColumn(0).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(0).setResizable(false);
			table.getColumnModel().getColumn(1).setMinWidth(300);
			table.getColumnModel().getColumn(1).setMaxWidth(300);
			table.getColumnModel().getColumn(1).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(2).setMinWidth(90);
			table.getColumnModel().getColumn(2).setMaxWidth(90);
			table.getColumnModel().getColumn(2).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(2).setResizable(false);
			table.getColumnModel().getColumn(3).setMinWidth(120);
			table.getColumnModel().getColumn(3).setMaxWidth(120);
			table.getColumnModel().getColumn(3).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(3).setResizable(false);
			table.getColumnModel().getColumn(4).setMinWidth(100);
			table.getColumnModel().getColumn(4).setMaxWidth(100);
			table.getColumnModel().getColumn(4).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(4).setResizable(true);
			table.getColumnModel().getColumn(5).setMinWidth(100);
			table.getColumnModel().getColumn(5).setMaxWidth(100);
			table.getColumnModel().getColumn(5).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(5).setResizable(false);
			table.getColumnModel().getColumn(6).setMinWidth(250);
			table.getColumnModel().getColumn(6).setMaxWidth(250);
			table.getColumnModel().getColumn(6).setCellRenderer(centralizado); 
				
			return this;
			
		}
	}