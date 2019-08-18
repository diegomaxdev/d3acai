package d3TableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import Entity.ClienteSiac;

public class ClienteSiacTableModel extends AbstractTableModel 
{
		private static final int COL_CODIGO = 0;
		private static final int COL_NOME = 1;
		private static final int COL_TELEFONE = 2;
		private static final int COL_OBSERVACAO = 3;

		private List<ClienteSiac> valores;
			
		public ClienteSiacTableModel(List<ClienteSiac> valores){
			this.valores = valores;
		}
				
		@Override
		public int getColumnCount() 
			{
				//Numero de Colunas
				return 4;
			}

		@Override
		public int getRowCount() 
		{
			//numero de Linha será retornado
			return valores.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int ColumnIndex) {
			ClienteSiac cliente  = valores.get(rowIndex);
			//Se o Valor da Coluna da linha o id tem esse valor
			if (ColumnIndex == COL_CODIGO){
				return cliente.getIdCliente();
			}else if (ColumnIndex == COL_NOME ){
				return cliente.getNome();
			}else	if (ColumnIndex == COL_TELEFONE){
				
				String Telefone = cliente.getTelefone().trim();
				if(Telefone.contains("("))
				{
					Telefone = Telefone.replace("(", "");
					Telefone = Telefone.replace(")", "");
					Telefone = Telefone.replace(" ", "");
					Telefone = Telefone.replace("-", "");
				}
				
				if(Telefone.length() == 0)
				{
					return cliente.getCelular();
				}
				else
				{
					return cliente.getTelefone();
				}
				
			}else	if (ColumnIndex == COL_OBSERVACAO){
				return cliente.getObs();
			}
			return null;
		}

		@Override
		public String getColumnName(int column) 
		{
			//Edita o nome da Coluna
			String coluna = "";
			switch (column) 
			{
			
			case COL_CODIGO :
				coluna = "Código";
				break;
			case COL_NOME :
				coluna = "Nome";
				break;
			case COL_TELEFONE :
				coluna = "Telefone";
				break;
			case COL_OBSERVACAO :
				coluna = "Observação";
				break;
			default:
				throw new IllegalArgumentException("Coluna Inválida");
			}
				return coluna;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			//Indica o tipo de Dado de cada coluna
			if (columnIndex == COL_CODIGO){
				return Integer.class;
			}else if (columnIndex == COL_NOME){
				return String.class;
			}else	if (columnIndex == COL_TELEFONE){
				return String.class;
			}else	if (columnIndex == COL_OBSERVACAO){
				return String.class;
			}
			return null;
		}
		
		public ClienteSiac get(int row)
		{
			return valores.get(row);
		}
	}
