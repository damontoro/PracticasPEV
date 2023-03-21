package src.problema;

import java.util.ArrayList;

import src.utils.TipoProblema;
import src.cruce.ICruce;
import src.individuo.Individuo;
import src.mutacion.IMutacion;
import src.seleccion.ISeleccion;

public abstract class Problema implements Cloneable{
	
	protected TipoProblema tipo;



	public Problema() {
		
	}

	public TipoProblema getTipo() {return tipo;}
	public void setTipo(TipoProblema tipo) {this.tipo = tipo;}

	abstract public Individuo build();
	abstract public Individuo build(Individuo i);
	abstract public <T> Individuo build(ArrayList<T> valores);
	
	abstract public <T> double evaluar(ArrayList<T> fenotipo);

    abstract public ArrayList<IMutacion> getMutaciones();

    abstract public ArrayList<ICruce> getCruces();

    abstract public ArrayList<ISeleccion> getSelecciones();

}