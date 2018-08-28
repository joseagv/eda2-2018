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
		
		String nombreFichero = directorio + "copia4.txt";
		String nombreMaquina = directorio + "maquina4.txt";
		String nombreTabla = directorio2 + "tablaP4.txt";
		
		File f = new File(nombreTabla);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		Recorridos grafo = new Recorridos (nombreFichero, nombreMaquina);
		
		System.out.println("Grafo leido con "+grafo.numeroVertices+" vertices");
		String resultado = "Grafo leido con "+grafo.numeroVertices+" vertices";
		bw.write(resultado);
		
		System.out.println(" y con "+grafo.getNumAutomatizadas()+
				" estaciones automatizadas y "+
				grafo.getNumNoAutomatizadas()+" estaciones no automatizadas");
		ArrayList<VerticeR> sol = grafo.calcularRecorridos ();
		for (VerticeR v : sol) {
			System.out.println(v);
		}
		resultado = " y con "+grafo.getNumAutomatizadas()+
				" estaciones automatizadas y "+
				grafo.getNumNoAutomatizadas()+" estaciones no automatizadas\n";
		bw.write(resultado);	
				
		ArrayList<VerticeR> solu = grafo.calcularRecorridos ();
		for (VerticeR v : solu) {
			bw.write(v.toString());
		};
		bw.close();
	}


}
