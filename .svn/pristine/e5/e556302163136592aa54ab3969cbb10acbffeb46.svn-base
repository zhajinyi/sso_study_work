package org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtPunishs;


import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoPunishmentDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl.SisoPunishmentServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.DateUtils;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.WebServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Author:     BaoLing
 * @Date:    2019/1/15 11:44
 * @Description:  调用webservice接口，将处分和解除处分信息存入数据库
 * @Version:    1.0
 */
@Component
public class FwdtPClient {
  private static final Logger logger= LoggerFactory.getLogger(FwdtPClient.class);
  private static final PunishService service = new PunishServiceService().getPort(PunishService.class);
  @Autowired
  private SisoPunishmentServiceImpl spImpl;
  private static FwdtPClient pClient;
  @PostConstruct
  public void init() {
    pClient = this;
    pClient.spImpl = this.spImpl;
  }
  //invoke business method
  private static String str1 = service.queryPunishInfos();
  private static final String K1 = "SisoPunishImpl";
  private static String str2 = service.queryRePunishApplyInfos();
  private static final String K2 = "SisoRepunishapplyImpls";

  /**
   *运行方法，在WebServiceUtils定时调用
   */
  public static void doRun(){
//判断是否有数据传过来，有则返回List，无则返回null
    List<Map> punishList = WebServiceUtils.isHaveMap(str1,K1);
    List<Map> rePunishList = WebServiceUtils.isHaveMap(str2,K2);
    if (null!=punishList){
      doSave(punishList,rePunishList);
    }else {
      logger.info(K1+"没有数据!");
    }
  }
  private static void doSave(List<Map> punishList,List<Map> rePunishList){
    for (Map p:punishList){
      SisoPunishmentDto pDto =new SisoPunishmentDto();
      String id = p.get("applyid").toString();
      Boolean isUpdate = pClient.spImpl.countByPrimaryKey(id);
      pDto.setId(id);
      pDto.setEmpId(p.get("var1").toString());
      pDto.setReason(p.get("reason").toString());
      pDto.setLevel(p.get("punishlevel").toString());
      String endtime = p.get("endtime").toString().substring(0,19);//年月日时分秒
      pDto.setPunishTime(endtime);
      String relieveTime = "";
      if (null!=rePunishList){
        for (Map r:rePunishList){
          if (id.equals(r.get("empty1").toString())){
            relieveTime = r.get("endtime").toString().substring(0,19);
            pDto.setRelieveTime(relieveTime);
            break;
          }
        }
      }
      String result = relieveTime.isEmpty()?"未解除":"已解除";
      pDto.setResult(result);
      String recordTime = DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
      pDto.setRecordTime(recordTime);

      if (isUpdate){
        logger.info("update : K为"+K1+", id = "+id+"的数据");
        pClient.spImpl.updateEntityByPrimaryKey(pDto);
      }else {
        logger.info("insert : K为"+K1+", id = "+id+"的数据");
        pClient.spImpl.insertEntity(pDto);
      }
    }
  }


}