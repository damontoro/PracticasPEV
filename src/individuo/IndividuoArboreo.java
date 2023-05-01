package src.individuo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import src.problema.ProblemaRegSim.Symbol;
import src.problema.ProblemaRegSim.Symbol.Symbols;
import src.problema.ProblemaRegSim;
import src.utils.BinTree;
import src.utils.TipoConst;

public class IndividuoArboreo extends Individuo{

	private BinTree<Symbol> genotipo;
	private static final int[] enterosPosibles = ProblemaRegSim.getEnterosPosibles();
	private static final List<Symbols> functions = ProblemaRegSim.getFunctions();
	private static final List<Symbols> literals = ProblemaRegSim.getLiterals();

	public IndividuoArboreo(TipoConst tipo, int max_depth) {
		super();

		switch(tipo) {
			case CRECIENTE: genotipo = buildCreciente(0, max_depth); 
				break;
			case COMPLETO: genotipo = buildCompleto(0, max_depth); 
				break;
			default: throw new UnsupportedOperationException("Unimplemented method 'initPoblacion'");
		}
	}

	public IndividuoArboreo(IndividuoArboreo i) {
		super(i);
		genotipo = deSerialize(serialize(i.getGenotipo()));
	}

	private BinTree<Symbol> buildCompleto(int height, int maxDepth){
		//Si es la altura maxima cogemos un terminal
		if(height == maxDepth - 1)
			return new BinTree<Symbol>(
				new Symbol(
					literals.get(random.nextInt(literals.size())), 
					enterosPosibles[random.nextInt(enterosPosibles.length)])
				);

		//Si no cogemos una funcion
		return new BinTree<Symbol>(
				buildCompleto(height + 1, maxDepth), 
				buildCompleto(height + 1, maxDepth), 
				new Symbol(functions.get(random.nextInt(functions.size())), 
				null)
			);
	}

	public BinTree<Symbol> buildCreciente(int height, int maxDepth){
		if(height == maxDepth - 1) //Si la altura es maxima cogemos un terminal
			return new BinTree<Symbol>(
				new Symbol(
					literals.get(random.nextInt(literals.size())), 
					enterosPosibles[random.nextInt(enterosPosibles.length)])
				);
		
		//Si la altura en la que estamos es cero, siempre cogemos una funcion
		Symbols s = (height == 0) ? functions.get(random.nextInt(functions.size())) :
									Symbols.values()[random.nextInt(Symbols.values().length)];

		if(literals.contains(s))
			return new BinTree<Symbol>(new Symbol(s, enterosPosibles[random.nextInt(enterosPosibles.length)]));

		return new BinTree<Symbol>(
			buildCreciente(height + 1, maxDepth), 
			buildCreciente(height + 1, maxDepth), 
			new Symbol(s,null));
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
			if (literals.contains(Symbol.getSymbol(token).getSymbol()))
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
