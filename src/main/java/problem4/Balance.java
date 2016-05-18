package problem4;

import java.text.DecimalFormat;

public class Balance {

	double[] kassen = new double[6];
	double[] order = new double[6];
	DecimalFormat df = new DecimalFormat("####0.00");

	public void add(int index, double zahl) {
		kassen[index - 1] += zahl;
	}

	public void out() {
		order = kassen.clone();
		double max;
		int index = 0;
		for (int i = 0; i < order.length; i++) {
			max = -1;
			for (int j = 0; j < order.length; j++) {
				if (order[j] > max) {
					index = j;
					max = order[j];
				}

			}
			System.out.print("Kasse " + (index + 1) + ": "
					+ df.format(order[index]) + "€; ");
			order[index] = -1;
		}
		System.out.println();
	}

}
