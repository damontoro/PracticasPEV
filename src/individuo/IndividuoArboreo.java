package src.individuo;

import java.util.ArrayDeque;
import java.util.Deque;

import src.problema.ProblemaRegSim.Symbol;
import src.problema.ProblemaRegSim.Symbol.Symbols;
import src.problema.ProblemaRegSim;
import src.utils.BinTree;
import src.utils.TipoConst;

public class IndividuoArboreo extends Individuo{

	private BinTree<Symbol> genotipo;
	

	public IndividuoArboreo(TipoConst tipo, int max_depth) {
		super();

		while(tipo.equals(TipoConst.RANDOM))
			tipo = TipoConst.values()[(int) (Math.random() * TipoConst.values().length)];

		switch(tipo) {
			case CRECIENTE: genotipo = buildCreciente(0, max_depth); 
				break;
			case COMPLETO: genotipo = buildCompleto(0, max_depth); 
				break;
			default: break;
		}
	}

	public IndividuoArboreo(IndividuoArboreo i) {
		super(i);
		genotipo = deSerialize(serialize(i.getGenotipo()));
	}

	private BinTree<Symbol> buildCompleto(int height, int maxDepth){
		if(height == maxDepth - 1)
			return new BinTree<Symbol>(
				new Symbol(ProblemaRegSim.getLiterals().get(random.nextInt(0, 2)), random.nextInt(-2,3)));

		return new BinTree<Symbol>(
				buildCompleto(height + 1, maxDepth), 
				buildCompleto(height + 1, maxDepth), 
				new Symbol(ProblemaRegSim.getFunctions().get(random.nextInt(0, 3)), null) );
	}

	private BinTree<Symbol> buildCreciente(int height, int maxDepth){
		if(height == maxDepth - 1)
			return new BinTree<Symbol>(
				new Symbol(ProblemaRegSim.getLiterals().get(random.nextInt(0, 2)), random.nextInt(-2,3)));
		
		Symbols s = (height == 0) ? ProblemaRegSim.getFunctions().get(random.nextInt(0, 3)) :
									Symbols.values()[random.nextInt(2, Symbols.values().length)];

		if(ProblemaRegSim.getLiterals().contains(s))
			return new BinTree<Symbol>(new Symbol(s, random.nextInt(-2,3)));

		return new BinTree<Symbol>(
			buildCreciente(height + 1, maxDepth), 
			buildCreciente(height + 1, maxDepth), 
			new Symbol(s,null) );
	}

	public String serialize(BinTree<Symbol> tree){
		StringBuilder sb = new StringBuilder();
		
		if (tree == null)
			return "";
		
		sb.append(serialize(tree.getLeftChild()));
		sb.append(serialize(tree.getRightChild()));
		sb.append("(" + tree.getElem().toString() + ")");

		return sb.toString();
	}

	private static BinTree<Symbol> deSerialize(String str) {
		Deque<BinTree<Symbol>> stack = new ArrayDeque<BinTree<Symbol>>();

		for (int i = 0; i < str.length(); i++) {
			String token = String.valueOf(str.charAt(i));
			if (token.equals("(")){
				token = "";
				while (str.charAt(++i) != ')')
					token += str.charAt(i);
			}
			if (ProblemaRegSim.getLiterals().contains(Symbol.getSymbol(token).getSymbol()))
				stack.push(new BinTree<Symbol>(Symbol.getSymbol(token)));
			else {
				BinTree<Symbol> right = stack.pop();
				BinTree<Symbol> left = stack.pop();
				stack.push(new BinTree<Symbol>(left, right, Symbol.getSymbol(token)));
			}
		}
		return stack.pop();
	}


	@Override
	@SuppressWarnings("unchecked")
	public String getFenotipo() {
		return toString();
	}

	@Override
	@SuppressWarnings("unchecked")
	public BinTree<Symbol> getGenotipo() {
		return genotipo;
	}

	@Override
	public String toString() {
		return genotipo.toString();
	}
	
}
