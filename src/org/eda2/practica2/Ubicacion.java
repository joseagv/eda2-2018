package org.eda2.practica2;

public class Ubicacion implements Comparable {

	private int calle;
	private int avenida;
	
	public Ubicacion(int calle, int avenida) {
		this.calle = calle;
		this.avenida = avenida;
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
		return calle + " " + avenida;
	}

	@Override
	public int compareTo(Object o) {
		Ubicacion u = (Ubicacion)o;
		if (this.calle < u.calle)
			return -1;
		if (this.calle > u.calle)
			return +1;
		if (this.avenida < u.avenida)
			return -1;
		if (this.avenida > u.avenida)
			return +1;
		return 0;
	}
	
	
}
