package models;

public class Jugador {

	private String nombre;
	private int puntos = 0;

	public Jugador(String nombre) {
		setNombre(nombre);
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}// no seria necesario que la tenga publica, ya que nadie de afuera cambia el valor sin crear un nuevo objeto

	public String getNombre() {
		return this.nombre;
	}

	public void setPuntos(int puntos) {
		this.puntos += this.puntos + puntos;
	}

	public int getPuntos() {
		return this.puntos;
	}

}
