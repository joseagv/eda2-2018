package org.eda2.practica2;

public class Poste implements Comparable {

	private int numPoste;
	private Ubicacion ubicacion;
	private boolean pasaCorriente;
	
	public Poste(int numPoste, Ubicacion ubicacion, boolean pasaCorriente) {
		this.numPoste = numPoste;
		this.ubicacion = ubicacion;
		this.pasaCorriente = pasaCorriente;
	}

	public int getNumPoste() {
		return numPoste;
	}

	public void setnPoste(int numPoste) {
		this.numPoste = numPoste;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean close() {
		return pasaCorriente;
	}

	public void setPasaCorriente(boolean pasaCorriente) {
		this.pasaCorriente = pasaCorriente;
	}

	@Override
	public String toString() {
		return numPoste+" "+ubicacion + " " + pasaCorriente;
	}

	@Override
	public int compareTo(Object o) {
		Poste p = (Poste) o;
		if (numPoste < p.numPoste)
			return -1;
		if (numPoste > p.numPoste)
			return +1;
		return 0;
	}
	
	
}
