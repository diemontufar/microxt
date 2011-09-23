package mobile.core.message;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Message {

    private List<Data> dataList = new ArrayList<Data>();

    public Message(XMLParser xml) throws Exception {
        Node node = xml.findNode(Definition.MESSAGE);
        NodeList nodeList = xml.getChildrenByTagName(node, Definition.DATA);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            Data data = new Data(n);
            this.addData(data);
        }
    }

    public void addData(Data data) {
        this.dataList.add(data);
    }

    public Data getData(String id) {
        for (Data data : this.dataList) {
            if ((data.getId().compareTo(id)) == 0) {
                return data;
            }
        }
        return null;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public Data removeData(String id) {
        for (Data data : this.dataList) {
            if ((data.getId().compareTo(id)) == 0) {
                dataList.remove(data);
            }
        }
        return null;
    }

    public String toString() {
        String message = "<" + Definition.MESSAGE + ">";
        for (Data data : this.getDataList()) {
            message += data.toString();
        }
        message += "</" + Definition.MESSAGE + ">";
        return message;
    }
}
