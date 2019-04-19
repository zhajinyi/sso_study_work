
package org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtWalkSchool;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtWalkSchool package. 
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

    private final static QName _QueryWalkSchoolResponseWalkSchools_QNAME = new QName("http://www.primeton.com/walkSchoolInterfaceService", "walkSchools");
    private final static QName _QueryWalkSchoolYear_QNAME = new QName("http://www.primeton.com/walkSchoolInterfaceService", "year");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtWalkSchool
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryWalkSchoolResponse }
     * 
     */
    public QueryWalkSchoolResponse createQueryWalkSchoolResponse() {
        return new QueryWalkSchoolResponse();
    }

    /**
     * Create an instance of {@link QueryWalkSchool }
     * 
     */
    public QueryWalkSchool createQueryWalkSchool() {
        return new QueryWalkSchool();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/walkSchoolInterfaceService", name = "walkSchools", scope = QueryWalkSchoolResponse.class)
    public JAXBElement<String> createQueryWalkSchoolResponseWalkSchools(String value) {
        return new JAXBElement<String>(_QueryWalkSchoolResponseWalkSchools_QNAME, String.class, QueryWalkSchoolResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.primeton.com/walkSchoolInterfaceService", name = "year", scope = QueryWalkSchool.class)
    public JAXBElement<String> createQueryWalkSchoolYear(String value) {
        return new JAXBElement<String>(_QueryWalkSchoolYear_QNAME, String.class, QueryWalkSchool.class, value);
    }

}
