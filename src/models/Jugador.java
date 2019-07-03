package models;

public class Jugador {

	private String nombre;
	private int puntos = 0;

	public Jugador(String nombre) {
		setNombre(nombre);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getPuntos() {
		return this.puntos;
	}

	public void resetPuntos() {
		this.puntos = 0;
	}

}
