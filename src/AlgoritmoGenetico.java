package src;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import src.individuo.Individuo;
import src.seleccion.ISeleccion;
import src.utils.BinTree;
import src.utils.TipoConst;
import src.problema.ProblemaRegSim.Symbol;
import src.problema.*;
import src.cruce.*;
import src.mutacion.*;
import src.patrones.AGobserver;
import src.patrones.Observable;


public class AlgoritmoGenetico implements Observable<AGobserver>{
	
	private ArrayList<Individuo> poblacion;
	private ArrayList<Individuo> elite;
	private Problema problema; //Aqui tenemos nuestro fitness, min y maximo

	private boolean intervalos;
	private String tituloEjeX;


	private int tamPoblacion;
	private int numGeneraciones;
	private int ejecucionActual;
	private int generacionActual;
	private int alturaMaxima;
	private double probCruce;
	private double probMutacion;
	private double elitismo;

	private double mejorGen;
	private double mediaFitness;
	private double mediaNodos;
	
	private Individuo mejorAbs;
	private TipoConst tipoConst;

	private final Random random;
	private ArrayList<AGobserver> observers;

	private ICruce cruce;
	private IMutacion mutacion;
	private ISeleccion seleccion;
	private IBloating bloating;

	public AlgoritmoGenetico() {

		random = new Random();
		observers = new ArrayList<AGobserver>();

		this.problema = new ProblemaRegSim();

		this.poblacion = new ArrayList<Individuo>();
		this.elite = new ArrayList<Individuo>();

		this.mejorAbs = null;
		this.intervalos = false;
	}

	void initPoblacion(){
		reset();

		poblacion = problema.initPoblacion(tamPoblacion, tipoConst, alturaMaxima);

		evalPoblacion();
		cogerDatos();
	}

	void evalPoblacion(){

		for(Individuo i : poblacion){
			i.setFitness(problema.evaluar(i));
		}
		controlBloating();
	}

	void extraerElite(){
		
		ordenarPoblacion();
		elite.clear();
		for(int i = 0; i < elitismo * tamPoblacion; i++)
			elite.add(problema.build(poblacion.get(i)));
	}

	void controlBloating(){
		if(bloating == null) return;

		bloating.penalizar(generacionActual, numGeneraciones, poblacion, random);
	}

	void seleccion(){
		poblacion = seleccion.select(poblacion, random, problema); //Poblacion ini size individuos elegidos
	}

	void cruce(){
		Collections.shuffle(poblacion);
		poblacion = cruce.cruzar(poblacion, problema, random, probCruce); //N individuos cruzados
	}

	void mutacion(){
		for(int i = 0; i < poblacion.size(); i++)
			poblacion.set(i, mutacion.mutar(poblacion.get(i), problema, random, probMutacion));
	}

	void introducirElite(){
		ordenarPoblacion();
		for(int i = poblacion.size() - 1, j = 0; j < elite.size(); i--, j++)
			poblacion.set(i, problema.build(elite.get(j)));
	}

	void cogerDatos(){
		double acum = 0;
		
		for(Individuo i : poblacion){
			acum += i.getFitness();
		}
		this.mediaFitness = acum / poblacion.size();

		ordenarPoblacion();
		if(mejorAbs == null || problema.compare(poblacion.get(0) , mejorAbs) < 0)
			mejorAbs = problema.build(poblacion.get(0));

		if(problema.getClass() == ProblemaRegSim.class){
			double acumNodos = 0;
			for(Individuo i : poblacion){
				acumNodos += ((BinTree<Symbol>)i.getGenotipo()).getNumNodes();
			}
			this.mediaNodos = acumNodos / poblacion.size();
		}

		this.mejorGen = poblacion.get(0).getFitness();
	}

	public void run(){
		this.setIntervalos(false);
		try{
			onInit(this);
			initPoblacion();
			for(this.ejecucionActual = 1, this.generacionActual = 1; this.ejecucionActual <= numGeneraciones; this.ejecucionActual++, this.generacionActual++){
				extraerElite();
				seleccion();
				cruce();
				mutacion();
				evalPoblacion();
				introducirElite();
				cogerDatos();
				onChange(this);
				Thread.sleep(10);
			}
			onEnd(this);
		}
		catch(InterruptedException e){}
		catch(Exception e){
			onError("Error en el algoritmo genetico");
			//Mensaje por favor revisa las opciones del algoritmo genetico usando un JOption pane
			JOptionPane.showConfirmDialog(null, "Comprueba los parametros del algoritmo","ERROR", JOptionPane.DEFAULT_OPTION, 0);
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public void run(int step, int min, int max){
		this.setIntervalos(true);
		try{
			onInit(this);
			for (this.ejecucionActual = min; this.ejecucionActual <= max; this.ejecucionActual+= step) {
				initPoblacion();
				int j;
				for(j = 0, this.generacionActual = 1; j < numGeneraciones; j++, this.generacionActual++){
					extraerElite();
					controlBloating();
					seleccion();
					cruce();
					mutacion();
					evalPoblacion();
					introducirElite();
					cogerDatos();
				}
				onChange(this);
			}
			onEnd(this);
		}catch(Exception e){onError(e.getMessage());}
	}

	private void ordenarPoblacion(){
		poblacion.sort((a, b) -> problema.compare(a, b));
	}

	public void reset(){
		this.mejorAbs = null;
		this.mediaNodos = 0;
		this.poblacion = new ArrayList<Individuo>();
		Individuo.setTamCromosoma(null);
	}

	public void addObserver(AGobserver o){
		observers.add(o);
	}

	public void removeObserver(AGobserver o){
		observers.remove(o);
	}

	void onInit(AlgoritmoGenetico ag){for(AGobserver o : observers){o.onInit(ag);}}
	void onChange(AlgoritmoGenetico ag){for(AGobserver o : observers){o.onChange(ag);}}	
	void onError(String err){for(AGobserver o : observers){o.onError(err);}}
	void onEnd(AlgoritmoGenetico ag){for(AGobserver o : observers){o.onEnd(ag);}}

	//Los getters y setters de los atributos compactados
	public ArrayList<ISeleccion> getSelecciones() {return problema.getSelecciones();}
	public ArrayList<ICruce> getCruces() {return problema.getCruces();}
	public ArrayList<IMutacion> getMutaciones() {return problema.getMutaciones();}
	public ArrayList<IBloating> getBloatings() {return problema.getBloatings();}
	public Problema getProblema() {return problema;}
	public Individuo getMejorAbs() {return mejorAbs;}
	public double getMejorGen() {return mejorGen;}
	public double getMediaFitness() {return mediaFitness;}
	public double getMediaNodos() {return mediaNodos;}
	public int getEjecucionActual() {return ejecucionActual;}
	public int getGeneracionActual() {return generacionActual;}
	public boolean getIntervalos() {return intervalos;}
	public String getTituloEjeX() {return tituloEjeX;}
	
	
	public void setTamPoblacion(int tamPoblacion) {this.tamPoblacion = tamPoblacion;}
	public void setNumGeneraciones(int numGeneraciones) {this.numGeneraciones = numGeneraciones;}
	public void setProbCruce(double probCruce) {this.probCruce = probCruce;}
	public void setProbMutacion(double probMutacion) {this.probMutacion = probMutacion;}
	public void setAlturaMaxima(int alturaMaxima) {this.alturaMaxima = alturaMaxima;}
	public void setTipoConst(TipoConst tipo){this.tipoConst = tipo;}
	public void setElitismo(double elitismo) {this.elitismo = elitismo;}
	public void setIntervalos(boolean intervalos) {this.intervalos = intervalos;}
	public void setEjecucionActual(int ejecucionActual) {this.ejecucionActual = ejecucionActual;}
	public void setTituloEjeX(String tituloEjeX) {this.tituloEjeX = tituloEjeX;}
	public void setPoblacion(ArrayList<Individuo> poblacion) {this.poblacion = poblacion;}
	public void setProblema(Problema problema) {this.problema = problema;}
	public void setSeleccion(ISeleccion seleccion) {this.seleccion = seleccion;}
	public void setCruce(ICruce cruce) {this.cruce = cruce;}
	public void setMutacion(IMutacion mutacion) {this.mutacion = mutacion;}
	public void setBloating(IBloating bloating) {this.bloating = bloating;}
}
