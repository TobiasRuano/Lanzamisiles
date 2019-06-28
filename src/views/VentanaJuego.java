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
	private JLabel labelPuntajeAProxVida = new JLabel("Nueva Vida en : 300 pts");
	private JLabel labelPuntajeTotal = new JLabel("Puntaje Total: 0");
	private JLabel labelVidas = new JLabel("Vidas: 3");
	private JLabel labelNivel = new JLabel("Nivel: 1");
	private JLabel labelBarcosHundidos = new JLabel();
	private JLabel labelBarcosRestantes = new JLabel();
	private JLabel labelPotencia = new JLabel();

	private double multiplicadorPotencia = 1;

	private JLabel tanque = new JLabel();
	private JLabel misil = new JLabel();
	private JLabel barco = new JLabel();
	private JLabel mira = new JLabel();
	private Container contenedor;
	private Controlador controlador;
	private Timer movimientoTimer;
	private Timer fueraVentanaTimer;
	private Timer labelsTimer;
	private Timer cargaMisilTimer;
	private Timer impactoTimer;
	private Point destino;

	private String urlImagenMira = "/Users/tobiasruano/Developer/Lanzamisiles/src/imagenes/target.png";

	public VentanaJuego(JFrame principal, String nombre, String dificultad) {
		configurar(dificultad, nombre);
		this.aux = (VentanaPrincipal) principal;
		eventos(contenedor);
	}

	private void configurar(String dificultad, String nombre) {
		this.controlador = Controlador.miInstancia(dificultad, nombre);

		this.contenedor = this.getContentPane();
		this.contenedor.setLayout(null);
		this.contenedor.setBackground(Color.white);

		this.setSize(1000, 700);
		this.setResizable(false);
		this.setTitle("Lanzamisiles");
		addKeyListener(this);

		botonSalir = new JButton("Salir");

		destino = new Point(500, 600);

		labelNivel.setBounds(20, 25, 75, 25);
		labelVidas.setBounds(115, 25, 75, 25);
		labelPuntajeAProxVida.setBounds(220, 25, 150, 25);
		labelPuntajeTotal.setBounds(390, 25, 150, 25);
		labelPotencia.setBounds(830, 655, 150, 25);
		labelBarcosHundidos.setBounds(580, 25, 150, 25);
		labelBarcosRestantes.setBounds(750, 25, 150, 25);
		botonSalir.setBounds(920, 25, 60, 25);

		tanque.setBounds(500, 600, 100, 100);
		barco.setBounds(500, 75, 100, 100);
		mira.setBounds(500, 50, 50, 50);

		mira.setIcon(new ImageIcon(urlImagenMira));

		labelNivel.setText("Nivel: " + controlador.getNivel());
		labelVidas.setText("Vidas: " + controlador.getVidas());
		labelPuntajeAProxVida.setText("Nueva Vida en: " + (300 - controlador.getPuntos()) + "pts");
		labelBarcosHundidos.setText("Barcos Hundidos: " + controlador.getCantBarcosEliminados());
		labelPuntajeTotal.setText("Puntaje Total: " + controlador.getTotalPuntos());
		labelBarcosRestantes.setText("Barcos Restantes: " + ( 10 - controlador.getCantBarcos()));
		labelPotencia.setText("Potencia: " + this.multiplicadorPotencia + "/1,5");

		barco.setVisible(true);

		botonSalir.setFocusable(false);

		contenedor.add(labelNivel);
		contenedor.add(labelVidas);
		contenedor.add(labelPuntajeAProxVida);
		contenedor.add(labelPuntajeTotal);
		contenedor.add(labelBarcosHundidos);
		contenedor.add(labelBarcosRestantes);
		contenedor.add(labelPotencia);
		contenedor.add(tanque);
		contenedor.add(barco);
		contenedor.add(mira);
		contenedor.add(botonSalir);

		presentarTanque(this.controlador.crearTanque(nombre));
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
		int intervalo = (int) (this.controlador.getIntervaloDisparos() * 1000.0);
		this.cargaMisilTimer = new Timer(intervalo, misilL); // ese 1000 es el tiempo de carga del misil 1s
		this.cargaMisilTimer.start();
	}

	private void crearTempFueraVista() {
		ActionListener vistaL = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcularMisilFueraVentana();
				calcularBarcoFueraVentana();
			}
		};
		this.fueraVentanaTimer = new Timer(100, vistaL);
		this.fueraVentanaTimer.start();
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

	private void calcularMisilFueraVentana() {
		Rectangle posicion = controlador.getPosicionMisil();
		if (this.misil != null && posicion != null) {
			if (fueraDeVentana(posicion) == true) {
				this.controlador.eliminarMisil();
			}
		}
	}

	private void calcularBarcoFueraVentana() {
		Rectangle posicion = controlador.posicionBarco();
		boolean direccion = controlador.sentidoBarco();
		if (this.barco != null && posicion != null) {
			if (this.barcoFueraDeVentana(direccion, posicion)) {
				this.controlador.eliminarBarco();
			}
		}
	}

	private boolean fueraDeVentana(Rectangle posicion) {
		int arriba = (int) posicion.getMinY();
		int abajo = (int) posicion.getMaxY();
		int izq = (int) posicion.getMinX();
		int der = (int) posicion.getMaxX();
		if (izq > 1000 || der < 0 || arriba > 700 || abajo < 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean barcoFueraDeVentana(boolean direccion, Rectangle posicion) {
		int izq = (int) posicion.getMinX();
		int der = (int) posicion.getMaxX();
		if (direccion == true) {
			return izq > 1000;
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
		actualizarBarcos();
	}

	private void actualizarBarcos() {
		labelBarcosHundidos.setText("Barcos Hundidos: " + controlador.getCantBarcosEliminados());
		labelBarcosRestantes.setText("Barcos Restantes: " + ( 10 - controlador.getCantBarcos()));
	}

	private void actualizarPuntaje() {
		int puntos = controlador.getPuntos();
		labelPuntajeAProxVida.setText("Nueva Vida en: " + (300 - puntos) + "pts");
		labelPuntajeTotal.setText("Puntaje Total: " + controlador.getTotalPuntos());
	}

	private void actualizarVidas() {
		int vidas = controlador.getVidas();
		labelVidas.setText("Vidas: " + vidas);
	}

	private void finDelNivel() {
		if (controlador.avanzaNivel() == true) {
			JOptionPane.showMessageDialog(this.contenedor, "Nivel " + (controlador.getNivel() - 1) + " Completado!");
		} else {
			if (controlador.perdio() == true) {
				JOptionPane.showMessageDialog(this.contenedor, "Fin del Juego! Puntuacion: " + controlador.getTotalPuntos());
				this.setVisible(false);
				VentanaPrincipal principal = new VentanaPrincipal();
				principal.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this.contenedor, "Nivel " + controlador.getNivel() + " no Completado");
			}
		}
		labelNivel.setText("Nivel: " + controlador.getNivel());
	}

	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		String strDouble = "";
		switch(keyCode) {
			case KeyEvent.VK_UP:
				this.multiplicadorPotencia = this.multiplicadorPotencia + 0.1;
				if (this.multiplicadorPotencia > 1.5) {
					this.multiplicadorPotencia = 1.5;
				}
				strDouble = String.format("%.1f", this.multiplicadorPotencia);
				labelPotencia.setText("Potencia: " + strDouble + "/1,5");
				break;

			case KeyEvent.VK_DOWN:
				this.multiplicadorPotencia = this.multiplicadorPotencia - 0.1;
				if (this.multiplicadorPotencia < 0.5) {
					this.multiplicadorPotencia = 0.5;
				}
				strDouble = String.format("%.1f", this.multiplicadorPotencia);
				labelPotencia.setText("Potencia: " + strDouble + "/1,5");
				break;

			case KeyEvent.VK_LEFT:
				this.destino.x = this.destino.x - controlador.getSensibilidadMira();
				if (this.destino.x < 0) {
					this.destino.x = 0;
				}
				this.mira.setBounds(this.destino.x, 50, 50, 50);
				System.out.println(this.destino.x);
				break;

			case KeyEvent.VK_RIGHT :
				this.destino.x = this.destino.x + controlador.getSensibilidadMira();
				if (this.destino.x > 960) {
					this.destino.x = 960;
				}
				this.mira.setBounds(this.destino.x, 50, 50, 50);
				System.out.println(this.destino.x);
				break;

			case KeyEvent.VK_SPACE :
				Point fin = new Point(this.destino.x, 0);
				boolean disparo = controlador.dispararBarco(fin, this.multiplicadorPotencia);
				System.out.println("Se ha disparado, velocidad: " + this.multiplicadorPotencia);
				if (disparo) {
					cargaMisilTimer.restart();
				}
				break;
		}
	}

	public void keyReleased(KeyEvent a) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
