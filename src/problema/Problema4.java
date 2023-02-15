package src.problema;
import src.individuo.Individuo;

public class Problema4 extends Problema{
	

	@Override
	public Individuo<? extends Number> build(float precision){
		return new IndividuoReal(MIN, MAX, precision);
	}
}
