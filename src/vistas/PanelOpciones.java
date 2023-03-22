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
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

import java.util.List;
import java.util.ArrayList;

import src.AlgoritmoGenetico;
import src.patrones.AGobserver;
import src.seleccion.ISeleccion;
import src.mutacion.IMutacion;
import src.cruce.ICruce;

public class PanelOpciones extends JPanel implements AGobserver{

	private AlgoritmoGenetico ag;

	private int width, height;
	private boolean intervalos = false;
	
	private JTextField minPoblacion, maxPoblacion, generaciones;
	private JSpinner minProbCru, maxProbCru;
	private JSpinner minProbMut, maxProbMut;
	private JSpinner minElitismo, maxElitismo;

	private ArrayList<JLabel> labelList;

	private JComboBox<ISeleccion> selSeleccion;
	private JComboBox<ICruce> selCruce;
	private JComboBox<IMutacion> selMutacion;

	private List<ISeleccion> selecciones;
	private List<ICruce> cruces;
	private List<IMutacion> mutaciones;

	private JButton btnIniciar;

	private SwingWorker<Void, Void> worker;

	public PanelOpciones(AlgoritmoGenetico ag, int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.ag = ag;

		ag.addObserver(this);
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		selecciones = ag.getSelecciones();
		cruces = ag.getCruces();
		mutaciones = ag.getMutaciones();

		// Inicializar componentes

		labelList = new ArrayList<>();
		generaciones = new JTextField("250");
		generaciones.setHorizontalAlignment(JTextField.CENTER);

		minPoblacion = new JTextField("1000");
		maxPoblacion = new JTextField("100");
		minPoblacion.setHorizontalAlignment(JTextField.CENTER);
		maxPoblacion.setHorizontalAlignment(JTextField.CENTER);
		
		minProbCru = new JSpinner(new SpinnerNumberModel(60, 0, 100, 1));
		maxProbCru = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

		minProbMut = new JSpinner(new SpinnerNumberModel(1, 0, 100, 1));
		maxProbMut = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

		minElitismo = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		maxElitismo = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

		selSeleccion = new JComboBox<>();
		selCruce = new JComboBox<>();
		selMutacion = new JComboBox<>();

		btnIniciar = new JButton("Evolucionar");
		btnIniciar.addActionListener((e) -> {runButton();});

		for (ISeleccion s : selecciones){selSeleccion.addItem(s);}
		for(ICruce c : cruces){selCruce.addItem(c);}
		for(IMutacion m : mutaciones){selMutacion.addItem(m);}

		this.add(createViewPanel(generaciones, "Generaciones"));
		this.add(intervaloPanel("Poblacion", minPoblacion, maxPoblacion));
		this.add(intervaloPanel("Probabilidad de cruce", minProbCru, maxProbCru));
		this.add(intervaloPanel("Probabilidad de mutacion", minProbMut, maxProbMut));
		this.add(intervaloPanel("Porcentaje elitismo", minElitismo, maxElitismo));

		this.add(createViewPanel(selSeleccion, "Seleccion"));
		this.add(createViewPanel(selCruce, "Cruce"));
		this.add(createViewPanel(selMutacion, "Mutacione"));

		this.add(btnIniciar);

		changeMode(false);
		this.setVisible(true);
	}

	private void runButton(){
		reset();
		loadData();

		worker = new SwingWorker<Void, Void>(){
			@Override
			protected Void doInBackground() throws Exception{
				runAG();
				return null;
			}
		};
		worker.execute();
	}

	private void runAG(){
		if (intervalos == false){
			ag.run();
		}
		else{
			// TODO: hacer que se ejecute con intervalos
		}
	}

	private JButton iniButtonRun(){

		btnIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				loadData();
				if(intervalosOFF == true)
					ag.run(intervalosOFF);
				else{
					for(int i = 0; i < numIteraciones; i++)
						ag.setValorInterv(lo que toque)
						ag.run(intervalosOFF);

				}
				*/
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

	private void reset(){
		if(worker != null && !worker.isDone()) 
			worker.cancel(true);
	}

	private void loadData(){
		ag.setNumGeneraciones(Integer.parseInt(generaciones.getText()));
		ag.setTamPoblacion(Integer.parseInt(minPoblacion.getText()));
		ag.setProbCruce(Double.valueOf((int)minProbCru.getValue()) / 100);
		ag.setProbMutacion(Double.valueOf((int)minProbMut.getValue()) / 100);
		ag.setElitismo(Double.valueOf((int)minElitismo.getValue()) / 100);
		ag.setSeleccion((ISeleccion)selSeleccion.getSelectedItem());
		ag.setCruce((ICruce)selCruce.getSelectedItem());
		ag.setMutacion((IMutacion)selMutacion.getSelectedItem());
	}

	@Override
	public void onInit(AlgoritmoGenetico ag) {
		
	}

	@Override
	public void onChange(AlgoritmoGenetico ag) {
		//repaint();
	}

	@Override
	public void onError(String err) {
		reset();
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
