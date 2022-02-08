package com.service.country.file;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.service.country.file.XmlConstants.*;

@NoArgsConstructor(access = AccessLevel.NONE)
public class XmlNodeOperation {

    public static Node createCountryNode(Document document) {
        Element root = createNameSpaceElement(document, COUNTRY_URI, COUNTRY, COUNTRY_PREFIX);
        attributeHandler(root, XMLNS, COUNTRY_SPECIFIC_PREFIX_USA, COUNTRY_SPECIFIC_URI_USA);
        attributeHandler(root, XMLNS, COUNTRY_SPECIFIC_PREFIX_ENG, COUNTRY_SPECIFIC_URI_ENG);
        return document.appendChild(root);
    }

    public static Node createStateNode(Document document, Node parent) {
        Element state = createNameSpaceElement(document, STATE_URI, STATE, STATE_PREFIX);
        attributeHandler(state, XMLNS, STATE_SPECIFIC_PREFIX_USA_NYC, STATE_SPECIFIC_URI_USA_NYC);
        attributeHandler(state, XMLNS, STATE_SPECIFIC_PREFIX_ENG_LND, STATE_SPECIFIC_URI_ENG_LND);
        return appendElementToNode(parent, state);
    }

    public static Node createStateDetailNode(Document document, Node parent, DataExampleObject obj) {
        Element stateDetailElement = createElement(document, STATE_PREFIX, STATE_DETAIl);

        Element id = createElement(document, STATE_PREFIX, ID);
        createTextNode(document, id, obj.getId());
        appendElementToElement(stateDetailElement, id);

        Element name = createElement(document, STATE_PREFIX, NAME);
        createTextNode(document, name, obj.getName());
        appendElementToElement(stateDetailElement, name);

        Element currency = createElement(document, STATE_PREFIX, CURRENCY);
        createTextNode(document, currency, obj.getCurrency());
        appendElementToElement(stateDetailElement, currency);

        Element capitalCity = createElement(document, STATE_PREFIX, CAPITAL_CITY);
        createTextNode(document, capitalCity, obj.getCapitalCity());
        appendElementToElement(stateDetailElement, capitalCity);

        return appendElementToNode(parent, stateDetailElement);
    }

    private static Element createNameSpaceElement(Document document, String namespaceUri, String qualifiedName, String prefix) {
        Element element = document.createElementNS(namespaceUri, qualifiedName);
        element.setPrefix(prefix);
        return element;
    }

    private static void attributeHandler(Element element, String preTag, String prefix, String namespace) {
        element.setAttribute(preTag + ":" + prefix, namespace);
    }

    private static Node appendElementToNode(Node parent, Element child) {
        return parent.appendChild(child);
    }

    private static void appendElementToElement(Element parent, Element child){
        parent.appendChild(child);
    }

    private static Element createElement(Document document, String namespace, String qualifiedName) {
        return document.createElement(namespace + ":" + qualifiedName);
    }

    private static void createTextNode(Document document, Element parent, String data) {
        parent.appendChild(document.createTextNode(Objects.isNull(data) ? StringUtils.EMPTY : data));
    }


}
