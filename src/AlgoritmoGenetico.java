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
	private float precision;
	private boolean elitismo;

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
		this.problema = new Problema1();
		this.poblacion = new ArrayList<Individuo>();
		this.seleccion = new SeleccionRuleta();
	}

	void initPoblacion(){
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
			i = random.nextDouble() < probMutacion ? mutacion.mutar(i, problema) : i; 
	}

	public void run(){
		/*initPoblacion();
		evalPoblacion();
		for(int i = 0; i < numGeneraciones; i++){
			seleccion();
			cruce();
			mutacion();
			evalPoblacion();
		}*/
		//asignación del mejor individuo
	}


	//Los getters y setters de los atributos compactados
	public int getTamPoblacion() {return tamPoblacion;}
	public int getNumGeneraciones() {return numGeneraciones;}
	public double getProbCruce() {return probCruce;}
	public double getProbMutacion() {return probMutacion;}
	public double getPrecision() {return precision;}
	public boolean isElitismo() {return elitismo;}
	public ArrayList<Individuo> getPoblacion() {return poblacion;}
	public Problema getProblema() {return problema;}
	public ISeleccion getSeleccion() {return seleccion;}
	
	public void setTamPoblacion(int tamPoblacion) {this.tamPoblacion = tamPoblacion;}
	public void setNumGeneraciones(int numGeneraciones) {this.numGeneraciones = numGeneraciones;}
	public void setProbCruce(double probCruce) {this.probCruce = probCruce;}
	public void setProbMutacion(double probMutacion) {this.probMutacion = probMutacion;}
	public void setPrecision(float precision) {this.precision = precision;}
	public void setElitismo(boolean elitismo) {this.elitismo = elitismo;}
	public void setPoblacion(ArrayList<Individuo> poblacion) {this.poblacion = poblacion;}
	public void setProblema(Problema problema) {this.problema = problema;}
	public void setSeleccion(ISeleccion seleccion) {this.seleccion = seleccion;}
}
