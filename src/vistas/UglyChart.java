package src.vistas;

import javax.swing.JPanel;

import org.math.plot.*;
import java.util.ArrayList;

import src.AlgoritmoGenetico;
import src.patrones.AGobserver;

public class UglyChart extends JPanel implements AGobserver{


	private Plot2DPanel plot;
	private ArrayList<Double> mejorFitness = new ArrayList<Double>();
	private ArrayList<Double> mediaFitness = new ArrayList<Double>();
	private ArrayList<Double> mejorAbsoluto = new ArrayList<Double>();

	ArrayList<Integer> yData = new ArrayList<Integer>();


	public UglyChart(){

		plot = new Plot2DPanel();

		// create your PlotPanel (you can use it as a JPanel)

		// define the legend position
		plot.addLegend("SOUTH");

		// put the PlotPanel in a JFrame like a JPanel
		
		this.setSize(900, 900);
		this.add(plot);
		this.setVisible(true);
	}

	@Override
	public void onInit(AlgoritmoGenetico ag) {
		plot.removeAll();

		mejorFitness.clear();
		mediaFitness.clear();
		mejorAbsoluto.clear();
		yData.clear();

		mejorFitness.add(ag.getMejorGen());
		mediaFitness.add(ag.getMediaFitness());
		mejorAbsoluto.add(ag.getMejorAbs().getFitness());
		yData.add(0);
		
	}

	@Override
	public void onChange(AlgoritmoGenetico ag) {
		yData.add(ag.getGenActual());
		mejorFitness.add(ag.getMejorGen());
		mediaFitness.add(ag.getMediaFitness());
		mejorAbsoluto.add(ag.getMejorAbs().getFitness());

		plot.removeAll();

		double [] mejorFitness = new double [this.mejorFitness.size()];
		double [] mediaFitness = new double [this.mediaFitness.size()];
		double [] mejorAbsoluto = new double [this.mejorAbsoluto.size()];
		double [] yData = new double [this.yData.size()];

		for(int i = 0; i < mejorFitness.length; i++){
			mejorFitness[i] = this.mejorFitness.get(i);
			mediaFitness[i] = this.mediaFitness.get(i);
			mejorAbsoluto[i] = this.mejorAbsoluto.get(i);
			yData[i] = this.yData.get(i);
		}

		plot.addLinePlot("Mejor Individuo", yData, mejorFitness);
		plot.addLinePlot("Media Fitness", yData, mediaFitness);
		plot.addLinePlot("Mejor Absoluto", yData, mejorAbsoluto);

		this.repaint();
		this.revalidate();
		
	}

	@Override
	public void onError(String err) {
		
	}

}
