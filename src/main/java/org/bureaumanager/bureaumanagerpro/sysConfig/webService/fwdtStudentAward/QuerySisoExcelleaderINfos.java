
package org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtStudentAward;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="excellentyear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "excellentyear"
})
@XmlRootElement(name = "querySisoExcelleaderINfos")
public class QuerySisoExcelleaderINfos {

    @XmlElementRef(name = "excellentyear", namespace = "http://www.primeton.com/studentExcellAwardService", type = JAXBElement.class, required = false)
    protected JAXBElement<String> excellentyear;


    public JAXBElement<String> getExcellentyear() {
        return excellentyear;
    }


    public void setExcellentyear(JAXBElement<String> value) {
        this.excellentyear = value;
    }

}
