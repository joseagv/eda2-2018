package org.eda2.practica3;

import java.util.Arrays;
import java.util.TreeMap;

public class TablaPrecio {

	private static final double DESCUENTO = 0.10;
	private double []precio;
	
	public TablaPrecio () {
		precio = new double [6];
		for (int i=0; i<precio.length; i++)
			precio[i] = 0;
	}
	
	public double get (int i) {
		return precio[i];
	}
	
	public void set (int i, double valor) {
		precio[i] = valor;
	}
	
	public void incrementar (int i, double valor) {
		precio[i] = precio[i] + valor;
	}

	public void incrementarTodos(double[] importeEntradas) {
		for (int i=0; i<precio.length; i++) {
			precio[i] = precio[i] + importeEntradas[i];
		}
	}

	public void incrementarTodos(double[] importeSegmentos, 
							double longitud) {
		for (int i=0; i<precio.length; i++) {
			precio[i] = precio[i] + importeSegmentos[i] * longitud;
		}
	}

	//le resta el 10% a las 3 ultimas tarifas que son las de
	//tarjeta abono
	public void reducirAbono() {
		for (int i=3; i<precio.length; i++) {
			precio[i] = precio[i] - precio[i] * DESCUENTO;
		}
	}
	
	public String toString () {
		String cadena = "";
		for (int i=0; i<precio.length; i++) {
			cadena = cadena + "\t\t" + String.format("%.2f", precio[i]);
			if (i < precio.length-1)
				cadena = cadena +" € ";
		}
		return cadena + " €";
	}
}
