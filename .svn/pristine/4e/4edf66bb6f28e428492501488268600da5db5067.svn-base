package org.bureaumanager.bureaumanagerpro.sysConfig.config;
import org.bureaumanager.bureaumanagerpro.sysConfig.webService.classDataInterface.GetClassInfos;
import org.bureaumanager.bureaumanagerpro.sysConfig.webService.dorm.GetSuShe;
import org.bureaumanager.bureaumanagerpro.sysConfig.webService.empDataInterface.GetEmpInfos;
import org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtBursaryAndHighExcellent.StudentAwardClient;
import org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtClassAward.ClassAwardClient;
import org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtPunishs.FwdtPClient;
import org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtStudentAward.StudentAwardClient2;
import org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtStudentAward.StudentAwardClient3;
import org.bureaumanager.bureaumanagerpro.sysConfig.webService.fwdtWalkSchool.WalkClient;
import org.bureaumanager.bureaumanagerpro.sysConfig.webService.organization.GetOrganizations;
import org.bureaumanager.bureaumanagerpro.sysConfig.webService.studentInfo.GetStudentInfo;
import org.bureaumanager.bureaumanagerpro.sysConfig.webService.userDataInterface.GetUserInfos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;


@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

/*    @Scheduled(fixedRate = 3600000)
    public void printCurrentTime(){
        logger.info("间隔五秒触发："+sdf.format(new Date()));
    }

    @Scheduled(cron = "0 0 20 * * ?")
    public void printCurrentTime2(){
        logger.info("每天20时触发"+sdf.format(new Date()));
    }*/


    @Scheduled(cron = "0 0 1 * * ?")
    public void updateStuInfo() throws Exception {
        /*logger.info("==========宿舍信息开始更新==========");
        GetSuShe.getAllSuShe();
        logger.info("==========宿舍信息更新结束==========");
        logger.info("==========人员信息开始更新==========");
        GetEmpInfos.getAllEmployee();
        logger.info("==========人员信息更新结束==========");
        logger.info("==========班级信息开始更新==========");
        GetClassInfos.getAllBanji();
        logger.info("==========班级信息更新结束==========");
        logger.info("==========部门信息开始更新==========");
        GetOrganizations.getAllOrganizations();
        logger.info("==========部门信息更新结束==========");
        logger.info("==========学生信息开始更新==========");
        GetStudentInfo.getAllstudentinfo();
        logger.info("==========学生信息更新结束==========");
        logger.info("==========用户信息开始更新==========");
        GetUserInfos.getAllUserInfo();
        logger.info("==========用户信息更新结束==========");*/
        logger.info("==========优秀毕业生信息开始更新==========");
        StudentAwardClient3.doRun();
        logger.info("==========优秀毕业生信息更新结束==========");
        logger.info("==========校级优秀班干部信息开始更新==========");
        StudentAwardClient2.doRun();
        logger.info("==========校级优秀班干部信息更新结束==========");
        logger.info("==========奖学金及省级优秀学生开始更新==========");
        StudentAwardClient.doRun();
        logger.info("==========奖学金及省级优秀学生更新结束==========");
        logger.info("==========优秀班级开始更新==========");
        ClassAwardClient.doRun();
        logger.info("==========优秀班级更新结束==========");
        logger.info("==========处分及解除处分开始更新==========");
        FwdtPClient.doRun();
        logger.info("==========处分及解除处分更新结束==========");
        logger.info("==========走读开始更新==========");
        WalkClient.doRun();
        logger.info("==========走读更新结束==========");
    }

}
