package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

import src.utils.Utils;
import src.utils.TipoProblema;

public class SeleccionRuleta implements Cloneable, ISeleccion{
	
	public SeleccionRuleta() {
		super();
	}

	@Override
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand, TipoProblema tipo) {
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
		ArrayList<Double> fitness = new ArrayList<Double>();
		double totalFitness = 0;

		fitness = (tipo == TipoProblema.MINIMIZACION) ? corrigeMinimizar(poblacion) : corrigeMaximizar(poblacion);

		for(int i = 0; i < fitness.size(); i++) //Aqui se calcula el total de la suma de los fitness
			totalFitness += fitness.get(i);

		for(int i = 0; i < fitness.size(); i++)//Aqui se calcula la probabilidad acumulada de cada individuo
			fitness.set(i, (fitness.get(i) / totalFitness) + (i > 0 ? fitness.get(i-1) : 0));

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
