package d3Validacoes;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidaDataVencimento {
	
	private int dia, mes, ano;
	private Date dataCorrigida;

	public int getDia() { return dia;}
	public void setDia(int dia) {this.dia = dia;	}
	
	public int getMes() {return mes;}
	public void setMes(int mes) {this.mes = mes;}
	
	public int getAno() {return ano;}
	public void setAno(int ano) {this.ano = ano;}
	
	public Date getDataCorrigida() {
		return dataCorrigida;
	}
	public void setDataCorrigida(Date dataCorrigida) {
		this.dataCorrigida = dataCorrigida;
	}
	
	public static Date DataPadraoSQL;
	
	//public boolean isDataValida() {return dataValida;}
	//public void setDataValida(boolean dataValida) {	this.dataValida = dataValida;}
	
	public static Date getDataPadraoSQL(){return DataPadraoSQL;}
	public static void setDataPadraoSQL(Date dataPadraoSQL){DataPadraoSQL = dataPadraoSQL;}
	
	public void carregaDataPadrao()
	{
	
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
		java.util.Date dataPadrao = null;
	
		try
		{
			dataPadrao = format.parse("01/01/1900");
		} 
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		java.sql.Date dataSqlPadrao = new java.sql.Date(dataPadrao.getTime());
		setDataPadraoSQL(dataSqlPadrao);

	}

	int fevereiro = 28;
	public void validaData(/*int ano, int dia, int mes*/)
	{
	
		if(ano % 4 == 0)
		{
			fevereiro = 29;
			if(mes == 2)
			{
				if(dia <= 29)
				{
					setDia(dia);
					setMes(mes);
					setAno(ano);
				}
				else
				{
					setDia(fevereiro);
					setMes(mes);
					setAno(ano);
				}	
			}
		}
		else
		{	
			//MÊS DE FEVEREIRI SEM SER EM ANO BISSEXTO
			if(mes == 2)
			{
				if(dia <= 28)
				{
					setDia(dia);
					setMes(mes);
					setAno(ano);
				}
				else
				{
					setDia(fevereiro);
					setMes(mes);
					setAno(ano);
				}	
			}
		}	
			//MESES QUE POSSUEM 31 DIAS
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)
		{
			if(dia <= 31)
			{
				setDia(dia);
				setMes(mes);
				setAno(ano);
			}
			else
			{
					setDia(31);
					setMes(mes);
					setAno(ano);
			}
		}
		//MESES QUE POSSUEM 30 DIAS
		else
		{
			if(dia <= 30)
			{
				setDia(dia);
				setMes(mes);
				setAno(ano);
			}
			else
			{
				setDia(30);
				setMes(mes);
				setAno(ano);
			}	
		}
		
	String dataCerta = ""; 
	if((""+dia).length() == 1 && (""+mes).length() == 1)
	{
		dataCerta = "0" + dia + "/0" + mes + "/" + ano;
	}
	else if((""+mes).length() == 1 && (""+dia).length() == 2)
	{
		dataCerta = dia + "/0" + mes + "/" + ano;
	}	
	else if((""+dia).length() == 1 && (""+mes).length() == 2)
	{
		dataCerta = "0" + dia + "/" + mes + "/" + ano;
	}
	else if((""+dia).length() == 2 && (""+mes).length() == 2)
	{
		dataCerta =  dia + "/" + mes + "/" + ano;
	}	
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
	java.util.Date data = null;
	
	try
	{
		data = format.parse(dataCerta);
	} 
	catch (ParseException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	java.sql.Date dataSql = new java.sql.Date(data.getTime());
	setDataCorrigida(dataSql);
	
	
	java.util.Date dataPadrao = null;
	
	try
	{
		dataPadrao = format.parse("01/01/1900");
	} 
	catch (ParseException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	java.sql.Date dataSqlPadrao = new java.sql.Date(data.getTime());
	setDataPadraoSQL(dataSqlPadrao);
	
	
	
	}
	
}




