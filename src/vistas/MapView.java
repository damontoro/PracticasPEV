package src.vistas;

import src.AlgoritmoGenetico;
import src.individuo.Individuo;
import src.patrones.AGobserver;
import src.problema.ProblemaTSP;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;


public class MapView extends JPanel implements AGobserver{
	
	private SpainMap imagen;
	private Individuo mejorAbs;
	private JPanel ciudadesPanel;
	private JPanel mapaPanel;

	public MapView(AlgoritmoGenetico ag) {

		ag.addObserver(this);
		imagen = new SpainMap();
		mapaPanel = new JPanel();
		ciudadesPanel = new JPanel();

		this.setLayout(new FlowLayout());
		this.setMaximumSize(new Dimension(600, 600));
		this.setPreferredSize(new Dimension(600, 600));
		this.setMinimumSize(new Dimension(600, 600));
		this.setSize(new Dimension(600, 600));
		this.setVisible(true);

		ciudadesPanel.setMaximumSize(new Dimension(700, 150));
		ciudadesPanel.setPreferredSize(new Dimension(700, 150));
		ciudadesPanel.setMinimumSize(new Dimension(700, 150));
		ciudadesPanel.setSize(new Dimension(700, 150));

		mapaPanel.add(imagen);
		this.add(mapaPanel);
		this.add(ciudadesPanel);
	}
	
	public void setCiudades(){

		if(mejorAbs == null)return;

		ciudadesPanel.removeAll();
		ciudadesPanel.setLayout(new FlowLayout());
		ciudadesPanel.add(new JLabel(ProblemaTSP.Ciudad.MADRID.toString() + " -> "));
		for(int i = 0; i < mejorAbs.getFenotipo().size(); i++){
			JLabel ciudad = new JLabel();
			ciudad.setText(ProblemaTSP.Ciudad.values()[(int)mejorAbs.getFenotipo().get(i)].toString() + " -> ");
			ciudadesPanel.add(ciudad);
		}
		ciudadesPanel.add(new JLabel(ProblemaTSP.Ciudad.MADRID.toString()));
		ciudadesPanel.repaint();
		ciudadesPanel.revalidate();
	}

	@Override
	public void onInit(AlgoritmoGenetico ag) {
		mejorAbs = null;
	}

	@Override
	public void onChange(AlgoritmoGenetico ag) {
		if(mejorAbs == null || (ag.getProblema().compare(ag.getMejorAbs(), mejorAbs) < 0)){
			mejorAbs = ag.getMejorAbs();
			imagen.setFenotipo(mejorAbs.getFenotipo());
		}
	}

	@Override
	public void onError(String err) {}

	@Override
	public void onEnd(AlgoritmoGenetico ag) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setCiudades();
				imagen.repaint();
			}
		});
	}



}
