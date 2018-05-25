package org.eda2.practica2;

import java.util.ArrayList;

public class Camino {

	public static final double TIEMPOMANZANA = 5;
	public static final double TIEMPOINTERCAMBIO = 7;
	public static final double TIEMPOESTACION = 5;
	private ArrayList<Vertice> listaVertices;
	private ArrayList<Arista> listaAristas;
	private double tiempo;
	
	public Camino (ArrayList<Vertice> lista, Grafo grafo) {
		this.listaVertices = lista;
		listaAristas = calcularLista (grafo);
		tiempo = calcularTiempo();
	}

	private ArrayList<Arista> calcularLista(Grafo grafo) {
		listaAristas = new ArrayList<Arista> ();
		for (int i=1; i<listaVertices.size(); i++) {
			Arista a = grafo.getArista(listaVertices.get(i-1), listaVertices.get(i));
			listaAristas.add(a);
		}
		return listaAristas;
	}

	private double calcularTiempo() {
		tiempo = 0;
		for (int i=0; i<listaVertices.size(); i++) {
			Arista a = listaAristas.get(i);
			tiempo += TIEMPOMANZANA * a.getLongitud();
			if (i < listaVertices.size()-1) {
				if (a.getDestino().isCruce()) 
					tiempo += TIEMPOINTERCAMBIO;
				else
					tiempo += TIEMPOESTACION;
			}
		}
		return tiempo;
	}
	
	public String toString() {
		String cadena = "CAMINO DESDE "+listaVertices.get(0).toString()+
				" HASTA "+listaVertices.get(listaVertices.size()-1).toString()+"\n";
		for (int i=0; i<listaVertices.size(); i++) {
			cadena += listaVertices.get(i).toString();
			if (i < listaVertices.size()-1)
				cadena += " --> ";
		}
		cadena += "\nTiempo total: "+tiempo + " minutos";
		return cadena;
	}
}
