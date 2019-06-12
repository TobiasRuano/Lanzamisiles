package models;

import java.awt.Point;
import java.awt.Rectangle;

import models.Misil;

public class Tanque {

	private int x = 375;
	private int y = 500;
	private String tanqueURL = "/Users/tobiasruano/Developer/Lanzamisiles/src/imagenes/tank.png";
	private String nombre;

	public Tanque(String nombre) {
		setNombre(nombre);
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Misil disparar(Point fin, int velocidad) {
		Point origen = new Point();
		origen.x = this.x;
		origen.y = this.y;
		return new Misil(origen, fin, velocidad);
	}

	public String getImagePath() {
		return this.tanqueURL;
	}

	public Rectangle getPosicion() {
		return new Rectangle(this.x, this.y, 100, 200); // 100 y 200 son las dimenciones del .png
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}
