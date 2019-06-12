package models;

import java.awt.Rectangle;

public class Barco {

	private final String barcoToLeftURL = "/Users/tobiasruano/Developer/Lanzamisiles/src/imagenes/shipRight.png";
	private final String barcoToRightURL = "/Users/tobiasruano/Developer/Lanzamisiles/src/imagenes/shipLeft.png";
	private int x;
	private int y = 200;
	private int velocidad;
	private String path;
	private boolean sentido;

	public Barco(int velocidad, boolean sentido) {
		this.sentido = sentido;
		if (sentido == true) {
			this.velocidad = velocidad;
			this.x = -100;
			this.path = barcoToRightURL;
		} else {
			this.velocidad = -velocidad;
			this.x = 1000; //TODO: fuera de la ventana por 100 pixeles margen derecho
			this.path = barcoToLeftURL;
		}
	}

	public int moverseEnX(int x) {
		this.x += x * this.velocidad;
		return this.x;
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
