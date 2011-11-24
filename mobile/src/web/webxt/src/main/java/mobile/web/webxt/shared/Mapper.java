package mobile.web.webxt.shared;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Mapper {

	public Map<Integer, String> hashmap = new HashMap<Integer, String>();
	public String value = "";
	public int index;

	public Mapper() {

	}

	public void addValues(int index, String value) {
		hashmap.put(index, value.trim());
	}

	public Boolean searchByIndex(int idx) {
		value = hashmap.get(idx);
		if (value != null) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public Boolean searchByValue(String val) {

		Iterator<?> it = hashmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			if (val.compareTo(e.getValue().toString()) == 0) {
				return true;
			}
		}
		return false;
	}

	public int getIndex(String val) {
		int ind = 0;

		Iterator<?> it = hashmap.entrySet().iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry e = (Map.Entry) it.next();
			if (val.compareTo(e.getValue().toString()) == 0) {
				ind = (Integer) e.getKey();
			}
		}
		return ind;
	}

	public void removeItem(int indx) {
		hashmap.remove(indx);
	}
}
