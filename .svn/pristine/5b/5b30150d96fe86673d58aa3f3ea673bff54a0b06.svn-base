package org.bureaumanager.bureaumanagerpro.sysConfig.pojo;

import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserDto;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


public class Msg {
    //状态码
    private int code;
    //提示信息
    private String msg;
    //用户要返回给浏览器的数据
    private Map<String,Object> extend = new HashMap<String,Object>();

    //请求成功，返回信息
    public static Msg success(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }
    //请求失败，返回信息
    public static Msg failure(){
        Msg result = new Msg();
        result.setCode(500);
        result.setMsg("failure");
        return result;
    }

    //将user放入session中
    public static Msg login(HttpSession session, SysUserDto userdDto){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("login success");
        session.setAttribute("user",userdDto);
        return result;
    }

    //将返回对象加入到Msg中
    public Msg add(String key,Object value) {
        this.getExtend().put(key,value);
        return this;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
