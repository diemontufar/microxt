package mobile.core.message;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.InputSource;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.sun.org.apache.xpath.internal.XPathAPI;


public class XMLParser {

    private static DOMParser parser = new DOMParser();
    private Document document;

    public XMLParser(String xml) throws Exception {
        parser.parse(new InputSource(new ByteArrayInputStream(xml.getBytes())));
        document = parser.getDocument();
    }

    public Document getDocument() {
        return document;
    }

    public Node findNode(String pXpath) throws Exception {
        NodeIterator nodeIterator = this.findNodeIterator(pXpath);
        return nodeIterator.nextNode();
    }

    public Node findNode(Node pNode, String pXpath) throws Exception {
        NodeIterator nodeIterator = this.findNodeIterator(pNode, pXpath);
        return nodeIterator.nextNode();
    }

    public String getValue(String pXpath) throws Exception {
        Node node = findNode(pXpath);
        return (node.getNodeValue() != null) ? node.getNodeValue() : node.getFirstChild().getNodeValue();
    }

    public String getValue(Node pNode, String pXpath) throws Exception {
        Node node = findNode(pNode, pXpath);
        return (node.getNodeValue() != null) ? node.getNodeValue() : node.getFirstChild().getNodeValue();
    }

    public String getValue(Node pNode) throws Exception {
        NodeList nodeList = ((pNode == null) ? document : pNode).getChildNodes();
        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.TEXT_NODE) {
                    return node.getNodeValue();
                }
            }
        }
        return null;
    }

    public NodeIterator findNodeIterator(String pXpath) throws Exception {
        return XPathAPI.selectNodeIterator(this.document, pXpath);
    }

    public NodeIterator findNodeIterator(Node pNode, String pXpath) throws Exception {
        Document document = new DocumentImpl();
        return XPathAPI.selectNodeIterator(document.importNode(pNode, true), pXpath);
    }

    public Node getChildByTagName(String name) throws Exception {
        return getChildByTagName(this.document.getDocumentElement(), name);
    }

    public Node getChildByTagName(Node pNode, String name) throws Exception {
        try {
            Element element = (Element)pNode;
            return element.getElementsByTagName(name).item(0);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public NodeList getChildrenByTagName(Node pNode, String name) throws Exception {
        try {
            Element element = (Element)pNode;
            return element.getElementsByTagName(name);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public List<Node> getChildNodes() throws Exception {
        return this.getChildNodes(null);
    }

    public List<Node> getChildNodes(Node pNode) throws Exception {
        List<Node> listNode = new ArrayList<Node>();
        NodeList nodeList = ((pNode == null) ? document : pNode).getChildNodes();
        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    listNode.add(node);
                }
            }
        }
        return listNode;
    }

    public List<Node> getSingleChildren() throws Exception {
        return this.getSingleChildren(null);
    }

    public List<Node> getSingleChildren(Node pNode) throws Exception {
        List<Node> listNode = new ArrayList<Node>();
        List<Node> childNodes = this.getChildNodes(pNode);
        for (Node node : childNodes) {
            List<Node> ln = this.getChildNodes(node);
            if (ln.size() < 1) {
                listNode.add(node);
            }
        }
        return listNode;
    }

    public List<Node> getComplexChildren() throws Exception {
        return getComplexChildren(null);
    }

    public List<Node> getComplexChildren(Node pNode) throws Exception {
        List<Node> listNode = new ArrayList<Node>();
        List<Node> childNodes = this.getChildNodes(pNode);
        for (Node node : childNodes) {
            List<Node> ln = this.getChildNodes(node);
            if (ln.size() > 0) {
                listNode.add(node);
            }
        }
        return listNode;
    }
}
