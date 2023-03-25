package src.mutacion;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

import src.individuo.Individuo;
import src.problema.Problema;

public class MutacionInversion implements IMutacion{

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		if (rand.nextDouble() > probMutacion) return individuo;

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
		return "Mutacion Inversion";
	}

	
}
