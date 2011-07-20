package microxt.entity.generator;

public class PropertyData {
	StringBuilder annotations;
	String type;
	String name;
	
	public PropertyData(){
	}

	public StringBuilder getAnnotation() {
		return annotations;
	}

	public void setAnnotation(StringBuilder annotation) {
		this.annotations = annotation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String parameterName) {
		this.name = parameterName;
	}
}
