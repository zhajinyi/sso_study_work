package org.bureaumanager.bureaumanagerpro.model.stuSystem.controller;

import org.bureaumanager.bureaumanagerpro.model.stuSystem.service.impl.SisoWalkSchoolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/stu1")
public class SisoWalkSchoolController {

    @Autowired
    SisoWalkSchoolServiceImpl sisoWalkSchoolService;

    @RequestMapping(value = "/walk")
    public String walk(){
        boolean flag = sisoWalkSchoolService.countByPrimaryKey("202");
        System.out.println(flag);
        return "success";
    }

}
