package d3Validacoes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {

	private String cpf, cnpj, email, cartao;
	private boolean cpfValido = true;	
	private boolean cnpjValido = true;
	private boolean emailValido = true;
	private boolean cartaoValido = true;
	
	public boolean isCartaoValido()
	{
		return cartaoValido;
	}
	public void setCartaoValido(boolean cartaoValido)
	{
		this.cartaoValido = cartaoValido;
	}
	public boolean isEmailValido() {
		return emailValido;
	}
	public void setEmailValido(boolean emailValido) {
		this.emailValido = emailValido;
	}
	public boolean isCnpjValido() {
		return cnpjValido;
	}
	public void setCnpjValido(boolean cnpjValido) {
		this.cnpjValido = cnpjValido;
	}
	public boolean isCpfValido() {
		return cpfValido;
	}
	public void setCpfValido(boolean cpfValido) {
		this.cpfValido = cpfValido;
	}
	
	public void validaCartao(String cartao) {
		// ***********************************************************************************************************************************************************************
		// Atribui o valor deste Cpf é igual ao cpf recebido
		// ***********************************************************************************************************************************************************************
			this.cartao = cartao;
		
			if (cartao.equals("000000000000000") || cartao.equals("0000000000000000") || cartao.equals("111111111111111") || cartao.equals("1111111111111111")
			 || cartao.equals("222222222222222") || cartao.equals("2222222222222222") || cartao.equals("333333333333333") || cartao.equals("3333333333333333")
			 || cartao.equals("444444444444444") || cartao.equals("4444444444444444") || cartao.equals("555555555555555") || cartao.equals("5555555555555555")
			 || cartao.equals("666666666666666") || cartao.equals("6666666666666666") || cartao.equals("777777777777777") || cartao.equals("7777777777777777")
			 || cartao.equals("888888888888888") || cartao.equals("8888888888888888") || cartao.equals("999999999999999") || cartao.equals("9999999999999999"))
			{
				System.out.println("Cartão Inválido");
				setCartaoValido(false);
				return;
				// ***********************************************************************************************************************************************************************
				// TRATA SE TEM CARACTER ESPECIAL
			} 
			// ***********************************************************************************************************************************************************************
				int somatoriaCartao = 0 ; 
				int somatoriaNumerosPares = 0; 
				int somatoriaNumerosImpares = 0;
				int cont = 1;
				
				for (int i = 0; i < cartao.length(); i++)
				{
					if(i % 2 == 0)
					{
						if(Integer.parseInt(cartao.substring(i, cont))*2 > 9)
						{
							somatoriaNumerosPares += ((Integer.parseInt(cartao.substring(i, cont)))*2) - 9;
						}
						else
						{
							somatoriaNumerosPares += (Integer.parseInt(cartao.substring(i, cont)))*2;
						}		
					}
				else
				{
					somatoriaNumerosImpares += (Integer.parseInt(cartao.substring(i, cont)));
				}
				cont++;
			}
								
			somatoriaCartao = somatoriaNumerosPares  + somatoriaNumerosImpares;
				
			if(somatoriaCartao % 10 == 0)
			{
				setCartaoValido(true);
			}
			else
			{
				setCartaoValido(false);
			}	
		}
	// ************************************* FIM DO METODO
	// ******************************************
	
	public void validaCPF(String cpf) {
		// ***********************************************************************************************************************************************************************
		// Atribui o valor deste Cpf é igual ao cpf recebido
		// ***********************************************************************************************************************************************************************
		
			this.cpf = cpf;
		
			if (cpf.equals("00000000000") || cpf.equals("11111111111")
					|| cpf.equals("22222222222") || cpf.equals("33333333333")
					|| cpf.equals("44444444444") || cpf.equals("55555555555")
					|| cpf.equals("66666666666") || cpf.equals("77777777777")
					|| cpf.equals("88888888888") || cpf.equals("99999999999"))

			{
				System.out.println("CPF Inválido");
				setCpfValido(false);
				return;
				// ***********************************************************************************************************************************************************************
				// TRATA SE TEM CARACTER ESPECIAL
			} 
			else if (this.cpf.contains(".") || this.cpf.contains("-")) {
				this.cpf = this.cpf.replace(".", "");
				this.cpf = this.cpf.replace("-", "");
			}
			// ***********************************************************************************************************************************************************************
			// VERIFICA TAMANHO
			if (this.cpf.length() == 11) {

				char dig10, dig11;
				int somatoriaCPF, numeroConvertido, pesoMultiplicador;

				// ********************************************************CALCULA
				// O PRIMEIRO DIGITO
				// *****************************************************************************************
				somatoriaCPF = 0;
				pesoMultiplicador = 10;

				for (int posicao = 0; posicao < 9; posicao++) {

					// ***********************************************************************************************************************************************************************
					// converte o i-esimo character do CPF em um numero:
					// por exemplo, transforma o caractere '0' no inteiro 0
					// (48 eh a posicao de '0' na tabela ASCII)
					// System.out.println("numero na " + posicao + " posição : "
					// +
					// (this.cpf.charAt(posicao))); - CONFERE CPF E EXIBE
					// ***********************************************************************************************************************************************************************

					numeroConvertido = (int) (this.cpf.charAt(posicao) - 48);
					somatoriaCPF += (numeroConvertido * pesoMultiplicador);
					pesoMultiplicador -= 1;
				}
				int resto = 11 - (somatoriaCPF % 11);
				if ((resto == 10) || (resto == 11)) {
					dig10 = '0';
				} else {
					dig10 = (char) (resto + 48);
				}
				System.out.println("Digito 10 = " + dig10);
				// ***********************************************************************************************************************************************************************
				// Converte no respectivo caractere numerico - Calculo do 2o.
				// Digito - VERIFICADOR DO SEGUNDO DIGITO
				// ***********************************************************************************************************************************************************************
				somatoriaCPF = 0;
				pesoMultiplicador = 11;

				for (int posicao = 0; posicao < 10; posicao++) {
					numeroConvertido = (int) (cpf.charAt(posicao) - 48);
					somatoriaCPF += (numeroConvertido * pesoMultiplicador);
					pesoMultiplicador -= 1;
				}
				resto = 11 - (somatoriaCPF % 11);
				if ((resto == 10) || (resto == 11)) {
					dig11 = '0';
				} else {
					dig11 = (char) (resto + 48);
				}
				System.out.println("Digito 11 = " + dig11);

				// ***********************************************************************************************************************************************************************
				// Verifica se os digitos calculados conferem com os digitos -
				// informados. - EXIBE OS DOIS DIGITOS OBTIDOS
				// ***********************************************************************************************************************************************************************

				System.out
						.println("Este é o 10 digito : " + this.cpf.charAt(9));
				System.out.println("Este é o 11 digito : "
						+ this.cpf.charAt(10));

				// ******************************************************
				// COMPARA OS DOIS ULTIMOS DIGITOS
				// *********************************************************************************
				if ((dig10 == this.cpf.charAt(9))
						&& (dig11 == this.cpf.charAt(10))) {
					setCpfValido(true);
				} else {
					setCpfValido(false);
				}

			} else {
				setCpfValido(false);
			}
	}
	// ************************************* FIM DO METODO
	// ******************************************
	public void validaCnpj(String cnpj) {  
	   
		if(cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
				|| cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
				|| cnpj.equals("44444444444444") || cnpj.equals("55555555555333")
				|| cnpj.equals("66666666666666") || cnpj.equals("77777777777333")
				|| cnpj.equals("88888888888888") || cnpj.equals("99999999999333"))

		{
			System.out.println("CNPJ Inválido");
			setCnpjValido(false);
		}
		else if (!cnpj.substring(0, 1).equals("")) 
		{  
        try {  
            cnpj = cnpj.replace('.', ' ');//onde há ponto coloca espaço  
            cnpj = cnpj.replace('/', ' ');//onde há barra coloca espaço  
            cnpj = cnpj.replace('-', ' ');//onde há traço coloca espaço  
            cnpj = cnpj.replaceAll(" ", "");//retira espaço  
            int soma = 0, dig;  
            String cnpj_calculado = cnpj.substring(0, 12);  

            if (cnpj.length() != 14) {  
            	setCnpjValido(false); 
            }  
            char[] char_cnpj = cnpj.toCharArray();  
            /* Primeira parte */  
            for (int i = 0; i < 4; i++) {  
                if (char_cnpj[i] - 48 >= 0 && char_cnpj[i] - 48 <= 9) {  
                    soma += (char_cnpj[i] - 48) * (6 - (i + 1));  
                }  
            }  
            for (int i = 0; i < 8; i++) {  
                if (char_cnpj[i + 4] - 48 >= 0 && char_cnpj[i + 4] - 48 <= 9) {  
                    soma += (char_cnpj[i + 4] - 48) * (10 - (i + 1));  
                }  
            }  
            dig = 11 - (soma % 11);  
            cnpj_calculado += (dig == 10 || dig == 11) ? "0" : Integer.toString(  
                    dig);  
            /* Segunda parte */  
            soma = 0;  
            for (int i = 0; i < 5; i++) {  
                if (char_cnpj[i] - 48 >= 0 && char_cnpj[i] - 48 <= 9) {  
                    soma += (char_cnpj[i] - 48) * (7 - (i + 1));  
                }  
            }  
            for (int i = 0; i < 8; i++) {  
                if (char_cnpj[i + 5] - 48 >= 0 && char_cnpj[i + 5] - 48 <= 9) {  
                    soma += (char_cnpj[i + 5] - 48) * (10 - (i + 1));  
                }  
            }  
            dig = 11 - (soma % 11);  
            cnpj_calculado += (dig == 10 || dig == 11) ? "0" : Integer.toString(  
                    dig);  
            System.out.println(cnpj_calculado);
            System.out.println(cnpj);
            //COMPARA E VALIDA OS CNPJ
            if(cnpj.equals(cnpj_calculado))
            {
            	setCnpjValido(true);
            }
            else
            {
            	setCnpjValido(false);  
            }
              
        }  
        catch (Exception e) {  
        	setCnpjValido(false);  
        }  
    }  
    else {  
    	setCnpjValido(false);  
    }  
} 

	public void validaEmail(String email) {
		this.email = email;
		Pattern expressao = Pattern
				.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");

		// *************************** EXPLICACAO EXPRESSÂO REGULAR
		// ********************************

		// ^ começando a validação de um e-mail
		// - \\w Qualquer letra, dígito ou underscore ( _ ) - Palavra mais algo,
		// string que procura algo
		// - \\. expressao que pode agrupar um ponto
		// * quando a sequencia é opcional
		// [a-zA-Z] - qualquer caracter letras
		// {2,7} - xom tamanho minimo de 2 a 7
		// Ose seja ("^ - inicio [\\w-] palavra +(\\.[\\w-]+)* - opcional de
		// palavra com ponto ou caracter
		// @ - Simbolo obrigatório ([\\w-]+\\.) - qualquer caracter somado de
		// ponto +[a-zA-Z]{2,7}$" expressão com dois a 7 caracteres);

		// ************************************* FIM EXPLICAÇÂO
		// ******************************************

		Matcher mascara = expressao.matcher(email);

		if (mascara.find()) 
		{
			setEmailValido(true); 
		} 
		else 
		{
			setEmailValido(false); 
		}
	}

	
	
}
