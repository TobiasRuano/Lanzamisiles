package views;

import models.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controlador;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaRanking extends JFrame{

  Controlador controlador;
  JFrame aux;
  JButton botonSalir;
  JLabel[] labelNombre = new JLabel[10];
  JLabel[] labelPuntos = new JLabel[10];


  public VentanaRanking(JFrame principal) {
    this.aux = principal;
    configurar();
    eventos();
  }

  private void configurar() {
    Container contenedor = this.getContentPane();
    contenedor.setLayout(null);
    controlador = Controlador.miInstancia("sin nombre", "sin dificuldad");

    this.setSize(600, 400);
    this.setResizable(false);

    botonSalir = new JButton("Atras");
    int a = 0;
    while (a < 10) {
      labelNombre[a] = new JLabel("-");
      labelPuntos[a] = new JLabel("0");
      a += 1;
    }

    a = 0;
    int suma = 80;
    while (a < 10) {
      labelNombre[a].setBounds(160, suma, 45, 25);
      suma += 30;
      a += 1;
    }

    a = 0;
    suma = 80;
    while (a < 10) {
      labelPuntos[a].setBounds(370, suma, 45, 25);
      suma += 30;
      a += 1;
    }
    botonSalir.setBounds(535, 25, 60, 25);

    contenedor.add(botonSalir);
    a = 0;
    while (a < 10) {
      contenedor.add(labelNombre[a]);
      a += 1;
    }
    a = 0;
    while (a < 10) {
      contenedor.add(labelPuntos[a]);
      a += 1;
    }

    configurarRanking();
  }

  private void configurarRanking() {
	  String[] nombres = new String[10];
	  int[] puntos = new int[10];
	  nombres = controlador.getArrayNombres();
	  puntos = controlador.getArrayPuntos();
	  int i = 0;
	  while (i < 10 && nombres[i] != null) {
		  this.labelNombre[i].setText(nombres[i]);
		  String texto = Integer.toString(puntos[i]);
		  this.labelPuntos[i].setText(texto);
		  i += 1;
	  }
  }

  private void eventos() {
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aux.setVisible(true);
        VentanaRanking.this.setVisible(false);
			}
		});
  }
}
