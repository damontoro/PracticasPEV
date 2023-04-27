package src.mutacion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;

public class MutacionBasica implements IMutacion{

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		ArrayList<Character> genotipo = individuo.getGenotipo();

		for (int i = 0; i < genotipo.size(); i++) {
			if (rand.nextDouble() < probMutacion) {
				char c = genotipo.get(i);
				while(c == genotipo.get(i))
					c = (char)(rand.nextInt(256));
				genotipo.set(i, c);
			}
		}

		return problema.build(genotipo);
	}
	
	@Override
	public String toString() {
		return "Mutacion Basica";
	}
}
