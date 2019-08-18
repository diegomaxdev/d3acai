package d3Validacoes;

	import javax.swing.text.AttributeSet;
	import javax.swing.text.BadLocationException;
	import javax.swing.text.PlainDocument;

public class textEmail extends PlainDocument{


	private int limite = -1;
	public textEmail() {}
	
	public textEmail(int limite){
		this.limite = limite;
		
	}
			@Override
			public void insertString(int inicio, String conteudo, AttributeSet a)
					throws BadLocationException {
		
		
			if(this.limite >= 0){
				if(this.getText(0, this.getLength()).length() + conteudo.length() > this.limite) return;
			}
		//TRANFORMA EM MAIUSCULAS
		super.insertString(inicio, conteudo.toLowerCase(), a);
	}
}

