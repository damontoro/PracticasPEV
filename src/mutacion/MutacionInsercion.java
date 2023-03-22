package src.mutacion;

import java.util.Random;
import java.util.ArrayList;

import src.individuo.Individuo;
import src.problema.Problema;

public class MutacionInsercion implements IMutacion{

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		if (rand.nextDouble() > probMutacion) return individuo;
		ArrayList<Integer> genes = individuo.getGenotipo();
		int numInterc = rand.nextInt(1, genes.size() / 2);

		for(int i = 0; i < numInterc; i++) {
			Integer num = genes.get(rand.nextInt(0, genes.size() - 1));
			int pos = rand.nextInt(0, genes.size() - 1);
			genes.remove(num);
			genes.add(pos, num);
		}

		return problema.build(genes);
	}
	
	@Override
	public String toString() {
		return "Mutacion Insercion";
	}
}
