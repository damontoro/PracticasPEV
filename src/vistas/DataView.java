package src.vistas;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;

import src.AlgoritmoGenetico;

import java.awt.CardLayout;

public class DataView extends JPanel{

	private final JPanel cardPanel = new JPanel();
	private final JToolBar toolBar = new JToolBar();

	public DataView(AlgoritmoGenetico ag, int width, int height) {
		this.setLayout(new BorderLayout());

		this.add(toolBar, BorderLayout.NORTH);
		this.add(cardPanel, BorderLayout.CENTER);

		cardPanel.setLayout(new CardLayout());
		cardPanel.setVisible(true);
		cardPanel.add(new VistaGrafica(ag, width, height), "Grafica");
		cardPanel.add(new MapView(ag), "Mapa");
		cardPanel.add(new UglyChart(ag), "UglyChart");

		iniToolBar();
	}

	private void iniToolBar() {
		JButton btnGrafica = new JButton("Grafica");
		JButton btnMapa = new JButton("Mapa");
		JButton btnUglyChart = new JButton("UglyChart");

		toolBar.setFloatable(false);

		btnGrafica.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CardLayout cl = (CardLayout) (cardPanel.getLayout());
				cl.show(cardPanel, "Grafica");
			}
		});

		btnMapa.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CardLayout cl = (CardLayout) (cardPanel.getLayout());
				cl.show(cardPanel, "Mapa");
				repaint();
			}
		});

		btnUglyChart.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CardLayout cl = (CardLayout) (cardPanel.getLayout());
				cl.show(cardPanel, "UglyChart");
			}
		});


		toolBar.add(btnGrafica);
		toolBar.add(btnMapa);
		toolBar.add(btnUglyChart);
	}

}

