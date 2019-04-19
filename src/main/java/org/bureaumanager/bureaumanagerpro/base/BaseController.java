package org.bureaumanager.bureaumanagerpro.base;

import com.alibaba.fastjson.JSONObject;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoClassInfoDto;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.impl.SisoClassInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysOrganizationDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.service.impl.SysOrganizationServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.BeanValidators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: BaoLing
 * @Date: 2018/12/19 9:00
 * @Description: BaseController
 * @Version: 1.0
 */
public class BaseController {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * sisoClassInfoService
     */
    @Autowired
    protected SisoClassInfoServiceImpl sisoClassInfoService;

    /**
     * sysOrganizationService
     */
    @Autowired
    protected SysOrganizationServiceImpl sysOrganizationService;

    /**
     * 管理基础路径
     */
    @Value("${adminPath}")
    protected String adminPath;

    /**
     * 前端基础路径
     */
    @Value("${frontPath}")
    protected String frontPath;

    /**
     * 前端URL后缀
     */
    @Value("${urlSuffix}")
    protected String urlSuffix;

    /**
     * 验证Bean实例对象
     */
    @Autowired
    protected Validator validator;

    /**
     * 服务端参数有效性验证
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
     */
    protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
        try{
            BeanValidators.validateWithException(validator, object, groups);
        }catch(ConstraintViolationException ex){
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            addMessage(model, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }

    /**
     * 服务端参数有效性验证
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
     */
    protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
        try{
            BeanValidators.validateWithException(validator, object, groups);
        }catch(ConstraintViolationException ex){
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            addMessage(redirectAttributes, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }

    /**
     * 服务端参数有效性验证
     * @param object 验证的实体对象
     * @param groups 验证组，不传入此参数时，同@Valid注解验证
     * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
     */
    protected void beanValidator(Object object, Class<?>... groups) {
        BeanValidators.validateWithException(validator, object, groups);
    }

    /**
     * 添加Model消息
     * @param messages
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        model.addAttribute("message", sb.toString());
    }

    /**
     * 获取学院tree,不包含班级，路径/commTree1
     * @return
     * @throws Exception
     */
    public List<Map> selectCollegeTree() throws Exception{
        //获取学院list
        List<SysOrganizationDto> list = sysOrganizationService.selectCollegeTree();
        Map mapTree=new HashMap();
        List<Map<String,String>> mapItemList = new ArrayList<>();
        if (list.size()>0)
            for (SysOrganizationDto o : list){
                Map mapIt=new HashMap();
                String orgName = o.getOrgName();
                mapIt.put("id",o.getId());
                mapIt.put("name",orgName);
                mapTree.put("level","1");
                mapItemList.add(mapIt);
            }

        if (!mapItemList.isEmpty()){
            Map<String,List<Map<String,String>>> m = new HashMap <>();
            m.put("children",mapItemList);
            mapTree.putAll(m);
        }
        mapTree.put("level","0");
        mapTree.put("id","");
        mapTree.put("spread",true);
        mapTree.put("name","苏州工业园区服务外包职业学校");

        // System.out.println(mapTree);

        List<Map> mapsList = new ArrayList<>();
        mapsList.add(mapTree);
        return mapsList;
    }

    /**
     * 获取带有班级的学院tree,路径/commTree2
     * @return
     * @throws Exception
     */
    public List<Map> selectCollegeAndClassTree(@RequestParam(value = "grade",defaultValue = "") String grade) throws Exception{
        //获取学院list
        List<SysOrganizationDto> list = sysOrganizationService.selectCollegeTree();
        Map mapTree=new HashMap();
        List<Map<String,String>> mapItemList = new ArrayList<Map<String,String>>();
        if (list.size()>0)
            for (SysOrganizationDto o : list){
                List<Map> mapClassList = new ArrayList <Map>();
                Map mapIt=new HashMap();
                //获取班级（4年以内在校的班级）
                List<SisoClassInfoDto> clist = sisoClassInfoService.selectEntitiesByOrgId(o.getId(),grade);
                if (clist.size()>0)
                    for (SisoClassInfoDto c : clist){
                        String name = c.getClassName();
                        Map<String,String> mapClass=new HashMap<>();
                        mapClass.put("name",name);
                        mapClass.put("level","2");
                        mapClass.put("id",c.getId());
                        mapClassList.add(mapClass);
                    }
                String orgName = o.getOrgName();
                mapIt.put("children",mapClassList);
                mapIt.put("level","1");
                mapIt.put("id",o.getId());
                mapIt.put("name",orgName);
                mapItemList.add(mapIt);
            }

        if (!mapItemList.isEmpty()){
            Map<String,List<Map<String,String>>> m = new HashMap <>();
            m.put("children",mapItemList);
            mapTree.putAll(m);
        }
        mapTree.put("level","0");
        mapTree.put("id","");
        mapTree.put("spread",true);
        mapTree.put("name","苏州工业园区服务外包职业学校");

        // System.out.println(mapTree);

        List<Map> mapsList = new ArrayList<>();
        mapsList.add(mapTree);
        return mapsList;
    }
}
