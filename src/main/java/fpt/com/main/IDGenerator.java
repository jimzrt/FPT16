package fpt.com.main;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class IDGenerator {

	Set<Long> idList = new HashSet<Long>();
	private long counter = 1;

	public long giveId() throws Exception {

		if (idList.size() > 0 && Collections.max(idList) > counter) {
			counter = Collections.max(idList) + 1;
		} else {
			while (idList.contains(counter))
				counter++;
		}
		if (counter > 999999)
			throw new IDOverflowException();
		idList.add(counter);
		return counter;
	}

	public void clear() {
		counter = 1;
		idList.clear();
	}

	public void addId(long id) {
		idList.add(id);
	}

}
