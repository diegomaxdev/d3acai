package Entity;

import java.math.BigDecimal;
import java.sql.Date;

public class ClienteSiacAntigo 
{
	private int idCliente;
	private String Nome;
	private String Origem;
	private int Idade;
	private BigDecimal Beneficio;
	private String Telefone;
	private String Obs;
	private String Agencia;
	private String Conta;
	private String Banco;
	private String operador;
	private Date DataInclusao;
	private String Status;
	
	public int getIdCliente() {return idCliente;}
	public void setIdCliente(int idCliente) {this.idCliente = idCliente;}
	public String getNome() {return Nome;}
	public void setNome(String nome) {Nome = nome;}
	public String getOrigem() {return Origem;}
	public void setOrigem(String origem) {Origem = origem;}
	public int getIdade() {return Idade;}
	public void setIdade(int idade) {Idade = idade;}
	public BigDecimal getBeneficio() {return Beneficio;}
	public void setBeneficio(BigDecimal beneficio) {Beneficio = beneficio;}
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
	public String getStatus() {return Status;}
	public void setStatus(String status) {Status = status;}
}
