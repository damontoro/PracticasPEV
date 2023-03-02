package src.vistas;

import javax.swing.JPanel;
import java.util.ArrayList;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.XChartPanel;

public class VistaGrafica extends JPanel{
	final XYChart chart;

	ArrayList<Double> mejorFitness = new ArrayList<Double>();
	ArrayList<Double> mediaFitness = new ArrayList<Double>();
	ArrayList<Double> presionSelectiva = new ArrayList<Double>();

	ArrayList<Double> yData = new ArrayList<Double>();

	public VistaGrafica() {
		// Create Chart
		chart = new XYChartBuilder().width(600).height(400).title("Tabla").xAxisTitle("Generacion").build();

		// Customize Chart
		chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);

		// Series
		mejorFitness.add(0.0);
		mediaFitness.add(0.0);
		presionSelectiva.add(0.0);
		yData.add(0.0);
		chart.addSeries("Mejor Individuo", mejorFitness, yData);
		chart.addSeries("Media Fitness", mediaFitness, yData);
		chart.addSeries("Presion Selectiva", mejorFitness, yData);

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		JPanel chartPanel = new XChartPanel<XYChart>(chart);
		this.add(chartPanel);
		this.setVisible(true);
		mejorFitness.clear();
		mediaFitness.clear();
		presionSelectiva.clear();
		yData.clear();
	}

	public void reset() {
		mejorFitness.clear();
		mediaFitness.clear();
		presionSelectiva.clear();
		yData.clear();
	}

	public void reload(Double mejorFit, Double mediaFit, Double presion, Integer i) {
		yData.add(Double.valueOf(i));
		mejorFitness.add(mejorFit);
		mediaFitness.add(mediaFit);
		presionSelectiva.add(presion);
		chart.updateXYSeries("Mejor Individuo", yData, mejorFitness, null);
		chart.updateXYSeries("Media Fitness", yData, mediaFitness, null);
		chart.updateXYSeries("Presion Selectiva", yData, presionSelectiva, null);
	}
}
