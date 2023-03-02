package src.seleccion;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import src.individuo.Individuo;

public class SeleccionTruncamiento implements ISeleccion, Cloneable{

	@Override
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand) {
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();

		final double porcentaje = ThreadLocalRandom.current().nextDouble(0.1, 0.5);
		poblacion.sort((a, b) -> Double.compare(b.getFitness(), a.getFitness()));

		while(seleccionados.size() < poblacion.size()){
			for(int i = 0; i < poblacion.size() * porcentaje && seleccionados.size() < poblacion.size(); i++)
				seleccionados.add(poblacion.get(i));
		}

		return seleccionados;
	}
	
	@Override
	public SeleccionTruncamiento clone() { 
		try {
			return (SeleccionTruncamiento)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	@Override
	public String toString() {
		return "Seleccion Truncamiento";
	}
}
