package src.vistas;

import src.AlgoritmoGenetico;
import src.individuo.Individuo;
import src.patrones.AGobserver;


import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.FlowLayout;


public class MapView extends JPanel implements AGobserver{
	
	SpainMap imagen;
	Individuo mejorAbs;

	public MapView(AlgoritmoGenetico ag) {

		ag.addObserver(this);
		imagen = new SpainMap();
		this.setMaximumSize(new Dimension(592, 539));
		
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.add(imagen);

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
				imagen.repaint();
			}
		});
	}



}
