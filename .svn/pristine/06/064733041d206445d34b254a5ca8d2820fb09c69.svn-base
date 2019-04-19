package org.bureaumanager.bureaumanagerpro.sysConfig.utils;

import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @Author: BaoLing
 * @Date: 2019/1/16 17:07
 * @Description: WebServiceUtils
 * @Version: 1.0
 */
public class WebServiceUtils {
    private static final Logger logger= LoggerFactory.getLogger(WebServiceUtils.class);
    //获取年份，过去n年就传参数n
    //今年
    private static String currentYear= DateUtils.getYear();
    //去年
    private static String beforeYear1= DateUtils.pastYears(1);
    //前年
    private static String beforeYear2= DateUtils.pastYears(2);
    //大前年
    private static String beforeYear3= DateUtils.pastYears(3);



    /**
     * 针对{"K":[{"":"","":""},{"":"","":""}]}类型str，
     * 判断是否有数据，
     * 有则返回数据集合，没有则返回null
     * @param str  从接口得到的字符串
     * @param k K值
     * @return
     */
    public static List<Map> isHaveMap(String str,String k) {
        int c=str.indexOf("[");
        if (-1==c){
//            logger.info(k+"暂无数据!");
            return null;
        }
        Map<String,List<Map>> p=(Map) JSONArray.parse(str);
        List<Map> wList = p.get(k);
        return wList;
    }
}
