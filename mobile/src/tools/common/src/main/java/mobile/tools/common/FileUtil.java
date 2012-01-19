package mobile.tools.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {

	private FileUtil() {
		Log.getInstance().info("Create instace of FileUtil class");
	}

	public static String readFile(String path) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(path);
		String data = "";
		byte b[] = new byte[9999];
		int c = 0;
		do {
			c = fileInputStream.read(b);
			if (c > 0) {
				data += new String(b, 0, c, "UTF-8");
			}
		} while (c > 0);
		fileInputStream.close();
		return data;
	}

	public static void writeFile(String path, String data) throws Exception {
		String dir = path.substring(0, path.lastIndexOf('/'));
		File file = new File(dir);
		file.mkdirs();
		FileOutputStream outputStream = new FileOutputStream(path);
		outputStream.write(data.getBytes());
		outputStream.close();
	}
}