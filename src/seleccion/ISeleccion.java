package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.utils.TipoProblema;

public interface ISeleccion{
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand, TipoProblema tipo);

	default public ArrayList<Double> corrigeMinimizar(ArrayList<Individuo> poblacion){
		ArrayList<Double> valores = new ArrayList<Double>();
		for(Individuo i : poblacion)
			valores.add(i.getFitness());
		
		double max = valores.get(0);
		for(int i = 1; i < valores.size(); i++)
			if(valores.get(i) > max)
				max = valores.get(i);
		
		for(int i = 0; i < valores.size(); i++)
			valores.set(i, (1.05 * max) - valores.get(i));
		
		return valores;
	}

	default public ArrayList<Double> corrigeMaximizar(ArrayList<Individuo> poblacion){
		ArrayList<Double> valores = new ArrayList<Double>();
		for(Individuo i : poblacion)
			valores.add(i.getFitness());
		
		double min = valores.get(0);
		for(int i = 1; i < valores.size(); i++)
			if(valores.get(i) < min)
				min = valores.get(i);
		
		for(int i = 0; i < valores.size(); i++)
			valores.set(i, (1.05 * Math.abs(min)) + valores.get(i));
		
		return valores;
	}
}
