package src.cruce;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import src.individuo.Individuo;
import src.problema.Problema;

public class CrucePMX implements ICruce{

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

            

            //hijos.add(problema.build(genotipoHijo1));
            //hijos.add(problema.build(genotipoHijo2));
		}
		return hijos;
    }
    
	@Override
	public String toString() {
		return "Cruce PMX";
	}
}
