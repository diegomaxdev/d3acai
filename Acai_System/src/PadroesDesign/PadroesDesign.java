package PadroesDesign;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class PadroesDesign 
{
	public static final Font FONTETITULOS = new Font("Segoe UI", Font.BOLD, 14);
	public static final Font FONTELABEL = new Font("Segoe UI", Font.BOLD, 12);
	public static final Font FONTETEXT = new Font("Segoe UI", Font.BOLD, 12);
	public static final Font FONTEBOTOES = new Font("Segoe UI", Font.BOLD, 12);
	public static final Font FONTEBOTOESSTATUS = new Font("Arial", Font.BOLD, 8);
	public static final Font FONTEOBSERVACOES = new Font("Segoe UI", Font.BOLD, 11);
	public static final Font FONTEBOTOESPEQUENOS = new Font("Segoe UI", Font.BOLD, 10);
	public static final Font FONTEINFORMACOES = new Font("Segoe UI", Font.BOLD, 13);
	public static final Font FONTELABELLOGIN = new Font("Segoe UI", Font.BOLD, 16);
	public static final Color CORLABELLOGIN = new Color(213,212,189);
	
	public static final Dimension TM_BOTAO = new Dimension(120, 30);
	public static final Dimension TM_LABEL = new Dimension(60, 24);
	public static final Dimension TM_TEXT_FIELD = new Dimension(240, 24);
		
	public static Color CORTITULOS = new Color(38,38,38);
	public static Color CORLABEL = new Color(38,38,38);
	public static Color CORTEXT = new Color(38,38,38);
	public static Color CORBOTOES = new Color(38,38,38);
	public static Color COROBSERVACOES = new Color(38,38,38);
	public static Color CORTEXTOBSERVACOES = Color.getHSBColor(352,21,96);
	public static Color CORTEXTOBRIGATORIO = Color.getHSBColor(352,21,96);
		
	public static final int ESP_HOR = 10;
	public static final int ESP_VER = 8;
	
	public static Color getCORTITULOS() {return CORTITULOS;}
	public static void setCORTITULOS(Color cORTITULOS) {CORTITULOS = cORTITULOS;}
	public static Color getCORLABEL() {return CORLABEL;}
	public static void setCORLABEL(Color cORLABEL) {CORLABEL = cORLABEL;}
	public static Color getCORTEXT() {return CORTEXT;}
	public static void setCORTEXT(Color cORTEXT) {CORTEXT = cORTEXT;}
	public static Color getCORBOTOES() {return CORBOTOES;}
	public static void setCORBOTOES(Color cORBOTOES) {CORBOTOES = cORBOTOES;}
	public static Color getCOROBSERVACOES() {return COROBSERVACOES;}
	public static void setCOROBSERVACOES(Color cOROBSERVACOES) {COROBSERVACOES = cOROBSERVACOES;}
	public static Color getCORTEXTOBSERVACOES() {return CORTEXTOBSERVACOES;}
	public static void setCORTEXTOBSERVACOES(Color cORTEXTOBSERVACOES) {CORTEXTOBSERVACOES = cORTEXTOBSERVACOES;}
	public static Color getCORTEXTOBRIGATORIO() {return CORTEXTOBRIGATORIO;}
	public static void setCORTEXTOBRIGATORIO(Color cORTEXTOBRIGATORIO) {CORTEXTOBRIGATORIO = cORTEXTOBRIGATORIO;}
	public static Color getCorlabellogin() {return CORLABELLOGIN;}
	
	
	public static final Font FONTEPAINEL = new Font("Segoe UI", Font.BOLD, 40);
	public static final Font FONTENUMEROPAINEL = new Font("Segoe UI", Font.BOLD, 120);
	public static final Font FONTENUMEROPAINELATUAL = new Font("Segoe UI", Font.BOLD, 160);
	public static final Font FONTEPAINELDESCRICAOANTERIOR = new Font("Segoe UI", Font.BOLD, 20);
	public static final Font FONTEPAINELDESCRICAOATUAL = new Font("Segoe UI", Font.BOLD, 30);
}
