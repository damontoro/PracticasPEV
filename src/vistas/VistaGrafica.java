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

	ArrayList<Double> xData = new ArrayList<Double>();
	ArrayList<Double> yData = new ArrayList<Double>();

	public VistaGrafica() {
		// Create Chart
		chart = new XYChartBuilder().width(600).height(400).title("Area Chart").xAxisTitle("X").yAxisTitle("Y").build();

		// Customize Chart
		chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);

		// Series
		chart.addSeries("Mejor Individuo", new double[] { 0, 3, 5, 7, 9 }, new double[] { -3, 5, 9, 6, 5 });
		chart.addSeries("Media Fitness", new double[] { 0, 2, 4, 6, 9 }, new double[] { -1, 6, 4, 0, 4 });
		chart.addSeries("Presion Selectiva", new double[] { 0, 1, 3, 8, 9 }, new double[] { -2, -1, 1, 0, 1 });

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		JPanel chartPanel = new XChartPanel<XYChart>(chart);
		this.add(chartPanel);
		this.setVisible(true);
		xData.add(0.0);
		yData.add(0.0);
	}

	public void reload() {
		xData.add((double) xData.get(xData.size() - 1) + 1);
		yData.add((double) yData.get(yData.size() - 1) + 1);
		chart.updateXYSeries("Mejor Individuo", xData, yData, null);

		this.repaint();
	}
}
