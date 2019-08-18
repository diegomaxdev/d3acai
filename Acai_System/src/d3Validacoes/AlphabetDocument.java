package d3Validacoes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class AlphabetDocument extends PlainDocument {
	
	private int limite = -1;
	public AlphabetDocument() {}
	
	public AlphabetDocument(int limite){
		this.limite = limite;
		
	}
	
	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		
		for(char c: str.toCharArray()){
			if(! Character.isLetter(c) && c != ' '){
				return;
			}
		}
		
		if(this.limite >= 0){
			if(this.getText(0, this.getLength()).length() + str.length() > this.limite) return;
		}
		
		super.insertString(offs, str, a);
	}

}
