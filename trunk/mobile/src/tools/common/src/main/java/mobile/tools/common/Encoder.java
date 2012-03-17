package mobile.tools.common;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encoder {

	private static Encoder INSTANCE;
	private Cipher cipher;
	private SecretKey secretKey;
	private AlgorithmParameterSpec algorithm;

	private Encoder() throws Exception {
		cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		secretKey = new SecretKeySpec("PRIVATE ".getBytes(), "DES");
		byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
		algorithm = new IvParameterSpec(iv);
		Log.getInstance().info("Create instace of Encoder class");
	}

	private synchronized static Encoder getInstance() throws Exception {
		if (INSTANCE == null) {
			INSTANCE = new Encoder();
		}
		return INSTANCE;
	}

	public static String encrypt(String text) throws Exception {
		if ((text == null) || (text.compareTo("") == 0)) {
			return text;
		}
		getInstance().cipher.init(Cipher.ENCRYPT_MODE, getInstance().secretKey, getInstance().algorithm);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		OutputStream out = new CipherOutputStream(bout, getInstance().cipher);
		out.write(text.getBytes());
		out.flush();
		out.close();
		return byteArrayToHexString(bout.toByteArray());
	}

	public static String decrypt(String text) throws Exception {
		if ((text == null) || (text.compareTo("") == 0)) {
			return text;
		}
		getInstance().cipher.init(Cipher.DECRYPT_MODE, getInstance().secretKey, getInstance().algorithm);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		OutputStream out = new CipherOutputStream(bout, getInstance().cipher);
		out.write(hexStringToByteArray(text));
		out.flush();
		out.close();
		return new String(bout.toByteArray());
	}

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (byte element : b) {
			int v = element & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	private static byte[] hexStringToByteArray(String s) {
		byte[] b = new byte[s.length() / 2];
		for (int i = 0; i < b.length; i++) {
			int index = i * 2;
			int v = Integer.parseInt(s.substring(index, index + 2), 16);
			b[i] = (byte) v;
		}
		return b;
	}
}
