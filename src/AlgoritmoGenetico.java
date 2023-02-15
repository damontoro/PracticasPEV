package src;
import java.util.ArrayList;

import src.individuo.Individuo;
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

	private ICruce cruce;
	private IMutación mutacion;
	private ISeleccion seleccion;

	public AlgoritmoGenetico(int tamPoblacion, int numGeneraciones, double probCruce, double probMutacion) {
		this.tamPoblacion = tamPoblacion;
		this.numGeneraciones = numGeneraciones;
		this.probCruce = probCruce;
		this.probMutacion = probMutacion;
		this.problema = new Problema1();
		this.poblacion = new ArrayList<Individuo>();
	}

	void initPoblacion(){
		for(int i = 0; i < tamPoblacion; i++)
			poblacion.add(problema.build(precision));
	}

	void evalPoblacion(){
		for(Individuo i : poblacion)
			i.setFitness(problema.evaluar(i.getGenotipo()));
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
		initPoblacion();
		evalPoblacion();
		for(int i = 0; i < numGeneraciones; i++){
			seleccion();
			cruce();
			mutacion();
			evalPoblacion();
		}
		//asignación del mejor individuo
	}

}
