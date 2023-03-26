package src.vistas;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Dimension;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

import src.AlgoritmoGenetico;
import src.patrones.AGobserver;

import java.awt.Color;
import org.knowm.xchart.XChartPanel;

public class VistaGrafica extends JPanel implements AGobserver {

	private final XYChart chart;

	private ArrayList<Double> mejorFitness = new ArrayList<Double>();
	private ArrayList<Double> mediaFitness = new ArrayList<Double>();
	private ArrayList<Double> mejorAbsoluto = new ArrayList<Double>();

	ArrayList<Integer> xData = new ArrayList<Integer>();

	public VistaGrafica(AlgoritmoGenetico ag, int width, int height) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize(new Dimension(width, height));
		this.setVisible(true);
		
		// Create Chart
		chart = new XYChartBuilder().title("Tabla").xAxisTitle("Generacion").build();
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
		xData.add(0);
		chart.addSeries("Mejor Individuo", mejorFitness, xData);
		chart.addSeries("Media Fitness", mediaFitness, xData);
		chart.addSeries("Mejor Absoluto", mejorFitness, xData);

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.

		JPanel chartPanel = new XChartPanel<XYChart>(chart);
		chartPanel.setVisible(true);
		this.add(chartPanel);
		this.setVisible(true);

		mejorFitness.clear();
		mediaFitness.clear();
		mejorAbsoluto.clear();
		xData.clear();

		ag.addObserver(this);
	}

	private void updateChart(AlgoritmoGenetico ag) {
		xData.add(ag.getGenActual());
		mejorFitness.add(ag.getMejorGen());
		mediaFitness.add(ag.getMediaFitness());
		mejorAbsoluto.add(ag.getMejorAbs().getFitness());
		chart.updateXYSeries("Mejor Individuo", xData, mejorFitness, null);
		chart.updateXYSeries("Media Fitness", xData, mediaFitness, null);
		chart.updateXYSeries("Mejor Absoluto", xData, mejorAbsoluto, null);

		this.repaint();
		this.revalidate();
	}

	private void resetChart(AlgoritmoGenetico ag) {

		String xtitle = ag.getIntervalos() ? "Ejecución" : "Generacion";
		chart.setXAxisTitle(xtitle);

		mejorFitness.clear();
		mediaFitness.clear();
		mejorAbsoluto.clear();
		xData.clear();

		this.repaint();
		this.revalidate();
	}

	@Override
	public void onInit(AlgoritmoGenetico ag) {

		if (!ag.getIntervalos()) {
			resetChart(ag);
		}
	}

	@Override
	public void onChange(AlgoritmoGenetico ag) {
		if (!ag.getIntervalos())
			updateChart(ag);
	}

	@Override
	public void onError(String err) {}

	@Override
	public void onEnd(AlgoritmoGenetico ag) {
		if (ag.getIntervalos())
			updateChart(ag);
	}
}
