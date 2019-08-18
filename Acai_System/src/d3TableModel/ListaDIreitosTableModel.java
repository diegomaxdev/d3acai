package d3TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Entity.DireitosTabela;


public class ListaDIreitosTableModel extends AbstractTableModel{
	
	private static final int COL_IDROTINA = 0;
	private static final int COL_DESCRICAO = 1;

	//variavel do tipo Lista;
	private List<DireitosTabela> valores;
		
	public ListaDIreitosTableModel(List<DireitosTabela> valores){
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
		DireitosTabela tabelaDireitos  = valores.get(rowIndex);
		//Se o Valor da Coluna da linha o id tem esse valor
		if (ColumnIndex == COL_IDROTINA){
			return tabelaDireitos.getIdRotina();
		}else	if (ColumnIndex == COL_DESCRICAO){
			return tabelaDireitos.getDescricao();
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
		//Indica o tipo de Dado de cada coluna
		if (columnIndex == COL_IDROTINA)
		{
			return String.class;
		}
		else	if (columnIndex == COL_DESCRICAO)
		{
			return String.class;
		}
		return null;
	}
	
	
	public DireitosTabela get(int row){
		//Retorna valor da Linha
		return valores.get(row);
	}
	
	
	

}
