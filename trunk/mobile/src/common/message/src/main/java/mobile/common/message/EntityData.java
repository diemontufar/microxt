package mobile.common.message;

import java.util.ArrayList;
import java.util.List;

public class EntityData extends Data {

	public class Filter{
		private String filter;
		private String comparator;
		private String typeAndValue;
		private String value;
		
		public Filter(){
		}
		
		public Filter(String filter, String comparator, String typeAndValue, String value){
			this.filter = filter;
			this.comparator = comparator;
			this.typeAndValue = typeAndValue;
			this.value = value;
		}

		public String getFilter() {
			return filter;
		}

		public void setFilter(String filter) {
			this.filter = filter;
		}

		public String getComparator() {
			return comparator;
		}

		public void setComparator(String comparator) {
			this.comparator = comparator;
		}

		public String getTypeAndValue() {
			return typeAndValue;
		}

		public void setTypeAndValue(String typeAndValue) {
			this.typeAndValue = typeAndValue;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public final static String ALIAS = "_alias";
	public final static String QRY_FIELDS = "_qry_fields";
	public final static String OFFSET_PAGE = "_offset_page";
	public final static String LIMIT_PAGE = "_limit_page";
	public final static String TOTAL_PAGE = "_total_page";
	public final static String ORDER_BY = "_order_by";
	public final static String ORDER_DIR = "_order_dir";
	public final static String FILTERS = "_filters";

	public EntityData() {
	}

	public EntityData(String id) {
		super(id);
	}

	public void setDataId(String dataId) {
		super.setDataId(dataId);
	}

	public String getDataId() {
		return super.getDataId();
	}

	public void addField(String name, String value) {
		super.addField(name, value);
	}

	public void addField(Field field) {
		super.addField(field);
	}

	public Field getField(String name) {
		return super.getField(name);
	}

	public void removeField(String name) {
		super.removeField(name);
	}

	public List<Field> getFieldList() {
		return super.getFieldList();
	}

	public void setFieldList(List<Field> fieldList) {
		super.setFieldList(fieldList);
	}

	public void addItem(Item item) {
		super.addItem(item);
	}

	public Item getItem(Integer number) {
		return super.getItem(number);
	}

	public void removeItem(Integer number) {
		super.removeItem(number);
	}

	public void setItemList(List<Item> itemList) {
		super.setItemList(itemList);
	}

	public List<Item> getItemList() {
		return super.getItemList();
	}

	public Field getCreateField(String name) {
		return super.getCreateField(name);
	}

	public void setFieldValue(String name, String value) {
		super.setFieldValue(name, value);
	}

	public String getFieldValue(String name) {
		return super.getFieldValue(name);
	}

	// -------------------------------------------
	// Special fields
	// -------------------------------------------
	public void setAlias(String alias) {
		setFieldValue(EntityData.ALIAS, alias);
	}

	public String getAlias() {
		return getFieldValue(EntityData.ALIAS);
	}

	public void setQueryFields(String queryFields) {
		setFieldValue(EntityData.QRY_FIELDS, queryFields);
	}

	public String getQueryFields() {
		return getFieldValue(EntityData.QRY_FIELDS);
	}

	public void setOffset(Integer offset) {
		setFieldValue(EntityData.OFFSET_PAGE, offset.toString());
	}

	public Integer getOffset() {
		Integer number = null;
		if (getFieldValue(EntityData.OFFSET_PAGE) != null) {
			number = Integer.parseInt(getFieldValue(EntityData.OFFSET_PAGE));
		}
		return number;
	}

	public void setLimit(Integer limit) {
		setFieldValue(EntityData.LIMIT_PAGE, limit.toString());
	}

	public Integer getLimit() {
		Integer number = null;
		if (getFieldValue(EntityData.LIMIT_PAGE) != null) {
			number = Integer.parseInt(getFieldValue(EntityData.LIMIT_PAGE));
		}
		return number;
	}

	public void setTotal(Integer total) {
		setFieldValue(EntityData.TOTAL_PAGE, total.toString());
	}

	public Integer getTotal() {
		Integer number = null;
		if (getFieldValue(EntityData.TOTAL_PAGE) != null) {
			number = Integer.parseInt(getFieldValue(EntityData.TOTAL_PAGE));
		}
		return number;
	}

	public void setOrderBy(String order) {
		setFieldValue(EntityData.ORDER_BY, order);
	}

	public String getOrderBy() {
		return getFieldValue(EntityData.ORDER_BY);
	}

	public void setOrderDir(String orderDir) {
		setFieldValue(EntityData.ORDER_DIR, orderDir);
	}

	public String getOrderDir() {
		return getFieldValue(EntityData.ORDER_DIR);
	}

	public void setFilter(String filters) {
		setFieldValue(EntityData.FILTERS, filters);
	}

	public String getFilters() {
		return getFieldValue(EntityData.FILTERS);
	}
	
	public List<Filter> getLfilters() {
		List<Filter> filters = new ArrayList<Filter>();

		String strFilters = getFilters();
		String[] lFilters = strFilters.split(";");
		for (String filter : lFilters) {
			String[] part = filter.split(":");
			String field = part[0];
			String comparator = part[1];
			String typeAndValue = part[2];
			String value = typeAndValue;
			if(typeAndValue.indexOf("\\)\\)")>0){
				int i = typeAndValue.indexOf("\\)\\)");
				value = typeAndValue.substring(i);
			}
			Filter ofilter = new Filter(field, comparator, typeAndValue, value);
			filters.add(ofilter);
		}
		return filters;
	}
}
