package src;

import java.util.Random;
import java.util.ArrayList;

import src.individuo.Individuo;
import src.seleccion.ISeleccion;
import src.seleccion.SeleccionRuleta;
import src.problema.*;
import src.cruce.*;
import src.mutacion.*;

import src.utils.Pair;


public class AlgoritmoGenetico {
	
	private ArrayList<Individuo> poblacion;
	private Problema problema; //Aqui tenemos nuestro fitness, min y maximo, 

	private int tamPoblacion;
	private int numGeneraciones;
	private double probCruce;
	private double probMutacion;
	private double precision;
	private double elitismo;

	private Random random;

	private ICruce cruce;
	private IMutacion mutacion;
	private ISeleccion seleccion;

	private ArrayList<Individuo> seleccionados;

	public AlgoritmoGenetico(int tamPoblacion, int numGeneraciones, double probCruce, double probMutacion) {
		this.tamPoblacion = tamPoblacion;
		this.numGeneraciones = numGeneraciones;
		this.probCruce = probCruce;
		this.probMutacion = probMutacion;
		this.precision = 0.01;
		this.elitismo = 0.0;
		this.problema = new Problema1();
		this.poblacion = new ArrayList<Individuo>();
		this.seleccion = new SeleccionRuleta();
		this.cruce = new CruceMonopunto();
		this.mutacion = new MutacionBinaria();
	}

	void initPoblacion(){
		Individuo.setTamCromosoma(null);
		for(int i = 0; i < tamPoblacion; i++)
			poblacion.add(problema.build(precision));
	}

	void evalPoblacion(){
		for(Individuo i : poblacion)
			i.setFitness(problema.evaluar(i.getFenotipo()));
	}

	void seleccion(){
		seleccionados = seleccion.select(poblacion, random); //Poblacion ini size individuos elegidos
	}

	void cruce(){
		poblacion = cruce.cruzar(seleccionados, problema, random, probCruce); //N individuos cruzados
	}

	void mutacion(){
		for(Individuo i : poblacion)
			i = mutacion.mutar(i, problema, random, probMutacion);
	}

	public void run(){
		initPoblacion();
		evalPoblacion();
		for(int i = 0; i < numGeneraciones; i++){
			seleccion();
			cruce();
			mutacion();
			evalPoblacion();
		}
		poblacion.sort((a, b) -> Double.compare(a.getFitness(), b.getFitness()));
		System.out.println(poblacion.get(0).getFenotipo());
		System.out.println(poblacion.get(0).getFitness());
		//asignación del mejor individuo
	}


	//Los getters y setters de los atributos compactados
	public int getTamPoblacion() {return tamPoblacion;}
	public int getNumGeneraciones() {return numGeneraciones;}
	public double getProbCruce() {return probCruce;}
	public double getProbMutacion() {return probMutacion;}
	public double getPrecision() {return precision;}
	public double getElitismo() {return elitismo;}
	public ArrayList<Individuo> getPoblacion() {return poblacion;}
	public Problema getProblema() {return problema;}
	public ISeleccion getSeleccion() {return seleccion;}
	public ICruce getCruce() {return cruce;}
	public IMutacion getMutacion() {return mutacion;}
	
	
	public void setTamPoblacion(int tamPoblacion) {this.tamPoblacion = tamPoblacion;}
	public void setNumGeneraciones(int numGeneraciones) {this.numGeneraciones = numGeneraciones;}
	public void setProbCruce(double probCruce) {this.probCruce = probCruce;}
	public void setProbMutacion(double probMutacion) {this.probMutacion = probMutacion;}
	public void setPrecision(double precision) {this.precision = precision;}
	public void setElitismo(double elitismo) {this.elitismo = elitismo;}
	public void setPoblacion(ArrayList<Individuo> poblacion) {this.poblacion = poblacion;}
	public void setProblema(Problema problema) {this.problema = problema;}
	public void setSeleccion(ISeleccion seleccion) {this.seleccion = seleccion;}
	public void setCruce(ICruce cruce) {this.cruce = cruce;}
	public void setMutacion(IMutacion mutacion) {this.mutacion = mutacion;}
}
