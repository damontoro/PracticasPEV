package src.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;
import src.problema.ProblemaRegSim;
import src.problema.ProblemaRegSim.Symbol;
import src.problema.ProblemaRegSim.Symbol.Symbols;
import src.utils.BinTree;

public class MutacionFuncional implements IMutacion{

	private static final double probStop = 0.8;

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		if(rand.nextDouble() > probMutacion) return individuo;

		BinTree<Symbol> arbol = individuo.getGenotipo();

		while(!arbol.isLeaf() && rand.nextDouble() < probStop){
			if(arbol.getLeftChild().isLeaf() && arbol.getRightChild().isLeaf())
				break;
			if(rand.nextDouble() < 0.5 && !arbol.getLeftChild().isLeaf())
				arbol = arbol.getLeftChild();
			else if(!arbol.getRightChild().isLeaf())
				arbol = arbol.getRightChild();
		}

		List<Symbols> functions = ProblemaRegSim.getFunctions();
		Symbols s;
		do
			s = functions.get(rand.nextInt(functions.size()));
		while(arbol.getElem().getSymbol().equals(s));

		arbol.getElem().setSymbol(s);

		return problema.build(individuo);
	}
	

	@Override
	public String toString() {
		return "Mutacion Funcional";
	}
}
