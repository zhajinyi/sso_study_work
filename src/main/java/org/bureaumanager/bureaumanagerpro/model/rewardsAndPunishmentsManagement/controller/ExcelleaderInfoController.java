package org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bureaumanager.bureaumanagerpro.base.BaseController;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.pojo.dto.SisoExcelleaderInfoDto;
import org.bureaumanager.bureaumanagerpro.model.rewardsAndPunishmentsManagement.service.impl.SisoExcelleaderInfoServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/excelleader")
public class ExcelleaderInfoController extends BaseController{

    @Autowired
    private SisoExcelleaderInfoServiceImpl sisoExcelleaderInfoService;

    @RequestMapping(value = "/all" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Msg findAllClass(@RequestBody(required = false) SisoExcelleaderInfoDto sisoExcelleaderInfoDto) {
        PageHelper.startPage(sisoExcelleaderInfoDto.getCurrentPage(),sisoExcelleaderInfoDto.getPageSize());
        String orgid = sisoExcelleaderInfoDto.getOrgid();
        if("0".equals(orgid)){
            sisoExcelleaderInfoDto.setOrgid(null);
        }
        List<SisoExcelleaderInfoDto> list=sisoExcelleaderInfoService.queryByTemplate(sisoExcelleaderInfoDto);
        PageInfo page = new PageInfo(list,5);
        return Msg.success().add("PageInfo",page);
    }


    @RequestMapping("/tree")
    @ResponseBody
    @Override
    public List<Map> selectCollegeAndClassTree(@RequestParam(value = "grade",defaultValue = "",required = false) String grade) throws Exception{
//        String trueGrade = grade.equals("请选择")||grade.equals("全部")?"":grade;
        return super.selectCollegeAndClassTree(grade);
    }

}
