package src;

import java.util.Random;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import src.individuo.Individuo;
import src.seleccion.ISeleccion;
import src.seleccion.SeleccionRuleta;
import src.vistas.VistaPrincipal;
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
	private VistaPrincipal vista;

	private int tamPoblacion;
	private int numGeneraciones;
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

	public AlgoritmoGenetico(int tamPoblacion, int numGeneraciones, double probCruce, double probMutacion) {
		this.tamPoblacion = tamPoblacion;
		this.numGeneraciones = numGeneraciones;
		this.probCruce = probCruce;
		this.probMutacion = probMutacion;
		this.elitismo = 0.0;

		this.vista = null;
		this.problema = new ProblemaTSP();

		this.poblacion = new ArrayList<Individuo>();
		this.elite = new ArrayList<Individuo>();
		
		this.seleccion = new SeleccionRuleta();
		this.cruce = new CruceMonopunto();
		this.mutacion = null;
		this.mejorAbs = null;
	}

	void initPoblacion(){
		for(int i = 0; i < tamPoblacion; i++)
			poblacion.add(problema.build());
		
		mejorAbs = poblacion.get(0);
	}

	void evalPoblacion(){

		for(Individuo i : poblacion){
			i.setFitness(problema.evaluar(i.getFenotipo()));
		}
	}

	void extraerElite(){
		if(problema.getTipo() == TipoProblema.MAXIMIZACION){
			poblacion.sort((a, b) -> Double.compare(b.getFitness(), a.getFitness()));
		}
		else{
			poblacion.sort((a, b) -> Double.compare(a.getFitness(), b.getFitness()));
		}

		elite.clear();
		for(int i = 0; i < elitismo * tamPoblacion; i++)
			elite.add(problema.build(poblacion.get(i)));
	}

	void seleccion(){
		poblacion = seleccion.select(poblacion, random, problema); //Poblacion ini size individuos elegidos
	}

	void cruce(){
		poblacion = cruce.cruzar(poblacion, problema, random, probCruce); //N individuos cruzados
	}

	void mutacion(){
		for(Individuo i : poblacion)
			i = mutacion.mutar(i, problema, random, probMutacion);
	}

	void introducirElite(){
		if(problema.getTipo() == TipoProblema.MAXIMIZACION)
			poblacion.sort((a, b) -> Double.compare(a.getFitness(), b.getFitness()));//Menor a mayor
		else
			poblacion.sort((a, b) -> Double.compare(b.getFitness(), a.getFitness()));


		for(int i = 0; i < elite.size(); i++)
			poblacion.set(i, elite.get(i));
	}

	void cogerDatos(){
		double acum = 0;
		
		for(Individuo i : poblacion){
			acum += i.getFitness();
		}
		this.mediaFitness = acum / poblacion.size();

		if(problema.getTipo() == TipoProblema.MAXIMIZACION){
			poblacion.sort((a, b) -> Double.compare(b.getFitness(), a.getFitness()));
			if(mejorAbs.getFitness() <= poblacion.get(0).getFitness()){
				mejorAbs = poblacion.get(0);
			}
			mejorGen = poblacion.get(0).getFitness();
		}
		else{
			poblacion.sort((a, b) -> Double.compare(a.getFitness(), b.getFitness()));
			if(mejorAbs.getFitness() >= poblacion.get(0).getFitness()){
				mejorAbs = poblacion.get(0);
			}
			mejorGen = poblacion.get(0).getFitness();
		}
	}

	public void run(){
		try{
			for(AGobserver o : observers)
				o.onInit(this);

			initPoblacion();
			evalPoblacion();
			for(int i = 0; i < numGeneraciones; i++){
				extraerElite();
				seleccion();
				//cruce();
				//mutacion();
				introducirElite();
				evalPoblacion();
				cogerDatos();
				if (mejorAbs.getFitness() != mejorGen)
					System.out.println("Mejor absoluto: " + mejorAbs.getFitness() + " Mejor generacion: " + mejorGen);
				show(i + 1);
			}
		}catch(Exception e){
			//Mensaje por favor revisa las opciones del algoritmo genetico usando un JOption pane
			JOptionPane.showConfirmDialog(null, "Comprueba los parametros del algoritmo","ERROR", JOptionPane.DEFAULT_OPTION, 0);
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public void show(int i){
		try{
			vista.actualizarGrafica(mejorAbs, mejorGen, mediaFitness, 0, i);
			Thread.sleep(10);
		}catch(Exception e){
			//e.printStackTrace();
			Thread.currentThread().interrupt();
		}
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

	//Los getters y setters de los atributos compactados
	public int getTamPoblacion() {return tamPoblacion;}
	public int getNumGeneraciones() {return numGeneraciones;}
	public double getProbCruce() {return probCruce;}
	public double getProbMutacion() {return probMutacion;}
	public double getElitismo() {return elitismo;}
	public ArrayList<Individuo> getPoblacion() {return poblacion;}
	public Problema getProblema() {return problema;}
	public ISeleccion getSeleccion() {return seleccion;}
	public ICruce getCruce() {return cruce;}
	public IMutacion getMutacion() {return mutacion;}
	public VistaPrincipal getVista() {return vista;}
	
	
	public void setTamPoblacion(int tamPoblacion) {this.tamPoblacion = tamPoblacion;}
	public void setNumGeneraciones(int numGeneraciones) {this.numGeneraciones = numGeneraciones;}
	public void setProbCruce(double probCruce) {this.probCruce = probCruce;}
	public void setProbMutacion(double probMutacion) {this.probMutacion = probMutacion;}
	public void setElitismo(double elitismo) {this.elitismo = elitismo;}
	public void setPoblacion(ArrayList<Individuo> poblacion) {this.poblacion = poblacion;}
	public void setProblema(Problema problema) {this.problema = problema;}
	public void setSeleccion(ISeleccion seleccion) {this.seleccion = seleccion;}
	public void setCruce(ICruce cruce) {this.cruce = cruce;}
	public void setMutacion(IMutacion mutacion) {this.mutacion = mutacion;}
	public void setVista(VistaPrincipal vistaPrincipal) {this.vista = vistaPrincipal;}
}
