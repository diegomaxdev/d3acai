package d3Validacoes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LoginDocument extends PlainDocument {
	@Override
	public void insertString(int inicio, String conteudo, AttributeSet a)
			throws BadLocationException {
		
		for(char c: conteudo.toCharArray()){
			//Verificando se o conteúdo inserido possui caractere invalidos
			if(! Character.isLetter(c) && c != '.' && c != ' '){
				return;
			}
		}
	
		super.insertString(inicio, conteudo.toUpperCase(), a);
	}

}
