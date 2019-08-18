package Entity;

public class DireitosTabela {

	private String descricao;
	private int idRotina;
	
	public int getIdRotina() {
		return idRotina;
	}
	public void setIdRotina(int idRotina) {
		this.idRotina = idRotina;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	//NÂO USAREMOS
		@Override
		public String toString() 
		{
			return super.toString();
		}
		
}
