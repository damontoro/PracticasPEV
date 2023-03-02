package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.utils.Utils;

public class SeleccionEstocastica implements ISeleccion, Cloneable{

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
			fitness.set(i, fitness.get(i) + ((minFitness < 0) ? Math.abs(minFitness) : 0));
			totalFitness += fitness.get(i);
		}

		for(int i = 0; i < fitness.size(); i++) //Aqui se calcula la probabilidad acumulada de cada individuo
			fitness.set(i, (fitness.get(i) / totalFitness) + (i > 0 ? fitness.get(i-1) : 0));

		double distMarcas = 1.0 / poblacion.size();
		double indice = rand.nextDouble() * distMarcas;
		for(int i = 0; i < poblacion.size(); i++){

			int index = Utils.lower_bound(indice, fitness);
			seleccionados.add(poblacion.get(index));
			indice += distMarcas % 1.0;
		}
		return seleccionados;
	}
	
	@Override
	public SeleccionEstocastica clone() { 
		try {
			return (SeleccionEstocastica)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	@Override
	public String toString() {
		return "Seleccion Estocástica";
	}
}
