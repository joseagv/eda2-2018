package org.eda2.practica2;

import java.io.File;

public class ComprobarLineas {

	public static final int NVECES = 10;

	public static void main(String[] args) {
		long antes, despues, tiempoDyV, tiempoVZ;
		Poste poste = null;
		double sumaDyV, sumaVZ;
		Linea linea = null;
		String directorio = System.getProperty("user.dir") + File.separator + "src" + File.separator + "datosCiudad2"
				+ File.separator;
		System.out.println("Linea              Nº postes       Error        TiempoDyV         TiempoVZ");
		String nombreFichero = directorio + "ciudad.txt";
		Ciudad ciudad = new Ciudad(nombreFichero);
		for (int n = 0; n < ciudad.getNumLineas(); n++) {
			linea = ciudad.getLinea(n);
			sumaDyV = 0;
			sumaVZ = 0;
			for (int j=0; j<NVECES; j++) {
				antes = System.nanoTime();
				poste = linea.encontrarErrorDyV();
				despues = System.nanoTime();
				tiempoDyV = despues - antes;
				sumaDyV = sumaDyV + tiempoDyV;
				antes = System.nanoTime();
				poste = linea.encontrarErrorVZ();
				despues = System.nanoTime();
				tiempoVZ = despues - antes;
				sumaVZ = sumaVZ + tiempoVZ;
			}
			tiempoDyV = (long) (sumaDyV / NVECES);
			tiempoVZ = (long) (sumaVZ / NVECES);
			System.out.printf("%-16s  %10d  %10d  %15d  %15d\n", 
					linea.getNombreLinea(), linea.getNumeroPostes(), 
					poste.getNumPoste(), tiempoDyV, tiempoVZ);
		}
	}
}