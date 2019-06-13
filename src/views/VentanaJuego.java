package views;

import models.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaJuego extends JFrame {
	Tanque tanque;
	Barco barco;
	Misil misil;
 	VentanaPrincipal aux;
	JButton botonSalir;
	JLabel labelPuntaje;
	JLabel labelVidas;
	JLabel labelNivel;

	public VentanaJuego(JFrame principal) {
		this.aux = (VentanaPrincipal) principal;
		configurar();
		eventos();
	}

	private void configurar() {
		Container contenedor = this.getContentPane();
    contenedor.setLayout(null);

    this.setSize(800, 600);
    this.setResizable(false);

		botonSalir = new JButton("Salir");
		labelPuntaje = new JLabel("Puntaje: 0");
		labelVidas = new JLabel("Vidas: 3");
		labelNivel = new JLabel("Nivel: 0");

		botonSalir.setBounds(740, 0, 60, 40);


		contenedor.add(botonSalir);
	}

	private void eventos() {
		botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aux.setVisible(true);
				System.exit(0);
			}
		});

	}
}
