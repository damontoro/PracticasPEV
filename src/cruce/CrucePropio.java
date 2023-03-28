package src.cruce;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

import src.individuo.Individuo;
import src.problema.Problema;

public class CrucePropio implements ICruce {

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

			crearHijo(genotipoPadre1, genotipoPadre2, genotipoHijo1);
			crearHijo(genotipoPadre2, genotipoPadre1, genotipoHijo2);

			hijos.add(problema.build(genotipoHijo1));
			hijos.add(problema.build(genotipoHijo2));
		}
		return hijos;
	}

	private <T> void  crearHijo(ArrayList<T> genotipoPadre1, ArrayList<T> genotipoPadre2, ArrayList<T> genotipoHijo1) {
		int p1 = 0, p2 = 0;
		Set<T> set = new HashSet<T>();
		boolean turno = true;
		while(genotipoHijo1.size() < genotipoPadre1.size()){
			if(!turno){
				if (!set.contains(genotipoPadre1.get(p1))){
					genotipoHijo1.add(genotipoPadre1.get(p1));
					set.add(genotipoPadre1.get(p1));
				}
				p1 = (p1 + 1) % genotipoPadre1.size();
			}
			else{
				if (!set.contains(genotipoPadre2.get(p2))){
					genotipoHijo1.add(genotipoPadre2.get(p2));
					set.add(genotipoPadre2.get(p2));
				}
				p2 = (p2 + 1) % genotipoPadre2.size();
			}
			turno = !turno;
		}
	}

	@Override
	public String toString() {
		return "Cruce Propio";
	}

}
