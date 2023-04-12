package src.individuo;

import java.util.ArrayList;

import src.utils.BinTree;

public class IndividuoArboreo extends Individuo{

	private BinTree genotipo;
	

	public IndividuoArboreo() {
		super();
		genotipo = new BinTree();
	}


	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<String> getFenotipo() {
		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unchecked")
	public BinTree getGenotipo() {
		return genotipo;
	}
	
}
