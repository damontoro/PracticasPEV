package src.problema;
import src.individuo.Individuo;

public abstract class Problema {
	
	protected final double MIN;
	protected final double MAX;

	public Problema(double min, double max) {
		MIN = min;
		MAX = max;
	}

	abstract public Individuo<? extends Number> build(float precision);
}
