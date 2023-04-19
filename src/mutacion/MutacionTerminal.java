package src.mutacion;

import java.util.Random;

import src.individuo.Individuo;
import src.individuo.IndividuoArboreo;
import src.utils.BinTree;
import src.problema.ProblemaRegSim.Symbol;
import src.problema.Problema;

public class MutacionTerminal implements IMutacion{

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		if(rand.nextDouble() > probMutacion) return individuo;

		BinTree<Symbol> arbol = individuo.getGenotipo();

		while(!arbol.isLeaf()){
			arbol = (rand.nextDouble() < 0.5) ? arbol.getLeftChild() : arbol.getRightChild();
		}

		if (arbol.getElem().getSymbol().equals(Symbol.Symbols.X)){
			arbol.getElem().setSymbol(Symbol.Symbols.INT);
			arbol.getElem().setValue(rand.nextInt(-2, 3));
		}
		else
			arbol.getElem().setSymbol(Symbol.Symbols.X);

		return problema.build(individuo);
	}
	
	@Override
	public String toString() {
		return "Mutacion Terminal";
	}
}
