/*import org.knowm.xchart.*;
import org.knowm.xchart.XYSeries.*;
import org.knowm.xchart.style.Styler.LegendPosition;
import javax.swing.*;
import java.awt.*;

public class Prueba {
    // Create Chart
final XYChart chart = new XYChartBuilder().width(600).height(400).title("Area Chart").xAxisTitle("X").yAxisTitle("Y").build();

// Customize Chart
chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.StepArea);

// Series
chart.addSeries("a", new double[] { 0, 3, 5, 7, 9 }, new double[] { -3, 5, 9, 6, 5 });
chart.addSeries("b", new double[] { 0, 2, 4, 6, 9 }, new double[] { -1, 6, 4, 0, 4 });
chart.addSeries("c", new double[] { 0, 1, 3, 8, 9 }, new double[] { -2, -1, 1, 0, 1 });

// Schedule a job for the event-dispatching thread:
// creating and showing this application's GUI.
javax.swing.SwingUtilities.invokeLater(new Runnable() {

  @Override
  public void run() {

    // Create and set up the window.
    JFrame frame = new JFrame("Advanced Example");
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // chart
    JPanel chartPanel = new XChartPanel<XYChart>(chart);
    frame.add(chartPanel, BorderLayout.CENTER);

    // label
    JLabel label = new JLabel("Blah blah blah.", SwingConstants.CENTER);
    frame.add(label, BorderLayout.SOUTH);

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }
});
}
*/