package src.problema;
import src.individuo.Individuo;

public class Problema1 extends Problema{
	
	private final int min = 0;
	private final int max = 100;


	public Problema1() {
		super(0, 100);
	}
	
	@Override
	public Individuo<? extends Number> build(float precision) {
		return null;
	}
}
