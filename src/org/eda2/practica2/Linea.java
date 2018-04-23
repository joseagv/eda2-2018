package org.eda2.practica2;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/* supongo que la fuente de alimentacion esta al PRINCIPIO 
 * de la linea
 */
public class Linea {

	private String nombreLinea;
	private ArrayList<Poste> postes;
	
	public Linea(String nombreLinea, ArrayList<Poste> postes) {
		super();
		this.nombreLinea = nombreLinea;
		this.postes = postes;
	}

	/*public Linea (String nombreLinea, double longitud, double distancia) {
		this.nombreLinea = nombreLinea;
		this.longitud = longitud;
		this.distancia = distancia;
		numeroPostes = (int) (longitud / distancia) + 1;
		postes = new Poste [numeroPostes];
	}*/
	
	public Linea (String nombreFichero) {
		try {
			File file = new File (nombreFichero);
			nombreLinea = file.getName();
			nombreLinea = nombreLinea.replace(".txt", "");
			Scanner f = new Scanner (file);
			String cadena = f.nextLine();
			int numeroPostes = Integer.parseInt(cadena);
			postes = new ArrayList<Poste> ();
			for (int i=0; i<numeroPostes; i++) {
				cadena = f.nextLine();
				String[] trozos = cadena.split(" ");
				int calle = Integer.parseInt(trozos[1]);
				int avenida = Integer.parseInt(trozos[2]);
				Ubicacion u = new Ubicacion (calle, avenida);
				int nPoste = Integer.parseInt(trozos[0]);
				boolean corriente = Boolean.parseBoolean(trozos[3]);
				Poste p = new Poste(nPoste, u, corriente);
				postes.add(p);
			}
			f.close ();
		}
		catch (IOException e) {
			System.out.println("Error de lectura del fichero: "+nombreFichero);
		}
	}
	
	public boolean close (int i) {
		return postes.get(i).close();
	}
	
	public Poste encontrarErrorDyV () {
		return encontrarErrorDyV (0, postes.size()-1);
	}

	private Poste encontrarErrorDyV(int ini, int fin) {
		if (ini >= fin) {
			//if (postes[ini].close())
			if (close(ini) && ini != postes.size()-1) 
				return postes.get(ini+1);
			return postes.get(ini);
		}
		int med = (ini + fin) / 2;
		//if (postes[med].close()) {
		if (close(med)) {
			return encontrarErrorDyV (med + 1, fin);
		}
		return encontrarErrorDyV(ini, med-1);
	}
	
	public Poste encontrarErrorVZ () {
		int posicion = 0;
		Poste solucion = null;
		while (solucion == null) {
			if (posicion >= postes.size())
				return null;
			Poste x = postes.get(posicion);
			posicion++;
			if ( ! x.close())
				solucion = x;
		}
		return solucion;
	}

	public String getNombreLinea() {
		return nombreLinea;
	}

	public int getNumeroPostes() {
		return postes.size();
	}

	public ArrayList<Poste> getPostes() {
		return postes;
	}
	
	public void generarFallo () {
		int n = (int) (Math.random()*postes.size());
		for (int i=n; i<postes.size(); i++) {
			postes.get(i).setPasaCorriente(false);
		}
	}
}
