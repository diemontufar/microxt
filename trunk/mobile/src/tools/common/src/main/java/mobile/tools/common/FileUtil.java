package mobile.tools.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Manage read and write file
 */
public class FileUtil {
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
//		FileOutputStream outputStream = new FileOutputStream(path);
//		outputStream.write(data.getBytes());
//		outputStream.close();
	  	String file=path.substring(0,path.lastIndexOf('/'));
	  	File f=new File(file);
	  	f.mkdirs();
	    FileOutputStream fos = new FileOutputStream(path);
	    fos.write(data.getBytes());
	    fos.close();
	}
}