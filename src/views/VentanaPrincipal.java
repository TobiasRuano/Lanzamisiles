package views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame{
  JButton botonFacil;
  JButton botonDificil;
  JButton botonSalir;
  JButton botonPuntajes;
  JButton botonConfiguracion;
  JTextField textNombre;
  JLabel labelNombre;

  public VentanaPrincipal() {
    configurar();
    eventos();
  }

  private void configurar() {
    Container contenedor = this.getContentPane();
    contenedor.setLayout(null);

    this.setSize(600, 400);
    this.setResizable(false);

    botonFacil = new JButton("Facil");
    botonDificil = new JButton("Dificil");
    botonSalir = new JButton("Salir");
    botonPuntajes = new JButton("Puntajes Altos");
    botonConfiguracion = new JButton("Ajustes");
    labelNombre = new JLabel("Ingrese Nombre:");
    textNombre = new JTextField();

    labelNombre.setBounds(110, 40, 190, 40);
    textNombre.setBounds(300, 40, 160, 40);
    botonFacil.setBounds(146, 180, 110, 40);
    botonDificil.setBounds(346, 180, 110, 40);
    botonPuntajes.setBounds(30, 340, 110, 40);
    botonSalir.setBounds(470, 340, 110, 40);
    botonConfiguracion.setBounds(245, 340, 110, 40);

    contenedor.add(labelNombre);
    contenedor.add(textNombre);
    contenedor.add(botonFacil);
    contenedor.add(botonDificil);
    contenedor.add(botonPuntajes);
    contenedor.add(botonSalir);
    contenedor.add(botonConfiguracion);

  }

  private void eventos() {
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
    botonFacil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
      VentanaJuego juego = new VentanaJuego(VentanaPrincipal.this, textNombre.getText(), "Facil");
	    juego.setVisible(true);
      VentanaPrincipal.this.setVisible(false);
			}
		});
    botonDificil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
        VentanaJuego juego = new VentanaJuego(VentanaPrincipal.this, textNombre.getText(), "Dificil");
	      juego.setVisible(true);
        VentanaPrincipal.this.setVisible(false);
			}
		});
    botonPuntajes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
        VentanaRanking ranking = new VentanaRanking(VentanaPrincipal.this);
        ranking.setVisible(true);
        VentanaPrincipal.this.setVisible(false);
			}
		});
    botonConfiguracion.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        VentanaConfiguracion ajustes = new VentanaConfiguracion(VentanaPrincipal.this);
        ajustes.setVisible(true);
        VentanaPrincipal.this.setVisible(false);
      }
    });

  }

}
