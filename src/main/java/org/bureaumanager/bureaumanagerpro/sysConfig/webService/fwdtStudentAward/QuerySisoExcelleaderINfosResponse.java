
package org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtStudentAward;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="JSONSisoExcelleaderInfos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "jsonSisoExcelleaderInfos"
})
@XmlRootElement(name = "querySisoExcelleaderINfosResponse")
public class QuerySisoExcelleaderINfosResponse {

    @XmlElementRef(name = "JSONSisoExcelleaderInfos", namespace = "http://www.primeton.com/studentExcellAwardService", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jsonSisoExcelleaderInfos;

    /**
     * 获取jsonSisoExcelleaderInfos属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJSONSisoExcelleaderInfos() {
        return jsonSisoExcelleaderInfos;
    }

    /**
     * 设置jsonSisoExcelleaderInfos属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJSONSisoExcelleaderInfos(JAXBElement<String> value) {
        this.jsonSisoExcelleaderInfos = value;
    }

}
