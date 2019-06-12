package controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import models.Misil;
import models.Tanque;
import models.Barco;
import models.Jugador;

public class Controlador {

  private Jugador jugador;
	private Tanque tanque;
	private Barco barco;
	private Misil misil;

	private String dificultad;
	private int vidas = 3;
	private int puntos;
	private int velocidad = 2;
	private int cantBarcos = 0;
	private int cantBarcosEliminados;
	private boolean estaJugando = true;
	private boolean puedeDisparar = true;
	private Random booleanDireccionBarco;

	public Controlador(String dificultad, String nombre) {
		this.crearJugador(nombre);
		this.crearTanque(nombre); // En vez de pasar un string, deberia quizas pasar un objeto jugador.
		this.dificultad = dificultad; //TODO: implementar la dificultad de los barcos
		this.booleanDireccionBarco = new Random(); // boolean al azar para definir la direccion en la creaccion de los barcos
	}

	public Jugador crearJugador(String nombre) {
		if (this.jugador == null) {
			this.jugador = new Jugador(nombre);
		}
		return this.jugador;
	}

	public String getNombreJugador() {
		return this.jugador.getNombre();
	}

	public int getPuntosJugador() {
		return this.jugador.getPuntos();
	}

	public void setPuntosJugador() {
		this.jugador.setPuntos(puntos);
	}

	public Tanque crearTanque(String nombre) {
		if (this.tanque == null) {
			this.tanque = new Tanque(nombre);
		}
		return this.tanque;
	}

	public int getPuntos() {
		return puntos;
	}

	public Random getBooleanDireccionBarco() {
		return booleanDireccionBarco;
	}

	public Rectangle getPosicionMisil() {
		if (this.misil != null) {
			return this.misil.getPosicion();
		} else {
			return null;
		}
	}

	public int getVelocidad() {
		return velocidad;
	}

	public int getCantBarcos() {
		return cantBarcos;
	}

	public int getCantBarcosEliminados() {
		return cantBarcosEliminados;
	}

	public int getVidas() {
		return vidas;
	}

	public boolean estaJugando() {
		return estaJugando;
	}

	public boolean existeBarco() {
		return this.barco != null;
	}

	public void puedeDisparar(boolean puede) {
		this.puedeDisparar = puede;
	}

	public boolean jugando() {
		return this.cantBarcos < 10;
	}

	public Barco moverBarco(int x) {
		if (this.barco == null) {
			this.nuevoBarco();
		}
		this.barco.moverseEnX(x);
		return this.barco;
	}

	public boolean avanzaNivel() {
		boolean avanza = this.cantBarcosEliminados >= 5;
		this.cantBarcos = 0;
		this.cantBarcosEliminados = 0;
		if (avanza) {
			this.incrementarPuntos(100);
			this.velocidad += 2;
		} else {
			this.vidas--;
		}
		this.jugador.setPuntos(puntos);
		return avanza;
	}

	public void nuevoBarco() {
		if (this.barco == null) {
			if (this.barco == null) {
				this.barco = new Barco(this.velocidad, booleanDireccionBarco.nextBoolean());
			}
			this.cantBarcos++;
		}
	}

	public boolean dispararBarco() {
		Point fin = new Point();
		fin.y = 100;
		fin.x = 100;
		// Creo dos puntos para fijarlos como destino del misil -- Luego seran modificados con una mejor formar
		boolean disparo = this.tanque != null && this.puedeDisparar;
		if (disparo == true) {
			this.puedeDisparar = false;
			this.misil = this.tanque.disparar(fin, this.velocidad);
		}
		return disparo;
	}

	public boolean impacto() {
		if (this.barco != null && this.misil != null) {
			boolean hayImpacto = mismaPosicion(this.barco.getX(), this.barco.getY(), this.misil.getX(), this.misil.getY());

			if (hayImpacto) {
				this.incrementarPuntos(20);
				this.cantBarcosEliminados++;
				this.barco = null;
				this.misil = null;
			}
			return hayImpacto;
		} else {
			return false;
		}
	}

	public Rectangle posicionBarco() {
		if (this.barco != null) {
			return this.barco.getPosicion();
		} else {
			return null;
		}
	}

	private boolean mismaPosicion(int startBarcoX, int startBarcoY, int xMisil, int yMisil) {
		int endBarcoX = startBarcoX + 200;
		int endBarcoY = startBarcoY + 74;
		boolean x = xMisil <= endBarcoX && xMisil >= startBarcoX;
		boolean y = yMisil <= endBarcoY && yMisil >= startBarcoY;
		/*TODO: agregar la posibilidad de chequear si otra parte del misil esta dentro del area del barco
		lo haria mediante la implementacion de startMisil y endMisil en X e Y*/
		return x && y;
	}

	private void incrementarPuntos(int x) {
		this.puntos += x;
		if (this.puntos >= 300) {
			this.vidas++;
		}
	}

	public Misil moverMisil() {
		if (this.misil != null) {
			this.misil.moverse(this.velocidad);
		}
		return this.misil;
	}

	public void eliminarMisil() {
		this.misil = null;
	}

	public boolean getSentidoBarco() {
		if(this.barco != null) {
			return this.barco.getSentido();
		}
		return true;
	}

	public Rectangle getPosicionBarco() {
		if(this.barco != null) {
			return this.barco.getPosicion();
		}
		return null;
	}

	public void eliminarBarco() {
		this.barco = null;
	}

}
