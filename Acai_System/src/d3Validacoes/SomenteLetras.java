package d3Validacoes;

	import javax.swing.text.AttributeSet;
	import javax.swing.text.BadLocationException;
	import javax.swing.text.PlainDocument;

public class SomenteLetras extends PlainDocument{


	private int limite = -1;
	public SomenteLetras() {}
	
	public SomenteLetras(int limite){
		this.limite = limite;
		
	}
	

			@Override
			public void insertString(int inicio, String conteudo, AttributeSet a)
					throws BadLocationException {
		
					
			for(char c: conteudo.toCharArray()){
					//Verificando se o conteúdo inserido possui caractere invalidos
				if(c == ' ' || Character.isAlphabetic(c))
				{
					
				}
				else
				{
					return;
				}	
			}
			if(this.limite >= 0){
				if(this.getText(0, this.getLength()).length() + conteudo.length() > this.limite) return;
			}
		//TRANFORMA EM MAIUSCULAS
		super.insertString(inicio, conteudo.toUpperCase(), a);
	}
}

