package org.eda2.practica2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ComprobarLineas {

	public static final int NVECES = 10;

	public static void main(String[] args) throws IOException {
		String directorio2 = System.getProperty("user.dir")+
				File.separator+"src"+File.separator+
				"tiemposP2"+File.separator;
		long antes, despues, tiempoDyV, tiempoVZ;
		Poste poste = null;
		double sumaDyV, sumaVZ;
		Linea linea = null;
		String directorio = System.getProperty("user.dir") + File.separator + "src" + File.separator + "datosCiudad"
				+ File.separator;
		System.out.println("Linea              Nº postes       Error        TiempoDyV         TiempoVZ");
		String nombreFichero = directorio + "ciudad.txt";
		Ciudad ciudad = new Ciudad(nombreFichero);
		File file = new File(directorio2 + "Comparacion.txt");
		BufferedWriter f = new BufferedWriter (new FileWriter (file));
		f.write("Linea\tNº postes\tError\tTiempoDyV\tTiempoVZ\n");
		
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
				poste = linea.encontrarErrorVoraz();
				despues = System.nanoTime();
				tiempoVZ = despues - antes;
				sumaVZ = sumaVZ + tiempoVZ;
			}
			tiempoDyV = (long) (sumaDyV / NVECES);
			tiempoVZ = (long) (sumaVZ / NVECES);
			System.out.printf("%-16s  %10d  %10d  %15d  %15d\n", 
					linea.getNombreLinea(), linea.getNumeroPostes(), 
					poste.getNumPoste(), tiempoDyV, tiempoVZ);
			
			f.write(linea.getNombreLinea()+ "\t\t" + linea.getNumeroPostes() + "\t"  
			+ poste.getNumPoste() + "\t\t"	+ tiempoDyV + "\t" + tiempoVZ + "\n");
			
		}
		f.close();
	}
}