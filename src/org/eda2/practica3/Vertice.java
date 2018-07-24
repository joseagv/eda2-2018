package org.eda2.practica3;


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
		Vertice vertice = (Vertice) obj;
		return this.ubicacion.equals(vertice.ubicacion);
	}
	
	@Override
	public int compareTo(Object obj) {
		Vertice vertice = (Vertice) obj;
		return this.ubicacion.compareTo(vertice.ubicacion);
	}
	
	public String toString () {
		return ubicacion.toString();
	}
}
