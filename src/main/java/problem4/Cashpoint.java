package problem4;

import java.util.concurrent.locks.Lock;

public class Cashpoint implements Runnable, Comparable<Cashpoint> {

	private Balance balance;
	private Lock myLock;

	public int index;
	public int count = 9999;
	public boolean openable = true;
	public boolean closed = false;

	public Cashpoint(int index, Balance balance, Lock myLock) {

		this.index = index;
		this.balance = balance;
		this.myLock = myLock;

	}

	public void addKunde() {
		count++;
	}

	public void open() {
		System.out.println("Kasse " + index + " geoeffnet.");
		System.out.println();

		openable = false;
		count = 0;
		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public void run() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		double randomTimeQueue = (Math.random() * 4000) + 6000;

		while (true) {
			try {
				Thread.sleep((long) randomTimeQueue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (count != 0) {

				randomTimeQueue = (Math.random() * 4000) + 6000;

				myLock.lock();
				count--;

				System.out.println("Abarbeitung von Kunde an Kasse " + index + ". " + count + " verbleiben.");
				System.out.println();

				balance.getLock().lock();
				balance.add(index, Math.random() * 100);
				balance.out();
				balance.getLock().unlock();
				if (count == 0) {
					count = 10000;
					closed = true;
					System.out.println("Kasse " + index + " geschlossen.");
					System.out.println();
					myLock.unlock();
					break;

				} else {
					myLock.unlock();
				}
			} else {
				randomTimeQueue = 1000;
			}

		}

	}

	@Override
	public int compareTo(Cashpoint o) {
		return Integer.compare(this.count, o.count);
	}

	public String toString() {

		return "Kasse " + index + ": " + count;
	}

}
