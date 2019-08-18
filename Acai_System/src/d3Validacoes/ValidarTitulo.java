package d3Validacoes;

public class ValidarTitulo
{
	private static String agencia;	
	private static String conta;
	private static String numero;
	private static String carteira;
	private static String primeiroDigito;
	private static String segundoDigito;
	
	
	public static String getPrimeiroDigito(){return primeiroDigito;}
	public static void setPrimeiroDigito(String primeiroDigito){ValidarTitulo.primeiroDigito = primeiroDigito;}
	public static String getSegundoDigito(){return segundoDigito;}
	public static void setSegundoDigito(String segundoDigito){ValidarTitulo.segundoDigito = segundoDigito;}
	public static String getAgencia(){	return agencia;}
	public static void setAgencia(String agencia){	ValidarTitulo.agencia = agencia;}
	public static String getConta(){return conta;}
	public static void setConta(String conta){ValidarTitulo.conta = conta;}
	public static String getNumero(){return numero;}
	public static void setNumero(String numero){ValidarTitulo.numero = numero;}
	public static String getCarteira(){return carteira;}
	public static void setCarteira(String carteira){ValidarTitulo.carteira = carteira;}
	
	int p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21;
	
	public void carregarDigitos() 
	{
		String Agencia = "";
		Agencia = ValidarTitulo.getAgencia();
		if(Agencia.length() == 3)
		{
			Agencia += "0" + Agencia;
		}
		
		p1 = (Integer.parseInt(Agencia.substring(0,1))* 4);
		p2 = (Integer.parseInt(Agencia.substring(1,2))* 3);
		p3 = (Integer.parseInt(Agencia.substring(2,3))* 2);
		p4 = (Integer.parseInt(Agencia.substring(3,4))* 7);
	
		String Conta = "";
		Conta = ValidarTitulo.getConta();
		do
		{
			Conta = "0" + Conta ;
		}
		while(Conta.length() < 7);
		p5 = (Integer.parseInt(Conta.substring(0,1))* 6);
		p6 = (Integer.parseInt(Conta.substring(1,2))* 5);
		p7 = (Integer.parseInt(Conta.substring(2,3))* 4);
		p8 = (Integer.parseInt(Conta.substring(3,4))* 3);
		p9 = (Integer.parseInt(Conta.substring(4,5))* 2);
		p10 = (Integer.parseInt(Conta.substring(5,6))* 7);
		p11 = (Integer.parseInt(Conta.substring(6,7))* 6);
		
		String numeroTitulo = "";
		numeroTitulo = ValidarTitulo.getNumero();
		
		p12 = (Integer.parseInt(numeroTitulo.substring(0,1))* 5);
		p13 = (Integer.parseInt(numeroTitulo.substring(1,2))* 4);
		p14 = (Integer.parseInt(numeroTitulo.substring(2,3))* 3);
		p15 = (Integer.parseInt(numeroTitulo.substring(3,4))* 2);
		p16 = (Integer.parseInt(numeroTitulo.substring(4,5))* 7);
		p17 = (Integer.parseInt(numeroTitulo.substring(5,6))* 6);
		p18 = (Integer.parseInt(numeroTitulo.substring(6,7))* 5);
		p19 = (Integer.parseInt(numeroTitulo.substring(4,5))* 4);
		p20 = (Integer.parseInt(numeroTitulo.substring(5,6))* 3);
		p21 = (Integer.parseInt(numeroTitulo.substring(6,7))* 2);
		
		int Soma = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14 + p15 + p16 + p17 + p18 + p19 + p20 + p21;
		
		int resto = (Soma % 11);
		
		if(resto  == 1 || resto  == 0)
		{
			setPrimeiroDigito("0");
		}
		else
		{
			setPrimeiroDigito(("" + (11 - resto)).substring(0,1));
		}
		
		String Carteira = getCarteira();
		if(Carteira.length() == 1)
		{
			Carteira += "0" + Carteira;
		}
		p1 = (Integer.parseInt(Carteira.substring(0,1))* 2);
		p2 = (Integer.parseInt(Carteira.substring(1,2))* 7);
	
		p3 = (Integer.parseInt(numeroTitulo.substring(0,1))* 6);
		p4 = (Integer.parseInt(numeroTitulo.substring(1,2))* 5);
		p5 = (Integer.parseInt(numeroTitulo.substring(2,3))* 4);
		p6 = (Integer.parseInt(numeroTitulo.substring(3,4))* 3);
		p7 = (Integer.parseInt(numeroTitulo.substring(4,5))* 2);
		p8 = (Integer.parseInt(numeroTitulo.substring(5,6))* 7);
		p9 = (Integer.parseInt(numeroTitulo.substring(6,7))* 6);
		p10 = (Integer.parseInt(numeroTitulo.substring(4,5))*5);
		p11 = (Integer.parseInt(numeroTitulo.substring(5,6))*4);
		p12 = (Integer.parseInt(numeroTitulo.substring(6,7))*3);
		
		p13 = (Integer.parseInt(getPrimeiroDigito())*2);
		
		Soma = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13;
		
		resto = (Soma % 11);
		
		if(resto  == 0)
		{
			setSegundoDigito("0");
		}
		else if(resto  == 1)
		{
			setSegundoDigito("P");
		}
		else
		{
			setSegundoDigito(("" + (11 - resto)).substring(0,1));
		}
	}
}
