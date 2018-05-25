package org.eda2.practica2;

/* la clase vertice representa una estacion del metro */

public class Vertice implements Comparable {

	private String nombre;
	private Ubicacion ubicacion;
	private boolean cruce;
	
	public Vertice(String nombre, Ubicacion ubicacion, boolean cruce) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.cruce = cruce;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean isCruce() {
		return cruce;
	}

	public void setCruce(boolean cruce) {
		this.cruce = cruce;
	}

	public boolean equals (Object obj) {
		Vertice v = (Vertice) obj;
		return this.ubicacion.equals(v.ubicacion);
	}
	
	@Override
	public int compareTo(Object obj) {
		Vertice v = (Vertice) obj;
		return this.ubicacion.compareTo(v.ubicacion);
	}
	
	public String toString () {
		return ubicacion.toString();
	}
}
