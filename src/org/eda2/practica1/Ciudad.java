package org.eda2.practica1;

import java.util.Arrays;

public class Ciudad {

	private String nombreCiudad;
	private String rutaArchivoEntrada;
	private String rutaArchivoSalida;
	private Linea[] lineas;

	public Ciudad(String nombreCiudad, String rutaArchivoEntrada, String rutaArchivoSalida, Linea[] lineas) {
		super();
		this.nombreCiudad = nombreCiudad;
		this.rutaArchivoEntrada = rutaArchivoEntrada;
		this.rutaArchivoSalida = rutaArchivoSalida;
		this.lineas = lineas;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public String getRutaArchivoEntrada() {
		return rutaArchivoEntrada;
	}

	public void setRutaArchivoEntrada(String rutaArchivoEntrada) {
		this.rutaArchivoEntrada = rutaArchivoEntrada;
	}

	public String getRutaArchivoSalida() {
		return rutaArchivoSalida;
	}

	public void setRutaArchivoSalida(String rutaArchivoSalida) {
		this.rutaArchivoSalida = rutaArchivoSalida;
	}

	public Linea[] getLineas() {
		return lineas;
	}

	public void setLineas(Linea[] lineas) {
		this.lineas = lineas;
	}

	@Override
	public String toString() {
		return "Ciudad [nombreCiudad=" + nombreCiudad + ", rutaArchivoEntrada=" + rutaArchivoEntrada
				+ ", rutaArchivoSalida=" + rutaArchivoSalida + ", lineas=" + Arrays.toString(lineas) + "]";
	}

	private void generarCorte() {

	}

	public void generarLineas() {

	}

	public void leerFichero(String rutaArchivoEntrada) {

	}

	public void generarFichero(String rutaArchivoSalida) {

	}

}
