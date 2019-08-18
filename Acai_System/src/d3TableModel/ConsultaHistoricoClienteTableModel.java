package d3TableModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import Entity.Cliente;

public class ConsultaHistoricoClienteTableModel extends AbstractTableModel
{
	private static final int COL_ID = 0;	
	private static final int COL_NOME = 1;	
	private static final int COL_STATUS = 2;	
	private static final int COL_DATA = 3;
	private static final int COL_ORIGEM = 4;
	
	//variavel do tipo Lista;
	private List<Cliente> valores;
	
	
	public ConsultaHistoricoClienteTableModel(List<Cliente> valores){
		this.valores = valores;
	}
			
	@Override
	public int getColumnCount() 
		{
			//Numero de Colunas
			return 5;
		}

	@Override
	public int getRowCount() 
	{
	//numero de Linha será retornado
		return valores.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int ColumnIndex) {
		Cliente clientes = valores.get(rowIndex);
		//Se o Valor da Coluna da linha o id tem esse valor
		if (ColumnIndex == COL_ID){
			return clientes.getIdCliente();
		}else	if (ColumnIndex == COL_NOME){
			return clientes.getNome();
		}else	if (ColumnIndex == COL_STATUS){
			return clientes.getStatus();
		}else if (ColumnIndex == COL_DATA){
			return clientes.getDataVisual();
		}else if (ColumnIndex == COL_ORIGEM ){
			return clientes.getOrigem();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		//Edita o nome da Coluna
		String coluna = "";
		switch (column) {
		case  COL_ID:
			coluna = "Código";
			break;
		case COL_NOME:
			coluna = "Nome";
			break;
		case  COL_STATUS:
			coluna = "Status";
			break;
		case COL_DATA:
			coluna = "Data";
			break;
		case COL_ORIGEM:
			coluna = "Origem";
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
		}else	if (columnIndex == COL_NOME){
			return String.class;
		}else	if (columnIndex == COL_STATUS){
			return String.class;
		}else	if (columnIndex == COL_DATA){
			return String.class;
		}else	if (columnIndex == COL_ORIGEM){
			return String.class;
		}				
		return null;
	}
	
	public Cliente get(int row){
		//Retorna valor da Linha
		return valores.get(row);
	}
}
