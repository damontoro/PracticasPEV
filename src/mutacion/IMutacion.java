package src.mutacion;

import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;

public interface IMutacion {
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion);
}
