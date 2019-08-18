package CellRender;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ManutencaoClientesCellRender extends DefaultTableCellRenderer {
	
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
			
			if (isSelected)
			{
				setBackground(Color.DARK_GRAY);
				 setForeground(Color.WHITE);
			}
			
			
			if(value == null)
			{
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			}
			else if(value.equals("CLIENTE NÃO TRABALHADO") || value.equals("RETORNAR MAIS TARDE") || value.equals("NÃO ATENDE") || value.equals("NÚMERO SÓ CHAMA") || value.equals("OCUPADO")) 
			{
			   setForeground(Color.GREEN);	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			}
			else if(value.equals("SEM INTERESSE"))
			{
			   setForeground(Color.MAGENTA);	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			}
			else if(value.equals("AGENDAR RETORNO LIGAÇÂO"))
			{
			   setForeground(Color.YELLOW);	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			}
			else if(value.equals("CONTRATO FECHADO"))
			{
			   setForeground(Color.BLUE);	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			}
			else if(value.equals("VISITA AGENDADADA"))
			{
			   setForeground(Color.ORANGE);	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			}
			else if(value.equals("DESISTENCIA"))
			{
			   setForeground(Color.RED);	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			}
			else if(value.equals("SEM CLASSIFICACAO"))
			{
			   setForeground(Color.BLACK);	
			   setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 11));
			}
			
			ManutencaoClientesCellRender esquerda = new ManutencaoClientesCellRender();  
			ManutencaoClientesCellRender centralizado = new ManutencaoClientesCellRender();  
			ManutencaoClientesCellRender direita = new ManutencaoClientesCellRender(); 
			
			esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
			centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
			direita.setHorizontalAlignment(SwingConstants.RIGHT);
			
			table.getColumnModel().getColumn(0).setMinWidth(70);
			table.getColumnModel().getColumn(0).setMaxWidth(70);
			table.getColumnModel().getColumn(0).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(0).setResizable(false);
			table.getColumnModel().getColumn(1).setMinWidth(100);
			table.getColumnModel().getColumn(1).setMaxWidth(100);
			table.getColumnModel().getColumn(1).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(2).setMinWidth(70);
			table.getColumnModel().getColumn(2).setMaxWidth(70);
			table.getColumnModel().getColumn(2).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(2).setResizable(false);
			table.getColumnModel().getColumn(3).setMinWidth(250);
			table.getColumnModel().getColumn(3).setMaxWidth(250);
			table.getColumnModel().getColumn(3).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(3).setResizable(false);
			table.getColumnModel().getColumn(4).setMinWidth(300);
			table.getColumnModel().getColumn(4).setMaxWidth(300);
			table.getColumnModel().getColumn(4).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(4).setResizable(true);
			table.getColumnModel().getColumn(5).setMinWidth(140);
			table.getColumnModel().getColumn(5).setMaxWidth(140);
			table.getColumnModel().getColumn(5).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(5).setResizable(false);
			table.getColumnModel().getColumn(6).setMinWidth(250);
			table.getColumnModel().getColumn(6).setMaxWidth(250);
			table.getColumnModel().getColumn(6).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(7).setMinWidth(70);
			table.getColumnModel().getColumn(7).setMaxWidth(70);
			table.getColumnModel().getColumn(7).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(7).setResizable(false);
			table.getColumnModel().getColumn(8).setMinWidth(90);
			table.getColumnModel().getColumn(8).setMaxWidth(90);
			table.getColumnModel().getColumn(8).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(8).setResizable(true);
			table.getColumnModel().getColumn(9).setMinWidth(200);
			table.getColumnModel().getColumn(9).setMaxWidth(200);
			table.getColumnModel().getColumn(9).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(9).setResizable(false);
			table.getColumnModel().getColumn(10).setMinWidth(200);
			table.getColumnModel().getColumn(10).setMaxWidth(200);
			table.getColumnModel().getColumn(10).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(10).setResizable(false);
			table.getColumnModel().getColumn(11).setMinWidth(170);
			table.getColumnModel().getColumn(11).setMaxWidth(170);
			table.getColumnModel().getColumn(11).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(11).setResizable(false);
			table.getColumnModel().getColumn(12).setMinWidth(200);
			table.getColumnModel().getColumn(12).setMaxWidth(200);
			table.getColumnModel().getColumn(12).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(12).setResizable(false);
			table.getColumnModel().getColumn(13).setMinWidth(60);
			table.getColumnModel().getColumn(13).setMaxWidth(60);
			table.getColumnModel().getColumn(13).setCellRenderer(centralizado); 
			table.getColumnModel().getColumn(13).setResizable(false);
		
			return this;
			
		}
	}