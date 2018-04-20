package org.eda2.practica1;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/* supongo que la fuente de alimentacion esta al PRINCIPIO 
 * de la linea
 */
public class Linea {

	private String nombreLinea;
	private double distancia;
	private int numeroPostes;
	private double longitud;
	private ArrayList<Poste> postes;
	
	/*public Linea (String nombreLinea, double longitud, double distancia) {
		this.nombreLinea = nombreLinea;
		this.longitud = longitud;
		this.distancia = distancia;
		numeroPostes = (int) (longitud / distancia) + 1;
		postes = new Poste [numeroPostes];
	}*/
	
	public Linea (String nombreFichero) {
		try {
			Scanner f = new Scanner (new File (nombreFichero));
			nombreLinea = f.nextLine();
			String cadena = f.nextLine();
			distancia = Double.parseDouble(cadena);
			cadena = f.nextLine();
			numeroPostes = Integer.parseInt(cadena);
			longitud = distancia*numeroPostes;
			postes = new ArrayList<Poste>();
			for (int i=0; i<numeroPostes; i++) {
				cadena = f.nextLine();
				postes.add(new Poste (cadena));
			}
			f.close ();
		}
		catch (IOException e) {
			System.out.println("Error de lectura del fichero: "+nombreFichero);
		}
	}
	
	public Poste encontrarError () {
		return encontrarError (0, numeroPostes-1);
	}

	public boolean close (int i) {
		return postes.get(i).close();
	}
	
	private Poste encontrarError(int ini, int fin) {
		if (ini >= fin) {
			//if (postes[ini].close())
			if (close(ini))
				return postes.get(ini+1);
			return postes.get(ini);
		}
		int med = (ini + fin) / 2;
		//if (postes[med].close()) {
		if (close(med)) {
			return encontrarError (med + 1, fin);
		}
		return encontrarError(ini, med-1);
	}

	public String getNombreLinea() {
		// TODO Auto-generated method stub
		return nombreLinea;
	}

	public int getNumeroPostes() {
		// TODO Auto-generated method stub
		return numeroPostes;
	}
}
