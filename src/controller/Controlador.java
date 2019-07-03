package controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import models.*;

public class Controlador {

	private static Controlador instancia = null;

	private Jugador jugador;
	private Tanque tanque;
	private Barco barco;
	private Misil misil;

	private String dificultad;
	private int vidas = 3;
	private int puntos;
	private int velocidad = 1;
	private int totalPuntos = 0;
	private int cantBarcos = 0;
	private int cantBarcosEliminados;
	private int sensibilidadMira = 30;
	private int cantNiveles = 5;
	private int cantNivelesJugados = 0;
	private double velocidadPotenciada = 0;
	private double intervaloDisparo = 1.0;
	private boolean estaJugando = true;
	private boolean puedeDisparar = true;
	private Random booleanDireccionBarco;

	private String []arrayNombreRanking;
	private int []arrayPuntosRanking;

	private Controlador(String dificultad, String nombre) {
		this.crearJugador(nombre);
		this.crearTanque(nombre);
		this.dificultad = dificultad;
		this.booleanDireccionBarco = new Random();
		this.setPuntiacion();
		this.configurarDificultad();
	}

	public static Controlador miInstancia(String dificultad, String nombre) {
		if (instancia == null) {
			instancia = new Controlador(dificultad, nombre);
		}
		if (dificultad != "sin dificuldad") {
			instancia.setDificultad(dificultad);
			instancia.reiniciarJuego();
		}
		if (nombre != "sin nombre") {
			instancia.jugador.setNombre(nombre);
		} else {
			instancia.jugador.setNombre("Player");
		}
		return instancia;
	}

	private void setDificultad(String dif) {
		this.dificultad = dif;
	}

	private void configurarDificultad() {
		if (this.dificultad == "Dificil") {
			velocidad = 3;
			System.out.println("velocidad seteada: " + velocidad);
		} else if (this.dificultad == "Facil") {
			velocidad = 1;
			System.out.println("velocidad seteada: " + velocidad);
		}
	}

	private void reiniciarJuego() {
		configurarDificultad();
		this.velocidadPotenciada = 0;
		this.cantNivelesJugados = 0;
		this.vidas = 3;
		this.puntos = 0;
		this.totalPuntos = 0;
		this.cantBarcos = 0;
		this.cantBarcosEliminados = 0;
		this.jugador.resetPuntos();
		this.barco = null;
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

	public int getTotalPuntos() {
		return totalPuntos;
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

	public void setIntervaloDisparos(double intervalo) {
		this.intervaloDisparo = intervalo;
	}

	public Double getIntervaloDisparos() {
		return this.intervaloDisparo;
	}

	public void setSensibilidadMira(int sensibilidad) {
		this.sensibilidadMira = sensibilidad;
	}

	public int getSensibilidadMira() {
		return this.sensibilidadMira;
	}

	public int getPuntos() {
		return this.puntos;
	}

	public Random getBooleanDireccionBarco() {
		return this.booleanDireccionBarco;
	}

	public Rectangle getPosicionMisil() {
		if (this.misil != null) {
			return this.misil.getPosicion();
		} else {
			return null;
		}
	}

	public int getVelocidad() {
		return this.velocidad;
	}

	public void setCantNiveles(int cant) {
		this.cantNiveles = cant;
	}

	public int getNivel() {
		return (this.cantNivelesJugados + 1);
	}

	public int getCantBarcos() {
		return this.cantBarcos;
	}

	public int getCantBarcosEliminados() {
		return this.cantBarcosEliminados;
	}

	public int getVidas() {
		return this.vidas;
	}

	public boolean getEstaJugando() {
		return this.estaJugando;
	}

	public boolean existeBarco() {
		return this.barco != null;
	}

	public void puedeDisparar(boolean puede) {
		this.puedeDisparar = puede;
	}

	public boolean jugando() {
		return this.cantBarcos != 10;
	}

	public Barco moverBarco() {
		if (this.barco == null) {
			this.nuevoBarco();
		}
		this.barco.moverseEnX();
		return this.barco;
	}

	public boolean avanzaNivel() {
		boolean avanza = this.cantBarcosEliminados >= 5;
		boolean finJuego = this.getNivel() >= this.cantNiveles;
		this.cantBarcos = 0;
		this.cantBarcosEliminados = 0;
		if (avanza) {
			this.incrementarPuntos(100);
			this.velocidad += 1;
			this.cantNivelesJugados += 1;
		} else {
			this.vidas--;
		}
		// this.jugador.setPuntos(puntos);
		this.jugador.setPuntos(this.totalPuntos);
		if (finJuego) {
			return false;
		}
		return avanza;
	}

	public void guardar() {
		this.jugador.setPuntos(this.totalPuntos);
		this.setPuntuacionFinal();
	}

	public void nuevoBarco() {
		if (this.barco == null) {
			if (this.barco == null) {
				this.barco = new Barco(this.velocidad, booleanDireccionBarco.nextBoolean());
			}
			this.cantBarcos++;
		}
	}

	public boolean dispararBarco(Point fin, double potencia) {
		boolean disparo = this.tanque != null && this.puedeDisparar;
		if (disparo == true) {
			this.puedeDisparar = false;
			this.velocidadPotenciada = this.velocidad * potencia;
			this.misil = this.tanque.disparar(fin, velocidadPotenciada);
		}
		return disparo;
	}

	public boolean verificarImpacto() {
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

	private boolean mismaPosicion(int startBarcoX, int startBarcoY, int startMisilX, int startMisilY) {
		int endBarcoX = startBarcoX + 200;
		int endBarcoY = startBarcoY + 74;
		int endMisilX = startMisilX + 50;
		int endMisilY = startMisilY + 100;
		boolean x = (startMisilX <= endBarcoX && startMisilX >= startBarcoX) || (endMisilX >= startBarcoX && endMisilX <= endBarcoX);
		boolean y = (startMisilY <= endBarcoY && startMisilY >= startBarcoY) || (endMisilY >= startBarcoY && endMisilY <= endBarcoY);
		return x && y;
	}

	public Rectangle posicionBarco() {
		if (this.barco != null) {
			return this.barco.getPosicion();
		} else {
			return null;
		}
	}

	private void incrementarPuntos(int x) {
		this.puntos += x;
		this.totalPuntos += x;
		if (this.puntos >= 300) {
			this.vidas++;
			this.puntos = this.puntos - 300;
		}
	}

	public Misil moverMisil() {
		if (this.misil != null) {
			this.misil.moverse(this.velocidadPotenciada);
		}
		return this.misil;
	}

	public void eliminarMisil() {
		this.misil = null;
	}

	public boolean sentidoBarco() {
		if(this.barco != null) {
			return this.barco.getSentido();
		}
		return true;
	}

	public void eliminarBarco() {
		this.barco = null;
	}

	public boolean perdio() {
		if (this.vidas <= 0) {
			this.setPuntuacionFinal();
			return true;
		} else if (this.cantNivelesJugados >= this.cantNiveles) {
			this.setPuntuacionFinal();
			return true;
		}
		return false;
	}

	private void setPuntiacion() {
		int i = 0;
		arrayNombreRanking = new String[10];
		arrayPuntosRanking = new int[10];
		while (i < 10) {
			this.arrayNombreRanking[i] = "- - - -";
			this.arrayPuntosRanking[i] = 0;
			i += 1;
		}
	}

	public void setPuntuacionFinal() {
		int i = 0;
		while (i < 10 && this.arrayPuntosRanking[i] > this.jugador.getPuntos()) {
			i += 1;
		}
		if (i < 10) {
			int j = 9;
			while (j > i) {
				this.arrayNombreRanking[j] = this.arrayNombreRanking[j-1];
				this.arrayPuntosRanking[j] = this.arrayPuntosRanking[j-1];
				j -= 1;
			}
			this.arrayNombreRanking[i] = this.jugador.getNombre();
			this.arrayPuntosRanking[i] = this.jugador.getPuntos();
		}
	}

	public String[] getArrayNombres() {
		return arrayNombreRanking;
	}

	public int[] getArrayPuntos() {
		return arrayPuntosRanking;
	}

}
