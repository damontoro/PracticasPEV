package src.vistas;

import src.AlgoritmoGenetico;
import src.individuo.Individuo;
import src.patrones.AGobserver;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.FlowLayout;


public class MapView extends JPanel implements AGobserver{
	
	private SpainMap imagen;
	private Individuo mejorAbs;
	private JPanel mapaPanel;

	public MapView(AlgoritmoGenetico ag) {

		ag.addObserver(this);
		imagen = new SpainMap();
		mapaPanel = new JPanel();

		this.setLayout(new FlowLayout());
		this.setMaximumSize(new Dimension(600, 600));
		this.setPreferredSize(new Dimension(600, 600));
		this.setMinimumSize(new Dimension(600, 600));
		this.setSize(new Dimension(600, 600));
		this.setVisible(true);

		mapaPanel.add(imagen);
		this.add(mapaPanel);
	}

	@Override
	public void onInit(AlgoritmoGenetico ag) {
		mejorAbs = null;
	}

	@Override
	public void onChange(AlgoritmoGenetico ag) {
		if(mejorAbs == null || (ag.getProblema().compare(ag.getMejorAbs(), mejorAbs) < 0)){
			mejorAbs = ag.getMejorAbs();
		}
	}

	@Override
	public void onError(String err) {}

	@Override
	public void onEnd(AlgoritmoGenetico ag) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				imagen.setFenotipo(mejorAbs.getGenotipo());
				imagen.repaint();
			}
		});
	}



}
