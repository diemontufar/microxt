package mobile.message.message;

import java.util.List;


public class EntityData extends Data{

	public final static String PROCESS_TYPE = "_type";
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

	public void setId(String dataId) {
		super.setId(dataId);
	}

	public String getId() {
		return super.getId();
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
	
	//-------------------------------------------
	// Special fields
	//-------------------------------------------
	public void setProcessType(String processId) {
		setFieldValue(EntityData.PROCESS_TYPE, processId);
	}

	public String getProcessType() {
		return getFieldValue(EntityData.PROCESS_TYPE);
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
		return Integer.parseInt(getFieldValue(EntityData.OFFSET_PAGE));
	}

	public void setLimit(Integer limit) {
		setFieldValue(EntityData.LIMIT_PAGE, limit.toString());
	}

	public Integer getLimit() {
		return Integer.parseInt(getFieldValue(EntityData.LIMIT_PAGE));
	}

	public void setTotal(Integer total) {
		setFieldValue(EntityData.TOTAL_PAGE, total.toString());
	}

	public Integer getTotal() {
		return Integer.parseInt(getFieldValue(EntityData.TOTAL_PAGE));
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
}