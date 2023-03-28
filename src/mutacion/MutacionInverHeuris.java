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

		double fitOriginal = problema.evaluar(individuo);
		double prob = 0;
		double fitNuevo = 0;
		do{
		individuo = invierte(individuo, rand, problema);
		fitNuevo = problema.evaluar(individuo);
		prob = ((fitOriginal + fitNuevo) / 2) + (fitNuevo - fitOriginal);
		prob /= (fitOriginal + fitNuevo);
		}while ((fitNuevo == fitOriginal) || !((prob < 0.5) || (prob > 0.54)));

		return individuo;
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
