package src.mutacion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;

public class MutacionIntercambio implements IMutacion{

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		if (rand.nextDouble() > probMutacion) return individuo;
		ArrayList<Integer> genes = individuo.getGenotipo();
		int pos1 = rand.nextInt(0, genes.size() - 1);
		int pos2;
		do
			pos2 = rand.nextInt(0, genes.size() - 1);
		while(pos2 == pos1);

		int aux = genes.get(pos1);
		genes.set(pos1, genes.get(pos2));
		genes.set(pos2, aux);

		return problema.build(genes);
	}

	@Override
	public String toString() {
		return "Mutacion Intercambio";
	}

	
}
