
package org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtPunishs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtPunishs package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _QueryRePunishApplyInfosResponseOut1_QNAME = new QName("http://www.primeton.com/punishService", "out1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtPunishs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryPunishInfosResponse }
     * 
     */
    public QueryPunishInfosResponse createQueryPunishInfosResponse() {
        return new QueryPunishInfosResponse();
    }

    /**
     * Create an instance of {@link QueryRePunishApplyInfos }
     * 
     */
    public QueryRePunishApplyInfos createQueryRePunishApplyInfos() {
        return new QueryRePunishApplyInfos();
    }

    /**
     * Create an instance of {@link QueryRePunishApplyInfosResponse }
     * 
     */
    public QueryRePunishApplyInfosResponse createQueryRePunishApplyInfosResponse() {
        return new QueryRePunishApplyInfosResponse();
    }

    /**
     * Create an instance of {@link QueryPunishInfos }
     * 
     */
    public QueryPunishInfos createQueryPunishInfos() {
        return new QueryPunishInfos();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/punishService", name = "out1", scope = QueryRePunishApplyInfosResponse.class)
    public JAXBElement<String> createQueryRePunishApplyInfosResponseOut1(String value) {
        return new JAXBElement<String>(_QueryRePunishApplyInfosResponseOut1_QNAME, String.class, QueryRePunishApplyInfosResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/punishService", name = "out1", scope = QueryPunishInfosResponse.class)
    public JAXBElement<String> createQueryPunishInfosResponseOut1(String value) {
        return new JAXBElement<String>(_QueryRePunishApplyInfosResponseOut1_QNAME, String.class, QueryPunishInfosResponse.class, value);
    }

}
