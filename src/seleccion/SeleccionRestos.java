package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

public class SeleccionRestos implements ISeleccion, Cloneable{

	private double numIndividuos;
	private SeleccionRuleta ruleta = new SeleccionRuleta();

	@Override
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand) {
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
		ArrayList<Double> fitness = new ArrayList<Double>();
		double totalFitness = 0;
		double minFitness = Double.MAX_VALUE;
		numIndividuos = poblacion.size();

		for(Individuo i : poblacion){
			fitness.add(i.getFitness());
			if (i.getFitness() < minFitness)
				minFitness = i.getFitness();
		}

		for(int i = 0; i < fitness.size(); i++){
			fitness.set(i, fitness.get(i) + ((minFitness < 0) ? Math.abs(minFitness) : 0));
			totalFitness += fitness.get(i);
		}

		for(int i = 0; i < fitness.size(); i++) //Aqui se calcula la probabilidad de cada individuo
			fitness.set(i, (fitness.get(i) / totalFitness));

		for(int i = 0; i < poblacion.size(); i++){
			for(int j = 0; j < Math.floor(fitness.get(i) * numIndividuos); j++){
				seleccionados.add(poblacion.get(i));
			}
		}

		ArrayList<Individuo> restos = ruleta.select(poblacion, rand);
		for(int i = 0; i < restos.size() && seleccionados.size() < poblacion.size(); i++)
			seleccionados.add(restos.get(i));

		return seleccionados;
	}
	

	@Override
	public SeleccionRestos clone() { 
		try {
			return (SeleccionRestos)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	@Override
	public String toString() {
		return "Seleccion Restos";
	}
}
