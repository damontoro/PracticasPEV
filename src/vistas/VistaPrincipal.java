package src.vistas;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import src.AlgoritmoGenetico;


public class VistaPrincipal extends JFrame{
	
	final JLabel valido = new JLabel();
	final JLabel mejor = new JLabel();

	final VistaGrafica grafica = new VistaGrafica();
	final JButton boton = new JButton("Run");

	final JPanel opcionesPanel = new JPanel();
	final JPanel graficaPanel = new JPanel();

	private volatile Thread hilo = null;

	public VistaPrincipal(AlgoritmoGenetico ag){

		setTitle("Algoritmo Genético");
		setSize(1400, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		setLayout(new BorderLayout());

		opcionesPanel.setLayout(new BorderLayout());
		opcionesPanel.add(new VistaOpciones(ag, valido), BorderLayout.WEST);
		opcionesPanel.add(valido, BorderLayout.SOUTH);

		graficaPanel.setLayout(new BorderLayout());
		graficaPanel.add(grafica, BorderLayout.CENTER);

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
		graficaPanel.add(boton, BorderLayout.SOUTH);

		add(opcionesPanel, BorderLayout.WEST);
		add(graficaPanel, BorderLayout.CENTER);
		add(mejor, BorderLayout.SOUTH);
	}

	public void actualizarGrafica(ArrayList<Double> mejorFeno, Double mejorAbs, Double mejorGen, Double mediaFit,
								double precision, Integer i){

		String preci = "#.";
		for(double j = precision; j < 1; j *= 10)
			preci += "#";

		grafica.reload(mejorGen, mejorAbs, mediaFit, i);
		String mejorFenotipo = "[";
		DecimalFormat df = new DecimalFormat(preci);
		for(int j = 0; j < mejorFeno.size(); j++){
			mejorFenotipo += df.format(mejorFeno.get(j)) + 
			((j < mejorFeno.size() - 1) ? ", " : "");
		}
		mejorFenotipo += "]";
		mejor.setText("Mejor fenotipo : " + mejorFenotipo + 
						" Fitness: " + df.format(mejorAbs));
		this.repaint();
		this.revalidate();
	}

}
