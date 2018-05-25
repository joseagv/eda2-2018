package org.eda2.practica2;

import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Principal2B {

	public static void main(String[] args) {
		String directorio = System.getProperty("user.dir")
				+File.separator + "src"+File.separator
				+"datosCiudad" + File.separator;
		String nombreFichero = directorio + "copia.txt";
		Grafo grafo = new Grafo (nombreFichero);
		Vertice origen = new Vertice("em", new Ubicacion(1,6), false);
		TreeMap<Vertice, ArrayList<Vertice>> caminos =
						grafo.sacarCaminos(origen);
		System.out.println("Caminos desde "+origen);
		for (Entry<Vertice, ArrayList<Vertice>> e1 : caminos.entrySet()) {
			if (e1.getValue() != null) {
				double tiempo = grafo.calcularTiempo (e1.getValue());
				System.out.println("Destino: "+e1.getKey());
				System.out.print("  -> ");
				for (Vertice v : e1.getValue())
					System.out.print(v+"  ");
				System.out.println(" ==> "+tiempo+" minutos");
			}
		}
	}
}