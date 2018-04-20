package org.eda2.practica1;

import java.io.*;
import java.util.Locale;

public class GenerarLineas {

	public static void main(String[] args) {
		generarAleatorio();
		generarMejorCaso();
		generarPeorCasoConSolucion();
		generarPeorCasoSinSolucion();
	}
	
	private static void generarAleatorio() {
		String directorio = System.getProperty("user.dir")+
				File.separator+"src"+File.separator+
				"datosP1"+File.separator;
		for (int i=1; i<=20; i++) {
			String nombreLinea = "Linea "+i;
			String nombreFichero = directorio +nombreLinea+".txt";
			double distanciaPostes = Math.random()*75 + 25;
			//Postes Mínimos (2000) y Máximos de 10000
			int numeroPostes = (int) (Math.random() *  8000 + 2000);
			int posicionCorte = (int) (Math.random()*numeroPostes + 1);
			char []corriente = new char [numeroPostes];
			for (int j=0; j<corriente.length; j++) {
				if (j < posicionCorte)
					corriente[j] = '1';
				else
					corriente[j] = '0';
			}
			guardarEnFichero (nombreFichero, nombreLinea, 
					distanciaPostes, corriente);
		}
	}
	
	private static void generarMejorCaso() {
		String directorio = System.getProperty("user.dir")+
				File.separator+"src"+File.separator+
				"datosP1"+File.separator;
		String nombreLinea = "MejorLinea";
		String nombreFichero = directorio +nombreLinea+".txt";
		double distanciaPostes = Math.random()*75 + 25;
		//Postes Mínimos (2000) y Máximos de 10000
		int numeroPostes = 2000;
		char []corriente = new char [numeroPostes];
		for (int j=0; j<corriente.length; j++) {
			if (j <= corriente.length/2)
				corriente[j] = '1';
			else
				corriente[j] = '0';
			}
		guardarEnFichero (nombreFichero, nombreLinea,distanciaPostes, corriente);
	}
	
	private static void generarPeorCasoConSolucion() {
		String directorio = System.getProperty("user.dir")+
				File.separator+"src"+File.separator+
				"datosP1"+File.separator;
		for (int i=1; i<=20; i++) {
			String nombreLinea = "PeorLineaConSolucion " + i;
			String nombreFichero = directorio +nombreLinea+".txt";
			double distanciaPostes = Math.random()*75 + 25;
			//Postes Mínimos (2000) y Máximos de 10000
			int numeroPostes = (int) (Math.random() *  8000 + 2000);
			char []corriente = new char [numeroPostes];
			for (int j=0; j<corriente.length - 1; j++) {
				corriente[j] = '1';
			}
			corriente[numeroPostes - 1] = '0';
		
			guardarEnFichero (nombreFichero, nombreLinea,distanciaPostes, corriente);
		}
	}
	
	private static void generarPeorCasoSinSolucion() {
		String directorio = System.getProperty("user.dir")+
				File.separator+"src"+File.separator+
				"datosP1"+File.separator;	
			String nombreLinea = "PeorLineaSinSolucion";
			String nombreFichero = directorio +nombreLinea+".txt";
			double distanciaPostes = Math.random()*75 + 25;
			//Postes Mínimos (2000) y Máximos de 10000
			int numeroPostes = (int) (Math.random() *  8000 + 2000);
			char []corriente = new char [numeroPostes];
			for (int j=0; j<corriente.length; j++) {
				corriente[j] = '1';
			}
			guardarEnFichero (nombreFichero, nombreLinea,distanciaPostes, corriente);
	}

	private static void guardarEnFichero(String nombreFichero, 
					String nombreLinea, double distanciaPostes,
					char[] corriente) {
		try {
			BufferedWriter f = new BufferedWriter (new 
					FileWriter (new File (nombreFichero)));
			f.write(nombreLinea+"\n");
			f.write(String.format(Locale.US, "%.2f", distanciaPostes)+"\n");
			f.write(corriente.length+"\n");
			for (int i=0; i<corriente.length; i++) {
				f.write(i+" "+corriente[i]+"\n");
			}
			System.out.println("Fichero " + nombreFichero + " creado con éxito");
			f.close();
		}
		catch (IOException e) {
			System.out.println("Error en la creacion del fichero: "+
						nombreFichero);
		}
	}
}