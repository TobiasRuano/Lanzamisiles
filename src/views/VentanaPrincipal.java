package views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame{
  JButton botonFacil;
  JButton botonDificil;
  JButton botonSalir;
  JButton botonPuntajes;
  JTextField textNombre;
  JLabel labelNombre;

  public VentanaPrincipal() {
    configurar();
    eventos();
  }

  private void configurar() {
    Container contenedor = this.getContentPane();
    contenedor.setLayout(null);

    this.setSize(400, 600);
    this.setResizable(false);

    botonFacil = new JButton("Facil");
    botonDificil = new JButton("Dificil");
    botonSalir = new JButton("Salir");
    botonPuntajes = new JButton("Puntajes Altos");
    labelNombre = new JLabel("Ingrese Nombre:");
    textNombre = new JTextField();

    labelNombre.setBounds(20, 20, 60, 40);
    textNombre.setBounds(60, 20, 60, 40);
    botonFacil.setBounds(150, 200, 60, 40);
    botonDificil.setBounds(250, 200, 60, 40);
    botonPuntajes.setBounds(20, 400, 60, 40);
    botonSalir.setBounds(60, 400, 60, 40);

    contenedor.add(labelNombre);
    contenedor.add(textNombre);
    contenedor.add(botonFacil);
    contenedor.add(botonDificil);
    contenedor.add(botonPuntajes);
    contenedor.add(botonSalir);

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
        VentanaJuego juego = new VentanaJuego(VentanaPrincipal.this);
	    juego.setVisible(true);
      VentanaPrincipal.this.setVisible(false);
			}
		});
    botonDificil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
        VentanaJuego juego = new VentanaJuego(VentanaPrincipal.this);
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

  }

}
