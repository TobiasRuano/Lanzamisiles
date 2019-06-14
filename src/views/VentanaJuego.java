package views;

import models.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.*;
import controller.Controlador;

public class VentanaJuego extends JFrame {
 	VentanaPrincipal aux;
	JButton botonSalir;
	JLabel labelPuntaje;
	JLabel labelVidas;
	JLabel labelNivel;
	JLabel tanque;
	JLabel misil;
	JLabel barco;

	public VentanaJuego(JFrame principal, String nombre, String dificultad) {
		this.aux = (VentanaPrincipal) principal;
		configurar(nombre);
		eventos();
	}

	private void configurar(String nombre) {
		Container contenedor = this.getContentPane();
    contenedor.setLayout(null);

    this.setSize(800, 600);
    this.setResizable(false);

		botonSalir = new JButton("Salir");
		labelPuntaje = new JLabel("Puntaje: 0");
		labelVidas = new JLabel("Vidas: 3");
		labelNivel = new JLabel("Nivel: 0");
		tanque = new JLabel();
		barco = new JLabel();
		misil = new JLabel();

		botonSalir.setBounds(740, 0, 60, 40);
		labelNivel.setBounds(25, 25, 75, 25);
		labelVidas.setBounds(130, 25, 75, 25);
		labelPuntaje.setBounds(235, 25, 75, 25);
		tanque.setBounds(270, 475, 65, 125);

		contenedor.add(botonSalir);
		contenedor.add(labelNivel);
		contenedor.add(labelVidas);
		contenedor.add(labelPuntaje);
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
