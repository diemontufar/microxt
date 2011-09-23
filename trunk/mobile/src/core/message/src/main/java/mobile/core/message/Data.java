package mobile.core.message;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Data {

    private String id;
    private Integer page;
    private Integer size;
    private Integer items;
    private Integer total;
    private String event;
    private List<Field> fieldList = new ArrayList<Field>();
    private List<Item> itemList = new ArrayList<Item>();

    public Data(Node pNode) throws Exception {
        Element element = (Element)pNode;
        String id = element.getAttribute(Definition.ID);
        if (id != null && id.compareTo("") != 0) {
            this.id = id.trim();
        }
        String page = element.getAttribute(Definition.PAGE);
        if (page != null && page.compareTo("") != 0) {
            this.page = Integer.valueOf(page.trim());
        }
        String size = element.getAttribute(Definition.SIZE);
        if (size != null && size.compareTo("") != 0) {
            this.size = Integer.valueOf(size.trim());
        }
        String items = element.getAttribute(Definition.ITEMS);
        if (items != null && items.compareTo("") != 0) {
            this.items = Integer.valueOf(items.trim());
        }
        String total = element.getAttribute(Definition.TOTAL);
        if (total != null && total.compareTo("") != 0) {
            this.total = Integer.valueOf(total.trim());
        }
        String event = element.getAttribute(Definition.EVENT);
        if (event != null && event.compareTo("") != 0) {
            this.event = event.trim();
        }
        NodeList nodeListField = element.getElementsByTagName(Definition.FIELD);
        for (int i = 0; i < nodeListField.getLength(); i++) {
            Node node = nodeListField.item(i);
            if (node.getParentNode().getNodeName().compareTo(Definition.DATA) == 0) {
                Field field = new Field(node);
                this.fieldList.add(field);
            }
        }
        NodeList nodeListItem = element.getElementsByTagName(Definition.ITEM);
        for (int i = 0; i < nodeListItem.getLength(); i++) {
            Node node = nodeListItem.item(i);
            Item item = new Item(node);
            item.setNumber(i + 1);
            this.itemList.add(item);
        }

    }

    public Data(String id) {
        this.id = id.trim();
    }

    public String toString() {
        String data = "<" + Definition.DATA;
        if (this.getId() != null && this.getId().compareTo("") != 0) {
            data += " " + Definition.ID + "=\"" + this.getId() + "\"";
        }
        if (this.getPage() != null) {
            data += " " + Definition.PAGE + "=\"" + this.getPage() + "\"";
        }
        if (this.getSize() != null) {
            data += " " + Definition.SIZE + "=\"" + this.getSize() + "\"";
        }
        if (this.getItems() != null) {
            data += " " + Definition.ITEMS + "=\"" + this.getItems() + "\"";
        }
        if (this.getTotal() != null) {
            data += " " + Definition.TOTAL + "=\"" + this.getTotal() + "\"";
        }
        if (this.getEvent() != null && this.getEvent().compareTo("") != 0) {
            data += " " + Definition.EVENT + "=\"" + this.getEvent() + "\"";
        }
        data += ">";
        for (Field field : this.getFieldList()) {
            data += field.toString();
        }
        for (Item item : this.getItemList()) {
            data += item.toString();
        }
        data += "</" + Definition.DATA + ">";
        return data;
    }

    public void addField(Field field) {
        this.fieldList.add(field);
    }

    public Field getField(String name) {
        for (Field field : this.fieldList) {
            if ((field.getName().compareTo(name)) == 0) {
                return field;
            }
        }
        return null;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void addItem(Item item) {
        this.itemList.add(item);
    }

    public Item getItem(Integer number) {
        try {
            return this.itemList.get(number - 1);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Item> getItemList() {
        return this.itemList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    public Integer getItems() {
        return items;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
