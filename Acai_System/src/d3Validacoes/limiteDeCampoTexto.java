package d3Validacoes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class limiteDeCampoTexto extends PlainDocument {

	private int limite = -1;
	public limiteDeCampoTexto() {}
	
	public limiteDeCampoTexto(int limiteDoCampo){
		this.limite = limiteDoCampo;
		
	}
			@Override
			public void insertString(int inicio, String conteudo, AttributeSet a)
					throws BadLocationException {
		
			if(this.limite <= limite && this.limite >= 1){
				if(this.getText(0, this.getLength()).length() + conteudo.length() > this.limite) 
					return;
			}
			
					
		super.insertString(inicio, conteudo.toUpperCase(), a);
	}
}


