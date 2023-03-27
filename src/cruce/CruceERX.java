package src.cruce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;

public class CruceERX implements ICruce {

	@Override
	public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Problema problema, Random rand,
			double probCruce) {
		ArrayList<Individuo> hijos = new ArrayList<Individuo>();

		for (int i = 0; i < padres.size() - (padres.size() % 2); i += 2) {

			if (rand.nextDouble() > probCruce) {
				hijos.add(padres.get(i));
				hijos.add(padres.get(i + 1));
				continue;
			}

			ArrayList<T> genotipoPadre1 = padres.get(i).getGenotipo();
			ArrayList<T> genotipoPadre2 = padres.get(i + 1).getGenotipo();

			ArrayList<T> genotipoHijo1 = new ArrayList<T>();
			ArrayList<T> genotipoHijo2 = new ArrayList<T>();

			HashMap<T, Set<T>> mapa = new HashMap<T, Set<T>>();
			for (int j = 0; j < genotipoPadre1.size(); j++) {
				mapa.put(genotipoPadre1.get(j), new HashSet<T>());
				mapa.get(genotipoPadre1.get(j)).add(genotipoPadre1.get((j + 1) % genotipoPadre1.size()));
				mapa.get(genotipoPadre1.get(j))
						.add(genotipoPadre1.get((j - 1 + genotipoPadre1.size()) % genotipoPadre1.size()));
			}
			for (int j = 0; j < genotipoPadre2.size(); j++) {
				mapa.get(genotipoPadre2.get(j)).add(genotipoPadre2.get((j + 1) % genotipoPadre2.size()));
				mapa.get(genotipoPadre2.get(j))
						.add(genotipoPadre2.get((j - 1 + genotipoPadre2.size()) % genotipoPadre2.size()));
			}

			generarHijo(mapa, genotipoPadre1, genotipoPadre2, genotipoHijo1, rand);
			generarHijo(mapa, genotipoPadre2, genotipoPadre1, genotipoHijo2, rand);

			hijos.add(problema.build(genotipoHijo1));
			hijos.add(problema.build(genotipoHijo2));
		}

		return hijos;
	}

	private <T> void generarHijo(HashMap<T, Set<T>> mapa, ArrayList<T> genotipoPadre1, ArrayList<T> genotipoPadre2,
			ArrayList<T> genotipoHijo, Random rand) {
		Set<T> visitados = new HashSet<T>();
		T actual = genotipoPadre2.get(0);

		genotipoHijo.add(actual);
		visitados.add(actual);

		for (int j = 1; j < genotipoPadre1.size(); j++) {

			int min = Integer.MAX_VALUE;
			T siguiente = null;
			boolean value = false;

			for (T t : mapa.get(actual)) {
				if (!visitados.contains(t)) {
					if (mapa.get(t).size() < min) {
						min = mapa.get(t).size();
						siguiente = t;
					} else if (mapa.get(t).size() == min && value) {
						siguiente = t;
					}
				}
			}
			while (siguiente == null || visitados.contains(siguiente)) {
				siguiente = genotipoPadre2.get(rand.nextInt(genotipoPadre2.size()));
			}
			actual = siguiente;
			genotipoHijo.add(actual);
			visitados.add(actual);
		}
	}

	@Override
	public String toString() {
		return "Cruce ERX";
	}
}
