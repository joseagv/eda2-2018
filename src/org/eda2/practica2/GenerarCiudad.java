package org.eda2.practica2;

import java.io.*;
import java.util.Locale;

public class GenerarCiudad {

	public static void main(String[] args) {
		String directorio = System.getProperty("user.dir")+
				File.separator+"src"+File.separator+
				"datosCiudad"+File.separator;
		Ciudad ciudad = generarCiudad (directorio);
	}
	
	private static Ciudad generarCiudad(String directorio) {
		int nCalles = (int) (Math.random()*1000 + 1);
		int nAvenidas = (int) (Math.random()*1000 + 1);
		Ciudad ciudad = new Ciudad(nCalles, nAvenidas);
		//String nombreCiudad = directorio + "Ciudad.txt";
		ciudad.generarFallos ();
		ciudad.guardarCiudad (directorio);
		return ciudad;
	}



}
