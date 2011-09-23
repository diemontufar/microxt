package mobile.core.message;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Item {

    private Integer number;
    private String action;
    private List<Field> fieldList = new ArrayList<Field>();

    public Item(Integer number) {
        this.number = number;
    }

    public Item(Node node) {
        Element element = (Element)node;
        String number = element.getAttribute(Definition.NUMBER);
        if (number != null && number.compareTo("") != 0) {
            this.number = Integer.valueOf(number.trim());
        }
        String action = element.getAttribute(Definition.ACTION);
        if (action != null && action.compareTo("") != 0) {
            this.action = action.trim();
        }
        NodeList nodeListCampo = element.getElementsByTagName(Definition.FIELD);
        for (int i = 0; i < nodeListCampo.getLength(); i++) {
            Field campo = new Field(nodeListCampo.item(i));
            fieldList.add(campo);
        }
    }

    public String toString() {
        String item = "<" + Definition.ITEM;
        if (this.getNumber() != null) {
            item += " " + Definition.NUMBER + "=\"" + this.getNumber() + "\"";
        }
        if (this.getAction() != null) {
            item += " " + Definition.ACTION + "=\"" + this.getAction() + "\"";
        }
        item += ">";
        for (Field fields : this.getFieldList()) {
            item += fields.toString();
        }
        item += "</" + Definition.ITEM + ">";
        return item;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setAction(String action) {
        this.action = action.trim();
    }

    public String getAction() {
        return action;
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
}
