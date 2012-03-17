package mobile.tools.common.enums;

public enum ObjectionCode {

	SUCCESS("OK", "PROCESO EXITOSO"),
	FAILED("ERROR", "PROCESO FALLIDO"),
	NO_MESSAGE("NO_MENSAJE", "NINGUN MENSAJE RECIBIDO"),
	NO_CLASS("NO_CLASE", "CLASE {0} NO ENCONTRADA"),
	NO_SESSION("NO_SESION", "USUARIO {0} NO TIENE UNA SESION DEFINIDA"),
	ROLE_PROCESS("NO_PERMISO", "EL PROCESO {0} NO ESTA DEFINIDO PARA EL ROL {1}"),
	HOST_ADDRESS("HOST_IP", "EL HOST {0} NO ESTA DEFINIDO"),
	USER_NOT("NO_USUARIO", "EL USUARIO {0} NO EXISTE"),
	USER_NOT_ACTIVE("USUARIO_INACTIVO", "EL USUARIO {0} NO ESTA ACTIVO"),
	USER_PASSWORD("CONTRASEÑA_INCORRECTA", "LA CONTRASEÑA INGRESADA ES INCORRECTA"),
	USER_SESSION("USUARIO_SESION", "EL USUARIO {0} TIENE UNA SESIÓN ACTVA EN EL TERMINAL {1}"),
	USER_STATUS("USUARIO_ESTATUS", "EL ESTATUS PARA EL USUARIO {0} ES {1}");
	
	String code;
	String message;

	ObjectionCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
}