package org.eda2.practica4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.eda2.practica3.TablasPrecios;

import java.util.TreeMap;

public class Principal4 {

	public static void main(String[] args) throws IOException {
		String directorio = System.getProperty("user.dir")
				+File.separator + "src"+File.separator
				+"datosP4" + File.separator;
		String directorio2 = System.getProperty("user.dir")
				+File.separator + "src"+File.separator
				+"tablaP4" + File.separator;

		String nombreFichero = directorio + "grafo.txt";
		String nombreMaquina = directorio + "maquina4.txt";
		String nombreTabla = directorio2 + "tablaP4.txt";
		long antes, despues, tiempo;

		File f = new File(nombreTabla);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		Recorridos grafo = new Recorridos(nombreFichero, nombreMaquina);

		System.out.println();
		System.out.println("Grafo leido con "+grafo.numeroVertices+" vertices");
		String resultado = "Grafo leido con "+grafo.numeroVertices+" vertices";
		bw.write(resultado + "\n");

		System.out.println("Estaciones Automatizadas: "+grafo.getNumAutomatizadas());
		bw.write("Estaciones Automatizadas: "+grafo.getNumAutomatizadas() + "\n");

		System.out.println("Estaciones No Automatizadas: "+grafo.getNumNoAutomatizadas() + "\n");
		bw.write("Estaciones No Automatizadas: "+grafo.getNumNoAutomatizadas() + "\n");
		bw.write("RUTA ÓPTIMA\n");

		antes = System.nanoTime();
		ArrayList<VerticeMaquinas> sol = grafo.calcularRecorridos();
		for (VerticeMaquinas v: sol) {
			System.out.println(v);
			bw.write(v.toString() + "\n");
		}
		despues = System.nanoTime();
		tiempo = despues - antes;
		System.out.println();
		System.out.println("Tiempo que se tarda en calcular las rutas óptimas:" + tiempo + " nanosegundos");
		System.out.println("Tiempo en segundos:" + tiempo/1000000000 + " segundos");
		bw.close();
	}
}