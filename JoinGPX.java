package arch;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JoinGPX {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Необходимо минимум два файла");
            System.exit(0);
        }
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document baseDoc = builder.parse(args[0]);
            baseDoc.getDocumentElement().normalize();
           
            Node allTracks = baseDoc.getElementsByTagNameNS("*", "trkseg").item(0);
            for (int index = 1; index < args.length; index++) {
                Document doc = builder.parse(args[index]);
                NodeList tracks = doc.getElementsByTagName("trkpt");
                for (int i=0;i<tracks.getLength();i++) {
                    Node item = tracks.item(i);
                    item = baseDoc.importNode(item, true);
                    allTracks.appendChild(item);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(baseDoc);
            StreamResult result = new StreamResult(new File("full_1.gpx"));
            transformer.transform(source, result);
            System.out.println("Файл сохранен как full.gpx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
