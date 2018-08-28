package org.eda2.practica4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Recorridos extends Grafo {

	private ArrayList<VerticeR> noAutomatizadas;
	private ArrayList<VerticeR> automatizadas;

	private ArrayList<VerticeR> soa;// = null;
	private double voa;// = Double.MAX_VALUE;

	public Recorridos(String nombreGrafo, String nombreMaquinas) {
		super(nombreGrafo);
		noAutomatizadas = new ArrayList<VerticeR>();
		automatizadas = new ArrayList<VerticeR>();
		leerMaquinas(nombreMaquinas);
		floyd();
	}

	public int getNumAutomatizadas() {
		return automatizadas.size();
	}

	public int getNumNoAutomatizadas() {
		return noAutomatizadas.size();
	}

	private void leerMaquinas(String nombreMaquinas) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(nombreMaquinas)));
			String linea = br.readLine();
			int numero = 0;
			while (linea != null) {
				Scanner sc = new Scanner(linea);
				int calle = sc.nextInt();
				int avenida = sc.nextInt();
				Ubicacion ubica = new Ubicacion(calle, avenida);
				int correctas = sc.nextInt();
				int incorrectas = sc.nextInt();
				VerticeR v = new VerticeR(ubica, correctas, incorrectas, numero);
				System.out.println("Vertice: "+v);
				if (correctas == 0)
					noAutomatizadas.add(v);
				else
					automatizadas.add(v);
				numero++;
				linea = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error de lectura de " + nombreMaquinas);
		}
	}

	public ArrayList<VerticeR> calcularRecorridos() {
		ArrayList<VerticeR> solucion = new ArrayList<VerticeR>();
		VerticeR origen = new VerticeR(central.ubicacion);
		
		System.out.println(noAutomatizadas);
		System.out.println(automatizadas);
		int n = -1;
		for (int i=0; i<noAutomatizadas.size(); i++) {
			//System.out.println("comparando "+origen+" con "+noAutomatizadas.get(i));
			if (origen.ubicacion.equals(noAutomatizadas.get(i).ubicacion))
				n = i;
		}
		//System.out.println("n = "+n);
		if (n != -1)
			origen = noAutomatizadas.get(n);
		else {
			for (int i=0; i<automatizadas.size(); i++) {
				if (origen.ubicacion.equals(automatizadas.get(i).ubicacion))
					n = i;
			}
			origen = automatizadas.get(n);
		}
		System.out.println("origen = " + origen);
		ArrayList<VerticeR> solNoAuto = calcularRecorrido(origen, noAutomatizadas);
		solucion.addAll(solNoAuto);
		System.out.println("solucion parcial: " + solucion);
		// soa = null;
		// voa = Double.MAX_VALUE;
		origen = solNoAuto.get(solNoAuto.size() - 1);
		System.out.println("Origen = " + origen);
		ArrayList<VerticeR> solAuto = calcularRecorrido(origen, automatizadas);
		solucion.addAll(solAuto);
		return solucion;
	}

	public ArrayList<VerticeR> calcularRecorrido(VerticeR origen, ArrayList<VerticeR> listaVertices) {
		ArrayList<VerticeR> s = new ArrayList<VerticeR>();
		int nivel = 0;
		double tiempo = 0;
		voa = Double.MAX_VALUE;
		soa = null;
		soa = calcularRecorridoRec(origen, listaVertices, nivel, tiempo, s);
		return soa;
	}

	/*private ArrayList<VerticeR> calcularRecorridoRec(VerticeR origen, ArrayList<VerticeR> listaVertices, int nivel,
			double tiempo, ArrayList<VerticeR> s) {

		// System.out.println("llamada desde "+origen);
		for (int i = 0; i < listaVertices.size(); i++) {
			VerticeR v2 = listaVertices.get(i);
			if (!s.contains(v2)) {
				s.add(v2);
				tiempo = tiempo + distancias[origen.getNumero()][v2.getNumero()] + v2.getTiempo();
				if (tiempo < voa) {
					// System.out.println("comparando "+nivel+" con "
					// +(listaVertices.size()-1)+
					// " y con i = "+i+" tamaño s = "+
					// s.size());
					if (nivel == listaVertices.size() - 1) {
						voa = tiempo;
						soa = new ArrayList<VerticeR>(s);
					} else
						calcularRecorridoRec(v2, listaVertices, nivel + 1, tiempo, s);
				}
				tiempo = tiempo - distancias[origen.getNumero()][v2.getNumero()] - v2.getTiempo();
				s.remove(v2);
			}
		}
		return soa;
	}*/

	private ArrayList<VerticeR> calcularRecorridoRec(VerticeR origen, ArrayList<VerticeR> listaVertices, int nivel,
			double tiempo, ArrayList<VerticeR> s) {
			//System.out.print(".");
			int n = listaVertices.size();
			for (int i=0; i<n; i++) {
				VerticeR v2 = listaVertices.remove(i);
				s.add(v2);
				/*if (s.size() == 1) {
					System.out.println("El primero es: "+v2+" con tiempo "+tiempo
									+" y origen "+origen+" y n "+n);
					System.out.println("iguales: "+v2.equals(origen));
					if (v2.equals(origen))
						System.out.println("y distancia "+distancias[origen.getNumero()][v2.getNumero()]);
				}*/
				tiempo = tiempo + distancias[origen.getNumero()][v2.getNumero()] + v2.getTiempo();
				
				if (listaVertices.isEmpty()) {
				//if (nivel == numeroVertices){
					//System.out.println("listavertices = "+listaVertices);
					//System.out.println("Comparo "+tiempo+" con "+voa);
					if (tiempo < voa) {
						voa = tiempo;
						soa = new ArrayList<VerticeR>(s);
						System.out.println("y cambio "+soa);
					}
				}
				else {
					//si el tiempo ya supera el voa, podo y no llamo recursivamente
					//esta seria la poda
					if (tiempo < voa)
						calcularRecorridoRec(v2, listaVertices, nivel + 1, tiempo, s);
				}
				tiempo = tiempo - distancias[origen.getNumero()][v2.getNumero()] - v2.getTiempo();
				s.remove(v2);
				listaVertices.add(i, v2);
			}
		
		return soa;
	}

	///////////////////////////
	
	/*public ArrayList<VerticeR> calcularRecorrido(VerticeR origen, ArrayList<VerticeR> listaVertices) {
		ArrayList<VerticeR> s = new ArrayList<VerticeR>();
		int nivel = 0;
		double tiempo = 0;
		boolean []asignado = new boolean [listaVertices.size()+1];
		VerticeR []sol = new VerticeR [listaVertices.size()+1];
		VerticeR []solMejor = new VerticeR [listaVertices.size()+1];
		voa = Double.MAX_VALUE;
		calcularRecorridoRec (origen, )
		soa = new ArrayList<VerticeR> ();
		for (int i=0; i<solMejor.length; i++)
			soa.add(solMejor[i]);
	}
	
	private ArrayList<VerticeR> calcularRecorridoRec(VerticeR origen, ArrayList<VerticeR> listaVertices, int nivel,
			double tiempo, ArrayList<VerticeR> s) {
			//System.out.print(".");
		
			for (int i=0; i<listaVertices.size(); i++) {
				VerticeR v2 = listaVertices.remove(i);
				s.add(v2);
				tiempo = tiempo + distancias[origen.getNumero()][v2.getNumero()] + v2.getTiempo();
				
				if (listaVertices.isEmpty()) {
				//if (nivel == numeroVertices){
					//System.out.println("listavertices = "+listaVertices);
					//System.out.println("Comparo "+tiempo+" con "+voa);
					if (tiempo < voa) {
						voa = tiempo;
						soa = new ArrayList<VerticeR>(s);
						//System.out.println("y cambio "+soa);
					}
				}
				else {
					calcularRecorridoRec(v2, listaVertices, nivel + 1, tiempo, s);
				}
				tiempo = tiempo - distancias[origen.getNumero()][v2.getNumero()] - v2.getTiempo();
				s.remove(v2);
				listaVertices.add(i, v2);
			}
		
		return soa;
	}*/
}
