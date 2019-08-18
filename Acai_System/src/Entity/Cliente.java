package Entity;

public class Cliente 
{
	private int idCliente;
	private String nome;
	private String Cargo;
	private String Periodo;
	private String Empresa;
	private String salario;
	private String telefone;
	private String celular;
	private static java.sql.Date data;
	private String dataVisual;
	private String status;
	private String CPF;
	private String RG;
	private String CEP;
	private String tipoDeEndereco;
	private String logradouro;
	private String complemento;
	private String numero;
	private String cidade;
	private String UF;
	private String email;
	private String sexo;
	private String origem;
	private int idUsuario;
	private String usuario;
	private String Ocorrencias;
	
	public String getUsuario() {return usuario;}
	public void setUsuario(String usuario) {this.usuario = usuario;}
	public int getIdUsuario() {return idUsuario;}
	public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}
	public int getIdCliente() {return idCliente;}
	public void setIdCliente(int idCliente) {this.idCliente = idCliente;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public String getCargo() {return Cargo;}
	public void setCargo(String cargo) {Cargo = cargo;}
	public String getPeriodo() {return Periodo;}
	public void setPeriodo(String periodo) {Periodo = periodo;}
	public String getEmpresa() {return Empresa;}
	public void setEmpresa(String empresa) {Empresa = empresa;}
	public String getSalario() {return salario;}
	public void setSalario(String salario) {this.salario = salario;}
	public String getTelefone() {return telefone;}
	public void setTelefone(String telefone) {this.telefone = telefone;}
	public String getCelular() {return celular;}
	public void setCelular(String celular) {this.celular = celular;}
	public static java.sql.Date getData() {return data;}
	public static void setData(java.sql.Date data) {Cliente.data = data;}
	public String getCPF() {return CPF;}
	public void setCPF(String cPF) {CPF = cPF;}
	public String getRG() {	return RG;}
	public void setRG(String rG) {RG = rG;}
	public String getCEP() {return CEP;}
	public void setCEP(String cEP) {CEP = cEP;}
	public String getTipoDeEndereco() {return tipoDeEndereco;}
	public void setTipoDeEndereco(String tipoDeEndereco) {this.tipoDeEndereco = tipoDeEndereco;}
	public String getLogradouro() {return logradouro;}
	public void setLogradouro(String logradouro) {this.logradouro = logradouro;}
	public String getComplemento() {return complemento;}
	public void setComplemento(String complemento) {this.complemento = complemento;}
	public String getNumero() {return numero;}
	public void setNumero(String numero) {this.numero = numero;}
	public String getCidade() {return cidade;}
	public void setCidade(String cidade) {this.cidade = cidade;}
	public String getUF() {return UF;}
	public void setUF(String uF) {UF = uF;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getSexo() {return sexo;}
	public void setSexo(String sexo) {this.sexo = sexo;}
	public String getOrigem() {return origem;}
	public void setOrigem(String origem) {this.origem = origem;}
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	public String getDataVisual() {	return dataVisual;}
	public void setDataVisual(String dataVisual) {this.dataVisual = dataVisual;}
	public String getOcorrencias() {return Ocorrencias;}
	public void setOcorrencias(String ocorrencias) {Ocorrencias = ocorrencias;}	
	
}
