package src.utils;

import java.util.ArrayList;

public class Utils {

	private Utils(){

	}

	public static int lower_bound(Double value, ArrayList<Double> array){ //Returns the index
		int low = 0, high = array.size();

		return recursive_lower_bound(array, low, high, value);
	}

	static int recursive_lower_bound(ArrayList<Double> array, int low, int high, Double value)
	{

		if (low > high)
			return low;
		// Find the middle index
		int mid = low + (high - low) / 2;
		
		if (value <= array.get(mid)) 
			return recursive_lower_bound(array, low,  mid - 1, value);
		else
			return recursive_lower_bound(array, mid + 1, high, value);
	}


}
