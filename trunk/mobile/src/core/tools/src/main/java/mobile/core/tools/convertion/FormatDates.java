package mobile.core.tools.convertion;

import java.text.SimpleDateFormat;

/**
 * Clase que entrega los distintos formatos de fechas.
 * 
 */
public final class FormatDates {

	/**
	 * Intancia Singleton
	 */
	private static FormatDates instance = null;

	/**
	 * Obtiene el singleton
	 * 
	 * @return Referencia al singleton
	 */
	public static FormatDates getInstance() {
		synchronized (FormatDates.class) {

			if (instance == null) {
				instance = new FormatDates();
			}
		}
		return instance;
	}

	/**
	 * Crea una Instancia de FormatDates
	 */
	private FormatDates() {
	}

	/**
	 * Obtiene el formateador de un conteo de tiempo en String
	 * 
	 * @return Formateador.
	 * @throws Exception
	 */
	public SimpleDateFormat getTimeCountFormat() {
		return new SimpleDateFormat("mm:ss.SSS");
	}

	/**
	 * Obtiene el formateador de Horas para su transaporte en String
	 * 
	 */
	public SimpleDateFormat getTimeFormat() throws Exception {
		return new SimpleDateFormat("HH:mm:ss");
	}

	/**
	 * Obtiene el Formateador para Date
	 */
	public SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

	/**
	 * Obtiene el Formateador para DateTime
	 */
	public SimpleDateFormat getDatetimeFormat() throws Exception {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	}

	/**
	 * Obtiene el formateador de Timestamps para su transaporte en String
	 */
	public SimpleDateFormat getTimestampFormat() throws Exception {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * Obtiene el formateador de un anio.
	 */
	public SimpleDateFormat getYearFormat() throws Exception {
		return new SimpleDateFormat("yyyy");
	}
}
