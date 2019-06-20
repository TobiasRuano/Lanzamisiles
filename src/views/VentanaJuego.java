package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import models.*;
import controller.Controlador;

public class VentanaJuego extends JFrame implements KeyListener {

	private VentanaPrincipal aux;
	private JButton botonSalir;
	private JLabel labelPuntaje = new JLabel("Puntaje: 0");
	private JLabel labelVidas = new JLabel("Vidas: 3");
	private JLabel labelNivel = new JLabel("Nivel: 1");
	private JLabel tanque = new JLabel();
	private JLabel misil = new JLabel();
	private JLabel barco = new JLabel();
	private Container contenedor;
	private Controlador controlador;
	private Timer movimientoTimer;
	private Timer fueraVistaTimer;
	private Timer labelsTimer;
	private Timer cargaMisilTimer;
	private Timer impactoTimer;
	private Point destino;

	public VentanaJuego(JFrame principal, String nombre, String dificultad) {
		this.controlador = new Controlador(dificultad, nombre);
		configurar(nombre);
		presentarTanque(this.controlador.crearTanque(nombre));

		this.aux = (VentanaPrincipal) principal;
		eventos(contenedor);
	}

	private void configurar(String nombre) {
		contenedor = this.getContentPane();
		contenedor.setLayout(null);
		contenedor.setBackground(Color.white);

		this.setSize(800, 600);
		this.setResizable(false);
		this.setTitle("Lanzamisiles");
		addKeyListener(this);


		botonSalir = new JButton("Salir");

		destino = new Point(400, 600);

		botonSalir.setBounds(715, 25, 60, 40);
		labelNivel.setBounds(25, 25, 75, 25);
		labelVidas.setBounds(130, 25, 75, 25);
		labelPuntaje.setBounds(235, 25, 75, 25);
		tanque.setBounds(400, 500, 100, 100);
		barco.setBounds(400, 75, 100, 100);

		labelNivel.setText("Nivel: " + (controlador.getNivel() + 1));
		labelVidas.setText("Vidas: " + controlador.getVidas());
		labelPuntaje.setText("Puntos: " + controlador.getPuntos());

		barco.setVisible(true);
		
		//contenedor.add(botonSalir); //TODO: arranca seleccionado, no puedo disparar.
		//this.getRootPane().setDefaultButton(null);
		
		contenedor.add(labelNivel);
		contenedor.add(labelVidas);
		contenedor.add(labelPuntaje);
		contenedor.add(tanque);
		contenedor.add(barco);
	}

	private void eventos(Container contenedor) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aux.setVisible(true);
				VentanaJuego.this.setVisible(false);
			}
		});

		crearTempMovimiento();
		crearTempImpacto();
		crearTempLabels();
		crearTempFueraVista();
		crearTempRecarga();
	}

	private void crearTempRecarga() {
		ActionListener misilL = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.puedeDisparar(true);
			}
		};
		this.cargaMisilTimer = new Timer(1000, misilL); // ese 1000 es el tiempo de carga del misil 1s
		this.cargaMisilTimer.start();
	}

	private void crearTempFueraVista() {
		ActionListener vistaL = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcularMisilFueraVista();
				calcularBarcoFueraVista();
			}
		};
		this.fueraVistaTimer = new Timer(100, vistaL);
		this.fueraVistaTimer.start();
	}

	private void crearTempLabels() {
		ActionListener labelsL = new ActionListener() {
			public void actionPerformed(ActionEvent l) {
				actualizarVidas();
				actualizarPuntaje();
			}
		};
		this.labelsTimer = new Timer(100, labelsL);
		this.labelsTimer.start();
	}

	private void crearTempImpacto() {
		ActionListener impactoL = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controlador.estaJugando() == true) {
					if (controlador.jugando() != true) {
						finDelNivel();
					}
					verificarImpacto();
				}
			}
		};
		this.impactoTimer = new Timer(100, impactoL);
		this.impactoTimer.start();
	}

	private void crearTempMovimiento() {
		ActionListener movimientoL = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moverBarco();
				moverMisil();
			}
		};
		this.movimientoTimer = new Timer(10, movimientoL);
		this.movimientoTimer.start();
	}

	private void moverBarco() {
		Barco barco = controlador.moverBarco(1);
		presentarBarco(barco);
	}

	private void moverMisil() {
		Misil misil = controlador.moverMisil();
		presentarMisil(misil);
	}

	private void presentarTanque(Tanque tanque) {
		if (tanque != null) {
			String urlImagen = tanque.getImagePath();
			Rectangle bounds = tanque.getPosicion();
			if (this.tanque == null ){
				this.tanque = new JLabel();
			}
			this.tanque.setBounds(bounds);
			this.tanque.setText(tanque.getNombre());
			this.tanque.setIcon(new ImageIcon(urlImagen));
			this.tanque.setVisible(true);
			contenedor.add(this.tanque);
		}
	}

	private void presentarBarco(Barco barco) {
		if (barco != null) {
			String urlImagen = barco.getImagePath();
			Rectangle bounds = barco.getPosicion();
			if (this.barco == null ){
				this.barco = new JLabel();
			}
			this.barco.setBounds(bounds.x, bounds.y, 200,74);
			this.barco.setIcon(new ImageIcon(urlImagen));
			this.barco.setVisible(true);
			contenedor.add(this.barco);
		}
	}

	private void presentarMisil(Misil misil) {
		if (misil != null) {
			String urlImagen = misil.getImagePath();
			Rectangle bounds = misil.getPosicion();
			if (this.misil == null) {
				this.misil = new JLabel();
			}
			this.misil.setBounds(bounds);
			this.misil.setIcon(new ImageIcon(urlImagen));
			this.misil.setVisible(true);
			contenedor.add(this.misil);
		}
	}

	private void calcularMisilFueraVista() {
		Rectangle posicion = controlador.getPosicionMisil();
		if (this.misil != null && posicion != null) {
			if (fueraDeVista(posicion) == true) {
				this.controlador.eliminarMisil();
			}
		}
	}

	private void calcularBarcoFueraVista() {
		Rectangle posicion = controlador.posicionBarco();
		boolean direccion = controlador.sentidoBarco();
		if (this.barco != null && posicion != null) {
			if (this.barcoFueraDeVista(direccion, posicion)) {
				this.controlador.eliminarBarco();
			}
		}
	}

	private boolean fueraDeVista(Rectangle posicion) {
		int arriba = (int) posicion.getMinY();
		int abajo = (int) posicion.getMaxY();
		int izq = (int) posicion.getMinX();
		int der = (int) posicion.getMaxX();
		if (izq > 800 || der < 0 || arriba > 600 || abajo < 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean barcoFueraDeVista(boolean direccion, Rectangle posicion) {
		int izq = (int) posicion.getMinX();
		int der = (int) posicion.getMaxX();
		if (direccion == true) {
			return izq > 800;
		} else {
			return der < 0;
		}
	}

	private void verificarImpacto() {
		if (controlador.verificarImpacto() == true) {
			this.misil.setVisible(false);
			this.barco.setVisible(false);
			this.misil = null;
			this.barco = null;
		}
	}

	private void actualizarPuntaje() {
		int puntos = controlador.getPuntos();
		labelPuntaje.setText("Puntos: " + puntos);
	}

	private void actualizarVidas() {
		int vidas = controlador.getVidas();
		labelVidas.setText("Vidas: " + vidas);
	}

	private void finDelNivel() {
		if (controlador.avanzaNivel() == true) {
			JOptionPane.showMessageDialog(contenedor, "Nivel " + controlador.getNivel() + " Completado!");
		} else {
			if (controlador.perdio() == true) {
				JOptionPane.showMessageDialog(contenedor, "Fin del Juego! Puntuacion: " + controlador.getTotalPuntos());
				this.setVisible(false);
				VentanaPrincipal principal = new VentanaPrincipal();
				principal.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(contenedor, "Nivel " + controlador.getNivel() + " no Completado");
			}
		}
	}

	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			this.destino.x = this.destino.x - 20;
			System.out.println(this.destino.x);
		}

		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.destino.x = this.destino.x + 20;
			System.out.println(this.destino.x);
		}

		if (arg0.getKeyCode() == KeyEvent.VK_SPACE ) {
			System.out.println("Potencia de disparo");
			//TODO: agregar timer para la potancia del disparo
		}
	}

	public void keyReleased(KeyEvent a) {
		Point fin = new Point(this.destino.x, 0);
		if (a.getKeyCode() == KeyEvent.VK_SPACE ) {
			boolean disparo = controlador.dispararBarco(fin);
			System.out.println("Se ha disparado");
			if (disparo) {
				cargaMisilTimer.restart();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
