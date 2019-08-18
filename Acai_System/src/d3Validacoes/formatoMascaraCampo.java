package d3Validacoes;

import javax.swing.text.MaskFormatter;

public class formatoMascaraCampo {

	public static MaskFormatter formatoMascaraCampo(int intTpMascara) {  
		  
		  // Declara�ao e Inicializa�ao de Vari�veis  
		  MaskFormatter formato = new MaskFormatter();  
		  
		  try{  
		    switch (intTpMascara){  
		    case 1:  
		      //Formato data  
		      formato.setMask("##/##/####");  
		      formato.setPlaceholderCharacter('_');  
		      break;  
		    case 2:  
		      //Formato da data mostrada ao usu�rio  
		      //Tem um local onde o usu�rio me diz qual � o formato  
		      //de data que ele quer visualizar no sistema.  
		      formato.setMask("UU/UU/UUUU");  
		      formato.setPlaceholderCharacter('_');  
		      formato.setValidCharacters("DMY");  
		      break;  
		    case 3:  
		      //Formato de hora  
		      formato.setMask("##:##:##");  
		      formato.setPlaceholderCharacter('_');  
		      break;  
		    case 4:  
		      //Formato de hora  
		      formato.setMask("###.###.###-##");  
		      formato.setPlaceholderCharacter('_');  
		      break;  
		    case 5:  
		      //Formato de hora  
		      formato.setMask("##.###.###/####-##");  
		      formato.setPlaceholderCharacter('_');  
		      break;  
		    case 6:  
		      //Formato de hora  
		      formato.setMask("#####-###");  
		      formato.setPlaceholderCharacter('_');  
		      break;  
		    } 
		  
		  }  
		  catch (Exception ex) {  
		    ex.printStackTrace();  
		  }  
		  return formato;  
		}
	}