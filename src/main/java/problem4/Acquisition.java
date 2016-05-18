package problem4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Acquisition implements Runnable {

	List<Cashpoint> cashpoints = new ArrayList<Cashpoint>();
	boolean stopAcquisition = false;
	Balance balance = new Balance();
	Lock myLock = new ReentrantLock();

	@Override
	public void run() {

		cashpoints.add(new Cashpoint(1, balance, myLock));
		cashpoints.add(new Cashpoint(2, balance, myLock));
		cashpoints.add(new Cashpoint(3, balance, myLock));
		cashpoints.add(new Cashpoint(4, balance, myLock));
		cashpoints.add(new Cashpoint(5, balance, myLock));
		cashpoints.add(new Cashpoint(6, balance, myLock));
		cashpoints.get(0).open();

		System.out.println("Akquise gestartet.");
		long startTime = System.currentTimeMillis();
		double randomTime = Math.random() * 2000;

		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (stopAcquisition) {
				System.out.println("Akquise beendet.");
				break;
			}

			// acquisition
			if ((System.currentTimeMillis() - startTime) > randomTime) {

				startTime = System.currentTimeMillis();
				randomTime = Math.random() * 2000;
				work();

			}

		}

	}

	private void work() {

		int i = indexOfCashpoint();
		System.out.println("Neuer Kunde an Kasse " + (i + 1) + " eingereiht.");
		cashpoints.get(i).addKunde();

		if (cashpoints.get(i).count() == 6) {
			openNewCashpoint();
		}

		if (cashpoints.get(i).count() == 8) {
			System.out.println("Es befinden sich 8 Kunden an Kasse " + (i+1) + "." );
			
			stopAcquisition = true;
		}

	}

	private int indexOfCashpoint() {
		// TODO Auto-generated method stub
		int lowest = 100;
		int count = 100;
		for (int i = 0; i < cashpoints.size(); i++) {

			if (cashpoints.get(i).count() >= 0
					&& cashpoints.get(i).count() < count) {
				lowest = i;
				count = cashpoints.get(i).count();
			}
		}
		return lowest;
	}

	private void openNewCashpoint() {
		// TODO Auto-generated method stub
		for (Cashpoint cash : cashpoints) {
			if (cash.count() == -1) {
				cash.open();
				break;
			}
		}

	}

}
