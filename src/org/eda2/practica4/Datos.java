package org.eda2.practica4;

public class Datos {

	public static final double INFINITO = 1e20;
	
	public static enum TipoEstacion {Ne, Ni, Nc, Nn};
	
	public static int getMaquinas (TipoEstacion e) {
		switch (e) {
		case Ne: return 30;
		case Ni: return 20;
		case Nc: return 12;
		case Nn: return 10;
		default: return 0;
		}
	}
	
	public static double PORCNOAUTO = 0.25;
	
	public static double TIEMPO1MAQUINA = 2;
}
