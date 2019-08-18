package PrincipalMain;

import javax.swing.UIManager;

import ConexaoBancoDados.ValidacaoSistemaONLINE;
import d3Frame.LoginFrame;

public class d3Main {

	private static String lookAndFeel;

	public static String getLookAndFeel() {
		return lookAndFeel;
	}

	public static void setLookAndFeel(String lookAndFeel) {
		d3Main.lookAndFeel = lookAndFeel;
	}

	public static void main(String[] args) {
		try 
		{
			/* PADRAO - BEGE */d3Main.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
			UIManager.setLookAndFeel(d3Main.getLookAndFeel());

			ValidacaoSistemaONLINE valida = new ValidacaoSistemaONLINE();
			valida.ValidaLicenca();

			if (ValidacaoSistemaONLINE.isSistemaHabilitado() == true) {
				LoginFrame frame = new LoginFrame();
				frame.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
