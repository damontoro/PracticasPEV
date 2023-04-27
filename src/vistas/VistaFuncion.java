package src.vistas;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

import src.AlgoritmoGenetico;
import src.patrones.AGobserver;

import java.awt.Color;
import org.knowm.xchart.XChartPanel;

public class VistaFuncion extends JPanel implements AGobserver{

    private final XYChart chart;

    private List<Double> original = new ArrayList<Double>();
    private List<Double> mejorIndividuo = new ArrayList<Double>();
    private List<Double> xData = new ArrayList<Double>();

    public VistaFuncion(AlgoritmoGenetico ag, int width, int height) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
        
        // Create Chart
        chart = new XYChartBuilder().title("Funcion").xAxisTitle("X").build();
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
        original.add(0.0);
        mejorIndividuo.add(0.0);
        xData.add(0.0);
        chart.addSeries("Original", original, xData);
        chart.addSeries("Mejor Individuo", mejorIndividuo, xData);

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.

        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        chartPanel.setVisible(true);
        this.add(chartPanel);
        this.setVisible(true);

        original.clear();
        mejorIndividuo.clear();
        xData.clear();
        ag.addObserver(this);
    }

    @Override
    public void onInit(AlgoritmoGenetico ag) {
    }

    @Override
    public void onChange(AlgoritmoGenetico ag) {
    }

    @Override
    public void onError(String err) {
    }

    @Override
    public void onEnd(AlgoritmoGenetico ag) {
        original.clear();
        mejorIndividuo.clear();
        xData.clear();

		xData.addAll(ag.getProblema().getDataSet().getFirst());
		original.addAll(ag.getProblema().getDataSet().getSecond());
		mejorIndividuo.addAll(ag.getProblema().getDataSet(ag.getMejorAbs()).getSecond());

        chart.updateXYSeries("Original", xData, original, null);
        chart.updateXYSeries("Mejor Individuo", xData, mejorIndividuo, null);

        this.repaint();
        this.revalidate();
    }
    
}
