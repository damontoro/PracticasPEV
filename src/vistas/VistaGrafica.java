package src.vistas;

import javax.swing.JPanel;
import java.util.ArrayList;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import java.awt.Color;
import org.knowm.xchart.XChartPanel;

public class VistaGrafica extends JPanel{
	final XYChart chart;

	ArrayList<Double> mejorFitness = new ArrayList<Double>();
	ArrayList<Double> mediaFitness = new ArrayList<Double>();
	ArrayList<Double> mejorAbsoluto = new ArrayList<Double>();

	ArrayList<Double> yData = new ArrayList<Double>();

	public VistaGrafica() {
		// Create Chart
		chart = new XYChartBuilder().width(750).height(600).title("Tabla").xAxisTitle("Generacion").build();

		// Customize Chart
		chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
		chart.getStyler().setMarkerSize(1);
		chart.getStyler().setToolTipsEnabled(true);

		chart.getStyler().setZoomEnabled(true);
		chart.getStyler().setZoomResetByDoubleClick(false);
		chart.getStyler().setZoomResetByButton(true);
		chart.getStyler().setZoomSelectionColor(new Color(0,0 , 192, 128));

		// Series
		mejorFitness.add(0.0);
		mediaFitness.add(0.0);
		mejorAbsoluto.add(0.0);
		yData.add(0.0);
		chart.addSeries("Mejor Individuo", mejorFitness, yData);
		chart.addSeries("Media Fitness", mediaFitness, yData);
		chart.addSeries("Mejor Absoluto", mejorFitness, yData);

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		JPanel chartPanel = new XChartPanel<XYChart>(chart);
		this.add(chartPanel);
		this.setVisible(true);
		mejorFitness.clear();
		mediaFitness.clear();
		mejorAbsoluto.clear();
		yData.clear();
	}

	public void reset() {
		mejorFitness.clear();
		mediaFitness.clear();
		mejorAbsoluto.clear();
		yData.clear();
	}

	public void reload(Double mejorFit, Double mediaFit, Double mejorAbs, Integer i) {
		yData.add(Double.valueOf(i));
		mejorFitness.add(mejorFit);
		mediaFitness.add(mediaFit);
		mejorAbsoluto.add(mejorAbs);
		chart.updateXYSeries("Mejor Individuo", yData, mejorFitness, null);
		chart.updateXYSeries("Media Fitness", yData, mediaFitness, null);
		chart.updateXYSeries("Mejor Absoluto", yData, mejorAbsoluto, null);
	}
}
