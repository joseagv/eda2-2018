package org.eda2.practica4;


import org.eda2.practica4.Maquinas.TipoEstacion;

/* la clase vertice representa una estacion del metro */

public class Vertice implements Comparable {

	protected String nombre;
	protected Ubicacion ubicacion;
	protected TipoEstacion tipo;
	
	public Vertice(Ubicacion ubicacion) {
		this.nombre = ubicacion.toString();
		this.ubicacion = ubicacion;
		tipo = TipoEstacion.Nn;
	}
	
	public Vertice(Ubicacion ubicacion, String tipoEstacion) {
		this.nombre = ubicacion.toString();
		this.ubicacion = ubicacion;
		switch (tipoEstacion) {
		case "ne": tipo = TipoEstacion.Ne; break;
		case "ni": tipo = TipoEstacion.Ni; break;
		case "nc": tipo = TipoEstacion.Nc; break;
		case "nn": tipo = TipoEstacion.Nn; break;
		default: tipo = TipoEstacion.Nn; break;
		}
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

	public TipoEstacion getTipo() {
		return tipo;
	}

	public void setTipo(TipoEstacion tipo) {
		this.tipo = tipo;
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
