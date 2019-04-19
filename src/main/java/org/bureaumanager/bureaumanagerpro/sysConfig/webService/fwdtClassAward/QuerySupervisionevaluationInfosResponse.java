
package org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtClassAward;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "jsonSuervisionnevaluationInfos"
})
@XmlRootElement(name = "querySupervisionevaluationInfosResponse")
public class QuerySupervisionevaluationInfosResponse {

    @XmlElementRef(name = "JSONSuervisionnevaluationInfos", namespace = "http://www.primeton.com/teacherAwardService", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jsonSuervisionnevaluationInfos;


    public JAXBElement<String> getJSONSuervisionnevaluationInfos() {
        return jsonSuervisionnevaluationInfos;
    }


    public void setJSONSuervisionnevaluationInfos(JAXBElement<String> value) {
        this.jsonSuervisionnevaluationInfos = value;
    }

}
