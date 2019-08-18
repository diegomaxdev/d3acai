package d3TableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import Entity.DireitosUsuario;


public class DireitoTableModel extends AbstractTableModel
{
	private static final int COL_IDROTINA = 0;
	private static final int COL_DESCRICAO = 1;

	//variavel do tipo Lista;
	private List<DireitosUsuario> valores;
	
	
	public DireitoTableModel(List<DireitosUsuario> valores){
		this.valores = valores;
	}
			
	@Override
	public int getColumnCount() 
		{
		//Numero de Colunas
		return 2;
		}

	@Override
	public int getRowCount() 
	{
	//numero de Linha será retornado
		return valores.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int ColumnIndex) {
		DireitosUsuario direito  = valores.get(rowIndex);
		if (ColumnIndex == COL_IDROTINA){
			return direito.getIdRotina();
		}else	if (ColumnIndex == COL_DESCRICAO){
			return direito.getDescricao();
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
		case COL_IDROTINA:
			coluna = "Id Rotina";
			break;
		case COL_DESCRICAO:
			coluna = "Descrição";
			break;	
		default:
			throw new IllegalArgumentException("Coluna Inválida");
		}
			return coluna;
	}



	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == COL_IDROTINA){
			return String.class;
		}else	if (columnIndex == COL_DESCRICAO){
			return String.class;
		}
		return null;
	}
	
	
	public DireitosUsuario get(int row){
		//Retorna valor da Linha
		return valores.get(row);
	}
	
	
	

}
