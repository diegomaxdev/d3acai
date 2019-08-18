package d3TableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import Entity.Cliente_INCPP;


public class ManutencaoClientesTableModel extends AbstractTableModel 
{
		private static final int COL_CODIGO = 0;
		private static final int COL_NOME = 1;
		private static final int COL_CONTA = 2;
		private static final int COL_VALOR = 3;
		private static final int COL_ARQUIVO = 4;
		private static final int COL_PASTA = 5;		
		private static final int COL_LOCAL = 6;
	
			
		//variavel do tipo Lista;
		private List<Cliente_INCPP> valores;
			
		public ManutencaoClientesTableModel(List<Cliente_INCPP> valores){
			this.valores = valores;
		}
				
		@Override
		public int getColumnCount() 
			{
				//Numero de Colunas
				return 7;
			}

		@Override
		public int getRowCount() 
		{
			//numero de Linha será retornado
			return valores.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int ColumnIndex) {
			Cliente_INCPP cliente  = valores.get(rowIndex);
			//Se o Valor da Coluna da linha o id tem esse valor
			if (ColumnIndex == COL_CODIGO){
				return cliente.getIdCliente();
			}else if (ColumnIndex == COL_NOME ){
				return cliente.getNome();
			}else	if (ColumnIndex == COL_CONTA){
				return cliente.getConta();
			}else	if (ColumnIndex == COL_VALOR){
				return cliente.getValor();
			}else	if (ColumnIndex == COL_ARQUIVO){
				return cliente.getArquivo();
			}else	if (ColumnIndex == COL_PASTA){
				return cliente.getPasta();
			}else	if (ColumnIndex == COL_LOCAL){
				return cliente.getLocal();
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
			case COL_CONTA :
				coluna = "Conta";
				break;
			case COL_VALOR :
				coluna = "Valor";
				break;
			case COL_ARQUIVO:
				coluna = "Arquivo";
				break;
			case COL_PASTA:
				coluna = "Pasta";
				break;	
			case COL_LOCAL :
				coluna = "Local";
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
			}else	if (columnIndex == COL_CONTA){
				return String.class;
			}else	if (columnIndex == COL_VALOR){
				return String.class;
			}else	if (columnIndex == COL_ARQUIVO){
				return String.class;
			}else	if (columnIndex == COL_PASTA){
				return String.class;
			}else	if (columnIndex == COL_LOCAL){
				return String.class;
			}
			return null;
		}
		
		public Cliente_INCPP get(int row){
			//Retorna valor da Linha
			return valores.get(row);
		}
	}
