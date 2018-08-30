package org.eda2.practica5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;

public class TablasPrecios extends Grafo {

	private static final double[] IMPORTEENTRADAS = { 0.10, 0.08, 0.05, 0.10, 0.08, 0.05 };
	private static final double[] IMPORTESEGMENTOS = { 0.12, 0.09, 0.06, 0.12, 0.09, 0.06 };

	private TreeMap<Vertice, TreeMap<Vertice, TablaPrecio>> listaPrecios;
	private String nombres = "\tTSH Punta\tTSH Plana\tTSH Valle\tTAH Punta\tTAH Plana\tTAH Valle";
	private String leyenda = "Tarjeta simple en hora punta:\t TS. Hora Punta\n"
			+ "Tarjeta simple en hora plana:\t TS. Hora Plana\n"
			+ "Tarjeta simple en hora valle:\t TS. Hora Valle\n"
			+ "Tarjeta abono en hora simple:\t TA. Hora Simple\n"
			+ "Tarjeta abono en hora plana:\t TA. Hora Plana\n"
			+ "Tarjeta abono en hora valle:\t TA. Hora Valle\n";

	public TablasPrecios(String nombreFichero) {
		super(nombreFichero);
		floyd();
		listaPrecios = new TreeMap<Vertice, TreeMap<Vertice, TablaPrecio>>();
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {
				// calculo el camino desde el vertice i al vertice j
				if (i != j) {
					ArrayList<Vertice> lista = new ArrayList<Vertice>();
					lista.add(vertices.get(i));
					hacerCaminoFloyd(i, j, lista);
					lista.add(vertices.get(j));
					// System.out.println(lista);
					TablaPrecio tabla = calcularPrecio(lista);
					tabla.reducirAbono();
					if (!listaPrecios.containsKey(vertices.get(i)))
						listaPrecios.put(vertices.get(i), new TreeMap<Vertice, TablaPrecio>());
					listaPrecios.get(vertices.get(i)).put(vertices.get(j), tabla);
				}
			}
		}
	}

	private TablaPrecio calcularPrecio(ArrayList<Vertice> lista) {
		TablaPrecio tabla = new TablaPrecio();
		tabla.incrementarTodos(IMPORTEENTRADAS);
		Vertice anterior = lista.get(0);
		for (int i = 1; i < lista.size(); i++) {
			Vertice actual = lista.get(i);
			// System.out.println("Antes: " + anterior + " " + actual);
			Arista arista = getArista(anterior, actual);
			// System.out.println(arista);
			tabla.incrementarTodos(IMPORTESEGMENTOS, arista.getLongitud());
			anterior = actual;
		}
		return tabla;
	}

	public String escribirTablas() {
		String resultado = "";
		System.out.printf	("%70s", "TABLA PRECIOS");
		System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------");
		//System.out.println("Importe en €");
		System.out.printf("%68s", "LEYENDA\n");
		System.out.printf("%1s", leyenda);
		System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%1s %88s", "Recorrido" ,nombres);
		System.out.println("");
		for (Entry<Vertice, TreeMap<Vertice, TablaPrecio>> e1 : listaPrecios.entrySet()) {
			for (Entry<Vertice, TablaPrecio> e2 : e1.getValue().entrySet()) {
				System.out.println(e1.getKey() + " -> " + e2.getKey() + "  \tImportes: " + "" + e2.getValue());
				resultado = resultado +  e1.getKey() + " -> " + e2.getKey() + "  \tImportes: " + "" + e2.getValue() +"\n";
			}
		}
		return resultado;
		
	}
}
