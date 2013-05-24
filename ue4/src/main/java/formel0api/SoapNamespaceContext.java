package formel0api;

import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

public class SoapNamespaceContext implements NamespaceContext {

    public static final String PREFIX = "S";
    public static final String NAMESPACE = "http://schemas.xmlsoap.org/soap/envelope/";

    protected SoapNamespaceContext() {
    }

    @Override
    public String getNamespaceURI(String prefix) {
        if (prefix.equals(PREFIX)) {
            return NAMESPACE;
        }

        return XMLConstants.NULL_NS_URI;
    }

    @Override
    public String getPrefix(String namespace) {
        if (namespace.equals(NAMESPACE)) {
            return PREFIX;
        }

        return null;
    }

    @Override
    public Iterator getPrefixes(String namespace) {
        return null;
    }
}
