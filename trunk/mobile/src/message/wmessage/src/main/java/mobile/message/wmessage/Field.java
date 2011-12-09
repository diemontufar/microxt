package mobile.message.wmessage;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;

public class Field {

	private String name;

	private String value;

	public Field(String name, String value) {
		setName(name.trim());
		if (value != null) {
			setValue(value.trim());
		}
	}

	public Field(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			String name = element.getNodeName();
			if (name != null && name.compareTo("") != 0) {
				setName(name.trim());
			}
			String value = element.getNodeValue();
			if (value != null && value.compareTo("") != 0) {
				setValue(value.trim());
			}
		}
	}

	public String toXML() {
		String field = "<" + getName();
		if (getValue() != null && getValue().compareTo("") != 0) {
			field += ">" + getValue() + "</ " + getName() + ">";
		} else {
			field += "/>";
		}
		return field;
	}

	public String toJSON() {
		String field = "\"" + getName() + "\":";
		if (getValue() != null && getValue().compareTo("") != 0) {
			field += "\"" + getValue() + "\"";
		} else {
			field += "\"\"";
		}
		return field;
	}

	public void setName(String fieldName) {
		if (fieldName != null) {
			name = fieldName.trim();
		}
	}

	public String getName() {
		return name;
	}

	public void setValue(String fieldValue) {
		if (fieldValue != null) {
			value = fieldValue.trim();
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
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
		return new Date(dateTimeFormat.parse(value).getTime());
	}

	public Timestamp getTimeValue() throws Exception {
		if (value.indexOf('.') < 0) {
			value += ".0";
		}
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
		return new Timestamp(dateTimeFormat.parse(value).getTime());
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
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return new Timestamp(dateTimeFormat.parse(value).getTime());
	}

	public Boolean getBooleanValue() throws Exception {
		if (value != null && value.compareTo("1") == 0) {
			return true;
		} else {
			return false;
		}
	}
}
