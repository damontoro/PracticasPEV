package src.problema;

import java.util.ArrayList;

import src.utils.BinTree;
import src.utils.TipoProblema;
import src.cruce.ICruce;
import src.individuo.Individuo;
import src.mutacion.IMutacion;
import src.seleccion.ISeleccion;

public abstract class Problema implements Cloneable{
	
	protected TipoProblema tipo;

	public Problema() {}

	public TipoProblema getTipo() {return tipo;}
	public void setTipo(TipoProblema tipo) {this.tipo = tipo;}

	abstract public Individuo build();
	abstract public Individuo build(Individuo i);
	abstract public <T> Individuo build(ArrayList<T> valores);
	
	abstract public <T> double evaluar(Individuo i);

	public int compare(Individuo i1, Individuo i2) {
		double fit1 = i1.getFitness();
		double fit2 = i2.getFitness();
		if (tipo == TipoProblema.MINIMIZACION)
			return Double.compare(fit1, fit2);
		else
			return Double.compare(fit2, fit1);
	}

    abstract public ArrayList<IMutacion> getMutaciones();

    abstract public ArrayList<ICruce> getCruces();

    abstract public ArrayList<ISeleccion> getSelecciones();

}