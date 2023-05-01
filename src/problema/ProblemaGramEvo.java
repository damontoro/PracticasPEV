package src.problema;

import java.util.ArrayList;

import src.cruce.CruceMonopunto;
import src.cruce.CruceUniforme;
import src.individuo.Individuo;
import src.individuo.IndividuoEntero;
import src.mutacion.MutacionBasica;
import src.mutacion.MutacionIncremento;
import src.utils.Pair;
import src.utils.TipoConst;
import src.utils.TipoProblema;
import src.utils.UnsignedByte;
import src.seleccion.*;


/*
 * La gramática que vamos a usar es la siguiente
 * <I> : <exp> <op> <exp> //Lo hacemos así para que la expresión siempre tenga una operacion
 * <exp> : <exp> <op> <exp> | <var> | <sign> <exp>
 * <op> : + | - | *
 * <sign> : + | -
 * <var> : x | 1 | 2
 */


public class ProblemaGramEvo extends Problema{

	private static int wrapping = 2;
	private static int tamCromosoma = 31;

	private static int index, currWrap; private static boolean firstLap; //Estos parametros los usamos para calcular los codones

	private static final char[] ops = {'+', '-', '*'}; 
	private static final String[] vars = {"x", "1", "2"};

	public ProblemaGramEvo() {
		super();
		tipo = TipoProblema.MINIMIZACION;
		
		cruces.add(new CruceMonopunto());
		cruces.add(new CruceUniforme());
		
		mutaciones.add(new MutacionBasica());
		mutaciones.add(new MutacionIncremento());

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
		return new IndividuoEntero((ArrayList<UnsignedByte>) valores);
	}

	@Override
	public ArrayList<Individuo> initPoblacion(int tamPoblacion, TipoConst tipo, int alturaMaxima) {
		ArrayList<Individuo> poblacion = new ArrayList<Individuo>();
		for(int i = 0; i < tamPoblacion; i++){
			poblacion.add(new IndividuoEntero(tamCromosoma));
		}

		for(Individuo i : poblacion) //TODO
			System.out.println(i.toString());
		return poblacion;
	}

	@Override
	public <T> double evaluar(Individuo i) {
		try{
			double fitness = 0;

			for(int j = 0; j < dataSet.getFirst().size(); j++){
				double valueInd;
				valueInd = calcularCodones((ArrayList<UnsignedByte>)i.getGenotipo(), dataSet.getFirst().get(j));
				fitness += Math.pow(dataSet.getSecond().get(j) - valueInd, 2);
			}
			fitness = Math.sqrt(fitness);

			if(fitness < 0.0000000001) //Para que el numero no sea elevado a menos quince
				fitness = 0;

			return fitness;
		}
		catch(UnsupportedOperationException un){
			return 100;
		}
		catch(Exception e) {
			System.out.println("Error en evaluar");
			return 0;
		}
	}

	private double calcularCodones(ArrayList<UnsignedByte> genotipo, double value) {
		index = 0; currWrap = 0; firstLap = true;
		return calcularExp(genotipo, value);
	}
	
	private double calcularExp(ArrayList<UnsignedByte> genotipo, double value){
		double var;
		int codon = cogerCodon(genotipo);
		if (codon % 2 == 0 || firstLap){
			if(firstLap) firstLap = false;

			double exp1 = calcularExp(genotipo, value);
			char op = ops[cogerCodon(genotipo) % ops.length];
			double exp2 = calcularExp(genotipo, value);
			var = calcular(op, exp1, exp2);
		}
		else if(codon % 2 == 1){
			var = calcularVar(genotipo, value); 
		}
		else{
			int sign = (cogerCodon(genotipo) % 2 == 0) ? 1 : -1;
			var = sign * calcularExp(genotipo, value);
		}
		return var;
	}

	private double calcularVar(ArrayList<UnsignedByte> genotipo, double value){
		int codon = cogerCodon(genotipo);
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
			default : throw new UnsupportedOperationException("Unimplemented method 'calcular'");
		}
	}

	public static String decode(ArrayList<UnsignedByte> genotipo){
		index = 0; currWrap = 0; firstLap = true;
		try{
			return calcularExpString(genotipo);
		}catch(UnsupportedOperationException un){
			return "Individuo demasiado largo (probablemente un bucle)";
		}
	}

	private static String calcularExpString(ArrayList<UnsignedByte> genotipo){
		String var = "";
		int codon = cogerCodon(genotipo);
		if (codon % 2 == 0 || firstLap){
			if(firstLap) firstLap = false;

			String exp1 = calcularExpString(genotipo);
			char op = ops[cogerCodon(genotipo) % ops.length];
			String exp2 = calcularExpString(genotipo);
			var = "(" + exp1 + op + exp2 + ")";
		}
		else if(codon % 2 == 1){
			var = calcularVarString(genotipo); 
		}
		else{
			String sign = (cogerCodon(genotipo) % 2 == 0) ? "" : "-";
			var = "(" + sign + calcularExpString(genotipo) + ")";
		}
		return var;
	}

	private static String calcularVarString(ArrayList<UnsignedByte> genotipo){
		int codon = cogerCodon(genotipo);
		if(vars[codon%vars.length].equals("x"))
			return "x";
		else if(Integer.valueOf(vars[codon%vars.length]) < 0)
			return "(" + vars[codon%vars.length] + ")";
		else
			return vars[codon%vars.length];
	}

	private static int cogerCodon(ArrayList<UnsignedByte> genotipo){
		if(index >= tamCromosoma){
			if(currWrap < wrapping){
				index = 0; currWrap++;
			}
			else
				throw new UnsupportedOperationException("NO HAY SUFICIENTES WRAPPINGS");
		}
		int ret = genotipo.get(index).getValue();
		
		if(!firstLap)
			index++;

		return ret;
	}

	@Override
	public Pair<ArrayList<Double>, ArrayList<Double>> getDataSet(Individuo i) {
		Pair<ArrayList<Double>, ArrayList<Double>> res = new Pair<ArrayList<Double>, ArrayList<Double>>(
			new ArrayList<Double>(), new ArrayList<Double>()
		);
		double valueInd;
		for (int j = 0; j < dataSet.getFirst().size(); j++) {
			res.getFirst().add(dataSet.getFirst().get(j));
			try{
				valueInd = calcularCodones((ArrayList<UnsignedByte>)i.getGenotipo(), dataSet.getFirst().get(j));
			}catch(UnsupportedOperationException un){
				res.getSecond().add(1000.0);
				continue;
			}
			res.getSecond().add(valueInd);
		}
		return res;
	}

}
