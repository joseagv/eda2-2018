package org.eda2.practica2;

import java.io.*;
import java.util.ArrayList;
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

	public static final double MAX = Double.MAX_VALUE;
	private TreeMap<Vertice, TreeMap<Vertice, Arista>> grafo;
	private TreeMap<Vertice, Double> distancia;
	private TreeMap<Vertice, Vertice> previo;
	private int numeroCalles;
	private int numeroAvenidas;
	
	public Grafo (String nombreFichero) {
		grafo = new TreeMap<Vertice, TreeMap<Vertice, Arista>> ();
		try {
			Scanner entrada = new Scanner (new File (nombreFichero));
			numeroCalles = Integer.parseInt(entrada.nextLine());
			numeroAvenidas = Integer.parseInt(entrada.nextLine());
			//paso em y ce
			entrada.nextLine();
			entrada.nextLine();
			//leo todas las aristas del grafo y las inserto
			while (entrada.hasNextLine()) {
				String linea = entrada.nextLine();
				Scanner sc = new Scanner (linea);
				int calle1 = sc.nextInt();
				int avenida1 = sc.nextInt();
				int calle2 = sc.nextInt();
				int avenida2 = sc.nextInt();
				int longitud = sc.nextInt();
				String nom = sc.next();
				Vertice origen = new Vertice ("", new Ubicacion(calle1,avenida1), false);
				Vertice destino = new Vertice ("", new Ubicacion(calle2,avenida2), false);
				Arista aris = new Arista (origen, destino, longitud, nom);
				if ( ! grafo.containsKey(origen))
					grafo.put(origen, new TreeMap<Vertice, Arista>());
				grafo.get(origen).put(destino, aris);
				Arista aris2 = new Arista (destino, origen, longitud, nom);
				if ( ! grafo.containsKey(destino))
					grafo.put(destino, new TreeMap<Vertice, Arista>());
				grafo.get(destino).put(origen, aris);
				
			}
			entrada.close();
		}
		catch (IOException e) {
			
		}
	}
	
	public Arista getArista (Vertice origen, Vertice destino) {
		if ( ! grafo.containsKey(origen))
			return null;
		if (! grafo.get(origen).containsKey(destino))
			return null;
		return grafo.get(origen).get(destino);
	}
	
	
	/**********
	public void Dijkistra (Vertice origen) {
		ArrayList<Vertice> vertices = 
						new ArrayList<Vertice>(mapa.keySet());
		TreeSet<Vertice> s = new TreeSet<Vertice>();
		s.add (origen);
		distancia = new double [mapa.size()];
		previo = new Vertice[mapa.size()];
		for (int i=0; i<vertices.size(); i++) {
			Arista a = getArista (origen, vertices.get(i));
			if (a == null) {
				distancia[i] = INFINITO;
				previo[i] = null;
			}
			else {
				distancia[i] = a.getLongitud();
				previo[i] = origen;
			}
		}
		TreeSet<Vertice> vmenoss = new TreeSet<Vertice>(vertices);
		vmenoss.remove(origen);
		for (int i=1; i<=vertices.size()-2; i++) {
			Vertice w = extraerVertice (vertices, vmenoss, distancia);
			s.add(w);
			vmenoss.remove(w);
			for (Vertice v : vmenoss) {
				Arista a = getArista (w, v);
				if (a != null) {
					int posw = vertices.indexOf(w);
					int posv = vertices.indexOf(v);
					if (distancia[posw] + a.getLongitud() < distancia[posv]) {
						distancia[posv] = distancia[posw] + a.getLongitud();
						previo[posv] = w;
					}
				}
			}
		}
	}

	private Vertice extraerVertice(ArrayList<Vertice> vertices, 
				TreeSet<Vertice> vmenoss, double[] distancia2) {
		double min = Double.MAX_VALUE;
		Vertice vertmin = null;
		for (Vertice v : vmenoss) {
			int pos = vertices.indexOf(v);
			if (distancia2[pos] < min) {
				min = distancia2[pos];
				vertmin = v;
			}
		}
		return vertmin;
	}
	*****************************/
	
	//OTRA FORMA

	private void dijkstra (Vertice origen) {
		TreeSet<Vertice> s = new TreeSet<Vertice>();
		s.add (origen);
		distancia = new TreeMap<Vertice, Double> ();
		previo = new TreeMap<Vertice, Vertice> ();
		for (Vertice v : grafo.keySet()) {
			Arista a = getArista (origen, v);
			if (a == null) {
				distancia.put(v, MAX);
				previo.put(v, null);
			}
			else {
				distancia.put(v, a.getLongitud());
				previo.put(v, origen);
			}
		}
		TreeSet<Vertice> vmenoss = new TreeSet<Vertice> (grafo.keySet());
		vmenoss.remove(origen);
		while (vmenoss.size() > 0) {
			Vertice w = extraerVertice (vmenoss, distancia);
			System.out.println("Despues de extraer: "+w);
			s.add(w);
			vmenoss.remove(w);
			for (Vertice v : vmenoss) {
				Arista a = getArista (w, v);
				if (a != null) {
					if (distancia.get(w) + a.getLongitud() < distancia.get(v)) {
						distancia.put(v, distancia.get(w) + a.getLongitud());
						System.out.println("Cambiando previo de "+v+" a "+w);
						previo.put(v, w);
					}
				}
			}
		}
	}

	private Vertice extraerVertice(TreeSet<Vertice> vMenos, 
						TreeMap<Vertice, Double> distancia2) {
		double min = MAX*2;
		Vertice verticeMinimo = null;
		for (Vertice v : vMenos) {
			Double d = distancia2.get(v);
			System.out.println("v = "+v+" con distancia: "+d);
			if (d < min) {
				min = d;
				verticeMinimo = v;
			}
		}
		return verticeMinimo;
	}
	
	public TreeMap<Vertice, ArrayList<Vertice>> sacarCaminos
							(Vertice origen) {
		dijkstra (origen);
		TreeMap<Vertice, ArrayList<Vertice>> salida =
					new TreeMap<Vertice, ArrayList<Vertice>>();
		for (Vertice v : grafo.keySet()) {
			//System.out.println("mirando: "+v);
			if (v.equals(origen)) {
				//System.out.println("comparando "+v+" con "+origen);
				salida.put(v, null);
			}
			else {
				ArrayList<Vertice> lista = new ArrayList<Vertice> ();
				lista.add(origen);
				//System.out.println("antes: "+lista);
				hacerCamino (origen, v, lista);
				lista.add(v);
				//System.out.println("despues: "+lista);
				salida.put(v, lista);
			}
		}
		return salida;
	}


	private void hacerCamino(Vertice origen, Vertice v, 
						ArrayList<Vertice> lista) {
		System.out.println("camino de "+origen+" a "+v+" pasa por "+previo.get(v));
		if (previo.get(v) == null || previo.get(v).equals(origen)) {
			//lista.add(v);
		}
		else {
			hacerCamino (origen, previo.get(v), lista);
			lista.add(previo.get(v));
		}
	}
	

	public double calcularTiempo(ArrayList<Vertice> lista) {
		double tiempo = 0, tManzana=Camino.TIEMPOESTACION, tIntercambio=Camino.TIEMPOINTERCAMBIO, 
				tEstacion=Camino.TIEMPOMANZANA;
		//estacion inicial: t3
		tiempo = tiempo + tEstacion;
		Vertice anterior = lista.get(0);
		Arista aAnterior = null;
		//para el resto de estaciones
		for(int i=1; i<lista.size(); i++) {
			Vertice actual = lista.get(i);
			Arista aSiguiente = getArista(anterior, actual);
			tiempo = tiempo + aSiguiente.getLongitud()*tManzana;
			if (i < lista.size() - 1)
				tiempo = tiempo + tEstacion;
			if (aAnterior != null) {
				if ( ! aAnterior.getNombreLinea().equals(aSiguiente.getNombreLinea()))
					tiempo = tiempo + tIntercambio;
			}
			aAnterior = aSiguiente;
			anterior = actual;
		}
		return tiempo;
	}
}