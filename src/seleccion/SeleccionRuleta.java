package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;
import src.utils.Utils;

public class SeleccionRuleta implements Cloneable, ISeleccion{
	
	public SeleccionRuleta() {
		super();
	}

	@Override
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand, Problema p) {
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
		ArrayList<Double> fitness = new ArrayList<Double>();
		double totalFitness = 0;

		fitness = fitnessCorregido(poblacion, p);

		for(int i = 0; i < fitness.size(); i++) //Aqui se calcula el total de la suma de los fitness
			totalFitness += fitness.get(i);

			fitness.set(0, 0.0);
		for(int i = 1; i < fitness.size(); i++)//Aqui se calcula la probabilidad acumulada de cada individuo
			fitness.set(i, (fitness.get(i) / totalFitness) + fitness.get(i-1));

		for(int i = 0; i < poblacion.size(); i++){
			double aleatorio = rand.nextDouble();
			int index = Utils.lower_bound(aleatorio, fitness);
			seleccionados.add(p.build(poblacion.get(index)));
		}
		return seleccionados;
	}

	public ISeleccion clone() { 
		try {
			return (ISeleccion)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	@Override
	public String toString() {
		return "Ruleta";
	}
}
