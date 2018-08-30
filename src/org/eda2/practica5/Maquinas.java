package org.eda2.practica5;

public class Maquinas {

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

	public static double PROBNA = 0.25;

	public static double TIEMPOMAQUINA = 2;
}
