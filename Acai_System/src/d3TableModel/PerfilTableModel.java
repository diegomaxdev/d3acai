package d3TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Entity.Perfil;

public class PerfilTableModel extends AbstractTableModel{

	private static final int COL_ID = 0;
	private static final int COL_DESCRICAO = 1;
	private static final int COL_STATUS = 2;
		

	//variavel do tipo Lista;
	private List<Perfil> valores;
	
	
	public  PerfilTableModel(List<Perfil> valores){
		this.valores = valores;
	}
			
	@Override
	public int getColumnCount() 
		{
		//Numero de Colunas
		return 3;
		}

	@Override
	public int getRowCount() 
	{
	//numero de Linha será retornado
		return valores.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int ColumnIndex) {
		Perfil perfis = valores.get(rowIndex);
		//Se o Valor da Coluna da linha o id tem esse valor
		if (ColumnIndex == COL_ID){
			return perfis.getIdPerfil();
		}else	if (ColumnIndex == COL_DESCRICAO){
			return perfis.getDescricao();
		}else	if (ColumnIndex == COL_STATUS){
			return perfis.getStatusVisual();
		}
		
		return null;
	}



	@Override
	public String getColumnName(int column) {
		//Edita o nome da Coluna
		String coluna = "";
		switch (column) {
		case COL_ID:
			coluna = "Código";
			break;
		case COL_DESCRICAO:
			coluna = "Descrição";
			break;
		case COL_STATUS:
			coluna = "Status";
			break;	
		default:
			throw new IllegalArgumentException("Coluna Inválida");
		
		}
		
		return coluna;
	}



	@Override
	public Class<?> getColumnClass(int columnIndex) {
		//Indica o tipo de Dado de cada coluna
		if (columnIndex == COL_ID){
			return Integer.class;
		}else	if (columnIndex == COL_DESCRICAO){
			return String.class;
		}else	if (columnIndex == COL_STATUS){
			return String.class;
		}		
		
		return null;
	}
	
	
	public Perfil get(int row){
		//Retorna valor da Linha
		return valores.get(row);
	}
}