package src.problema;

import java.util.ArrayList;

import src.cruce.CruceMonopunto;
import src.cruce.CruceUniforme;
import src.individuo.Individuo;
import src.mutacion.MutacionBasica;
import src.utils.Pair;
import src.utils.TipoConst;
import src.utils.TipoProblema;
import src.seleccion.*;


/*
 * La gramática que vamos a usar es la siguiente
 * <exp> : <exp> <op> <exp> | <var>
 * <op> : + | - | * 
 * <var> : x | 0 | 1 | 2 | -1 | -2
 */


public class ProblemaGramEvo extends Problema{

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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'build'");
	}

	@Override
	public <T> Individuo build(ArrayList<T> valores) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'build'");
	}

	@Override
	public ArrayList<Individuo> initPoblacion(int tamPoblacion, TipoConst tipo, int alturaMaxima) {
		throw new UnsupportedOperationException("Unimplemented method 'initPoblacion'");
	}

	@Override
	public <T> double evaluar(Individuo i) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'evaluar'");
	}

	@Override
	public Pair<ArrayList<Double>, ArrayList<Double>> getDataSet(Individuo i) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getDataSet'");
	}
	
}
