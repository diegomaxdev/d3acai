package Informações;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;

import PrincipalMain.d3Frame;
import d3Frame.NumeroPedidoFrame;

public class Informacoes {
	JLabel JLabelRotulo; 
	int hora,minutos,segundos,h; 
	Calendar CalendarHora; 
	DecimalFormat formato;
	Date data;
	
	private static String horaAtual;
	private static String DataAual;
	private static String informacoesLogin;
	
	public static String getDataAual() {return DataAual;}
	public static void setDataAual(String dataAual) {DataAual = dataAual;}
	public static String getInformacoesLogin() {return informacoesLogin;}
	public static void setInformacoesLogin(String informacoesLogin) {Informacoes.informacoesLogin = informacoesLogin;}
	public static String getHoraAtual() {return horaAtual;}
	public static void setHoraAtual(String horaAtual) {Informacoes.horaAtual = horaAtual;}
	
	public void InformacoesLogin(){
		data = new Date(); 
	    SimpleDateFormat horaFormatada = new SimpleDateFormat("hh:mm a");  
	    SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy"); 
	    d3Frame.setHorarioDeAcesso("Informações do acesso : " +dataFormatada.format(data)+" às "+ horaFormatada.format(data));
	    NumeroPedidoFrame.setHorarioDeAcesso("Informações do acesso : " +dataFormatada.format(data)+" às "+ horaFormatada.format(data));
	}

	public void DATA()
	{
		data = new Date();  
	    SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
	    setDataAual("Data : " +dataFormatada.format(data));
	}
	
	public void HORAS()
	{ 
		CalendarHora = Calendar.getInstance(); 
		hora = CalendarHora.get(Calendar.HOUR_OF_DAY); 
		minutos = CalendarHora.get(Calendar.MINUTE); 
		segundos = CalendarHora.get(Calendar.SECOND); 
		formato = new DecimalFormat("00"); 
		
		if(d3Frame.lbInformacoes != null)
		{
			d3Frame.lbInformacoes.setText("Hora: "+formatar(hora%24)+":"+formatar(minutos)+":"+formatar(segundos) +"   "+ getDataAual() );
		}
		
		if(NumeroPedidoFrame.lbInformacoes != null)
		{
			NumeroPedidoFrame.lbInformacoes.setText("Hora: "+formatar(hora%24)+":"+formatar(minutos)+":"+formatar(segundos) +"   "+ getDataAual() );
		}
			
		setHoraAtual("Hora: "+formatar(hora%12)+":"+formatar(minutos)+":"+formatar(segundos));
	} 

	private String formatar(int num)
	{ 
		formato = new DecimalFormat("00"); 
		return formato.format(num); 
	} 
} 

