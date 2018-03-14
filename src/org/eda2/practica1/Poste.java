package org.eda2.practica1;

public class Poste {
	private boolean corriente;
	private int numPoste;

	public Poste(boolean corriente, int numPoste) {
		super();
		this.corriente = corriente;
		this.numPoste = numPoste;
	}

	public boolean isCorriente() {
		return corriente;
	}

	public void setCorriente(boolean corriente) {
		this.corriente = corriente;
	}

	public int getNumPoste() {
		return numPoste;
	}

	public void setNumPoste(int numPoste) {
		this.numPoste = numPoste;
	}

	@Override
	public String toString() {
		return "Poste [corriente=" + corriente + ", numPoste=" + numPoste + "]";
	}

}
