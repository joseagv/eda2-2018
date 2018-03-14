package org.eda2.practica1;

public class Ubicacion {

	private int numCalle;
	private int numAvenida;

	public Ubicacion(int numCalle, int numAvenida) {
		super();
		this.numCalle = numCalle;
		this.numAvenida = numAvenida;
	}

	public int getNumCalle() {
		return numCalle;
		//comentario de prueba
	}

	public void setNumCalle(int numCalle) {
		this.numCalle = numCalle;
	}

	public int getNumAvenida() {
		return numAvenida;
	}

	public void setNumAvenida(int numAvenida) {
		this.numAvenida = numAvenida;
	}

	@Override
	public String toString() {
		return "Ubicacion [numCalle=" + numCalle + ", numAvenida=" + numAvenida + "]";
	}

}
