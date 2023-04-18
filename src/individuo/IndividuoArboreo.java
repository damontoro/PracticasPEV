package src.individuo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import src.problema.ProblemaRegSim.Symbol;
import src.problema.ProblemaRegSim.Symbol.Symbols;
import src.problema.ProblemaRegSim;
import src.utils.BinTree;
import src.utils.TipoConst;

public class IndividuoArboreo extends Individuo{

	private static final int MIN_DEPTH = 2;
	private static final int MAX_DEPTH = 5;
	private int ini_depth;
	private BinTree<Symbol> genotipo;
	

	public IndividuoArboreo(TipoConst tipo) {
		super();
		ini_depth = random.nextInt(MIN_DEPTH, MAX_DEPTH + 1);

		while(tipo.equals(TipoConst.RANDOM))
			tipo = TipoConst.values()[(int) (Math.random() * TipoConst.values().length)];

		switch(tipo) {
			case CRECIENTE: genotipo = buildCreciente(0); 
				break;
			case COMPLETO: genotipo = buildCompleto(0); 
				break;
			case RAMPED_AND_HALF: //buildRampedAndHalf(); 
				break;
			default: break;
		}
	}

	public IndividuoArboreo(IndividuoArboreo i) {
		super();
		genotipo = new BinTree<Symbol>(i.getGenotipo());
	}

	public IndividuoArboreo(String postOrden){
		super();
		genotipo = buildPostOrden(postOrden);
	}

	// TODO: a probar
	private static BinTree<Symbol> buildPostOrden(String str) {
		Deque<BinTree<Symbol>> stack = new ArrayDeque<BinTree<Symbol>>();

		for (int i = 0; i < str.length(); i++) {
			String token = String.valueOf(str.charAt(i));
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

	private BinTree<Symbol> buildCompleto(int height){
		if(height == ini_depth - 1)
			return new BinTree<Symbol>(
				new Symbol(ProblemaRegSim.getLiterals().get(random.nextInt(0, 2)), random.nextInt(-2,3)));

		return new BinTree<Symbol>(
				buildCompleto(height + 1), 
				buildCompleto(height + 1), 
				new Symbol(ProblemaRegSim.getFunctions().get(random.nextInt(0, 3)), null) );
	}

	private BinTree<Symbol> buildCreciente(int height){
		if(height == ini_depth - 1)
			return new BinTree<Symbol>(
				new Symbol(ProblemaRegSim.getLiterals().get(random.nextInt(0, 2)), random.nextInt(-2,3)));
				
		Symbols s = Symbols.values()[random.nextInt(0, Symbols.values().length)];

		if(ProblemaRegSim.getLiterals().contains(s))
			return new BinTree<Symbol>(new Symbol(s, random.nextInt(-2,3)));

		return new BinTree<Symbol>(
			buildCreciente(height + 1), 
			buildCreciente(height + 1), 
			new Symbol(s,null) );
	}

	// TODO: a probar
	public String serialize(BinTree<Symbol> tree){
		StringBuilder sb = new StringBuilder();
		
		if (tree.isEmpty())
			return "";
		
		sb.append(serialize(tree.getLeftChild()));
		sb.append(serialize(tree.getRightChild()));
		sb.append(tree.getElem().toString());

		return sb.toString();
	}


	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<String> getFenotipo() {
		throw new UnsupportedOperationException();
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
