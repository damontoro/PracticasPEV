package src.cruce;

import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import src.individuo.Individuo;
import src.problema.Problema;

public class CruceCiclos implements ICruce{

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
			Map <T, Integer> mapaPadre1 = new HashMap<T, Integer>();
			Map <T, Integer> mapaPadre2 = new HashMap<T, Integer>();

			//mapa elemento -> posicion
			for (int j = 0; j < genotipoPadre1.size(); j++) {
				mapaPadre1.put(genotipoPadre1.get(j), j);
				mapaPadre2.put(genotipoPadre2.get(j), j);
			}

			//inicializamos los hijos
			for (int j = 0; j < genotipoPadre1.size(); j++) {
				genotipoHijo1.add(null);
				genotipoHijo2.add(null);
			}

			T value = genotipoPadre1.get(0);
			T iniVal = value;
			do{
				genotipoHijo1.set(mapaPadre1.get(value), value);
				value = genotipoPadre2.get(mapaPadre1.get(value));
			}while(!value.equals(iniVal));

			value = genotipoPadre2.get(0);
			iniVal = value;
			do{
				genotipoHijo2.set(mapaPadre2.get(value), value);
				value = genotipoPadre1.get(mapaPadre2.get(value));
			}while(!value.equals(iniVal));
			
			//rellenamos los hijos
			for (int j = 0; j < genotipoPadre1.size(); j++) {
				if(genotipoHijo1.get(j) == null){
					genotipoHijo1.set(j, genotipoPadre2.get(j));
				}
				if(genotipoHijo2.get(j) == null){
					genotipoHijo2.set(j, genotipoPadre1.get(j));
				}
			}

			hijos.add(problema.build(genotipoHijo1));
			hijos.add(problema.build(genotipoHijo2));
		}
		return hijos;
	}

	@Override
	public String toString(){
		return "Cruce Ciclos (CX)";
	} 
	
}
