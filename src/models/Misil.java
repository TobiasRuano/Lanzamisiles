package models;

import java.awt.Point;
import java.awt.Rectangle;

public class Misil {

	private double x;
	private double y;
	private int velocidad;
	private double angulo;
	private final boolean avanza;
	private String misilURL = "/Users/tobiasruano/eclipse-workspace/Lanzamisiles/src/imagenes/misil.png";
	private boolean sentido;

	public Misil(Point inicio, Point fin, int velocidad, boolean sentido) {
		this.velocidad = velocidad;
		avanza = fin.x >= 400;
		angulo = Math.atan2(Math.abs(fin.x - inicio.x), Math.abs(inicio.y - fin.y));
		this.x = inicio.x;
		this.y = inicio.y;
		this.sentido = sentido;
	}

	public void moverse(int velocidad) {
		double xVelocidad = (velocidad)*1.5 * Math.sin(angulo);
		double yVelocidad = (velocidad)*1.5 * Math.cos(angulo);

		if(sentido) {
			this.y -= yVelocidad;
		} else {
			this.y += yVelocidad;
		}
		if (avanza) {
			this.x = this.x + xVelocidad;
		} else {
			this.x = this.x - xVelocidad;
		}
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
