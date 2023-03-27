package src.vistas;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.math.plot.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import src.AlgoritmoGenetico;
import src.patrones.AGobserver;

public class UglyChart extends JPanel implements AGobserver{


	private Plot2DPanel plot;

	private ArrayList<Double> mejorFitness = new ArrayList<Double>();
	private ArrayList<Double> mediaFitness = new ArrayList<Double>();
	private ArrayList<Double> mejorAbsoluto = new ArrayList<Double>();


	public UglyChart(AlgoritmoGenetico ag){

		ag.addObserver(this);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		plot = new Plot2DPanel();
		
		plot.setAxisLabels("  Generacion", "Valor de la función");

		plot.addLegend("SOUTH");
		
		this.add(plot, BorderLayout.CENTER);
		setVisible(true);
	}

	@Override
	public void onInit(AlgoritmoGenetico ag) {
		plot.removeAllPlots();

		mejorFitness.clear();
		mediaFitness.clear();
		mejorAbsoluto.clear();
	}

	@Override
	public void onChange(AlgoritmoGenetico ag) {
		mejorFitness.add(ag.getMejorGen());
		mediaFitness.add(ag.getMediaFitness());
		mejorAbsoluto.add(ag.getMejorAbs().getFitness());
	}

	@Override
	public void onError(String err) {
	}

	@Override
	public void onEnd(AlgoritmoGenetico ag) {

		double [] mejorFitness = new double [this.mejorFitness.size()];
		double [] mediaFitness = new double [this.mediaFitness.size()];
		double [] mejorAbsoluto = new double [this.mejorAbsoluto.size()];
		double [] yData = new double [this.mejorFitness.size()];

		for(int i = 0; i < mejorFitness.length; i++){
			mejorFitness[i] = this.mejorFitness.get(i);
			mediaFitness[i] = this.mediaFitness.get(i);
			mejorAbsoluto[i] = this.mejorAbsoluto.get(i);
			yData[i] = i + 1;
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				plot.removeAllPlots();
				plot.addLinePlot("Mejor Individuo", yData, mejorFitness);
				plot.addLinePlot("Media Fitness", yData, mediaFitness);
				plot.addLinePlot("Mejor Absoluto", yData, mejorAbsoluto);
			}
		});
	}

}
