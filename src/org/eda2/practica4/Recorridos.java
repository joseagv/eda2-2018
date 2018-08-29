package org.eda2.practica4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Recorridos extends Grafo {

	private ArrayList<VerticeMaquinas> noAutomatizadas;
	private ArrayList<VerticeMaquinas> automatizadas;

	private ArrayList<VerticeMaquinas> recorridos;// = null;
	private double tiempoTotal;// = Double.MAX_VALUE;

	public Recorridos(String nombreGrafo, String nombreMaquinas) {
		super(nombreGrafo);
		noAutomatizadas = new ArrayList<VerticeMaquinas>();
		automatizadas = new ArrayList<VerticeMaquinas>();
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
				VerticeMaquinas v = new VerticeMaquinas(ubica, correctas, incorrectas, numero);
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

	public ArrayList<VerticeMaquinas> calcularRecorridos() {
		ArrayList<VerticeMaquinas> solucion = new ArrayList<VerticeMaquinas>();
		VerticeMaquinas origen = new VerticeMaquinas(central.ubicacion);

		System.out.println("No automatizadas:"  + noAutomatizadas);
		System.out.println("Automatizadas:" + automatizadas + "\n");
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
		System.out.println("\nOrigen = " + origen + "\n");
		ArrayList<VerticeMaquinas> solNoAuto = calcularRecorrido(origen, noAutomatizadas);
		solucion.addAll(solNoAuto);
		System.out.println("\nSolucion parcial: " + solucion);
		// soa = null;
		// voa = Double.MAX_VALUE;
		origen = solNoAuto.get(solNoAuto.size() - 1);
		System.out.println("\nOrigen = " + origen);
		ArrayList<VerticeMaquinas> solAuto = calcularRecorrido(origen, automatizadas);
		solucion.addAll(solAuto);
		return solucion;
	}

	public ArrayList<VerticeMaquinas> calcularRecorrido(VerticeMaquinas origen, ArrayList<VerticeMaquinas> listaVertices) {
		ArrayList<VerticeMaquinas> s = new ArrayList<VerticeMaquinas>();
		int nivel = 0;
		double tiempo = 0;
		tiempoTotal = Double.MAX_VALUE;
		recorridos = null;
		recorridos = calcularRecorridoRec(origen, listaVertices, nivel, tiempo, s);
		return recorridos;
	}

	private ArrayList<VerticeMaquinas> calcularRecorridoRec(VerticeMaquinas origen, ArrayList<VerticeMaquinas> listaVertices, int nivel,
			double tiempo, ArrayList<VerticeMaquinas> s) {
			int n = listaVertices.size();
			for (int i = 0; i < n; i++) {
				VerticeMaquinas v2 = listaVertices.remove(i);
				s.add(v2);
				tiempo = tiempo + distancias[origen.getNumero()][v2.getNumero()] + v2.getTiempo();

				if (listaVertices.isEmpty()) {
					if (tiempo < tiempoTotal) {
						tiempoTotal = tiempo;
						recorridos = new ArrayList<VerticeMaquinas>(s);
						System.out.println("y cambio "+recorridos);
					}
				}
				else {
					//si el tiempo ya supera el tiempoTotal, podo y no llamo recursivamente
					//esta seria la poda
					if (tiempo < tiempoTotal)
						calcularRecorridoRec(v2, listaVertices, nivel + 1, tiempo, s);
				}
				tiempo = tiempo - distancias[origen.getNumero()][v2.getNumero()] - v2.getTiempo();
				s.remove(v2);
				listaVertices.add(i, v2);
			}

		return recorridos;
	}
}