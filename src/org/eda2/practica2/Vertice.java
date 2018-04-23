package org.eda2.practica2;

/* la clase vertice representa una estacion del metro */

public class Vertice implements Comparable {

	private String nombre;
	private Ubicacion ubicacion;
	
	
	public boolean equals (Object obj) {
		Vertice v = (Vertice) obj;
		return this.nombre.equals(v.nombre);
	}
	
	@Override
	public int compareTo(Object obj) {
		Vertice v = (Vertice) obj;
		return this.ubicacion.compareTo(v.ubicacion);
	}
	
	
}
