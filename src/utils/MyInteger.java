package src.utils;

public class MyInteger implements Cloneable{
	
	private Integer value;

	public MyInteger(Integer value) {
		this.value = value;
	}
	
	public MyInteger clone() { 
		try {
			return (MyInteger)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	public Integer get() {return value;}
	public void set(Integer value) {this.value = value;}

	@Override
	public String toString() {
		switch (value) {
		case 1:
			return "Maximizar";
		case -1:
			return "Minimizar";
		default:
			return "Error";
		}
	}
}
