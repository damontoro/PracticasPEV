package src.vistas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.AlgoritmoGenetico;


public class VistaPrincipal extends JFrame{
	
	final JLabel valido = new JLabel();
	final VistaGrafica grafica = new VistaGrafica();
	final JButton boton = new JButton("Prueba");

	public VistaPrincipal(AlgoritmoGenetico ag){
		ag.setVista(this);

		setTitle("Algoritmo Genético");
		setSize(1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		setLayout(new BorderLayout());

		add(new VistaOpciones(ag, valido), BorderLayout.WEST);
		add(grafica, BorderLayout.CENTER);
		add(valido, BorderLayout.SOUTH);

		boton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				prueba();
			}
		});
		add(boton, BorderLayout.EAST);
	}

	public void prueba(){
		for(int i = 0; i < 10; i++){
			try{
				grafica.reload();
			}catch(Exception e){}
			this.repaint();
			this.revalidate();
		}
	}
}
