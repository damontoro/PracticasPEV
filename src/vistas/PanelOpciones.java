package src.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;

import java.util.List;
import java.util.Arrays;

import src.AlgoritmoGenetico;
import src.patrones.AGobserver;
import src.mutacion.IMutacion;
import src.mutacion.MutacionInsercion;
import src.cruce.CruceERX;
import src.cruce.CrucePMX;
import src.cruce.ICruce;

public class PanelOpciones extends JFrame implements AGobserver{
	
	JTextField txtPoblacion, txtGeneraciones;
	JSpinner txtProbCru, txtProbMut, txtElitismo;

	JComboBox selCruce, selMutacion;

	List<ICruce> cruces;
	List<IMutacion> mutaciones;

	JButton btnIniciar;

	JPanel panelPrincipal;

	public PanelOpciones(AlgoritmoGenetico ag) {
		super();

		ag.addObserver(this);
		cruces = Arrays.asList(new CruceERX(), new CrucePMX());
		mutaciones = Arrays.asList(new MutacionInsercion());

		this.setSize(500, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

		btnIniciar = new JButton("Iniciar");
		txtPoblacion = new JTextField();
		txtGeneraciones = new JTextField();
		txtProbCru = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.1));
		txtProbMut = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.1));
		txtElitismo = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.1));

		selCruce = new JComboBox(cruces.toArray());
		selMutacion = new JComboBox(mutaciones.toArray());

		panelPrincipal.add(pobPanel());
		panelPrincipal.add(genPanel());
		panelPrincipal.add(probCruPanel());
		panelPrincipal.add(probMutPanel());
		panelPrincipal.add(elitismoPanel());

		panelPrincipal.add(createViewPanel(selectCrucePanel(), "Cruces"));
		panelPrincipal.add(createViewPanel(selectMutacionPanel(), "Seleccion"));

		panelPrincipal.add(iniButtonRun());

		this.add(panelPrincipal);
	}


	private JPanel pobPanel(){
		JPanel panel = new JPanel();
		JLabel lblPoblacion = new JLabel("Poblacion");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(lblPoblacion);
		panel.add(txtPoblacion);
		return panel;
	}

	private JPanel genPanel(){
		JPanel panel = new JPanel();
		JLabel lblGeneraciones = new JLabel("Generaciones");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(lblGeneraciones);
		panel.add(txtGeneraciones);
		return panel;
	}

	private JPanel probCruPanel(){
		JPanel panel = new JPanel();
		JLabel lblProbCru = new JLabel("Prob. Cruce");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(lblProbCru);
		panel.add(txtProbCru);
		return panel;
	}

	private JPanel probMutPanel(){
		JPanel panel = new JPanel();
		JLabel lblProbMut = new JLabel("Prob. Mutacion");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(lblProbMut);
		panel.add(txtProbMut);
		return panel;
	}

	private JPanel elitismoPanel(){
		JPanel panel = new JPanel();
		JLabel lblElitismo = new JLabel("Elitismo");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(lblElitismo);
		panel.add(txtElitismo);
		return panel;
	}

	private JPanel selectCrucePanel(){
		JPanel panel = new JPanel();
		JLabel lblSelectCruce = new JLabel("Seleccion Cruce");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(lblSelectCruce);
		panel.add(selCruce);
		return panel;
	}

	private JPanel selectMutacionPanel(){
		JPanel panel = new JPanel();
		JLabel lblSelectMutacion = new JLabel("Seleccion Mutacion");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(lblSelectMutacion);
		panel.add(selMutacion);
		return panel;
	}

	private JButton iniButtonRun(){

		btnIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JDialog();
			}
		});

		return btnIniciar;
	}

	@Override
	public void onInit(AlgoritmoGenetico ag) {
		txtPoblacion.setText(String.valueOf(ag.getTamPoblacion()));
		txtGeneraciones.setText(String.valueOf(ag.getNumGeneraciones()));
		txtProbCru.setValue(ag.getProbCruce());
		txtProbMut.setValue(ag.getProbMutacion());
		txtElitismo.setValue(ag.getElitismo());
	}


	@Override
	public void onChange(AlgoritmoGenetico ag) {
		repaint();
	}

	@Override
	public void onError(String err) {
		
	}

	private JPanel createViewPanel(JComponent c, String title) {
		JPanel p = new JPanel( new BorderLayout());
		Border b = BorderFactory.createLineBorder(Color.black, 2);
		p.setBorder(BorderFactory.createTitledBorder(b, title));
		p.add(c, BorderLayout.CENTER);
		return p;
	}



}
