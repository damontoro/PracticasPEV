package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.utils.TipoProblema;

public class SeleccionRestos implements ISeleccion, Cloneable{

	private double numIndividuos;
	private SeleccionRuleta ruleta = new SeleccionRuleta();

	@Override
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand, TipoProblema tipo) {
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
		ArrayList<Double> fitness = new ArrayList<Double>();
		double totalFitness = 0;
		numIndividuos = poblacion.size();

		fitness = (tipo == TipoProblema.MINIMIZACION) ? corrigeMinimizar(poblacion) : corrigeMaximizar(poblacion);

		for(int i = 0; i < fitness.size(); i++) //Aqui se calcula el total de la suma de los fitness
			totalFitness += fitness.get(i);

		for(int i = 0; i < fitness.size(); i++) //Aqui se calcula la probabilidad de cada individuo
			fitness.set(i, (fitness.get(i) / totalFitness));

		for(int i = 0; i < poblacion.size(); i++){
			for(int j = 0; j < Math.floor(fitness.get(i) * numIndividuos); j++){
				seleccionados.add(poblacion.get(i));
			}
		}

		ArrayList<Individuo> restos = ruleta.select(poblacion, rand, tipo);
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
