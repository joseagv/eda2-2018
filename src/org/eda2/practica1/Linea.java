package org.eda2.practica1;

import java.util.Arrays;

public class Linea {

	private String nombreLinea;
	private int numPostes;
	private double distancia;
	private double longitud;
	private Poste[] postes;

	public Linea(String nombreLinea, int numPostes, double distancia, double longitud, Poste[] postes) {
		super();
		this.nombreLinea = nombreLinea;
		this.numPostes = numPostes;
		this.distancia = distancia;
		this.longitud = longitud;
		this.postes = postes;
	}

	public String getNombreLinea() {
		return nombreLinea;
	}

	public void setNombreLinea(String nombreLinea) {
		this.nombreLinea = nombreLinea;
	}

	public int getNumPostes() {
		return numPostes;
	}

	public void setNumPostes(int numPostes) {
		this.numPostes = numPostes;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public Poste[] getPostes() {
		return postes;
	}

	public void setPostes(Poste[] postes) {
		this.postes = postes;
	}

	@Override
	public String toString() {
		return "Linea [nombreLinea=" + nombreLinea + ", numPostes=" + numPostes + ", distancia=" + distancia
				+ ", longitud=" + longitud + ", postes=" + Arrays.toString(postes) + "]";
	}

	public void close(int numPoste) {

	}

	public int buscarCorteRec() {
		return numPostes;

	}

	public int buscarCorte() {
		return numPostes;

	}

}
