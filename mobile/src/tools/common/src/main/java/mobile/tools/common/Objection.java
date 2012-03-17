package mobile.tools.common;

import java.text.MessageFormat;

import mobile.tools.common.enums.ObjectionCode;

public class Objection extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ObjectionCode code;
	private Object[] parameters;

	public Objection(ObjectionCode code, Object... parameters) {
		super(code.getCode());
		setCode(code);
		setParameters(parameters);
	}

	public Objection(Throwable cause, ObjectionCode code, Object... parameters) {
		super(code.getCode());
		super.initCause(cause);
		setCode(code);
		setParameters(parameters);
	}

	public String getMessage() {
		return MessageFormat.format(code.getMessage(), getParameters());
	}

	public String getCode() {
		return code.getCode();
	}

	private void setCode(ObjectionCode code) {
		this.code = code;
	}

	public Object[] getParameters() {
		return parameters;
	}

	private void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
}
