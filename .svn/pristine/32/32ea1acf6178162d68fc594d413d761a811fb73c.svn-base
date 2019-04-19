package org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.controller;

import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoSpecialtyDto;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.impl.SisoSpecialtyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: BaoLing
 * @Date: 2019/3/1 10:55
 * @Description: SpecialtyController
 * @Version: 1.0
 */
@Controller
@RequestMapping("/stu/speciality")
public class SpecialtyController {
    @Autowired
    SisoSpecialtyServiceImpl sisoSpecialityService;
    @RequestMapping(value = "/findIds",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<Map> findIdAndNameByRealName(@RequestParam(value = "q",defaultValue = "") String q){
        List<SisoSpecialtyDto> specialtylist = sisoSpecialityService.selectIdsByName(q);
        List<Map> specialtyIds = new ArrayList<>();
        for (SisoSpecialtyDto s : specialtylist){
            Map specialtyMap= new HashMap();
            specialtyMap.put("id",s.getId());
            specialtyMap.put("text",s.getSpecialtyName());
            specialtyIds.add(specialtyMap);
        }
        return specialtyIds;
    }



}
