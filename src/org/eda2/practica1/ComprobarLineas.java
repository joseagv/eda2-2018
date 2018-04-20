package org.eda2.practica1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import org.eda2.practica1.Linea;
import org.eda2.practica1.Poste;

public class ComprobarLineas {

	public static final int NVECES = 10;
	
	public static void main(String[] args) {
		long antes, despues, tiempo;
		Poste poste=null;
		double suma;
		Linea linea=null;
		
		//Líneas alatorias
		String directorio = System.getProperty("user.dir")+
				File.separator+"src"+File.separator+
				"datosP1"+File.separator;
		String directorio2 = System.getProperty("user.dir")+
				File.separator+"src"+File.separator+
				"tiemposP1"+File.separator;

		for (int i=1; i<=20; i++) {
			String nombreFichero = directorio +"Linea "+i+".txt";
			String nombreTiempo = directorio2 + "Tiempo " + i + ".txt";
			suma = 0;
			for (int j=0; j<NVECES; j++) {
				linea = new Linea (nombreFichero);
				antes = System.nanoTime();
				poste = linea.encontrarError();
				despues = System.nanoTime();
				tiempo = despues - antes;
				suma = suma + tiempo;
			}
			tiempo = (long) (suma / NVECES);
			System.out.printf("%-16s  %10d  %10d  %15d\n", 
					linea.getNombreLinea(), linea.getNumeroPostes(), 
					poste.getNumeroPoste(), tiempo);
			guardarTiempo(nombreTiempo, linea.getNombreLinea(),
					linea.getNumeroPostes(), poste.getNumeroPoste(), tiempo);
		}
			//Mejor Línea
			String mejorLinea;
			mejorLinea = directorio +"MejorLinea.txt";
			suma = 0;
			for (int j=0; j<NVECES; j++) {
				linea = new Linea (mejorLinea);
				antes = System.nanoTime();
				poste = linea.encontrarError();
				despues = System.nanoTime();
				tiempo = despues - antes;
				suma = suma + tiempo;
			}
			tiempo = (long) (suma / NVECES);
			System.out.printf("%-16s  %10d  %10d  %15d\n", 
					linea.getNombreLinea(), linea.getNumeroPostes(), 
					poste.getNumeroPoste(), tiempo);
			
			String nombreTiempo = directorio2 + "MejorTiempo.txt";
			guardarTiempo(nombreTiempo, linea.getNombreLinea(),
					linea.getNumeroPostes(), poste.getNumeroPoste(), tiempo);
	
			//Peor Línea Con Solucion
			for (int i=1; i<=20; i++) {
				String peorLineaSolucion;
				peorLineaSolucion = directorio +"PeorLineaConSolucion " + i +".txt";
				suma = 0;
				for (int j=0; j<NVECES; j++) {
					linea = new Linea (peorLineaSolucion);
					antes = System.nanoTime();
					poste = linea.encontrarError();
					despues = System.nanoTime();
					tiempo = despues - antes;
					suma = suma + tiempo;
				}
				tiempo = (long) (suma / NVECES);
				System.out.printf("%-16s  %10d  %10d  %15d\n", 
					linea.getNombreLinea(), linea.getNumeroPostes(), 
					poste.getNumeroPoste(), tiempo);
			
				String nombreTiempo2 = directorio2 + "PeorTiempoConSolucion" + i + ".txt";
				guardarTiempo(nombreTiempo2, linea.getNombreLinea(),
					linea.getNumeroPostes(), poste.getNumeroPoste(), tiempo);
			}
			String nombreTiempo2 = directorio2 + "PeorTiempoConSolucion.txt";
			
			//Peor Línea Sin Solucion
			try {
				String peorLinea;
				peorLinea = directorio +"PeorLineaSinSolucion.txt";
				suma = 0;
				for (int j=0; j<NVECES; j++) {
					linea = new Linea (peorLinea);
					antes = System.nanoTime();
					poste = linea.encontrarError();
					despues = System.nanoTime();
					tiempo = despues - antes;
					suma = suma + tiempo;
				}
				tiempo = (long) (suma / NVECES);
				System.out.printf("%-16s  %10d  %10d  %15d\n", 
						linea.getNombreLinea(), linea.getNumeroPostes(), 
						poste.getNumeroPoste(), tiempo);
				
				String nombreTiempo3 = directorio2 + "PeorTiempoSinSolucion.txt";
				guardarTiempo(nombreTiempo2, linea.getNombreLinea(),
						linea.getNumeroPostes(), poste.getNumeroPoste(), tiempo);
			}	
			catch(Exception e) {
				System.out.println("PeorLineaSinSolucion\tNo se ha encontrado error");
			}	
	}
	
	private static void guardarTiempo(String nombreFichero, 
			String nombreLinea, int numeroPostes, int posteError, double tiempo) {
		try {
			BufferedWriter f = new BufferedWriter (new 
			FileWriter (new File (nombreFichero)));
			f.write(nombreLinea+"\n");
			f.write(numeroPostes+"\n");
			f.write(posteError+"\n");
			f.write(tiempo+"\n");
			f.close();
		}
		catch (IOException e) {
			System.out.println("Error en la creacion del fichero: "+nombreFichero);
		}
	}
}