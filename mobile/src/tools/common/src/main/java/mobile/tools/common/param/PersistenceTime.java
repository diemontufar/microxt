package mobile.tools.common.param;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class PersistenceTime {
	/**
	 * Obtiene la fecha por defecto de un registro activo
	 * 
	 * @return Fecha por defecto para registros historicos activos
	 * @throws Exception
	 */
	public static Timestamp getExpiredTime() throws Exception {
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date a = fd.parse("9999-12-31 00:00:00");
		return new Timestamp(a.getTime());
	}

	/**
	 * Obtiene la fecha actual
	 * 
	 * @return Fecha actual
	 * @throws Exception
	 */
	public static Timestamp getCurrentTime() throws Exception {
		java.util.Date date = new java.util.Date();
		return new Timestamp(date.getTime());
	}
}
