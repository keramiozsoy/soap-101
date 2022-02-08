package com.service.country.file;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.NONE)
@Slf4j
public class XMLFileOperation {
    private static DocumentBuilder documentBuilder;
    private static Transformer transformer;

    static {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void createDirectoryIfNotExist(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            boolean createdFolder = dir.mkdirs();
            if (!createdFolder) {
                log.error("Could not created folder -> [{}]", path);
            }
        }
    }

    public static boolean deleteOldXmlFiles(String path) {
        try {
            FileUtils.cleanDirectory(new File(path));
            return true;
        } catch (IOException e) {
            // no-op
            return false;
        }
    }

    public static void writeXmlToPath(Document document, String documentName, String path) {
        try {
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path + File.separator + documentName));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            log.error("Could not write to path -> [{}]", e.getLocalizedMessage());
        }
    }

    public static Optional<List<File>> readFiles(String localPath) {
        try {
            return Optional.of(Files.walk(Paths.get(localPath))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            // no-op
        }
        return Optional.empty();
    }

    public static Document prepareXml(){
        Document document = documentBuilder.newDocument();
        final Node nodeCountry = createCountryXmlObject(document);
        final Node nodeState = createStateXmlObject(document, nodeCountry);
        final List<DataExampleObject> list = new ArrayList<>();
        list.add(DataExampleObject.builder().id("1").name("A").currency("usd").capitalCity("c1").build());
        list.add(DataExampleObject.builder().id("2").name("B").currency("eur").capitalCity("c2").build());
        list.forEach(obj -> createStateDetailXmlObject(document, nodeState, obj));

        return document;
    }

    private static Node createCountryXmlObject(Document document) {
        return XmlNodeOperation.createCountryNode(document);
    }
    private static Node createStateXmlObject(Document document, Node parent) {
        return XmlNodeOperation.createStateNode(document, parent);
    }
    private static Node createStateDetailXmlObject(Document document, Node parent, DataExampleObject data) {
        return XmlNodeOperation.createStateDetailNode(document, parent, data);
    }
}
