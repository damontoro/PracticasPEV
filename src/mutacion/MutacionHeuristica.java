package src.mutacion;

import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import src.individuo.Individuo;
import src.problema.Problema;

public class MutacionHeuristica implements IMutacion{

	private static final int [][] permuts = { //Vamos a hacer permutaciones de 3 elementos siempre
		{1, 2, 3},
		{1, 3, 2},
		{2, 1, 3},
		{2, 3, 1},
		{3, 1, 2},
		{3, 2, 1}
	};

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		if (rand.nextDouble() > probMutacion) return individuo;
		
		ArrayList<Integer> genes = individuo.getGenotipo();

		Set<Integer> posiciones = new HashSet<Integer>();
		ArrayList<Integer> posList = new ArrayList<Integer>();
		while(posiciones.size() < 3){
			int pos = rand.nextInt(individuo.getGenotipo().size());
			if(posiciones.add(pos))
				posList.add(pos);
		}
		
		double mejorFitness = problema.evaluar(individuo);
		ArrayList<Integer> mejorGenotipo = individuo.getGenotipo();
		
		for(int [] permut : permuts){
			ArrayList<Integer> aux = new ArrayList<Integer>(genes);
			int i = 0;

			for(int pos : posiciones){
				aux.set(posList.get(permut[i] - 1), genes.get(pos));
				i++;
			}

			double fit = problema.evaluar(problema.build(aux));
			if(fit < mejorFitness){
				mejorFitness = fit;
				mejorGenotipo = aux;
			}
		}
		return problema.build(mejorGenotipo);
	}

	@Override
	public String toString() {
		return "Mutacion Heuristica";
	}
	
}
