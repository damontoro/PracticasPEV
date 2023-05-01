package src.mutacion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;
import src.utils.UnsignedByte;

public class MutacionIncremento implements IMutacion{

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		ArrayList<UnsignedByte> genotipo = individuo.getGenotipo();

		for (int i = 0; i < genotipo.size(); i++) {
			if (rand.nextDouble() < probMutacion) {
				int c = genotipo.get(i).getValue();
				if(rand.nextDouble() < 0.5)
					genotipo.get(i).setValue(((c - 1) + 256)%256);
				else
					genotipo.get(i).setValue((c + 1)%256);
			}
		}
		return problema.build(genotipo);
	}

	@Override
	public String toString() {
		return "Mutacion +-1";
	}
	
}
