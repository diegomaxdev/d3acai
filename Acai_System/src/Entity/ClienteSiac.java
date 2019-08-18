package Entity;

import java.math.BigDecimal;
import java.sql.Date;

public class ClienteSiac 
{
	private int idCliente;
	private String Nome;
	private String Celular;
	private String Telefone;
	private String Obs;
	private String Agencia;
	private String Conta;
	private String Banco;
	private String operador;
	private Date DataInclusao;
	private int idFilial;
	
	public int getIdCliente() {return idCliente;}
	public void setIdCliente(int idCliente) {this.idCliente = idCliente;}
	public String getNome() {return Nome;}
	public void setNome(String nome) {Nome = nome;}
	public String getTelefone() {return Telefone;}
	public void setTelefone(String telefone) {Telefone = telefone;}
	public String getObs() {return Obs;}
	public void setObs(String obs) {Obs = obs;}
	public String getAgencia() {return Agencia;}
	public void setAgencia(String agencia) {Agencia = agencia;}
	public String getConta() {return Conta;}
	public void setConta(String conta) {Conta = conta;}
	public String getBanco() {return Banco;}
	public void setBanco(String banco) {Banco = banco;}
	public String getOperador() {return operador;}
	public void setOperador(String operador) {this.operador = operador;}
	public Date getDataInclusao() {return DataInclusao;}
	public void setDataInclusao(Date dataInclusao) {DataInclusao = dataInclusao;}
	public String getCelular() {return Celular;}
	public void setCelular(String celular) {Celular = celular;}
	public int getIdFilial() {return idFilial;}
	public void setIdFilial(int idFilial) {	this.idFilial = idFilial;}

}
