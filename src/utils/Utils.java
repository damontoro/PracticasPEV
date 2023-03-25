package src.utils;

import java.util.ArrayList;

public class Utils {

	private Utils(){

	}

	public static int lower_bound(Double value, ArrayList<Double> array){ //Returns the index
		return recursive_lower_bound(array, 0, array.size(), value);
	}

	static int recursive_lower_bound(ArrayList<Double> array, int low, int high, Double value)
	{
		if (low == high - 1)
			return low;
		// Find the middle index
		int mid = (high + low) / 2;
		
		if(Double.compare(value, array.get(mid)) == 0)
			return mid;
		else if (Double.compare(value, array.get(mid)) < 0)
			return recursive_lower_bound(array, low, mid, value);
		else
			return recursive_lower_bound(array, mid, high, value);
	}


}
