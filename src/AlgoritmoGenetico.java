package src;
import java.util.ArrayList;

import src.individuo.Individuo;
import src.seleccion.ISeleccion;
import src.seleccion.SeleccionRuleta;
import src.problema.*;


public class AlgoritmoGenetico {
	
	private ArrayList<Individuo> poblacion;
	private Problema problema; //Aqui tenemos nuestro fitness, min y maximo, 

	private int tamPoblacion;
	private int numGeneraciones;
	private double probCruce;
	private double probMutacion;
	private float precision;
	private boolean elitismo;

	//private ICruce cruce;
	//private IMutación mutacion;
	private ISeleccion seleccion;

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
		seleccionados = seleccion.run(poblacion); //N individuos elegidos
	}

	void cruce(){
		cruzados = cruce.run(seleccionados, probCruce); //N individuos cruzados
	}

	void mutacion(){
		poblacion = cruzados + poblacion - seleccionados;
		for(Individuo i : poblacion)
			mutacion.run(i, probMutacion);
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
	public void setTamPoblacion(int tamPoblacion) {this.tamPoblacion = tamPoblacion;}
	public int getNumGeneraciones() {return numGeneraciones;}
	public void setNumGeneraciones(int numGeneraciones) {this.numGeneraciones = numGeneraciones;}
	public double getProbCruce() {return probCruce;}
	public void setProbCruce(double probCruce) {this.probCruce = probCruce;}
	public double getProbMutacion() {return probMutacion;}
	public void setProbMutacion(double probMutacion) {this.probMutacion = probMutacion;}
	public double getPrecision() {return precision;}
	public void setPrecision(float precision) {this.precision = precision;}
	public boolean isElitismo() {return elitismo;}
	public void setElitismo(boolean elitismo) {this.elitismo = elitismo;}
	public ArrayList<Individuo> getPoblacion() {return poblacion;}
	public void setPoblacion(ArrayList<Individuo> poblacion) {this.poblacion = poblacion;}
	public Problema getProblema() {return problema;}
	public void setProblema(Problema problema) {this.problema = problema;}
	public ISeleccion getSeleccion() {return seleccion;}
	public void setSeleccion(ISeleccion seleccion) {this.seleccion = seleccion;}
}
