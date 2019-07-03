package models;

import java.awt.Rectangle;

public class Barco {

	private final String barcoToLeftURL = "/Users/tobiasruano/Developer/Lanzamisiles/src/imagenes/shipRight.png";
	private final String barcoToRightURL = "/Users/tobiasruano/Developer/Lanzamisiles/src/imagenes/shipLeft.png";
	private int x;
	private int y = 75;
	private int velocidad;
	private String path;
	private boolean sentido;

	public Barco(int velocidad, boolean sentido) {
		this.sentido = sentido;
		if (sentido == true) {
			this.velocidad = velocidad;
			this.x = -200;
			this.path = barcoToRightURL;
		} else {
			this.velocidad = -velocidad;
			this.x = 1000;
			this.path = barcoToLeftURL;
		}
		System.out.println("barco creado con velocidad: " + this.velocidad);
	}

	public void moverseEnX() {
		//this.x += x * this.velocidad;
		if (sentido == true) {
			this.x += this.velocidad;
		} else {
			this.x += this.velocidad;
		}
		System.out.println("velocidad: " + this.velocidad);
	}

	public String getImagePath() {
		return this.path;
	}

	public Rectangle getPosicion() {
		return new Rectangle(this.x, this.y, 200, 74);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean getSentido() {
		return this.sentido;
	}

}
