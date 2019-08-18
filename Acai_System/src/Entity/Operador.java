package Entity;

public class Operador {
	
	private int idContato;
	private String descricao;
	private String statusVisual;
	
	public int getIdContato(){return idContato;}
	public void setIdContato(int idContato){this.idContato = idContato;}
	public String getDescricao() {	return descricao; }
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public String getStatusVisual() {return statusVisual;}
	public void setStatusVisual(String statusVisual) {this.statusVisual = statusVisual;	}

	@Override
	public String toString() {
		return super.toString();
	}

}

