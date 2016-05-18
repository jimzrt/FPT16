package problem4;

import java.util.concurrent.locks.Lock;

public class Cashpoint implements Runnable {

	int index;
	Balance balance;
	int count = -1;
	Lock myLock;

	public Cashpoint(int index, Balance balance, Lock myLock) {

		this.index = index;
		this.balance = balance;
		this.myLock = myLock;

	}

	public int count() {
		return count;
	}

	public void addKunde() {
		count++;
	}

	public void open() {
		System.out.println("Kasse " + index + " geoeffnet.");
		count++;
		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public void run() {
		if (index != 1)
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		long startTimeQueue = System.currentTimeMillis();
		double randomTimeQueue = (Math.random() * 4000) + 6000;

		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if ((System.currentTimeMillis() - startTimeQueue) > randomTimeQueue) {

				startTimeQueue = System.currentTimeMillis();
				randomTimeQueue = (Math.random() * 4000) + 6000;

				count--;

				System.out.println("Abarbeitung von Kunde an Kasse " + index
						+ ". " + count + " verbleiben.");
				myLock.lock();
				balance.add(index, Math.random() * 100);
				balance.out();
				myLock.unlock();
				if (count == 0) {
					count--;
					System.out.println("Kasse " + index + " geschlossen.");
					break;

				}
			}

		}

	}

}
