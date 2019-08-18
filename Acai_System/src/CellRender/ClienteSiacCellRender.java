package CellRender;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ClienteSiacCellRender extends DefaultTableCellRenderer {
	
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
				 setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			}		
			else
			{
				setForeground(Color.DARK_GRAY);	
				setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
				setBackground(Color.LIGHT_GRAY);
			}
			
			if(value == null)
			{
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			  // setBackground(Color.YELLOW);
			}
			else if(value.equals("CONFIRMADO"))
			{
			   setForeground(Color.BLUE);	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			  // setBackground(Color.YELLOW);
			   
			}
			else if(value.equals("COMPARECEU"))
			{
			   setForeground(Color.GREEN);	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			  // setBackground(Color.YELLOW);
			   
			}
			else if (value.equals("CANCELADO"))
			{
			   setForeground(Color.RED);	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			  // setBackground(Color.YELLOW);
			}
			else if(value.equals("SEM DIREITO"))
			{
			   setForeground(Color.CYAN);	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			  // setBackground(Color.YELLOW);
			}
			else if(value.equals("REAGENDADO"))
			{
			   setForeground(Color.MAGENTA);	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			  // setBackground(Color.YELLOW);
			}		
			else
			{	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			   setForeground(Color.DARK_GRAY);
			} 
			
						
			if (isSelected)
			{
				setBackground(Color.DARK_GRAY);
				 setForeground(Color.WHITE);
			}
			
			ClienteSiacCellRender esquerda = new ClienteSiacCellRender();  
			ClienteSiacCellRender centralizado = new ClienteSiacCellRender();  
			ClienteSiacCellRender direita = new ClienteSiacCellRender(); 
			
			esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
			centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
			direita.setHorizontalAlignment(SwingConstants.RIGHT);
			
			table.getColumnModel().getColumn(0).setMinWidth(74);
			table.getColumnModel().getColumn(0).setMaxWidth(74);
			table.getColumnModel().getColumn(0).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(0).setResizable(false);
			table.getColumnModel().getColumn(1).setMinWidth(350);
			table.getColumnModel().getColumn(1).setMaxWidth(350);
			table.getColumnModel().getColumn(1).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(2).setMinWidth(140);
			table.getColumnModel().getColumn(2).setMaxWidth(140);
			table.getColumnModel().getColumn(2).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(2).setResizable(false);
			table.getColumnModel().getColumn(3).setMinWidth(370);
			table.getColumnModel().getColumn(3).setMaxWidth(370);
			table.getColumnModel().getColumn(3).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(3).setResizable(false);
					
			return this;
			
		}
	}