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


  public VentanaRanking(JFrame principal) {
    this.aux = principal;
    configurar();
    eventos();
  }

  private void configurar() {
    Container contenedor = this.getContentPane();
    contenedor.setLayout(null);

    this.setSize(600, 400);
    this.setResizable(false);

    botonSalir = new JButton("Atras");
    botonSalir.setBounds(535, 25, 60, 25);

    contenedor.add(botonSalir);

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
