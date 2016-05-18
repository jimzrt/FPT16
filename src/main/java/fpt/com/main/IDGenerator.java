package fpt.com.main;

import java.util.ArrayList;

public class IDGenerator {

	ArrayList<Long> idList = new ArrayList<Long>();
	private long counter = 1;

	public long giveId() throws Exception {
		while (idList.contains(counter))
			counter++;
		if (counter > 999999)
			throw new IDOverflowException();
		idList.add(counter);
		System.out.println(counter);
		System.out.println(counter++);
		return counter++;
	}

	public void clear() {
		counter = 1;
	}

	public void addId(long id) {
		idList.add(id);
	}

}
