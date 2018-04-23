package org.eda2.practica2;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/* esta clase almacena el grafo de las lineas de metro
 * igual que en EDA I voy a hacer un 
 * TreeMap<Vertice, TreeMap<Vertice, Arista>>
 * para poner los vertices como claves de un TreeMap, 
 * necesito que implementen la interface Comparable
 */
public class Grafo {

	public static final double INFINITO = 1e60;
	private TreeMap<Vertice, TreeMap<Vertice, Arista>> mapa;
	//private double []distancia;
	//private Vertice []previo;
	private TreeMap<Vertice, Double> distancia;
	private TreeMap<Vertice, Vertice> previo;
	
	
	
	public Arista getArista (Vertice origen, Vertice destino) {
		if ( ! mapa.containsKey(origen))
			return null;
		if (! mapa.get(origen).containsKey(destino))
			return null;
		return mapa.get(origen).get(destino);
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
		for (Vertice v : mapa.keySet()) {
			Arista a = getArista (origen, v);
			if (a == null) {
				distancia.put(v, INFINITO);
				previo.put(v, null);
			}
			else {
				distancia.put(v, a.getLongitud());
				previo.put(v, origen);
			}
		}
		TreeSet<Vertice> vmenoss = new TreeSet<Vertice> (mapa.keySet());
		vmenoss.remove(origen);
		while (vmenoss.size() > 1) {
			Vertice w = extraerVertice (vmenoss, distancia);
			s.add(w);
			vmenoss.remove(w);
			for (Vertice v : vmenoss) {
				Arista a = getArista (w, v);
				if (a != null) {
					if (distancia.get(w) + a.getLongitud() < distancia.get(v)) {
						distancia.put(v, distancia.get(w) + a.getLongitud());
						previo.put(v, w);
					}
				}
			}
		}
	}

	private Vertice extraerVertice(TreeSet<Vertice> vmenoss, 
						TreeMap<Vertice, Double> distancia2) {
		double min = INFINITO;
		Vertice vertmin = null;
		for (Vertice v : vmenoss) {
			Double d = distancia2.get(v);
			if (d < min) {
				min = d;
				vertmin = v;
			}
		}
		return vertmin;
	}
	
	public TreeMap<Vertice, ArrayList<Arista>> sacarCaminos(Vertice origen) {
		dijkstra (origen);
		TreeMap<Vertice, ArrayList<Arista>> salida = new TreeMap<Vertice, ArrayList<Arista>>();
		return salida;		
	}
}