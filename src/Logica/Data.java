package Logica;

import java.awt.Color;
import java.awt.Font;

public abstract class Data {

	public static Color FONDO_TABLERO = Color.BLACK;
	public static Color COLOR_PALETA = Color.WHITE;
	public static Color COLOR_BOLA = Color.WHITE;
	
	public static int TABLERO_ANCHO = 600;
	public static int TABLERO_ALTO = 400;
	public static int PALETA_ALTO = 70;
	public static int PALETA_ANCHO = 10;
	public static double ACELERACION_BOLA = 0.3;
	
	public static int DIAMETRO_BOLA = 15;
	
	public static int MOVIMIENTO_PALA = 3;
	
	public static Font FUENTE_SCORE = new Font("Arial", Font.BOLD, 34);
	
	public static enum MOVIMIENTO {UP, DOWN, NONE};
	
}
