package src;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import src.individuo.Individuo;
import src.seleccion.ISeleccion;
import src.seleccion.SeleccionRuleta;
import src.problema.*;
import src.cruce.*;
import src.mutacion.*;
import src.patrones.AGobserver;
import src.patrones.Observable;
import src.utils.TipoProblema;


public class AlgoritmoGenetico implements Observable<AGobserver>{
	
	private ArrayList<Individuo> poblacion;
	private ArrayList<Individuo> elite;
	private Problema problema; //Aqui tenemos nuestro fitness, min y maximo

	private boolean intervalos;

	private int tamPoblacion;
	private int numGeneraciones;
	private int genActual;
	private double probCruce;
	private double probMutacion;
	private double elitismo;

	private double mejorGen;
	private double mediaFitness;
	
	private Individuo mejorAbs;

	final private Random random = new Random();
	private ArrayList<AGobserver> observers = new ArrayList<AGobserver>();

	private ICruce cruce;
	private IMutacion mutacion;
	private ISeleccion seleccion;

	public AlgoritmoGenetico() {

		this.problema = new ProblemaTSP();

		this.poblacion = new ArrayList<Individuo>();
		this.elite = new ArrayList<Individuo>();
		
		this.seleccion = new SeleccionRuleta();
		this.cruce = new CruceMonoOrd();
		this.mutacion = new MutacionIntercambio();
		this.mejorAbs = null;
		this.intervalos = false;
	}

	void initPoblacion(){
		reset();
		for(int i = 0; i < tamPoblacion; i++)
			poblacion.add(problema.build());

		mejorAbs = poblacion.get(0);
		evalPoblacion();
		cogerDatos();
	}

	void evalPoblacion(){

		for(Individuo i : poblacion){
			i.setFitness(problema.evaluar(i));
		}
	}

	void extraerElite(){
		
		ordenarPoblacion();
		elite.clear();
		for(int i = 0; i < elitismo * tamPoblacion; i++)
			elite.add(problema.build(poblacion.get(i)));
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

		if(problema.compare(poblacion.get(0) , mejorAbs) < 0){
			mejorAbs = poblacion.get(0);
		}
		this.mejorGen = poblacion.get(0).getFitness();
	}

	public void run(){
		try{
			onInit(this);
			initPoblacion();
			for(this.genActual = 0; this.genActual < numGeneraciones; this.genActual++){
				extraerElite();
				seleccion();
				cruce();
				mutacion();
				evalPoblacion();
				introducirElite();
				cogerDatos();
				onChange(this);
				if(!intervalos){
					System.out.println("Presion selectiva: " + presionselectiva());
					Thread.sleep(10);
				}
				
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

	private double presionselectiva(){
		if(problema.getTipo() == TipoProblema.MAXIMIZACION){
			return mejorGen / mediaFitness;
		}
		else{
			return ((mediaFitness - mejorGen) + mediaFitness) / mediaFitness ;
		}
	}

	private void ordenarPoblacion(){
		poblacion.sort((a, b) -> problema.compare(a, b));
	}

	public void reset(){
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
	public Individuo getMejorAbs() {return mejorAbs;}
	public double getMejorGen() {return mejorGen;}
	public double getMediaFitness() {return mediaFitness;}
	public int getGenActual() {return genActual;}
	public boolean getIntervalos() {return intervalos;}
	
	
	public void setTamPoblacion(int tamPoblacion) {this.tamPoblacion = tamPoblacion;}
	public void setNumGeneraciones(int numGeneraciones) {this.numGeneraciones = numGeneraciones;}
	public void setProbCruce(double probCruce) {this.probCruce = probCruce;}
	public void setProbMutacion(double probMutacion) {this.probMutacion = probMutacion;}
	public void setElitismo(double elitismo) {this.elitismo = elitismo;}
	public void setIntervalos(boolean intervalos) {this.intervalos = intervalos;}
	public void setPoblacion(ArrayList<Individuo> poblacion) {this.poblacion = poblacion;}
	public void setProblema(Problema problema) {this.problema = problema;}
	public void setSeleccion(ISeleccion seleccion) {this.seleccion = seleccion;}
	public void setCruce(ICruce cruce) {this.cruce = cruce;}
	public void setMutacion(IMutacion mutacion) {this.mutacion = mutacion;}
}
