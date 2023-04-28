package src.problema;

import java.util.ArrayList;

import src.cruce.CruceMonopunto;
import src.cruce.CruceUniforme;
import src.individuo.Individuo;
import src.individuo.IndividuoEntero;
import src.mutacion.MutacionBasica;
import src.utils.Pair;
import src.utils.TipoConst;
import src.utils.TipoProblema;
import src.seleccion.*;


/*
 * La gramática que vamos a usar es la siguiente
 * <I> : <exp> <op> <exp> //Lo hacemos así para que la expresión siempre tenga una operacion
 * <exp> : <exp> <op> <exp> | <var> 
 * <op> : + | - | * | ^
 * <var> : x | 0 | 1 | 2 | -1 | -2
 */


public class ProblemaGramEvo extends Problema{

	private enum Rules{ EXP, OP, VAR};

	private int wrapping = 2;
	private int tamCromosoma = 8;

	private static final char[] ops = {'+', '-', '*', '^'}; 
	private static final String[] vars = {"x", "0", "1", "2", "-1", "-2"};

	public ProblemaGramEvo() {
		super();
		tipo = TipoProblema.MINIMIZACION;
		
		cruces.add(new CruceMonopunto());
		cruces.add(new CruceUniforme());
		
		mutaciones.add(new MutacionBasica());

		selecciones.add(new SeleccionRanking());
		selecciones.add(new SeleccionRuleta());
		selecciones.add(new SeleccionTorneoDet());
		selecciones.add(new SeleccionTorneoProb());
		selecciones.add(new SeleccionTruncamiento());
		selecciones.add(new SeleccionRestos());
	}


	@Override
	public Individuo build(Individuo i) {
		return new IndividuoEntero((IndividuoEntero)i);
	}

	@Override
	public <T> Individuo build(ArrayList<T> valores) {
		return new IndividuoEntero((ArrayList<Character>) valores);
	}

	@Override
	public ArrayList<Individuo> initPoblacion(int tamPoblacion, TipoConst tipo, int alturaMaxima) {
		throw new UnsupportedOperationException("Unimplemented method 'initPoblacion'");
	}

	@Override
	public <T> double evaluar(Individuo i) {
		try{
			double fitness = 0;

			for(int j = 0; j < dataSet.getFirst().size(); j++){
				double valueInd;
				valueInd = calcularCodones((ArrayList<Character>)i.getGenotipo(), 0, 0, dataSet.getFirst().get(j));
				fitness += Math.pow(dataSet.getSecond().get(j) - valueInd, 2);
			}
			fitness = Math.sqrt(fitness);
			return fitness;
		}catch(Exception e) {
			System.out.println("Error en evaluar");
			return 0;
		}
	}

	private double calcularCodones(ArrayList<Character> genotipo, int i, int wrap, double value) {
		return calcularExp(genotipo, 0, 0, value);
	}
	
	private double calcularExp(ArrayList<Character> genotipo, Integer i, Integer wrap, double value){
		double var;
		if (Character.getNumericValue(genotipo.get(i)) % 2 == 0 || i == 0){
			double exp1 = calcularExp(genotipo, ++i, wrap, value); aumentarIndice(i, wrap);
			char op = ops[genotipo.get(i) % ops.length]; ++i;
			double exp2 = calcularExp(genotipo, ++i, wrap, value); aumentarIndice(i, wrap);
			var = calcular(op, exp1, exp2);
		}
		else{
			var = calcularVar(genotipo, ++i, wrap, value); aumentarIndice(i, wrap);
			return var;
		}
		return var;
	}

	private char calcularOp(ArrayList<Character> genotipo, Integer i, Integer wrap){
		int codon = genotipo.get(i); 
		i = aumentarIndice(i, wrap);
		return ops[codon % ops.length];
	}

	private double calcularVar(ArrayList<Character> genotipo, Integer i, Integer wrap, double value){
		int codon = genotipo.get(i); 
		if(vars[codon%vars.length].equals("x"))
			return value;
		else
			return Integer.valueOf(vars[codon%vars.length]);
	}

	private double calcular(char op, double exp1, double exp2){
		switch(op){
			case '+': return exp1 + exp2;
			case '-': return exp1 - exp2;
			case '*': return exp1 * exp2;
			case '^': return Math.pow(exp1, exp2);
			default : throw new UnsupportedOperationException("Unimplemented method 'calcular'");
		}
	}

	private int aumentarIndice(Integer i, Integer wrap){
		i++;
		if(i >= tamCromosoma){
			if(wrap > 0){
				i = 0; wrap--;
			}
			else
				throw new UnsupportedOperationException("NO HAY SUFICIENTES WRAPPINGS");
		}
		return i;
	}

	@Override
	public Pair<ArrayList<Double>, ArrayList<Double>> getDataSet(Individuo i) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getDataSet'");
	}


	
}
