package src.vistas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;

import src.AlgoritmoGenetico;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;

public class DataView extends JPanel{

	private final JPanel cardPanel = new JPanel();
	private final JToolBar toolBar = new JToolBar();

	public DataView(AlgoritmoGenetico ag, int width, int height) {
		this.setLayout(new BorderLayout());

		this.add(toolBar, BorderLayout.NORTH);
		this.add(cardPanel, BorderLayout.CENTER);

		cardPanel.setLayout(new CardLayout());
		cardPanel.setVisible(true);
		cardPanel.add(new VistaGrafica(ag, width, height), "Grafica Evolucion");
		cardPanel.add(new VistaFuncion(ag, width, height), "Funciones");
		JScrollPane sp = new JScrollPane(new TreeView(ag));
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setPreferredSize(new Dimension(width, height));
		cardPanel.add(sp, "Arbol");
		cardPanel.add(new UglyChart(ag), "UglyChart");

		iniToolBar();
	}

	private void iniToolBar() {
		JButton btnGrafica = new JButton("Grafica Evolucion");
		JButton btnFunciones = new JButton("Funciones");
		JButton btnArbol = new JButton("Arbol");
		JButton btnUglyChart = new JButton("UglyChart");


		toolBar.setFloatable(false);

		addPanelChange(btnGrafica, "Grafica Evolucion");
		addPanelChange(btnFunciones, "Funciones");
		addPanelChange(btnArbol, "Arbol");
		addPanelChange(btnUglyChart, "UglyChart");


		toolBar.add(btnGrafica);
		toolBar.add(btnFunciones);
		toolBar.add(btnArbol);
		toolBar.add(btnUglyChart);
	}

	public void addPanelChange(JButton b, String panel){
		b.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CardLayout cl = (CardLayout) (cardPanel.getLayout());
				cl.show(cardPanel, panel);
			}
		});
	}

}

