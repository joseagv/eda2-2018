package org.eda2.practica2;

/* la clase arista es un tramo del metro entre 2 estaciones 
 * ATENCION: una linea de metro, va en ambos sentidos
 * eso significa que es un grafo no dirigido
 * las aristas estaran repetidas pero con origen y 
 * destino cambiados de orden
 *  
 */

public class Arista {

	public static double tiempoArista = 5;
	private Vertice origen;
	private Vertice destino;
	private double longitud;
	private String nombreLinea; 
	
	public Arista(Vertice origen, Vertice destino, double longitud, String nombreLinea) {
		this.origen = origen;
		this.destino = destino;
		this.longitud = longitud;
		this.nombreLinea = nombreLinea;
	}

	public boolean equals (Object obj) {
		Arista a = (Arista) obj;
		return this.origen.equals(a.origen) &&
					this.destino.equals(a.destino);
	}

	public Vertice getOrigen() {
		return origen;
	}

	public void setOrigen(Vertice origen) {
		this.origen = origen;
	}

	public Vertice getDestino() {
		return destino;
	}

	public void setDestino(Vertice destino) {
		this.destino = destino;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getNombreLinea() {
		return nombreLinea;
	}

	public void setNombreLinea(String nombreLinea) {
		this.nombreLinea = nombreLinea;
	}
	
	
}
