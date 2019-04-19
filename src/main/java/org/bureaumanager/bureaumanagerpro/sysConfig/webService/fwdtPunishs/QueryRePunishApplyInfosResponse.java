
package org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtPunishs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "out1"
})
@XmlRootElement(name = "queryRePunishApplyInfosResponse")
public class QueryRePunishApplyInfosResponse {

    @XmlElementRef(name = "out1", namespace = "http://www.primeton.com/punishService", type = JAXBElement.class, required = false)
    protected JAXBElement<String> out1;


    public JAXBElement<String> getOut1() {
        return out1;
    }


    public void setOut1(JAXBElement<String> value) {
        this.out1 = value;
    }

}
