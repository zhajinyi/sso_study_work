package org.bureaumanager.bureaumanagerpro.sysConfig.controller;

import org.bureaumanager.bureaumanagerpro.model.stuSystem.service.impl.SisoWalkSchoolServiceImpl;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author BL
 * @Date:
 * @Description
 * @Version
 */
@Controller
@RequestMapping("/tester")
public class TestController {
    //@Resource
    //BbsSortServiceImpl bbsSortServiceImpl;

    @Resource
    SisoWalkSchoolServiceImpl sisoWalkSchoolServiceImpl;

    @RequestMapping(value = "/so")
    public String sortList(HttpServletRequest request) {
        request.setAttribute("Key", "Hello world");
        return "testHello";
    }

    @RequestMapping(value = "/solo")
    public String sortOne(HttpServletRequest request ) {

        //BbsSort sort = bbsSortServiceImpl.selectByPrimaryKey(1);
//        List<BbsSort> list= bbsSortServiceImpl.selectAllBbsSortByPage(pager);
       // request.setAttribute("Key", "Hello world");
     /*   request.setAttribute("sort", sort);
        request.setAttribute("pager", pager);*/
//        request.setAttribute("list",  list);
        return "testHello";
    }

    //    @RequestMapping("/itemsPage")
//    public String itemsPage(HttpServletRequest request,PageBean<BbsSort> pager){
////        List<BbsSort> sList= bbsSortServiceImpl.findItemByPage(pager);
//        PageBean<BbsSort> pager1 =bbsSortServiceImpl.findItemPageBean(pager);
//
////        request.setAttribute("sList",  sList);
//        request.setAttribute("pager",  pager1);
//        return "testHello";
//    }
    @RequestMapping("/itemsPageDo")
    public String itemsPageDo(HttpServletRequest request) {
//        List<BbsSort> sList= bbsSortServiceImpl.findItemByPage(pager);
     /*   PageBean <BbsSort> pager1 = bbsSortServiceImpl.findItemPageBean(pager);*/
//        request.setAttribute("sList",  sList);
      /*  request.setAttribute("list", pager1);*/
        return "testHello";
    }

    @RequestMapping("/itemsPageDo1")
    public String itemsPageDo1(HttpServletRequest request) {
//        List<BbsSort> sList= bbsSortServiceImpl.findItemByPage(pager);
 //       PageBean <BbsSort> pager1 = bbsSortServiceImpl.findItemPageBean(pager);
//        request.setAttribute("sList",  sList);
   //     request.setAttribute("list", pager1);
        return "test1";
    }
}
