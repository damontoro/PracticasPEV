package src.utils;

public class UnsignedByte {
	
	private byte myByte;

	public UnsignedByte(int i) {
		if(i < 0 || i > 255)
			throw new IllegalArgumentException("UnsignedByte must be between 0 and 255");
		myByte = (byte) i;
	}

	public UnsignedByte(UnsignedByte b) {
		myByte = b.myByte;
	}

	public void setValue(int i) {
		if(i < 0 || i > 255)
			throw new IllegalArgumentException("UnsignedByte must be between 0 and 255");
		myByte = (byte) i;
	}

	public int getValue() {return myByte & 0xFF;}

	@Override
	public String toString() {return String.valueOf(getValue());}
}
