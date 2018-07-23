package org.eda2.practica3;

import java.io.*;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/* esta clase almacena el grafo de las lineas de metro
 * igual que en EDA I voy a hacer un 
 * TreeMap<Vertice, TreeMap<Vertice, Arista>>
 * para poner los vertices como claves de un TreeMap, 
 * necesito que implementen la interface Comparable
 */
public class Grafo {

	public static final double INFINITO = 1e20;
	protected TreeMap<Vertice, TreeMap<Vertice, Arista>> mapa;
	// private double []distancia;
	// private Vertice []previo;
	protected TreeMap<Vertice, Double> distancia;
	protected TreeMap<Vertice, Vertice> previo;
	protected int numeroCalles;
	protected int numeroAvenidas;

	// para la practica 3

	protected ArrayList<Vertice> vertices;
	protected double[][] distancias;
	protected Vertice[][] anteriores;

	public Grafo(String nombreFichero) {
		mapa = new TreeMap<Vertice, TreeMap<Vertice, Arista>>();
		try {
			Scanner entrada = new Scanner(new File(nombreFichero));
			numeroCalles = Integer.parseInt(entrada.nextLine());
			numeroAvenidas = Integer.parseInt(entrada.nextLine());
			// paso em y ce
			entrada.nextLine();
			entrada.nextLine();
			// leo todas las aristas del grafo y las inserto
			while (entrada.hasNextLine()) {
				String linea = entrada.nextLine();
				Scanner sc = new Scanner(linea);
				int c1 = sc.nextInt();
				int a1 = sc.nextInt();
				int c2 = sc.nextInt();
				int a2 = sc.nextInt();
				int lon = sc.nextInt();
				String nom = sc.next();
				Vertice origen = new Vertice("", new Ubicacion(c1, a1), false);
				Vertice destino = new Vertice("", new Ubicacion(c2, a2), false);
				Arista aris = new Arista(origen, destino, lon, nom);
				if (!mapa.containsKey(origen))
					mapa.put(origen, new TreeMap<Vertice, Arista>());
				mapa.get(origen).put(destino, aris);
				Arista aris2 = new Arista(destino, origen, lon, nom);
				if (!mapa.containsKey(destino))
					mapa.put(destino, new TreeMap<Vertice, Arista>());
				mapa.get(destino).put(origen, aris);

			}
			entrada.close();
		} catch (IOException e) {

		}
	}

	public Arista getArista(Vertice origen, Vertice destino) {
		if (!mapa.containsKey(origen))
			return null;
		if (!mapa.get(origen).containsKey(destino))
			return null;
		return mapa.get(origen).get(destino);
	}

	/**********
	 * public void Dijkistra (Vertice origen) { ArrayList<Vertice> vertices = new
	 * ArrayList<Vertice>(mapa.keySet()); TreeSet<Vertice> s = new
	 * TreeSet<Vertice>(); s.add (origen); distancia = new double [mapa.size()];
	 * previo = new Vertice[mapa.size()]; for (int i=0; i<vertices.size(); i++) {
	 * Arista a = getArista (origen, vertices.get(i)); if (a == null) { distancia[i]
	 * = INFINITO; previo[i] = null; } else { distancia[i] = a.getLongitud();
	 * previo[i] = origen; } } TreeSet<Vertice> vmenoss = new
	 * TreeSet<Vertice>(vertices); vmenoss.remove(origen); for (int i=1;
	 * i<=vertices.size()-2; i++) { Vertice w = extraerVertice (vertices, vmenoss,
	 * distancia); s.add(w); vmenoss.remove(w); for (Vertice v : vmenoss) { Arista a
	 * = getArista (w, v); if (a != null) { int posw = vertices.indexOf(w); int posv
	 * = vertices.indexOf(v); if (distancia[posw] + a.getLongitud() <
	 * distancia[posv]) { distancia[posv] = distancia[posw] + a.getLongitud();
	 * previo[posv] = w; } } } } }
	 * 
	 * private Vertice extraerVertice(ArrayList<Vertice> vertices, TreeSet<Vertice>
	 * vmenoss, double[] distancia2) { double min = Double.MAX_VALUE; Vertice
	 * vertmin = null; for (Vertice v : vmenoss) { int pos = vertices.indexOf(v); if
	 * (distancia2[pos] < min) { min = distancia2[pos]; vertmin = v; } } return
	 * vertmin; }
	 *****************************/

	// OTRA FORMA

	private void dijkstra(Vertice origen) {
		TreeSet<Vertice> s = new TreeSet<Vertice>();
		s.add(origen);
		distancia = new TreeMap<Vertice, Double>();
		previo = new TreeMap<Vertice, Vertice>();
		for (Vertice v : mapa.keySet()) {
			Arista a = getArista(origen, v);
			if (a == null) {
				distancia.put(v, INFINITO);
				previo.put(v, null);
			} else {
				distancia.put(v, a.getLongitud());
				previo.put(v, origen);
			}
		}
		TreeSet<Vertice> vmenoss = new TreeSet<Vertice>(mapa.keySet());
		vmenoss.remove(origen);
		while (vmenoss.size() > 0) {
			Vertice w = extraerVertice(vmenoss, distancia);
			System.out.println("Despues de extraer: " + w);
			s.add(w);
			vmenoss.remove(w);
			for (Vertice v : vmenoss) {
				Arista a = getArista(w, v);
				if (a != null) {
					if (distancia.get(w) + a.getLongitud() < distancia.get(v)) {
						distancia.put(v, distancia.get(w) + a.getLongitud());
						System.out.println("Cambiando previo de " + v + " a " + w);
						previo.put(v, w);
					}
				}
			}
		}
	}

	private Vertice extraerVertice(TreeSet<Vertice> vmenoss, TreeMap<Vertice, Double> distancia2) {
		double min = INFINITO * 2;
		Vertice vertmin = null;
		for (Vertice v : vmenoss) {
			Double d = distancia2.get(v);
			System.out.println("v = " + v + " con distancia: " + d);
			if (d < min) {
				min = d;
				vertmin = v;
			}
		}
		return vertmin;
	}

	public TreeMap<Vertice, ArrayList<Vertice>> sacarCaminos(Vertice origen) {
		dijkstra(origen);
		TreeMap<Vertice, ArrayList<Vertice>> salida = new TreeMap<Vertice, ArrayList<Vertice>>();
		for (Vertice v : mapa.keySet()) {
			// System.out.println("mirando: "+v);
			if (v.equals(origen)) {
				// System.out.println("comparando "+v+" con "+origen);
				salida.put(v, null);
			} else {
				ArrayList<Vertice> lista = new ArrayList<Vertice>();
				lista.add(origen);
				// System.out.println("antes: "+lista);
				hacerCamino(origen, v, lista);
				lista.add(v);
				// System.out.println("despues: "+lista);
				salida.put(v, lista);
			}
		}
		return salida;
	}

	private void hacerCamino(Vertice origen, Vertice v, ArrayList<Vertice> lista) {
		System.out.println("camino de " + origen + " a " + v + " pasa por " + previo.get(v));
		if (previo.get(v) == null || previo.get(v).equals(origen)) {
			// lista.add(v);
		} else {
			hacerCamino(origen, previo.get(v), lista);
			lista.add(previo.get(v));
			// hacerCamino (previo.get(v), v, lista);
		}
	}

	public double calcularTiempo(ArrayList<Vertice> lista) {
		double tiempo = 0, t1 = 4, t2 = 7, t3 = 5;
		// estacion inicial: t3
		tiempo = tiempo + t3;
		Vertice anterior = lista.get(0);
		Arista aAnterior = null;
		// para el resto de estaciones
		for (int i = 1; i < lista.size(); i++) {
			Vertice actual = lista.get(i);
			Arista aSiguiente = getArista(anterior, actual);
			tiempo = tiempo + aSiguiente.getLongitud() * t1;
			if (i < lista.size() - 1)
				tiempo = tiempo + t3;
			if (aAnterior != null) {
				if (!aAnterior.getNombreLinea().equals(aSiguiente.getNombreLinea()))
					tiempo = tiempo + t2;
			}
			aAnterior = aSiguiente;
			anterior = actual;
		}
		return tiempo;
	}

	////////////////////////////////////////////
	// practica 3
	////////////////////////////////////////

	public void floyd() {

		inicializarFloyd();
		ejecutarFloyd();

	}

	private void inicializarFloyd() {
		vertices = new ArrayList<Vertice>();
		int i = 0;
		for (Vertice v : mapa.keySet()) {
			vertices.add(v);
			i++;
		}
		distancias = new double[mapa.size()][mapa.size()];
		anteriores = new Vertice[mapa.size()][mapa.size()];
		for (i = 0; i < distancias.length; i++) {
			for (int j = 0; j < distancias.length; j++) {
				if (i == j) {
					distancias[i][j] = 0;
					anteriores[i][j] = null;
				} else {
					Arista a = getArista(vertices.get(i), vertices.get(j));
					if (a == null) {
						distancias[i][j] = INFINITO;
						anteriores[i][j] = null;
					} else {
						distancias[i][j] = a.getLongitud();
						anteriores[i][j] = vertices.get(i);
					}
				}
			}
		}
	}

	private void ejecutarFloyd() {
		for (int k = 0; k < distancias.length; k++) {
			for (int i = 0; i < distancias.length; i++) {
				for (int j = 0; j < distancias.length; j++) {
					if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
						distancias[i][j] = distancias[i][k] + distancias[k][j];
						anteriores[i][j] = vertices.get(k);
					}
				}
			}
		}
	}

	public void hacerCaminoFloyd(int i, int j, ArrayList<Vertice> lista) {
		if (anteriores[i][j] == null || anteriores[i][j].equals(vertices.get(i))) {
			// lista.add(v);
		} else {
			int pos = vertices.indexOf(anteriores[i][j]);
			hacerCaminoFloyd(i, pos, lista);
			lista.add(anteriores[i][j]);
			hacerCaminoFloyd (pos, j, lista);
		}
	}
}
