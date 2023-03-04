package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;
import src.utils.Utils;

public class SeleccionEstocastica implements ISeleccion, Cloneable{

	@Override
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand, Problema p) {
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
		ArrayList<Double> fitness = new ArrayList<Double>();
		double totalFitness = 0;

		fitness = fitnessCorregido(poblacion, p);

		for(int i = 0; i < fitness.size(); i++) //Aqui se calcula el total de la suma de los fitness
			totalFitness += fitness.get(i);

		for(int i = 0; i < fitness.size(); i++) //Aqui se calcula la probabilidad acumulada de cada individuo
			fitness.set(i, (fitness.get(i) / totalFitness) + (i > 0 ? fitness.get(i-1) : 0));

		double distMarcas = 1.0 / poblacion.size();
		double indice = rand.nextDouble(0, distMarcas);
		
		for(int i = 0; i < poblacion.size(); i++){
			int index = Utils.lower_bound(indice, fitness);
			seleccionados.add(p.build(poblacion.get(index)));
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
