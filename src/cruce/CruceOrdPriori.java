package src.cruce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

import src.individuo.Individuo;
import src.problema.Problema;

public class CruceOrdPriori implements ICruce{

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

			while (posiciones.size() < numIntercambios) {
				posiciones.add(rand.nextInt(0, genotipoPadre1.size() - 1));
			}

			HashMap <T, Integer> mapaPadre1 = new HashMap<T, Integer>();
			HashMap <T, Integer> mapaPadre2 = new HashMap<T, Integer>();

			//mapa elemento -> posicion
			for (int j = 0; j < genotipoPadre1.size(); j++) {
				mapaPadre1.put(genotipoPadre1.get(j), j);
				mapaPadre2.put(genotipoPadre2.get(j), j);
			}

			Set setHijo1 = new HashSet<Integer>(); //las posiciones de Padre2 que no se van a copiar en h1
			ArrayList<Integer> posicionesHijo1 = new ArrayList<Integer>();
			for(Integer pos : posiciones){
				setHijo1.add(mapaPadre2.get(genotipoPadre1.get(pos)));
				posicionesHijo1.add(mapaPadre2.get(genotipoPadre1.get(pos)));
			}
			Set setHijo2 = new HashSet<Integer>(); //las posiciones de Padre1 que no se van a copiar en h2
			ArrayList<Integer> posicionesHijo2 = new ArrayList<Integer>();
			for(Integer pos : posiciones){
				setHijo2.add(mapaPadre1.get(genotipoPadre2.get(pos)));
				posicionesHijo2.add(mapaPadre1.get(genotipoPadre2.get(pos)));
			}

			for(int j = 0; j < genotipoPadre1.size(); j++){
				if(!setHijo1.contains(j)){
					genotipoHijo1.add(genotipoPadre2.get(j));
				}
				else
					genotipoHijo1.add(null);
				if(!setHijo2.contains(j)){
					genotipoHijo2.add(genotipoPadre1.get(j));
				}
				else 
					genotipoHijo2.add(null);
			}

			int posPadre = 0;
			for(int j = 0; j < genotipoPadre1.size(); j++){
				if(genotipoHijo1.get(j) == null){
					genotipoHijo1.set(j, genotipoPadre2.get(posicionesHijo1.get(posPadre)));
					posPadre++;
				}
			}
			posPadre = 0;
			for(int j = 0; j < genotipoPadre1.size(); j++){
				if(genotipoHijo2.get(j) == null){
					genotipoHijo2.set(j, genotipoPadre1.get(posicionesHijo2.get(posPadre)));
					posPadre++;
				}
			}

			

			hijos.add(problema.build(genotipoHijo1));
			hijos.add(problema.build(genotipoHijo2));
		}

		return hijos;
	}
	
}
