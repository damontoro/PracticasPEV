package src.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import src.AlgoritmoGenetico;
import src.patrones.AGobserver;
import src.problema.IBloating;
import src.problema.Problema;
import src.problema.ProblemaGramEvo;
import src.problema.ProblemaRegSim;
import src.seleccion.ISeleccion;
import src.utils.TipoConst;
import src.mutacion.IMutacion;
import src.cruce.ICruce;

public class PanelOpciones extends JPanel implements AGobserver{

	private AlgoritmoGenetico ag;

	private int width, height;
	private boolean intervalos = false;
	private boolean problemaGramEvol = false;

	private int min, max, step;
	
	private JSpinner minPoblacion, maxPoblacion, generaciones;
	private JSpinner minProbCru, maxProbCru;
	private JSpinner minProbMut, maxProbMut;
	private JSpinner minElitismo, maxElitismo;
	private JSpinner minSize, maxSize;

	private JSpinner stepS;
	private JSpinner minSIntervalo;
	private JSpinner maxSIntervalo;

	private ArrayList<JLabel> labelList;
	private ArrayList<JSpinner> spinnerList;
	private Map<JSpinner, String> spinnerMap;

	private JComboBox<Problema> selProblema;
	private JComboBox<ISeleccion> selSeleccion;
	private JComboBox<ICruce> selCruce;
	private JComboBox<IMutacion> selMutacion;
	private JComboBox<IBloating> selBloating;
	private JComboBox<TipoConst> selTipoConst;

	private List<Problema> problemas;
	private List<ISeleccion> selecciones;
	private List<ICruce> cruces;
	private List<IMutacion> mutaciones;
	private List<IBloating> bloatings;

	private List<JPanel> panelList;

	private JCheckBox chkBloating;

	private JButton btnIniciar;

	private JLabel stepLabel;

	private SwingWorker<Void, Void> worker;

	public PanelOpciones(AlgoritmoGenetico ag, int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.ag = ag;

		ag.addObserver(this);
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Inicializar listas
		panelList = new ArrayList<>();
		problemas = new ArrayList<>();
		problemas.add(new ProblemaRegSim());
		problemas.add(new ProblemaGramEvo());
		selecciones = ag.getSelecciones();
		cruces = ag.getCruces();
		mutaciones = ag.getMutaciones();
		bloatings = ag.getBloatings();

		// Inicializar componentes

		labelList = new ArrayList<>();
		spinnerList = new ArrayList<>();
		spinnerMap = new java.util.HashMap<>();

		// Crear componentes

		generaciones = new JSpinner(new SpinnerNumberModel(100, 0, Integer.MAX_VALUE, 1));
		spinnerList.add(generaciones);

		minPoblacion = new JSpinner(new SpinnerNumberModel(100, 0, Integer.MAX_VALUE, 1));
		maxPoblacion = new JSpinner(new SpinnerNumberModel(100, 0, Integer.MAX_VALUE, 1));
		spinnerList.add(minPoblacion);
		spinnerMap.put(minPoblacion, "Poblacion");
		spinnerList.add(maxPoblacion);
		JButton btnPoblacion = new JButton("Intervalo");
		
		minProbCru = new JSpinner(new SpinnerNumberModel(60, 0, 100, 1));
		maxProbCru = new JSpinner(new SpinnerNumberModel(60, 0, 100, 1));
		spinnerList.add(minProbCru);
		spinnerMap.put(minProbCru, "Probabilidad de cruce (%)");
		spinnerList.add(maxProbCru);
		JButton btnProbCru = new JButton("Intervalo");

		minProbMut = new JSpinner(new SpinnerNumberModel(5, 0, 100, 1));
		maxProbMut = new JSpinner(new SpinnerNumberModel(5, 0, 100, 1));
		spinnerList.add(minProbMut);
		spinnerMap.put(minProbMut, "Probabilidad de mutación (%)");
		spinnerList.add(maxProbMut);
		JButton btnProbMut = new JButton("Intervalo");

		minElitismo = new JSpinner(new SpinnerNumberModel(1, 0, 100, 1));
		maxElitismo = new JSpinner(new SpinnerNumberModel(1, 0, 100, 1));
		spinnerList.add(minElitismo);
		spinnerMap.put(minElitismo, "Porcentaje elitismo");
		spinnerList.add(maxElitismo);
		JButton btnElitismo = new JButton("Intervalo");

		minSize = new JSpinner(new SpinnerNumberModel(5, 2, 5, 1));
		maxSize = new JSpinner(new SpinnerNumberModel(5, 2, 5, 1));
		spinnerList.add(minSize);
		spinnerMap.put(minSize, "Altura máxima inicial del árbol");
		spinnerList.add(maxSize);
		JButton btnSize = new JButton("Intervalo");

		selProblema = new JComboBox<>();
		selSeleccion = new JComboBox<>();
		selCruce = new JComboBox<>();
		selMutacion = new JComboBox<>();
		selBloating = new JComboBox<>();
		selTipoConst = new JComboBox<>(TipoConst.values());

		for(Problema p : problemas){selProblema.addItem(p);}
		for(ISeleccion s : selecciones){selSeleccion.addItem(s);}
		for(ICruce c : cruces){selCruce.addItem(c);}
		for(IMutacion m : mutaciones){selMutacion.addItem(m);}
		for(IBloating b : bloatings){selBloating.addItem(b);}

		JPanel panelAux = new JPanel();

		this.add(createViewPanel(selProblema, "Problema"));
		this.add(createViewPanel(generaciones, "Generaciones"));
		this.add(intervaloPanel("Poblacion", minPoblacion, maxPoblacion, btnPoblacion));
		this.add(intervaloPanel("Probabilidad de cruce (%)", minProbCru, maxProbCru, btnProbCru));
		this.add(intervaloPanel("Probabilidad de mutación (%)", minProbMut, maxProbMut, btnProbMut));
		this.add(intervaloPanel("Porcentaje elitismo", minElitismo, maxElitismo, btnElitismo));
		panelList.add(intervaloPanel("Altura máxima inicial del árbol", minSize, maxSize, btnSize));
		this.add(panelList.get(panelList.size() - 1));

		panelList.add(createViewPanel(selTipoConst, "Tipo de Inicialización"));
		this.add(panelList.get(panelList.size() - 1));
		this.add(createViewPanel(selSeleccion, "Selección"));
		this.add(createViewPanel(selCruce, "Cruce"));
		this.add(createViewPanel(selMutacion, "Mutaciones"));
		panelList.add(createViewPanel(iniBloating(), "Bloating"));
		this.add(panelList.get(panelList.size() - 1));

		selProblema.addActionListener((e) -> {problemaChanged();});

		JPanel footer = new JPanel();
		footer.setLayout(new BoxLayout(footer, BoxLayout.LINE_AXIS));
		stepS = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		spinnerList.add(stepS);
		btnIniciar = new JButton("Evolucionar");
		btnIniciar.addActionListener((e) -> {runButton();});
		stepLabel = new JLabel("Step: ");

		footer.add(Box.createRigidArea(new Dimension(2, 0)));
		footer.add(stepLabel);
		footer.add(stepS);
		stepS.setMinimumSize(new Dimension(50, 20));
		footer.add(Box.createRigidArea(new Dimension(2, 0)));
		footer.add(btnIniciar);
		footer.add(Box.createRigidArea(new Dimension(2, 0)));

		alignSpinners();

		this.add(footer);

		changeMode(null, null, null);

		this.setVisible(true);
	}

	private void runButton(){
		reset();
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
		if (!intervalos){
			loadData();
			ag.run();
		}
		else{
			loadData();
			this.min = (int)minSIntervalo.getValue();
			this.max = (int)maxSIntervalo.getValue();
			this.step = (int)stepS.getValue();
			if (min >= max){
				onError("El valor mínimo del intervalo debe ser menor que el máximo");
			}
			else{
				this.btnIniciar.setEnabled(false);
				ag.run(step, min, max);
				this.btnIniciar.setEnabled(true);
			}
		}
	}

	private JPanel iniBloating(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		chkBloating = new JCheckBox();
		chkBloating.addActionListener((e) -> selBloating.setEnabled(chkBloating.isSelected()));
		selBloating.setEnabled(false);
		panel.add(selBloating);
		panel.add(chkBloating);
		return panel;
	}

	private void intButton(JLabel min, JLabel max, JSpinner minS, JSpinner maxS){
		if (minSIntervalo == minS && intervalos){
			intervalos = !intervalos;
			minSIntervalo = null;
			maxSIntervalo = null;
		}
		else {
			intervalos = true;
			minSIntervalo = minS;
			maxSIntervalo = maxS;
		}
		changeMode(min, max, maxS);
	}

	private void changeMode(JLabel min, JLabel max, JSpinner s){
		for(JLabel l : labelList){
			l.setVisible(false);
		}

		this.maxElitismo.setVisible(false);
		this.maxProbCru.setVisible(false);
		this.maxProbMut.setVisible(false);
		this.maxPoblacion.setVisible(false);
		this.maxSize.setVisible(false);

		if(min != null) min.setVisible(intervalos);
		if(max != null) max.setVisible(intervalos);
		if(s != null) s.setVisible(intervalos);
		stepS.setVisible(intervalos);
		stepLabel.setVisible(intervalos);
	}

	private void problemaChanged() {
		boolean isRegSim = true;
		Problema p = (Problema)selProblema.getSelectedItem();
		ag.setProblema(p);
		isRegSim = (p.getClass() == ProblemaRegSim.class);

		if (chkBloating.isSelected() && isRegSim){
			chkBloating.setSelected(chkBloating.isSelected());
			selBloating.setEnabled(chkBloating.isSelected());
		} else{
			chkBloating.setSelected(false);
			selBloating.setEnabled(false);
		}
		for (JPanel pnl : panelList){
			pnl.setVisible(isRegSim);
		}
		cruces = ag.getCruces();
		mutaciones = ag.getMutaciones();
		bloatings = ag.getBloatings();

		selCruce.removeAllItems();
		selMutacion.removeAllItems();
		selBloating.removeAllItems();

		for (ICruce c : cruces){
			selCruce.addItem(c);
		}
		for (IMutacion m : mutaciones){
			selMutacion.addItem(m);
		}
		for (IBloating b : bloatings){
			selBloating.addItem(b);
		}
	}

	private void reset(){
		if(worker != null && !worker.isDone()) 
			worker.cancel(true);
	}

	private void loadData(){
		ag.setIntervalos(intervalos);
		ag.setNumGeneraciones((int)generaciones.getValue());
		ag.setTamPoblacion((int)(minPoblacion.getValue()));
		ag.setProbCruce(Double.valueOf((int)minProbCru.getValue()) / 100);
		ag.setProbMutacion(Double.valueOf((int)minProbMut.getValue()) / 100);
		ag.setAlturaMaxima((int)minSize.getValue());
		ag.setElitismo(Double.valueOf((int)minElitismo.getValue()) / 100);
		ag.setTipoConst((TipoConst)selTipoConst.getSelectedItem());
		ag.setSeleccion((ISeleccion)selSeleccion.getSelectedItem());
		ag.setCruce((ICruce)selCruce.getSelectedItem());
		ag.setMutacion((IMutacion)selMutacion.getSelectedItem());
		ag.setBloating(chkBloating.isSelected() ? (IBloating)selBloating.getSelectedItem() : null);
	}

	@Override
	public void onInit(AlgoritmoGenetico ag) {
		if (intervalos){
			this.min = (int)minSIntervalo.getValue();
			this.max = (int)maxSIntervalo.getValue();
			this.step = (int)stepS.getValue();
			ag.setTituloEjeX(spinnerMap.get(minSIntervalo));
		}
		else {
			ag.setTituloEjeX("Generación");
		}
	}

	@Override
	public void onChange(AlgoritmoGenetico ag) {
		if(intervalos){
			minSIntervalo.setValue((int)minSIntervalo.getValue() + step);
			loadData();
		}
	}

	@Override
	public void onError(String err) {
		JOptionPane.showConfirmDialog(null, err,"ERROR", JOptionPane.DEFAULT_OPTION, 0);
		reset();
	}

	@Override
	public void onEnd(AlgoritmoGenetico ag) {
		if (intervalos && minSIntervalo != null){
			minSIntervalo.setValue(min);
		}
	}

	private JPanel intervaloPanel(String title, JSpinner min, JSpinner max, JButton intBtn){
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JPanel panelMin = new JPanel();
		panelMin.setLayout(new BoxLayout(panelMin, BoxLayout.LINE_AXIS));
		JLabel minL = new JLabel("Min:");

		panelMin.add(minL);
		panelMin.add(Box.createRigidArea(new Dimension(8, 0)));
		min.setMinimumSize(new Dimension(50, 20));
		panelMin.add(min);

		JPanel panelMax = new JPanel();
		panelMax.setLayout(new BoxLayout(panelMax, BoxLayout.LINE_AXIS));
		JLabel maxL = new JLabel("Max:");
		
		panelMax.add(maxL);
		panelMax.add(Box.createRigidArea(new Dimension(5, 0)));
		max.setMinimumSize(new Dimension(50, 20));
		panelMax.add(max);

		this.labelList.add(minL);
		this.labelList.add(maxL);

		panel.add(panelMax);
		panel.add(panelMin);
		
		contentPanel.add(panel);

		intBtn.addActionListener((e) -> {intButton(minL, maxL, min, max);});
		contentPanel.add(intBtn);

		return createViewPanel(contentPanel, title);
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

	private void alignSpinners(){
		for (JSpinner s : spinnerList) {
			JTextField aux = (JTextField)s.getEditor().getComponent(0);
			aux.setHorizontalAlignment(JTextField.CENTER);
		}
	}

}
