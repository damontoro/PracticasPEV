package src.cruce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import src.individuo.Individuo;
import src.problema.Problema;

public class CrucePosPriori implements ICruce {

	@Override
	public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Problema problema, Random rand,
			double probCruce) {
		ArrayList<Individuo> hijos = new ArrayList<Individuo>();
		for (int i = 0; i < padres.size(); i += 2) {

			if (rand.nextDouble() < probCruce) {
				hijos.add(padres.get(i));
				hijos.add(padres.get(i + 1));
				continue;
			}

			ArrayList<T> genotipoPadre1 = padres.get(i).getGenotipo();
			ArrayList<T> genotipoPadre2 = padres.get(i + 1).getGenotipo();

			ArrayList<T> genotipoHijo1 = new ArrayList<T>();
			ArrayList<T> genotipoHijo2 = new ArrayList<T>();

			int numIntercambios = rand.nextInt(1, genotipoPadre1.size() / 2);
			Set<Integer> posiciones = new HashSet<Integer>();
			Set<T> setHijo1 = new HashSet<T>(),
					setHijo2 = new HashSet<T>();

			while (posiciones.size() < numIntercambios) {
				posiciones.add(rand.nextInt(0, genotipoPadre1.size() - 1));
			}

			for (int j = 0; j < genotipoPadre1.size(); j++) {
				if (posiciones.contains(j)) {
					genotipoHijo1.add(genotipoPadre2.get(j));
					setHijo1.add(genotipoPadre2.get(j));
					genotipoHijo2.add(genotipoPadre1.get(j));
					setHijo2.add(genotipoPadre1.get(j));
				} else {
					genotipoHijo1.add(null);
					genotipoHijo2.add(null);
				}
			}

			int indPadre = 0;
			for (int j = 0; j < genotipoPadre1.size();) {
				if (genotipoHijo1.get(j) != null) {
					j++;
					continue;
				}

				if (!setHijo1.contains(genotipoPadre1.get(indPadre))) {
					genotipoHijo1.set(j, genotipoPadre1.get(indPadre));
					j++;
				}
				indPadre = (indPadre + 1) % genotipoPadre1.size();
			}
			indPadre = 0;
			for (int j = 0; j < genotipoPadre2.size();) {
				if (genotipoHijo2.get(j) != null) {
					j++;
					continue;
				}

				if (!setHijo2.contains(genotipoPadre2.get(indPadre))) {
					genotipoHijo2.set(j, genotipoPadre2.get(indPadre));
					j++;
				}
				indPadre = (indPadre + 1) % genotipoPadre2.size();
			}

			hijos.add(problema.build(genotipoHijo1));
			hijos.add(problema.build(genotipoHijo2));
		}

		return hijos;
	}

	@Override
	public String toString() {
		return "Cruce posiciones prioritarias";
	}

}
