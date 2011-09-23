package mobile.core.message;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Test {

    public String readFile(String path) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(path);
        String data = this.readStream(fileInputStream);
        fileInputStream.close();
        return data;
    }

    public String readStream(InputStream stream) throws Exception {
        byte b[] = new byte[9999];
        int c = 0;
        String data = "";
        do {
            c = stream.read(b);
            if (c > 0) {
                data += new String(b, 0, c, "UTF-8");
            }
        } while (c > 0);
        return data;
    }

    public void writeFile(String path, String data) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(path);
        outputStream.write(data.getBytes());
        outputStream.close();
    }

    public static void main(String[] args) {
        Test test = new Test();
        try {
            String data = test.readFile("D:/Scala/Develop/mensaje/mensaje.xml");
            XMLParser xml = new XMLParser(data);
            Message message = new Message(xml);
            String salida = message.toString();
            test.writeFile("D:/Scala/Develop/mensaje/salida.xml", salida);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
