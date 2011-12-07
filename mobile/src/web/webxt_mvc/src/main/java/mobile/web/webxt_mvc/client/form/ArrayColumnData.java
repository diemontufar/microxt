package mobile.web.webxt_mvc.client.form;

import java.util.ArrayList;
import java.util.List;

public class ArrayColumnData extends ArrayList<MyColumnData> {
	public ArrayColumnData() {
		super();
	}

	private static final long serialVersionUID = 1L;

	public List<String> getIdFields() {
		List<String> lIdFields = new ArrayList<String>();
		for (int i = 0; i < this.size(); i++) {
			lIdFields.add(get(i).getId());
		}
		return lIdFields;
	}
}
