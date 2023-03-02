package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.utils.TipoProblema;

public class SeleccionTruncamiento implements ISeleccion, Cloneable{

	@Override
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand, TipoProblema tipo) {
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();


		final double porcentaje = rand.nextDouble(0.1, 0.5);

		//Ordenamos la poblacion por fitness
		poblacion.sort((a, b) -> (tipo == TipoProblema.MAXIMIZACION) ? 
				Double.compare(b.getFitness(), a.getFitness()) : //De mayor a menor
				Double.compare(a.getFitness(), b.getFitness())); //De menor a mayor

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
