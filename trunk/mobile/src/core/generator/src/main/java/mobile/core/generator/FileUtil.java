package mobile.core.generator;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	public static void writeFile(String path, String data) throws Exception{
	  	/*
		String file = path.substring(0,path.lastIndexOf('/'));
	  	File f=new File(file);
	  	f.mkdirs();
	    FileOutputStream fos = new FileOutputStream(path);
	    fos.write(data.getBytes());
	    fos.close();
	    */
		File file = new File(path);
		FileUtils.writeStringToFile(file, data);
	}

	public static String readFile(String path) throws Exception {
		File file;
		String data;
		
		try {
			file = new File(path);
			if(!file.exists()){
		    	throw new Exception("File not found");
		    }
		    
		    data = FileUtils.readFileToString(file);
		} catch (Exception e) {
			throw e;
		} finally{
			//TODO corrregir esto
			file=null;
		}
				
	    return data;
	}
	
	/*
	public static void createFolder(String path){
		File file = new File(path);
		file.mkdir();
	}
	*/

}
