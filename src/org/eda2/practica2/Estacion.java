package org.eda2.practica2;

public class Estacion implements Comparable {

	private int calle;
	private int avenida;
	
	public Estacion(int calle, int avenida) {
		this.calle = calle;
		this.avenida = avenida;
	}
	
	public Estacion(Ubicacion ubi) {
		this.calle = ubi.getCalle();
		this.avenida = ubi.getAvenida();
	}

	public int getCalle() {
		return calle;
	}

	public void setCalle(int calle) {
		this.calle = calle;
	}

	public int getAvenida() {
		return avenida;
	}

	public void setAvenida(int avenida) {
		this.avenida = avenida;
	}

	@Override
	public String toString() {
		return "Estacion: " + calle + " " + avenida;
	}

	@Override
	public int compareTo(Object o) {
		Estacion e = (Estacion)o;
		if (this.calle < e.calle)
			return -1;
		if (this.calle > e.calle)
			return +1;
		if (this.avenida < e.avenida)
			return -1;
		if (this.avenida > e.avenida)
			return +1;
		return 0;
	}	
}