package org.eda2.practica3;

import java.util.ArrayList;

public class Camino {

	public static final double TIEMPOMANZANA = 4;
	public static final double TIEMPOINTERCAMBIO = 7;
	public static final double TIEMPOESTACION = 5;
	private ArrayList<Vertice> lista;
	private ArrayList<Arista> lista2;
	private double tiempo;
	
	public Camino (ArrayList<Vertice> lista, Grafo grafo) {
		this.lista = lista;
		lista2 = calcularLista (grafo);
		tiempo = calcularTiempo();
	}

	private ArrayList<Arista> calcularLista(Grafo grafo) {
		lista2 = new ArrayList<Arista> ();
		for (int i=1; i<lista.size(); i++) {
			Arista a = grafo.getArista(lista.get(i-1), lista.get(i));
			lista2.add(a);
		}
		return lista2;
	}

	private double calcularTiempo() {
		tiempo = 0;
		for (int i=0; i<lista.size(); i++) {
			Arista a = lista2.get(i);
			tiempo += TIEMPOMANZANA * a.getLongitud();
			if (i < lista.size()-1) {
				if (a.getDestino().isCruce()) 
					tiempo += TIEMPOINTERCAMBIO;
				else
					tiempo += TIEMPOESTACION;
			}
		}
		return tiempo;
	}
	
	public String toString() {
		String cadena = "CAMINO DESDE "+lista.get(0).toString()+
				" HASTA "+lista.get(lista.size()-1).toString()+"\n";
		for (int i=0; i<lista.size(); i++) {
			cadena += lista.get(i).toString();
			if (i < lista.size()-1)
				cadena += " --> ";
		}
		cadena += "\nTiempo total: "+tiempo + " minutos";
		return cadena;
	}
}
