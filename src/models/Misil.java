package models;

import java.awt.Point;
import java.awt.Rectangle;

public class Misil {

	private double x;
	private double y;
	private double velocidad;
	private double angulo;
	private final boolean direccion;
	private String misilURL = "/Users/tobiasruano/Developer/Lanzamisiles/src/imagenes/misil.png";

	public Misil(Point inicio, Point fin, double velocidad) {
		this.velocidad = velocidad;
		direccion = fin.x >= 400;
		angulo = Math.atan2(Math.abs(fin.x - inicio.x), Math.abs(inicio.y - fin.y));
		this.x = inicio.x;
		this.y = inicio.y;
	}

	public void moverse(double velocidad) {
		double xVelocidad = (velocidad)*1.5 * Math.sin(angulo);
		double yVelocidad = (velocidad)*1.5 * Math.cos(angulo);

		if (direccion) {
			this.x = this.x + xVelocidad;
		} else {
			this.x = this.x - xVelocidad;
		}
		this.y = this.y - yVelocidad;
	}

	public String getImagePath() {
		return this.misilURL;
	}

	public int getX() {
		return (int) this.x;
	}

	public int getY() {
		return (int) this.y;
	}

	public Rectangle getPosicion() {
		return new Rectangle((int) this.x, (int) this.y, 50, 100);
	}

}
