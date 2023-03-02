package src.vistas;

import java.util.ArrayList;
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
	final JButton boton = new JButton("Run");

	private volatile Thread hilo = null;

	ArrayList<ArrayList<Double>> tablaX = new ArrayList<ArrayList<Double>>();
	ArrayList<ArrayList<Integer>> tablaY = new ArrayList<ArrayList<Integer>>();
	int i = 0;

	public VistaPrincipal(AlgoritmoGenetico ag){

		setTitle("Algoritmo Genético");
		setSize(1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		setLayout(new BorderLayout());

		add(new VistaOpciones(ag, valido), BorderLayout.WEST);
		add(grafica, BorderLayout.CENTER);
		add(valido, BorderLayout.SOUTH);
		ag.setVista(this);

		boton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(hilo != null && hilo.isAlive()){
					hilo.interrupt();
					while(hilo.isAlive()){}
				}
				ag.reset();
				grafica.reset();
				hilo = new Thread (() -> ag.run());
				hilo.start();
			}
		});
		add(boton, BorderLayout.EAST);
	}

	public void actualizarGrafica(Double mejorFit, Double mediaFit, Double presion, Integer i){
		grafica.reload(mejorFit, mediaFit, presion, i);
		this.repaint();
		this.revalidate();
	}

	public void loadTablas(ArrayList<ArrayList<Integer>> x, ArrayList<ArrayList<Double>> y){
		tablaX = y;
		tablaY = x;
	}

}
