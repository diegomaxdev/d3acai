package d3Frame;
import javax.swing.UIManager;

import ConexaoBancoDados.ValidacaoSistemaONLINE;


public class SistemaINCPP {

	public static void main(String[] args) 
	{
		try 
		{	
			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");

			ValidacaoSistemaONLINE valida = new ValidacaoSistemaONLINE();
			valida.ValidaLicenca();
			
			if(ValidacaoSistemaONLINE.isSistemaHabilitado() == true)
			{
				LoginFrame frame = new LoginFrame();
				frame.setVisible(true);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
