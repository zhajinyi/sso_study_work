package org.bureaumanager.bureaumanagerpro.sysConfig.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class GlobalController {

    @RequestMapping("/stuWork")
    public String index(HttpRequest request)  {
        return "forward:index.html";
    }

    @RequestMapping(value = "/mul")
    public int mulParam(int param) {
        return 9/param;
    }

    /**
     * 处理404.400.500的错误请求控制器
     * @return
     */
    @RequestMapping("error-404")
    public String toPage404(){
        return "error/404";
    }
    @RequestMapping("error-400")
    public String toPage400(){
        return "error/404";
    }
    @RequestMapping("error-500")
    public String toPage500(){
        return "error/404";
    }
}
