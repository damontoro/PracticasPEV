package src.cruce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;

public class CruceMonoOrd implements ICruce {

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

			ArrayList<Integer> repPadre1 = new ArrayList<Integer>();
			ArrayList<Integer> repPadre2 = new ArrayList<Integer>();

			calcularRepresentacion(genotipoPadre1, genotipoPadre2, repPadre1, repPadre2);
			cruceMonopunto(repPadre1, repPadre2, rand);
			recalcularHijos(repPadre1, repPadre2, genotipoHijo1, genotipoHijo2, genotipoPadre1, genotipoPadre2);

			hijos.add(problema.build(genotipoHijo1));
			hijos.add(problema.build(genotipoHijo2));
			
		}

		return hijos;
	}

	@SuppressWarnings("unchecked")
	private <T> void recalcularHijos(ArrayList<Integer> repPadre1, ArrayList<Integer> repPadre2, ArrayList<T> genotipoHijo1,
			ArrayList<T> genotipoHijo2, ArrayList<T> genotipoPadre1, ArrayList<T> genotipoPadre2) {

		ArrayList<Integer> aux = new ArrayList<Integer>();
		for (int j = 0; j < genotipoPadre1.size(); j++) {
			aux.add((Integer)genotipoPadre1.get(j));
		}
		Collections.sort(aux);
		for (int j = 0; j < repPadre1.size(); j++) {
			int pos = repPadre1.get(j);
			genotipoHijo1.add((T) aux.remove(pos));
		}
		
		aux.clear();
		for (int j = 0; j < genotipoPadre2.size(); j++) {
			aux.add((Integer)genotipoPadre2.get(j));
		}
		Collections.sort(aux);
		for (int j = 0; j < repPadre2.size(); j++) {
			int pos = repPadre2.get(j);
			genotipoHijo2.add((T) aux.remove(pos));
		}
	}

	private void cruceMonopunto(ArrayList<Integer> repPadre1, ArrayList<Integer> repPadre2, Random rand) {
		int puntoCruce = rand.nextInt(repPadre1.size());
		for (int i = 0; i < puntoCruce; i++) {
			int aux = repPadre1.get(i);
			repPadre1.set(i, repPadre2.get(i));
			repPadre2.set(i, aux);
		}
	}


	private <T> void  calcularRepresentacion(ArrayList<T> genotipoPadre1, ArrayList<T> genotipoPadre2,
			ArrayList<Integer> repPadre1, ArrayList<Integer> repPadre2) {

		ArrayList<Integer> aux = new ArrayList<Integer>();
		for (int j = 0; j < genotipoPadre1.size(); j++) {
			aux.add((Integer)genotipoPadre1.get(j));
		}
		Collections.sort(aux);
		for(T t : genotipoPadre1){
			int pos = aux.indexOf(t);
			repPadre1.add(pos);
			aux.remove(pos);
		}
		
		aux.clear();
		for (int j = 0; j < genotipoPadre2.size(); j++) {
			aux.add((Integer)genotipoPadre2.get(j));
		}
		Collections.sort(aux);
		for(T t : genotipoPadre2){
			int pos = aux.indexOf(t);
			repPadre2.add(pos);
			aux.remove(pos);
		}
		
	}

	@Override
	public String toString() {
		return "Codificación Ordinal";
	}

}