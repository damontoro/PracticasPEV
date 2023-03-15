package src.cruce;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

import src.individuo.Individuo;
import src.problema.Problema;

public class CruceOX implements ICruce{

	@Override
	public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Problema problema, Random rand,
			double probCruce) {
		
		ArrayList<Individuo> hijos = new ArrayList<Individuo>();
		for(int i = 0; i < padres.size(); i+=2){
			
			if(rand.nextDouble() < probCruce){
				hijos.add(padres.get(i));
				hijos.add(padres.get(i + 1));
				continue;
			}
			
			ArrayList<T> genotipoPadre1 = padres.get(i).getGenotipo();
			ArrayList<T> genotipoPadre2 = padres.get(i + 1).getGenotipo();

			ArrayList<T> genotipoHijo1 = new ArrayList<T>(genotipoPadre2);
			ArrayList<T> genotipoHijo2 = new ArrayList<T>(genotipoPadre1);

			int pos1 = rand.nextInt(0, genotipoPadre1.size() - 1);
			int pos2;
			do
				pos2 = rand.nextInt(0, genotipoPadre1.size() - 1);
			while(pos2 == pos1);
			
			int inicio = Math.min(pos1, pos2);
			int fin = Math.max(pos1, pos2);
			Set<T> setIntervHijo1 = new HashSet<T>();
			Set<T> setIntervHijo2 = new HashSet<T>();
			
			for(int j = inicio; j <= fin; j++){ //Metemos lo que hay en el intervalo
				setIntervHijo1.add(genotipoPadre2.get(j));
				setIntervHijo2.add(genotipoPadre1.get(j));
			}
			int posHijo = fin + 1, posPadre = fin + 1;
			while(posHijo != inicio){ //Aqui asignamos valores al hijo 1
				if(!setIntervHijo1.contains(genotipoPadre1.get(posPadre))){
					genotipoHijo1.set(posHijo, genotipoPadre1.get(posPadre));
					posHijo = (posHijo + 1) % genotipoPadre1.size();
				}
				else
					posPadre = (posPadre + 1) % genotipoPadre1.size();
			}

			posHijo = fin + 1; posPadre = fin + 1;
			while(posHijo != inicio){ //Aqui asignamos valores al hijo 2
				if(!setIntervHijo2.contains(genotipoPadre2.get(posPadre))){
					genotipoHijo2.set(posHijo, genotipoPadre2.get(posPadre));
					posHijo = (posHijo + 1) % genotipoPadre2.size();
				}
				else
					posPadre = (posPadre + 1) % genotipoPadre2.size();
			}
			
			hijos.add(problema.build(genotipoHijo1));
			hijos.add(problema.build(genotipoHijo2));
		}

		return hijos;
	}

	@Override
	public String toString() {
		return "Cruce OX";
	}
	
}
