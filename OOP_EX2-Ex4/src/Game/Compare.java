package Game;

import java.util.Comparator;

public class Compare implements Comparator<Double> {
	


	/**
	 * Compare betweet two numbers
	 */
	@Override
	public int compare(Double a, Double b) {
		// TODO Auto-generated method stub
		if(a > b) return 1;
		if (a < b) return -1;
		return 0;
	}

}