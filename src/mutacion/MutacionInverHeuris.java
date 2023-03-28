package src.mutacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;

public class MutacionInverHeuris implements IMutacion{

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		if (rand.nextDouble() > probMutacion) return individuo;

		Individuo mejor = problema.build(individuo);
		double fitOriginal = problema.evaluar(individuo);
		mejor.setFitness(fitOriginal);
		double fitNuevo = 0;
		for (int i = 0; i < rand.nextInt(3,7); i++){
			individuo = invierte(individuo, rand, problema);
			fitNuevo = problema.evaluar(individuo);
			if (fitNuevo < fitOriginal){
				mejor = problema.build(individuo.getGenotipo());
				fitOriginal = fitNuevo;
			}
		}

		return mejor;
	}

	private Individuo invierte(Individuo individuo, Random rand, Problema problema){
		ArrayList<Integer> genes = individuo.getGenotipo();
		int pos1 = rand.nextInt(individuo.getGenotipo().size());
		int pos2 = pos1;

		while(pos1 == pos2){
			pos2 = rand.nextInt(individuo.getGenotipo().size());
		}

		if(pos1 > pos2){
			int aux = pos1;
			pos1 = pos2;
			pos2 = aux;
		}

		for(int i = 0; i < (pos2 - pos1) / 2; i++){
			Collections.swap(individuo.getGenotipo(), pos1 + i, pos2 - 1 - i);
		}

		return problema.build(genes);
	}

	@Override
	public String toString() {
		return "Mutacion propia";
	}
	
}
