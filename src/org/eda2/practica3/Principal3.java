package org.eda2.practica3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Principal3 {

	public static void main(String[] args) throws IOException {

		String directorio = System.getProperty("user.dir")
				+File.separator + "src"+File.separator
				+"datosCiudad" + File.separator;
		String directorio2 = System.getProperty("user.dir")
				+File.separator + "src"+File.separator
				+"tablaP3" + File.separator;
		String nombreFichero = directorio + "ciudad2B.txt";
		String nombreTabla = directorio2 + "tablaPrecios.txt";
		long antes, despues, tiempo;

		File f = new File(nombreTabla);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		TablasPrecios grafo = new TablasPrecios (nombreFichero);
		//grafo.escribirTablas();
		antes = System.nanoTime();
		String resultado = grafo.escribirTablas();
		despues = System.nanoTime();
		tiempo = despues - antes;
		System.out.println();
		System.out.println("Tiempo que se tarda en calcular las tablas:" + tiempo);
		bw.write(resultado);
		bw.close();
		}
}
