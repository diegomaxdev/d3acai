package d3TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Entity.Usuario;
public class UsuarioTableModel extends AbstractTableModel{

	private static final int COL_ID = 0;
	private static final int COL_USUARIO = 1;
	
	//variavel do tipo Lista;
	private List<Usuario> valores;
	
	
	public UsuarioTableModel(List<Usuario> valores){
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
	//numero de Linha ser� retornado
		return valores.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int ColumnIndex) {
		Usuario usuario = valores.get(rowIndex);
		//Se o Valor da Coluna da linha o id tem esse valor
		if (ColumnIndex == COL_ID){
			return usuario.getId();
		}else	if (ColumnIndex == COL_USUARIO){
			return usuario.getUsuario();
		}
		return null;
	}



	@Override
	public String getColumnName(int column) {
		//Edita o nome da Coluna
		String coluna = "";
		switch (column) {
		case COL_ID:
			coluna = "C�digo";
			break;
		case COL_USUARIO:
			coluna = "Usu�rio ";
			break;
		default:
			throw new IllegalArgumentException("Coluna Inv�lida");
		
		}
		
		return coluna;
	}



	@Override
	public Class<?> getColumnClass(int columnIndex) {
		//Indica o tipo de Dado de cada coluna
		if (columnIndex == COL_ID){
			return Long.class;
		}
		else	if (columnIndex == COL_USUARIO){
			return String.class;
		}	
		return null;
	}
	
	
	public Usuario get(int row){
		//Retorna valor da Linha
		return valores.get(row);
	}
	
	
	

}