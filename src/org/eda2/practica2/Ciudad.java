package org.eda2.practica2;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Ciudad {

	private static final double TAMANIOCALLE = 50;
	private static final double TAMANIOAVENIDA = 75;
	private int nCalles;
	private int nAvenidas;
	private int distanciaPostes;
	private Ubicacion em;
	private Ubicacion ce;
	private ArrayList<Linea> lineas;
	private ArrayList<Estacion> estaciones;

	public Ciudad (int nCalles, int nAvenidas) {
		this.nCalles = nCalles;
		this.nAvenidas = nAvenidas;
		distanciaPostes = (int) (Math.random() * 5);
		em = new Ubicacion (1, nAvenidas/2);
		ce = new Ubicacion (nCalles/2, nAvenidas/2);
		lineas = new ArrayList<Linea>();
		generarLineasCiudad ();
		generarEstacionesCiudad();
	}
	
	private void generarLineasCiudad () {
		int calle, avenida, nC, nA, nPoste, nPoste2, avenida2, calle2;
		ArrayList<Poste> postes, postes2;
		Linea linea;
		
		nA = (int) (TAMANIOAVENIDA / distanciaPostes); 
		nC = (int) (TAMANIOCALLE / distanciaPostes);
		for (int i=1; i<=4; i++) {
			switch (i) {
			case 1:
				postes = new ArrayList<Poste> ();
				//genero el trozo comun de las lineas 1
				nPoste = 1;
				avenida = nAvenidas/2;
				for (calle=1; calle<=nCalles/2; calle++) {
					for (int j=1; j<=nC; j++) {
						Ubicacion u = new Ubicacion (calle, avenida);
						Poste p = new Poste (nPoste, u, true);
						postes.add(p);
						nPoste++;
					}
				}
				//genero el resto de la linea 1a
				postes2 = new ArrayList<Poste> (postes);
				calle = nCalles / 2;
				nPoste2 = nPoste;
				for (avenida = nAvenidas/2; avenida>=1; avenida--) {
					for (int j=1; j<=nA; j++) {
						Ubicacion u = new Ubicacion (calle, avenida);
						Poste p = new Poste (nPoste2, u, true);
						postes2.add(p);
						nPoste2 ++;
					}
				}
				linea = new Linea ("Linea1a", postes2);
				lineas.add(linea);
				//genero el resto de la linea 1b
				postes2 = new ArrayList<Poste> (postes);
				avenida = nAvenidas / 2;
				nPoste2 = nPoste;
				for (calle=nCalles/2+1; calle<=nCalles; calle++) {
					for (int j=1; j<=nC; j++) {
						Ubicacion u = new Ubicacion (calle, avenida);
						Poste p = new Poste (nPoste2, u, true);
						postes2.add(p);
						nPoste2++;
					}
				}
				linea = new Linea ("Linea1b", postes2);
				lineas.add(linea);
				//genero el resto de la linea 1c
				postes2 = new ArrayList<Poste> (postes);
				calle = nCalles / 2;
				nPoste2 = nPoste;
				for (avenida = nAvenidas/2+1; avenida<=nAvenidas; avenida++) {
					for (int j=1; j<=nA; j++) {
						Ubicacion u = new Ubicacion (calle, avenida);
						Poste p = new Poste (nPoste2, u, true);
						postes2.add(p);
						nPoste2++;
					}
				}
				linea = new Linea ("Linea1c", postes2);
				lineas.add(linea);
				break;
			case 2:
				postes = new ArrayList<Poste>();
				nPoste = 1;
				calle = 1;
				for (avenida=nAvenidas/2+1; avenida<=nAvenidas; avenida++) {
					for (int j=1; j<=nA; j++) {
						Ubicacion u = new Ubicacion (calle, avenida);
						Poste p = new Poste (nPoste, u, true);
						postes.add(p);
						nPoste++;
					}
				}
				avenida = nAvenidas;
				for (calle=1; calle<=nCalles; calle++) {
					for (int j=1; j<=nC; j++) {
						Ubicacion u = new Ubicacion (calle, avenida);
						Poste p = new Poste (nPoste, u, true);
						postes.add(p);
						nPoste++;
					}
				}
				calle = nCalles;
				for (avenida=nAvenidas; avenida>=1; avenida--) {
					for (int j=1; j<=nA; j++) {
						Ubicacion u = new Ubicacion (calle, avenida);
						Poste p = new Poste (nPoste, u, true);
						postes.add(p);
						nPoste++;
					}
				}
				avenida = 1;
				for (calle=nCalles; calle>=1; calle--) {
					for (int j=1; j<=nC; j++) {
						Ubicacion u = new Ubicacion (calle, avenida);
						Poste p = new Poste (nPoste, u, true);
						postes.add(p);
						nPoste++;
					}
				}
				calle = 1;
				for (avenida=1; avenida<nAvenidas/2; avenida++) {
					for (int j=1; j<=nA; j++) {
						Ubicacion u = new Ubicacion (calle, avenida);
						Poste p = new Poste (nPoste, u, true);
						postes.add(p);
						nPoste++;
					}
				}
				linea = new Linea ("Linea2", postes);
				lineas.add(linea);
				break;
			case 3: //genero lineas 3 y 4
				postes = new ArrayList<Poste> ();
				postes2 = new ArrayList<Poste> ();
				avenida = Math.round(nAvenidas / 4);
				avenida2 = nAvenidas - nAvenidas / 4;
				nPoste = 1;
				for(calle=1; calle<=nCalles; calle++) {
					for (int j=1; j<=nC; j++) {
						Ubicacion u = new Ubicacion (calle, avenida);
						Poste p = new Poste (nPoste, u, true);
						postes.add(p);
						u = new Ubicacion (calle, avenida2);
						p = new Poste (nPoste, u, true);
						postes2.add(p);
						nPoste++;
					}
				}
				linea = new Linea ("Linea3", postes);
				lineas.add(linea);
				linea = new Linea ("Linea4", postes2);
				lineas.add(linea);
				break;
			case 4: //genero lineas 5 y 6
				postes = new ArrayList<Poste> ();
				postes2 = new ArrayList<Poste> ();
				calle = Math.round(nCalles / 4);
				calle2 = nCalles - nCalles / 4;
				nPoste = 1;
				for(avenida=1; avenida<=nAvenidas; avenida++) {
					for (int j=1; j<=nA; j++) {
						Ubicacion u = new Ubicacion (calle, avenida);
						Poste p = new Poste (nPoste, u, true);
						postes.add(p);
						u = new Ubicacion (calle2, avenida);
						p = new Poste (nPoste, u, true);
						postes2.add(p);
						nPoste++;
					}
				}
				linea = new Linea ("Linea5", postes);
				lineas.add(linea);
				linea = new Linea ("Linea6", postes2);
				lineas.add(linea);
				break;
			}
		}
	}
	
	//Falta crear añadir el arraylist de estaciones, se estan sobreescribiendo
	private void generarEstacionesCiudad () {
		int calle, avenida, nC, nA, avenida2, calle2;
		
		nA = (int) (TAMANIOAVENIDA / distanciaPostes); 
		nC = (int) (TAMANIOCALLE / distanciaPostes);
		
		//Generamos estacion central
		Estacion central = new Estacion(nCalles/2, nAvenidas/2);
		
		//Generar estaciones partiendo de Ce
		for (int i=1; i<=4; i++) {
			switch (i) {
			case 1:
				//Genero estaciones partiendo de Ce hacia el sur
				calle = nCalles/2;
				avenida = nAvenidas/2;
				for (int j=calle - 1; j>=1; j--) {
					if(j%2 == 0){
						Estacion e = new Estacion (calle, avenida);
						estaciones.add(e);
					}				
				}
				//Genero estaciones partiendo de Ce hacia el norte
				for (int j=calle + 1; j<=nCalles; j++) {
					if(j%2 == 0){
						Estacion e = new Estacion (calle, avenida);
						estaciones.add(e);
					}				
				}
			
				//Genero estaciones partiendo de Ce hacia el oeste
				for (int j=avenida - 1; j<=1; j--) {
					if(j%2 == 0){
						Estacion e = new Estacion (calle, avenida);
						estaciones.add(e);
					}				
				}
	
				//Genero estaciones partiendo de Ce hacia el este
				for (int j=avenida + 1; j>=nAvenidas; j++) {
					if(j%2 == 0){
						Estacion e = new Estacion (calle, avenida);
						estaciones.add(e);
					}				
				}
				break;
			
			//Línea circular
			case 2:
				calle = nCalles/2;
				avenida = nAvenidas/2;
				Estacion e3 = new Estacion(nCalles ,avenida);
				Estacion em = new Estacion(1 ,avenida);
				estaciones.add(em);
				estaciones.add(e3);
				//Partimos de Em hacia la izq creando las estaciones cada dos avenidas en la calle 1 y n
				for (int j=avenida - 2; j<=1; j--) {
					if(j%2 == 0) {
						Estacion e = new Estacion(1 ,avenida);
						estaciones.add(e);
						Estacion e2 = new Estacion(nCalles ,avenida);
						estaciones.add(e2);
					}
					
				}
				//Partimos de Em hacia la der creando las estaciones cada dos avenidas en la calle 1 y n
				for (int j=avenida + 2; j<=nAvenidas; j++) {
					if(j%2 == 0) {
						Estacion e = new Estacion(1 ,avenida);
						estaciones.add(e);
						Estacion e2 = new Estacion(nCalles ,avenida);
						estaciones.add(e2);
					}				
				}
				
				//Partimos de la calle central hacia el sur creando las estaciones cada dos avenidas
				for (int j=calle; j>=1; j--) {
					if(j%2 == 0) {
						Estacion e = new Estacion(j ,1);
						estaciones.add(e);
						Estacion e2 = new Estacion(j ,nAvenidas);
						estaciones.add(e2);
						
					}				
				}
				
				//Partimos de la calle central hacia el norte creando las estaciones cada dos avenidas
				for (int j=calle; j<=nCalles; j++) {
					if(j%2 == 0) {
						Estacion e = new Estacion(j ,1);
						estaciones.add(e);
						Estacion e2 = new Estacion(j ,nAvenidas);
						estaciones.add(e2);
					}				
				}
				break;
				
				//Línea 3 y 4
				case 3:
					calle = 1;
					avenida = Math.round(nAvenidas / 4);
					avenida2 = nAvenidas - nAvenidas / 4;
					//Línea 3 hacia el norte
					for (int j=calle; j<=nCalles; j++) {
						if(j%2 == 1) {
							Estacion e = new Estacion(j ,avenida);
							estaciones.add(e);
						}					
					}
					//Línea 4 hacia el norte
					for (int j=avenida2; j<=nCalles; j++) {
						if(j%2 == 1) {
							Estacion e = new Estacion(j ,avenida2);
							estaciones.add(e);
						}					
					}
				break;
				
				//Líneas 5 y 6
				case 4: 
					avenida = 1;
					calle = Math.round(nCalles / 4);
					calle2 = nAvenidas - nCalles / 4;
					//Línea 5 hacia el este
					for (int j=avenida; j<=nAvenidas; j++) {
						if(j%2 == 1) {
							Estacion e = new Estacion(calle ,j);
							estaciones.add(e);
						}					
					}
					//Línea 6 hacia el este
					for (int j=avenida; j<=nAvenidas; j++) {
						if(j%2 == 1) {
							Estacion e = new Estacion(calle2 ,j);
							estaciones.add(e);
						}					
					}
				break;
			}
		}
	}
	
	public void generarFallos () {
		for (int i=0; i<lineas.size(); i++) {
			lineas.get(i).generarFallo();
		}
	}
	
	public void guardarCiudad(String directorio) {
		String nombreFichero = directorio + "ciudad.txt";
		try {
			BufferedWriter f = new BufferedWriter (new 
					FileWriter (new File (nombreFichero)));
			f.write(nCalles+"\n");
			f.write(nAvenidas+"\n");
			//f.write(String.format(Locale.US, "%.2f", distanciaPostes)+"\n");
			f.write(distanciaPostes+"\n");
			f.write("em "+em+"\n");
			f.write("ce "+ce+"\n");
			f.write(lineas.size()+"\n");
			for (int i=0; i<lineas.size(); i++) {
				Linea l = lineas.get(i);
				String nombre = directorio + l.getNombreLinea()+".txt";
				f.write(nombre+"\n");
			}
			f.close();
		}
		catch (IOException e) {
			System.out.println("Error en la creacion del fichero: "+
						nombreFichero);
		}
		for (int i=0; i<lineas.size(); i++) {
			Linea l = lineas.get(i);
			nombreFichero = directorio + l.getNombreLinea()+".txt";
			try {
				BufferedWriter f = new BufferedWriter (new 
						FileWriter (new File (nombreFichero)));
				f.write(l.getNumeroPostes()+"\n");
				for (int j=0; j<l.getNumeroPostes(); j++) {
					Poste p = l.getPostes().get(j);
					f.write(p+"\n");
				}
				f.close();
			}
			catch (IOException e) {
				System.out.println("Error en la creacion del fichero: "+
							nombreFichero);
			}
		}
	}
	
	public Ciudad(String nombreFichero) {
		try {
			Scanner f = new Scanner (new File(nombreFichero));
			nCalles = Integer.parseInt(f.nextLine());
			nAvenidas = Integer.parseInt(f.nextLine());
			distanciaPostes = Integer.parseInt(f.nextLine());
			String cadena = f.nextLine();
			String []trozos = cadena.split(" ");
			em = new Ubicacion (Integer.parseInt(trozos[1]),
								Integer.parseInt(trozos[2]));
			cadena = f.nextLine();
			trozos = cadena.split(" ");
			ce = new Ubicacion (Integer.parseInt(trozos[1]),
								Integer.parseInt(trozos[2]));
			lineas = new ArrayList<Linea> ();
			int numLineas = Integer.parseInt(f.nextLine());
			for (int i=0; i<numLineas; i++) {
				String nombreFicheroLinea = f.nextLine();
				Linea linea = new Linea (nombreFicheroLinea); 
				lineas.add(linea);
			}
			f.close();
		}
		catch (IOException e) {
			System.out.println("Error de lectura del fichero");
		}
	}
	
	public int getNumLineas () {
		return lineas.size();
	}
	
	public Linea getLinea (int n) {
		return lineas.get(n);
	}
	
	public String getNombreLinea (int n) {
		return lineas.get(n).getNombreLinea();
	}
}
