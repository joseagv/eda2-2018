package org.eda2.practica1;

import java.util.Scanner;

public class Poste {

	private int numeroPoste;
	private boolean pasaCorriente;
	
	public Poste(int numeroPoste, boolean pasaCorriente) {
		this.numeroPoste = numeroPoste;
		this.pasaCorriente = pasaCorriente;
	}

	public Poste(String cadena) {
		String []trozos = cadena.split(" ");
		numeroPoste = Integer.parseInt(trozos[0]);
		pasaCorriente = (trozos[1].equals ("1"));
	}

	public int getNumeroPoste() {
		return numeroPoste;
	}

	public void setNumeroPoste(int numeroPoste) {
		this.numeroPoste = numeroPoste;
	}

	//public boolean isPasaCorriente() {
	public boolean close() {
		return pasaCorriente;
	}

	public void setPasaCorriente(boolean pasaCorriente) {
		this.pasaCorriente = pasaCorriente;
	}

	@Override
	public String toString() {
		return "Poste [numeroPoste=" + numeroPoste + ", pasaCorriente=" + pasaCorriente + "]";
	}
	
	
}
