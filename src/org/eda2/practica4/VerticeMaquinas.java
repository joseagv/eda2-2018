package org.eda2.practica4;

public class VerticeMaquinas extends Vertice {

	private int correctas;
	private int incorrectas;
	private double tiempo;
	private int numeroEstacion;

	public VerticeMaquinas(Ubicacion ubicacion) {
		super(ubicacion);
		correctas = 0;
		incorrectas = 0;
		tiempo = 0;
		numeroEstacion = 1;
	}

	public VerticeMaquinas (Ubicacion ubicacion, int correctas,
				int incorrectas, int numero) {
		super(ubicacion);
		this.correctas = correctas;
		this.incorrectas = incorrectas;
		this.tiempo = Maquinas.TIEMPOMAQUINA * incorrectas;
		this.numeroEstacion = numero;
	}

	public int getCorrectas() {
		return correctas;
	}

	public void setCorrectas(int correctas) {
		this.correctas = correctas;
	}

	public int getIncorrectas() {
		return incorrectas;
	}

	public void setIncorrectas(int incorrectas) {
		this.incorrectas = incorrectas;
	}

	public int getNumero () {
		return numeroEstacion;
	}

	public double getTiempo() {
		return tiempo;
	}

	public boolean equals (Object obj) {
		/*Vertice v = (Vertice) obj;
		return this.ubicacion.equals(v.ubicacion);*/
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString()+" -> Estación: "+numeroEstacion;
	}
}