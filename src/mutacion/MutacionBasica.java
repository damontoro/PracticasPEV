package src.mutacion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;
import src.utils.UnsignedByte;

public class MutacionBasica implements IMutacion{

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		ArrayList<UnsignedByte> genotipo = individuo.getGenotipo();

		for (int i = 0; i < genotipo.size(); i++) {
			if (rand.nextDouble() < probMutacion) {
				int c = genotipo.get(i).getValue();
				while(c == genotipo.get(i).getValue())
					c = rand.nextInt(256);
				genotipo.get(i).setValue(c);
			}
		}

		return problema.build(genotipo);
	}
	
	@Override
	public String toString() {
		return "Mutacion Basica";
	}
}
