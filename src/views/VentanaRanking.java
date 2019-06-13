package views;

import models.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaRanking extends JFrame{

  JFrame aux;
  JButton botonSalir;
  JLabel[] labelPuntaje;
  JLabel[] labelNombre;


  public VentanaRanking(JFrame principal) {
    this.aux = principal;
    configurar();
    eventos();
  }

  private void configurar() {
    Container contenedor = this.getContentPane();
    contenedor.setLayout(null);

    this.setSize(800, 600);
    this.setResizable(false);

    botonSalir = new JButton("Salir");
    botonSalir.setBounds(740, 0, 60, 40);

    contenedor.add(botonSalir);

  }

  private void eventos() {
    botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aux.setVisible(true);
        VentanaRanking.this.setVisible(false);
			}
		});
  }
}
