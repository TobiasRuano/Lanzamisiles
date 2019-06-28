package views;

import models.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Controlador;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaConfiguracion extends JFrame {
	JFrame aux;
	JButton botonSalir;
  JButton botonDisparo1;
  JButton botonDisparo15;
  JButton botonDisparo20;
  JButton botonSenBaja;
  JButton botonSenMedia;
  JButton botonSenAlta;
  JButton botonSetNivel;
  JTextField cantNiveles;
  JLabel labelIntervaloDisparo;
  JLabel labelNumeroNiveles;
  JLabel labelSensibilidad;

	Container contenedor;
  Controlador controlador;


	public VentanaConfiguracion(JFrame principal) {
		this.aux = principal;
		configurar();
		eventos();
	}

	private void configurar() {
		Container contenedor = this.getContentPane();
		contenedor.setLayout(null);
    this.controlador = Controlador.miInstancia("sin dificuldad", "sin nombre");

		this.setSize(600, 400);
		this.setResizable(false);

		botonSalir = new JButton("Atras");
    botonDisparo1 = new JButton("1 segundo");
    botonDisparo15 = new JButton("1,5 segundos");
    botonDisparo20 = new JButton("2 segundos");
    botonSenAlta = new JButton("Alta");
    botonSenBaja = new JButton("Baja");
    botonSenMedia = new JButton("Media");
    botonSetNivel = new JButton("Setear");
    cantNiveles = new JTextField("5");
    labelIntervaloDisparo = new JLabel("Intervalo del disparo: ");
    labelNumeroNiveles = new JLabel("Numero de niveles: ");
    labelSensibilidad = new JLabel("Sensibilidad: ");

		botonSalir.setBounds(535, 25, 60, 40);
    botonSenBaja.setBounds(280, 95, 80, 30);
    botonSenMedia.setBounds(380, 95, 80, 30);
    botonSenAlta.setBounds(480, 95, 80, 30);
    botonDisparo1.setBounds(280, 155, 80, 30);
    botonDisparo15.setBounds(380, 155, 80, 30);
    botonDisparo20.setBounds(480, 155, 80, 30);
    botonSetNivel.setBounds(400, 280, 80, 30);

    cantNiveles.setBounds(280, 225, 150, 30);

    labelSensibilidad.setBounds(12, 95, 220, 25);
    labelIntervaloDisparo.setBounds(12, 155, 220, 25);
    labelNumeroNiveles.setBounds(12, 225, 220, 25);

		contenedor.add(botonSalir);
    contenedor.add(botonDisparo1);
    contenedor.add(botonDisparo15);
    contenedor.add(botonDisparo20);
    contenedor.add(botonSenAlta);
    contenedor.add(botonSenBaja);
    contenedor.add(botonSenMedia);
    contenedor.add(botonSetNivel);
    contenedor.add(cantNiveles);
    contenedor.add(labelIntervaloDisparo);
    contenedor.add(labelNumeroNiveles);
    contenedor.add(labelSensibilidad);
	}

	private void eventos() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aux.setVisible(true);
				VentanaConfiguracion.this.setVisible(false);
			}
		});
    botonDisparo1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controlador.setIntervaloDisparos(1);
      }
    });
    botonDisparo15.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controlador.setIntervaloDisparos(1.5);
      }
    });
    botonDisparo20.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controlador.setIntervaloDisparos(2);
      }
    });
    botonSenBaja.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controlador.setSensibilidadMira(30);
      }
    });
    botonSenMedia.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controlador.setSensibilidadMira(60);
      }
    });
    botonSenAlta.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controlador.setSensibilidadMira(90);
      }
    });
    botonSetNivel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String text = cantNiveles.getText();
        int cant = Integer.parseInt(text);
        System.out.println("El usuario ingreso: " + cant);
        if (cant < 5) {
          cantNiveles.setText("5");
          cant = 5;
        }
        controlador.setCantNiveles(cant);
      }
    });
	}
}
