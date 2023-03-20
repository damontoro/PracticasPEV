package src.problema;

import java.util.ArrayList;

import src.utils.TipoProblema;

import src.individuo.Individuo;

public abstract class Problema implements Cloneable{
	
	protected TipoProblema tipo;



	public Problema() {
		
	}

	public Problema clone() { 
		try {
			return (Problema)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	public TipoProblema getTipo() {return tipo;}
	public void setTipo(TipoProblema tipo) {this.tipo = tipo;}

	abstract public Individuo build();
	abstract public Individuo build(Individuo i);
	abstract public <T> Individuo build(ArrayList<T> valores);
	
	abstract public <T> double evaluar(ArrayList<T> fenotipo);

}