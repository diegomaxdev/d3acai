package Entity;

public class Mailing 
{
	private int id;
	private String Nome;
	private String Telefone;
	private String Arquivo;
	private String Fornecedor;
	private String Data;
		
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNome() {return Nome;}
	public void setNome(String nome) {Nome = nome;}
	public String getTelefone() {return Telefone;}
	public void setTelefone(String telefone) {Telefone = telefone;}
	public String getArquivo() {return Arquivo;}
	public void setArquivo(String arquivo) {Arquivo = arquivo;}
	public String getFornecedor() {return Fornecedor;}
	public void setFornecedor(String fornecedor) {Fornecedor = fornecedor;}
	public String getData() {return Data;}
	public void setData(String data) {Data = data;}
	
}
