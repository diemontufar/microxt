package mobile.tools.common.convertion;

import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;

public class ConvertionManager {

	private static Date convertDate(String sValue) throws ParseException {
		String value = sValue;
		String d[] = value.split(" ");
		if (d.length == 2) {
			value = d[0];
		}
		return new Date(FormatDates.getInstance().getDateFormat()
				.parse(value.replaceAll("\\/", "-")).getTime());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T convertObject(Object value, String type) {
		try {
			Class c = null;
	        try {
	            c = Class.forName(type);
	        } catch (ClassNotFoundException e) {
	            c = String.class;
	        }
	        
	        return (T) ConvertionManager.convertObject(value, c);
		} catch (Exception e) {
			throw new Error(e);
		}
	}
	
	/**
	 * Metodo Encargado de la conversión del tipo de Dato de un Objeto
	 * 
	 * @param pValue
	 *            Objeto que se quiere convertir.
	 * @param pType
	 *            Tipo de dato al que se quiere convertir.
	 * @return Object Objeto ya convertido.
	 * @throws Exception
	 */
	public static <T> T convertObject(Object pValue, Class<T> pType) {
		try {
			return ConvertionManager.convertObject(pValue, pType, false);
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T convertObject(Object pValue, Class<T> pType,
			boolean pUpper) throws Exception {
		Object value = pValue;
		if (value == null) {
			return null;
		}
		if ((pType.getName().compareTo("java.lang.String") == 0) && pUpper) {
			value = value.toString().toUpperCase();
		}

		if ((value.getClass().getName().compareTo(pType.getName()) == 0)) {
			return (T) value;
		}
		if ((pType.getName().compareTo("java.lang.String") != 0)
				&& (pValue.toString().compareTo("") == 0)) {
			return null;
		}
		if ((value.getClass().getName().compareTo("java.lang.String") == 0)
				&& (pType.getName().compareTo("java.lang.Boolean") == 0)) {
			String val = String.valueOf(value).trim();
			T oVal;
			if (val.compareTo("1") == 0) {
				oVal = (T) new Boolean(true);
			} else {
				oVal = (T) new Boolean(false);
			}
			return oVal;
		}

		try {
			T oVal = ConvertionManager.convertValueByType(value, pType);
			if (oVal != null) {
				return oVal;
			}
		} catch (ParseException ex) {
			throw new Error(ex);
		}

		return (T) value;
	}

	private static Timestamp convertTimestamp(String sValue)
			throws ParseException {
		String val = sValue;
		if (val.indexOf('.') < 0) {
			val += ".0";
		}
		String d[] = val.split(" ");
		if (d.length == 2) {
			val = d[0] + " " + d[1].replaceAll("-", ":");
		}
		try {
			Timestamp times = new Timestamp(FormatDates.getInstance()
					.getTimestampFormat().parse(val).getTime());
			String sTimestamp = FormatDates.getInstance()
					.getTimestampFormat().format(times);
			if (sTimestamp.compareTo(val) == 0) {
				return times;
			}
			return Timestamp.valueOf(val);
		} catch (Exception e) {
			return new Timestamp(FormatDates.getInstance()
					.getDateFormat().parse(val).getTime());
		}
	}

	private static Blob manageBlob(Object value) throws Exception {
		byte[] b = null;
		if (value instanceof String) {
			b = ((String) value).getBytes();
		} else {
			b = (byte[]) value;
		}
		return (Blob) createBLOB(b);
	}

	@SuppressWarnings("unchecked")
	private static <T> T convertValueByType(Object value, Class<T> pType)
			throws Exception {
		try {
			return pType.cast(value);
		} catch (ClassCastException e) {
		}
		if ((pType.getName().compareTo(Blob.class.getName()) == 0)
				|| (value instanceof byte[])) {
			return (T) ConvertionManager.manageBlob(value);
		}
		String sValue = value.toString();
		sValue = ConvertionManager.correctNumberValue(sValue, pType);
		T oVal = ConvertionManager.valFromConstructor(pType, sValue);
		if (oVal != null) {
			return oVal;
		}
		if (pType.getName().compareTo(Timestamp.class.getName()) == 0) {
			return (T) ConvertionManager.convertTimestamp(sValue);
		}
		if (pType.getName().compareTo(Date.class.getName()) == 0) {
			return (T) ConvertionManager.convertDate(sValue);
		}

		if (pType.getName().compareTo(Clob.class.getName()) == 0) {
			return (T) createCLOB(sValue);
		}
		if (pType.getName().compareTo(Blob.class.getName()) == 0) {
			// return BlobProxy.generateProxy(sValue.getBytes());
			return (T) createBLOB(sValue.getBytes());
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> T createCLOB(String sValue) throws Exception {
		// return ClobProxy.generateProxy(sValue);
		try {
			return (T) createCLOBHB36(sValue);
			// (T) Hibernate.createClob(sValue);//

		} catch (NoClassDefFoundError e) {
			Class c = Class.forName("org.hibernate.lob.ClobImpl");
			return (T) c.getConstructor(String.class).newInstance(sValue);
			// return (T) new ClobImpl(sValue);
		} catch (Exception e) {
			Class c = Class.forName("org.hibernate.lob.ClobImpl");
			return (T) c.getConstructor(String.class).newInstance(sValue);
			// return (T) new ClobImpl(sValue);

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> T createBLOB(byte[] pB) throws Exception {
		try {
			return (T) createBLOBHB36(pB);
			// (T) Hibernate.createBlob(pB);

		} catch (NoClassDefFoundError e) {
			Class c = Class.forName("org.hibernate.lob.BlobImpl");
			return (T) c.getConstructor(byte[].class).newInstance(pB);
			// return (T) new BlobImpl(pB);
		} catch (Exception e) {
			if (e instanceof ClassNotFoundException) {
				Class c = Class.forName("org.hibernate.lob.BlobImpl");
				return (T) c.getConstructor(byte[].class).newInstance(pB);
			}
			throw e;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> T createCLOBHB36(String sValue) throws Exception {
		Class c = Class.forName("org.hibernate.engine.jdbc.ClobProxy");
		Method m = c.getMethod("generateProxy", String.class);
		return (T) m.invoke(null, sValue);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> T createBLOBHB36(byte[] sValue) throws Exception {
		Class c = Class.forName("org.hibernate.engine.jdbc.BlobProxy");
		Method m = c.getMethod("generateProxy", byte[].class);
		return (T) m.invoke(null, sValue);
	}

	private static String correctNumberValue(String pValue, Class<?> pType) {
		String sValue = pValue;
		if ((pType.getName().compareTo("java.math.BigDecimal") == 0)
				&& (sValue.indexOf(',') > 0)) {
			sValue = sValue.replaceAll("\\.", "");
			sValue = sValue.replace(',', '.');
		}
		if (((pType.getName().compareTo("java.lang.Integer") == 0) || (pType
				.getName().compareTo("java.lang.Long") == 0))
				&& (sValue.indexOf('.') > 0)) {
			// if (sValue.indexOf('.') > 0) {
			sValue = sValue.substring(0, sValue.indexOf('.'));
			// }
		}
		return sValue;
	}

	public static String readClob(Clob pData) throws Exception {
		Reader read = pData.getCharacterStream();
		char buff[] = new char[9999];
		int car = 0;
		String sVal = "";
		try {
			do {
				car = read.read(buff);
				if (car > 0) {
					sVal += new String(buff, 0, car);
				}
			} while (car > 0);
		} finally {
			read.close();
		}
		return sVal;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T valFromConstructor(String type, String sValue){
		try {
			Class c = Class.forName("org.hibernate.engine.jdbc.BlobProxy");
			return (T) valFromConstructor(c, sValue);
		} catch (Exception e) {
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	private static <T> T valFromConstructor(Class<T> pType, String sValue) {
		Class<?>[] param = { String.class };
		try {
			Constructor<?> c = pType.getConstructor(param);
			Object[] data = { sValue };
			return (T) c.newInstance(data);
		} catch (Exception e) {
			if (pType.getName().compareTo("java.math.BigDecimal") == 0) {
				throw new Error(e);
			}
		}
		return null;
	}

}
