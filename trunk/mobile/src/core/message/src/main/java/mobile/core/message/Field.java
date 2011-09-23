package mobile.core.message;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class Field {

    private String name;
    private String value;
    private String operator;
    private String order;

    public Field(String name) {
        this.name = name.trim();
    }

    public Field(Node node) {
        Element element = (Element)node;
        String name = element.getAttribute(Definition.NAME);
        if (name != null && name.compareTo("") != 0) {
            this.name = name.trim();
        }
        String value = element.getTextContent();
        if (value != null && value.compareTo("") != 0) {
            this.value = value.trim().toUpperCase();
        }
        String operator = element.getAttribute(Definition.OPERATOR);
        if (operator != null && operator.compareTo("") != 0) {
            this.operator = operator.trim();
        }
        String order = element.getAttribute(Definition.ORDER);
        if (order != null && order.compareTo("") != 0) {
            this.order = order.trim();
        }
    }

    public String toString() {
        String field = "<" + Definition.FIELD;
        if (this.getName() != null && this.getName().compareTo("") != 0) {
            field += " " + Definition.NAME + "=\"" + this.getName() + "\"";
        }
        if (this.getOperator() != null && this.getOperator().compareTo("") != 0) {
            field += " " + Definition.OPERATOR + "=\"" + this.getOperator() + "\"";
        }
        if (this.getOrder() != null && this.getOrder().compareTo("") != 0) {
            field += " " + Definition.ORDER + "=\"" + this.getOrder() + "\"";
        }
        if (this.getValue() != null && this.getValue().compareTo("") != 0) {
            field += ">" + this.getValue() + "</" + Definition.FIELD + ">";
        } else {
            field += "/>";
        }
        return field;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name.trim();
        }
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        if (value != null) {
            this.value = value.trim().toUpperCase();
        }
    }

    public String getValue() {
        return value;
    }

    public Integer getIntegerValue() throws Exception {
        value = value.substring(0, value.indexOf('.'));
        return new Integer(value);
    }

    public Long getLongValue() {
        value = value.substring(0, value.indexOf('.'));
        return new Long(value);
    }

    public BigDecimal getBigDecimalValue() throws Exception {
        if (value.indexOf(',') > 0) {
            value = value.replaceAll("\\.", "");
            value = value.replace(',', '.');
        }
        return new BigDecimal(value);
    }

    public Date getDateValue() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new Date(simpleDateFormat.parse(value).getTime());
    }

    public Timestamp getTimeValue() throws Exception {
        if (value.indexOf('.') < 0) {
            value += ".0";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new Timestamp(simpleDateFormat.parse(value).getTime());
    }

    public Timestamp getTimestampValue() throws Exception {
        if (value.indexOf('.') < 0) {
            value += ".0";
        }
        int length = value.length() - value.indexOf('.');
        if (length >= 4) {
            value = value.substring(0, value.length() - length + 4);
        } else if (length == 3) {
            value += "0";
        } else if (length == 2) {
            value += "00";
        } else if (length == 1) {
            value += "000";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return new Timestamp(simpleDateFormat.parse(value).getTime());
    }


    public Boolean getBooleanValue() throws Exception {
        if (value != null && value.compareTo("1") == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setOperator(String operator) {
        if (operator != null) {
            this.operator = operator.trim();
        }
    }

    public String getOperator() {
        return operator;
    }

    public void setOrder(String order) {
        if (order != null) {
            this.order = order.trim();
        }
    }

    public String getOrder() {
        return order;
    }
}
