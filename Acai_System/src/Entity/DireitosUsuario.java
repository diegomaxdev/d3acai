package Entity;

public class DireitosUsuario {

	private int idRotina;
	private int idUsuario;
	private String descricao;
	private String usuario;
	
	
	public int getIdRotina() {
		return idRotina;
	}
	public void setIdRotina(int idRotina) {
		this.idRotina = idRotina;
	}
	
	public int getIdusuario() {
		return idUsuario;
	}
	public void setIdusuario(int idusuario) {
		this.idUsuario = idusuario;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	//NÂO USAREMOS
	@Override
	public String toString() 
	{
		return super.toString();
	}
	


}
