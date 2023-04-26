package src.problema;

import java.util.ArrayList;

import src.utils.Pair;
import src.utils.TipoConst;
import src.utils.TipoProblema;
import src.cruce.ICruce;
import src.individuo.Individuo;
import src.mutacion.IMutacion;
import src.seleccion.ISeleccion;

public abstract class Problema implements Cloneable{
	
	protected ArrayList<ICruce> cruces;
	protected ArrayList<IMutacion> mutaciones;
	protected ArrayList<ISeleccion> selecciones;

	//x, f(x)
	protected ArrayList<Pair<Double, Double>> dataSet;

	protected TipoProblema tipo;

	public Problema() {
		cruces = new ArrayList<ICruce>();
		mutaciones = new ArrayList<IMutacion>();
		selecciones = new ArrayList<ISeleccion>();
	}

	public TipoProblema getTipo() {return tipo;}
	public void setTipo(TipoProblema tipo) {this.tipo = tipo;}

	abstract public Individuo build(TipoConst tipo);
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

    public ArrayList<IMutacion> getMutaciones(){return mutaciones;}
    public ArrayList<ICruce> getCruces(){return cruces;}
    public ArrayList<ISeleccion> getSelecciones(){return selecciones;}

	public ArrayList<Pair<Double, Double>> getDataSet() {
		return dataSet;
	}

}