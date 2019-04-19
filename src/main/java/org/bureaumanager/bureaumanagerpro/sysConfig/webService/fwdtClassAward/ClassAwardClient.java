package org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtClassAward;

import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoAdvancedInfoDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl.SisoAdvancedInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.WebServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class ClassAwardClient {
    private static final Logger logger= LoggerFactory.getLogger(ClassAwardClient.class);
    private static final TeacherAwardService service = new TeacherAwardServiceService().getPort(TeacherAwardService.class);
    @Autowired
    private SisoAdvancedInfoServiceImpl spImpl3;

    private static ClassAwardClient classAwardClient;

    @PostConstruct
    public void init() {
        classAwardClient = this;
        classAwardClient.spImpl3 = this.spImpl3;
    }
    //invoke business method
    private static String str1 = service.queryAdvancedApplyInfos("");
    private static final String K1 = "SisoAdvancedApplyImpls";
    public static void doRun(){
//判断是否有数据传过来，有则返回List，无则返回null
        List<Map> advanceList = WebServiceUtils.isHaveMap(str1,K1);
        if (null!=advanceList){
            doSave(advanceList);
        }else {
            logger.info(K1+"没有数据!");
        }
    }

    private static void doSave(List<Map> advanceList){
        for (Map p:advanceList){
            SisoAdvancedInfoDto pDto =new SisoAdvancedInfoDto();
            String id = p.get("applyid").toString();
            Boolean isUpdate = classAwardClient.spImpl3.countByPrimaryKey(id);
            pDto.setId(id);
            pDto.setEmpid(p.get("empid").toString());
            pDto.setEmpname(p.get("empname").toString());
            pDto.setOrgid(p.get("orgid").toString());
            pDto.setOrgname(p.get("orgname").toString());
            pDto.setBanjiName(p.get("banjiname").toString());
            pDto.setGraduate(p.get("graduate").toString());
            pDto.setDetail(p.get("detail").toString());
            pDto.setDept(p.get("dept").toString());
            pDto.setSuozaixi(p.get("suozaixi").toString());
            pDto.setAdvancedTime(p.get("endtime").toString());
            pDto.setBanjiId(p.get("banjidaima").toString());
            if (isUpdate){
                logger.info("update : K为"+K1+", id = "+id+"的数据");
                classAwardClient.spImpl3.updateEntityByPrimaryKey(pDto);
            }else {
                logger.info("insert : K为"+K1+", id = "+id+"的数据");
                classAwardClient.spImpl3.insertEntity(pDto);
            }
        }
    }

}
