package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

import src.utils.Utils;

public class SeleccionRuleta implements Cloneable, ISeleccion{
	
	public SeleccionRuleta() {
		super();
	}

	@Override
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand) {
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
		ArrayList<Double> fitness = new ArrayList<Double>();
		double totalFitness = 0;
		double minFitness = Double.MAX_VALUE;

		for(Individuo i : poblacion){
			fitness.add(i.getFitness());
			if (i.getFitness() < minFitness)
				minFitness = i.getFitness();
		}

		for(int i = 0; i < fitness.size(); i++){
			fitness.set(i, fitness.get(i) + ((minFitness < 0) ? minFitness : 0));
			totalFitness += fitness.get(i);
		}

		for(int i = 0; i < fitness.size(); i++)
			fitness.set(i, fitness.get(i) / totalFitness + (i > 0 ? fitness.get(i-1) : 0));

		for(int i = 0; i < poblacion.size(); i++){
			double aleatorio = rand.nextDouble();
			int index = Utils.lower_bound(aleatorio, fitness);
			seleccionados.add(poblacion.get(index));
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
