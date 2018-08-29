package org.eda2.practica4;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class GenerarMaquinas {

	public static void main(String[] args) {
		String directorio = System.getProperty("user.dir")+
				File.separator+"src"+File.separator+
				"datosB"+File.separator;
		String nombreArchivo = directorio + "grafo.txt";
		Grafo grafo = new Grafo (nombreArchivo);
		String nombreMaquinas = directorio + "maquina4.txt";
		generarMaquinas (grafo, nombreMaquinas);
	}

	private static void generarMaquinas (Grafo grafo,
					String nombreMaquinas) {
		int nA, nB;

		try {
			PrintWriter pw = new PrintWriter (new FileWriter
							(new File(nombreMaquinas)));
			ArrayList<Vertice> vertices = grafo.getVertices();
			for (Vertice v : vertices) {
				int numMaquinas = Maquinas.getMaquinas(v.getTipo());
				if (Math.random() < Maquinas.PROBNA) {
					nA = 0;
					nB = numMaquinas;
				}
				else {
					nA = (int) (Math.random() * numMaquinas + 1);
					nB = numMaquinas - nA;
				}
				pw.println(v.getUbicacion().getCalle()+" "+
						v.getUbicacion().getAvenida()+" "+nA+" "+nB);
			}
			pw.close();
		}
		catch (IOException e) {

		}
	}
}