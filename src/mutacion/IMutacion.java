package src.mutacion;

import src.individuo.Individuo;
import src.problema.Problema;

public interface IMutacion {
	public Individuo mutar(Individuo individuo, Problema problema);
}
