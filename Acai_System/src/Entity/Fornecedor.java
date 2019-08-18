package Entity;

public class Fornecedor {
		
	private int idBanco;
	private String descricao;
	private int statusBancos;
	private String statusVisual;
	private String telefone;
	
	public int getIdBanco() {return idBanco;}
	public void setIdBanco(int idBanco) {this.idBanco = idBanco;}
	public String getDescricao() {return descricao;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public int getStatusBancos() {return statusBancos;}
	public void setStatusBancos(int statusBancos) {this.statusBancos = statusBancos;}
	public String getStatusVisual() {return statusVisual;}
	public void setStatusVisual(String statusVisual) {this.statusVisual = statusVisual;}
	public String getTelefone() {return telefone;}
	public void setTelefone(String telefone) {this.telefone = telefone;}
}

