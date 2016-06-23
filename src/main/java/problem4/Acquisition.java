package problem4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Acquisition implements Runnable {

	List<Cashpoint> cashpoints = new ArrayList<Cashpoint>();
	boolean stopAcquisition = false;
	Balance balance = new Balance();
	Lock counterLock = new ReentrantLock();
	ArrayList<Integer> cashpointIndex = new ArrayList<Integer>();
	int start = 1;

	public Acquisition() {
		System.out.println("Akquise gestartet.");

		for (int i = 1; i <= 6; i++) {
			cashpoints.add(new Cashpoint(i, balance, counterLock));
		}
	}

	@Override
	public void run() {

		double randomTime = Math.random() * 2000;

		while (!stopAcquisition) {
			try {
				Thread.sleep((long) randomTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counterLock.lock();
			addCustomer();
			counterLock.unlock();

			randomTime = Math.random() * 2000;

		}

		System.out.println("Akquise beendet.");
		System.out.println();

	}

	private void addCustomer() {

		int i = indexOfCashpoint();

		if (!cashpoints.get(i).closed) {
			if (cashpoints.get(i).openable) {
				cashpoints.get(i).open();
			}

			print();

			System.out.println("Neuer Kunde an Kasse " + cashpoints.get(i).index + " eingereiht.");
			System.out.println();

			cashpoints.get(i).addKunde();

			if (cashpoints.get(i).count == 6) {
				openNewCashpoint();
			}

			if (cashpoints.get(i).count == 8) {
				System.out.println("Es befinden sich 8 Kunden an Kasse " + cashpoints.get(i).index + ".");
				System.out.println();

				stopAcquisition = true;
			}
		} else {
			System.out.println("Alle Kassen geschlossen... Es warten bereits " + start++ + " Kunden.\n"
					+ "Und mit jeder weiteren Person wächst ihr Zorn und der Gedanke daran, sich in der anonymen Masse zu verlieren und den Laden einfach anzuzünden.");
			System.out.println();

		}

	}

	private int indexOfCashpoint() {

		return cashpoints.indexOf(Collections.min(cashpoints));

	}

	private void openNewCashpoint() {
		for (Cashpoint cash : cashpoints) {
			if (cash.openable == true) {
				cash.open();
				break;
			}
		}

	}

	private void print() {

		for (Cashpoint cash : cashpoints) {
			System.out.format("%-16s", "Kasse " + cash.index + ":");
		}
		System.out.println();
		for (Cashpoint cash : cashpoints) {
			if (cash.openable || cash.closed) {
				System.out.format("%-4s", "| ");
				System.out.format("%-8s", "closed ");
				System.out.format("%-4s", " |");

			} else {
				StringBuilder str = new StringBuilder();
				System.out.format("%-4s", "| " + cash.count);
				for (int i = 0; i < cash.count; i++) {
					str.append("o");

				}
				System.out.format("%-8s", str.toString());
				System.out.format("%-4s", " |");

			}
		}
		System.out.println();
		System.out.println();

	}

}
