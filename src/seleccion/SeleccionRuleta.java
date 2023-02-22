package src.seleccion;


public class SeleccionRuleta implements Cloneable, ISeleccion{
	
	public SeleccionRuleta() {
		super();
	}

	@Override
	public void run() {
		
	}

	public ISeleccion clone() { 
		try {
			return (ISeleccion)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}
}
