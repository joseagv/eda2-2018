package org.eda2.practica2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ComprobarRecorrido {

	public static void main(String[] args) throws IOException {
		String directorio = System.getProperty("user.dir")
				+File.separator + "src"+File.separator
				+"datosCiudad" + File.separator;
		String directorio2 = System.getProperty("user.dir")+
				File.separator+"src"+File.separator+
				"tiemposP2"+File.separator;
		String nombreFichero = directorio + "ciudad2B.txt";
		File file = new File(directorio2 + "tablaRecorrido.txt");
		BufferedWriter f = new BufferedWriter (new FileWriter (file));
		Grafo grafo = new Grafo (nombreFichero);
		Vertice origen = new Vertice("em", new Ubicacion(1,6), false);
		TreeMap<Vertice, ArrayList<Vertice>> caminos =
						grafo.obtenerCaminos(origen);
		System.out.println("Caminos desde "+origen);
		String c5 = "Caminos desde "+origen;
		f.write(c5 + "\n");
		for (Entry<Vertice, ArrayList<Vertice>> e1 : caminos.entrySet()) {
			if (e1.getValue() != null) {
				double tiempo = grafo.calcularTiempo (e1.getValue());
				System.out.println("Destino: "+e1.getKey());
				String c1 = "Destino: "+e1.getKey();
				f.write(c1 + "\n");
				System.out.print("  -> ");
				String c2 = "  -> ";
				f.write(c2);
				for (Vertice v : e1.getValue()) {
					System.out.print(v+"  ");
					String c3 = v+"  ";
					f.write(c3);
				}
				System.out.println(" ==> "+tiempo+" minutos");
				String c4 = " ==> "+tiempo+" minutos";
				f.write(c4 + "\n");				
			}
		}
		f.close();
	}
}
