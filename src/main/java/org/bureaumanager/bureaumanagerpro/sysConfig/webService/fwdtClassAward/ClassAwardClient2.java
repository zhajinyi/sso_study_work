package org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtClassAward;

import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoExcellclassInfoDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.SisoExcellclassInfoService;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl.SisoExcellclassInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.WebServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class ClassAwardClient2 {
    private static final Logger logger= LoggerFactory.getLogger(ClassAwardClient2.class);
    private static final TeacherAwardService service = new TeacherAwardServiceService().getPort(TeacherAwardService.class);

    @Autowired
    private SisoExcellclassInfoServiceImpl spImpl4;


    private static ClassAwardClient2 classAwardClient2;

    @PostConstruct
    public void init() {
        classAwardClient2 = this;
        classAwardClient2.spImpl4 = this.spImpl4;
    }


    private static final String K2 = "SisoExcellclassapplyImpls";

    public static void doRun(){
//判断是否有数据传过来，有则返回List，无则返回null
        String[] a={"2016-2017","2017-2018","2018-2019"};
        for (int i=0;i<a.length;i++){
            List<Map> excellClassList = WebServiceUtils.isHaveMap(service.queryExcellClassINfos(a[i]),K2);
            if (null!=excellClassList){
                doSave(excellClassList);
            }else {
                logger.info(K2+"没有数据!");
            }
        }
    }

    private static void doSave(List<Map> excellClassList){
        for (Map p:excellClassList){
            SisoExcellclassInfoDto pDto =new SisoExcellclassInfoDto();
            String id = p.get("applyid").toString();
            Boolean isUpdate = classAwardClient2.spImpl4.countByPrimaryKey(id);
            pDto.setId(id);
            pDto.setEmpcode(p.get("empcode").toString());
            pDto.setEmpname(p.get("empname").toString());
            pDto.setOrgid(p.get("orgid").toString());
            pDto.setOrgname(p.get("orgname").toString());
            pDto.setClassName(p.get("classname").toString());
            pDto.setExcellYear(p.get("excellyear").toString());
            pDto.setCardId(p.get("caedid").toString());
            pDto.setPhone(p.get("phone").toString());
            pDto.setMainStrong(p.get("mainstorg").toString());
            pDto.setExcellclassTime(p.get("endtime").toString());
            pDto.setClassid(p.get("empty1").toString());
            if (isUpdate){
                logger.info("update : K为"+K2+", id = "+id+"的数据");
                classAwardClient2.spImpl4.updateEntityByPrimaryKey(pDto);
            }else {
                logger.info("insert : K为"+K2+", id = "+id+"的数据");
                classAwardClient2.spImpl4.insertEntity(pDto);
            }
        }
    }
}
