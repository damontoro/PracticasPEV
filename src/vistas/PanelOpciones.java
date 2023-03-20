package src.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import java.util.ArrayList;
import java.util.Arrays;

import src.AlgoritmoGenetico;
import src.patrones.AGobserver;
import src.mutacion.IMutacion;
import src.mutacion.MutacionInsercion;
import src.cruce.CruceERX;
import src.cruce.CrucePMX;
import src.cruce.ICruce;

public class PanelOpciones extends JPanel implements AGobserver{

	private int width, height;
	
	private JTextField minPoblacion, maxPoblacion, generaciones;
	private JSpinner minProbCru, maxProbCru;
	private JSpinner minProbMut, maxProbMut;
	private JSpinner minElitismo, maxElitismo;

	private ArrayList<JLabel> labelList;

	private JComboBox<ICruce> selCruce;
	private JComboBox<IMutacion> selMutacion;

	private List<ICruce> cruces;
	private List<IMutacion> mutaciones;

	private JButton btnIniciar;

	public PanelOpciones(AlgoritmoGenetico ag, int width, int height) {
		super();
		this.width = width;
		this.height = height;

		ag.addObserver(this);
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		cruces = Arrays.asList(new CruceERX(), new CrucePMX());
		mutaciones = Arrays.asList(new MutacionInsercion());

		// Inicializar componentes

		labelList = new ArrayList<>();
		generaciones = new JTextField(String.valueOf(ag.getNumGeneraciones()));
		generaciones.setHorizontalAlignment(JTextField.CENTER);

		minPoblacion = new JTextField(String.valueOf(ag.getTamPoblacion()));
		maxPoblacion = new JTextField(String.valueOf(ag.getTamPoblacion()));
		minPoblacion.setHorizontalAlignment(JTextField.CENTER);
		maxPoblacion.setHorizontalAlignment(JTextField.CENTER);
		
		minProbCru = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		maxProbCru = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

		minProbMut = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		maxProbMut = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

		minElitismo = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		maxElitismo = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

		selCruce = new JComboBox<>();
		selMutacion = new JComboBox<>();

		btnIniciar = new JButton("Iniciar");

		for(ICruce c : cruces){selCruce.addItem(c);}
		for(IMutacion m : mutaciones){selMutacion.addItem(m);}

		this.add(createViewPanel(generaciones, "Generaciones"));
		this.add(intervaloPanel("Poblacion", minPoblacion, maxPoblacion));
		this.add(intervaloPanel("Probabilidad de cruce", minProbCru, maxProbCru));
		this.add(intervaloPanel("Probabilidad de mutacion", minProbMut, maxProbMut));
		this.add(intervaloPanel("Elitismo", minElitismo, maxElitismo));


		this.add(createViewPanel(selCruce, "Seleccion"));
		this.add(createViewPanel(selMutacion, "Seleccion"));

		this.add(iniButtonRun());

		changeMode(true);
		this.setVisible(true);
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

	private void changeMode(boolean hide){
		for(JLabel l : labelList){
			l.setVisible(hide);
		}
		this.maxElitismo.setVisible(hide);
		this.maxProbCru.setVisible(hide);
		this.maxProbMut.setVisible(hide);
		this.maxPoblacion.setVisible(hide);
	}

	@Override
	public void onInit(AlgoritmoGenetico ag) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onChange(AlgoritmoGenetico ag) {
		//repaint();
	}

	@Override
	public void onError(String err) {
		
	}

	private JPanel intervaloPanel(String title, JComponent min, JComponent max){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JPanel panelMin = new JPanel();
		panelMin.setLayout(new BoxLayout(panelMin, BoxLayout.LINE_AXIS));
		JLabel minL = new JLabel("Min: ");
		panelMin.add(minL);
		panelMin.add(min, BorderLayout.CENTER);

		JPanel panelMax = new JPanel();
		panelMax.setLayout(new BoxLayout(panelMax, BoxLayout.LINE_AXIS));
		JLabel maxL = new JLabel("Max: ");
		panelMax.add(maxL);
		panelMax.add(max);

		this.labelList.add(minL);
		this.labelList.add(maxL);

		panel.add(panelMin);
		panel.add(panelMax);

		return createViewPanel(panel, title);
	}

	private JPanel createViewPanel(JComponent c, String title) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 1));
		p.setPreferredSize(new Dimension(this.width * 9/10, height));
		Border b = BorderFactory.createLineBorder(Color.black, 2);
		p.setBorder(BorderFactory.createTitledBorder(b, title));
		p.add(c);
		return p;
	}



}
