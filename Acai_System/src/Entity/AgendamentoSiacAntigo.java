package Entity;

public class AgendamentoSiacAntigo 
{
	private int idAgenda;
	private String dataAgenda;
	private String horaAgenda;
	private int idCliente;
	private String status;
	private String DataChegada;
	private String HoraChegada;
	private String Operador;
	private String DataIncl;
	private int IDFilial;
	private String nomeFilial;
	private String nomeCliente;
	private String telefone;
	private java.sql.Date dataInclusaoSQL;

	public int getIdAgenda() {return idAgenda;}
	public void setIdAgenda(int idAgenda) {this.idAgenda = idAgenda;}
	public String getDataAgenda() {return dataAgenda;}
	public void setDataAgenda(String dataAgenda) {this.dataAgenda = dataAgenda;}
	public String getHoraAgenda() {return horaAgenda;}
	public void setHoraAgenda(String horaAgenda) {this.horaAgenda = horaAgenda;}
	public int getIdCliente() {return idCliente;}
	public void setIdCliente(int idCliente) {this.idCliente = idCliente;}
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	public String getDataChegada() {return DataChegada;}
	public void setDataChegada(String dataChegada) {DataChegada = dataChegada;}
	public String getHoraChegada() {return HoraChegada;}
	public void setHoraChegada(String horaChegada) {HoraChegada = horaChegada;}
	public String getOperador() {return Operador;}
	public void setOperador(String operador) {Operador = operador;}
	public String getDataIncl() {return DataIncl;}
	public void setDataIncl(String dataIncl) {DataIncl = dataIncl;}
	public int getIDFilial() {return IDFilial;}
	public void setIDFilial(int iDFilial) {IDFilial = iDFilial;}
	public String getNomeFilial() {return nomeFilial;}
	public void setNomeFilial(String nomeFilial) {this.nomeFilial = nomeFilial;}
	public String getNomeCliente() {return nomeCliente;}
	public void setNomeCliente(String nomeCliente) {this.nomeCliente = nomeCliente;}
	public String getTelefone() {return telefone;}
	public void setTelefone(String telefone) {this.telefone = telefone;}
	public java.sql.Date getDataInclusaoSQL() {return dataInclusaoSQL;}
	public void setDataInclusaoSQL(java.sql.Date dataInclusaoSQL) {this.dataInclusaoSQL = dataInclusaoSQL;}

			
}
