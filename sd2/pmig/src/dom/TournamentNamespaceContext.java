package dom;

import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

public class TournamentNamespaceContext implements NamespaceContext {

    public static final String PREFIX = "t";
    public static final String NAMESPACE = "http://www.dbai.tuwien.ac.at/education/ssd/SS13/uebung/Tournament";

    protected TournamentNamespaceContext() {
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