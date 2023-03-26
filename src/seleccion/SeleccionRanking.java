package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;
import src.utils.TipoProblema;
import src.utils.Utils;

public class SeleccionRanking implements ISeleccion {

	private final static double BETA = 2;

	@Override
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand, Problema p) {
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
		ArrayList<Individuo> temp = new ArrayList<Individuo>();

		for(int i = 0; i < poblacion.size(); i++)
			temp.add(poblacion.get(i));
		
		if(p.getTipo() == TipoProblema.MAXIMIZACION)
			temp.sort((a, b) -> Double.compare(b.getFitness(), a.getFitness()));
		else
			temp.sort((a, b) -> Double.compare(a.getFitness(), b.getFitness()));

		ArrayList<Double> fitness = new ArrayList<Double>();

		fitness = fitnessCorregido(temp, p);
		rankingPunctuation(fitness);

		for(int i = 0; i < temp.size(); i++){
			double aleatorio = rand.nextDouble();
			int index = Utils.lower_bound(aleatorio, fitness);
			seleccionados.add(p.build(temp.get(index)));
		}

		return seleccionados;
	}

	private void rankingPunctuation(ArrayList<Double> fitness) {
		double accPunc = 0.0;
		double probOfIth;

		for (double i = 0; i < fitness.size(); ++i) {
			probOfIth =  1.0 / fitness.size();
			probOfIth = probOfIth * (BETA - 2 * (BETA - 1) * (i / (fitness.size() - 1)));
			
			fitness.set((int)i, accPunc);
			accPunc += probOfIth;
		}
	}

	@Override
	public String toString() {
		return "Ranking";
	}
}
