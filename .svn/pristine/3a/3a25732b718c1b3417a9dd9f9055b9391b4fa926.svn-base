package org.bureaumanager.bureaumanagerpro.sysConfig.utils;


import org.bureaumanager.bureaumanagerpro.sysConfig.dao.SysDictMapper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 字典工具类
 * @author jeeplus
 * @version 2013-5-29
 */
public class DictUtils {
	
	private static SysDictMapper dictMapper = SpringContextHolder.getBean(SysDictMapper.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	
//	public static String getDictLabel(String value, String type, String defaultValue){
//		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
//			for (SysDictDto dict : getDictList(type)){
//				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
//					return dict.getLabel();
//				}
//			}
//		}
//		return defaultValue;
//	}
	
//	public static String getDictLabels(String values, String type, String defaultValue){
//		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
//			List<String> valueList = Lists.newArrayList();
//			for (String value : StringUtils.split(values, ",")){
//				valueList.add(getDictLabel(value, type, defaultValue));
//			}
//			return StringUtils.join(valueList, ",");
//		}
//		return defaultValue;
//	}
//
//	public static String getDictValue(String label, String type, String defaultLabel){
//		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
//			for (SysDictDto dict : getDictList(type)){
//				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
//					return dict.getValue();
//				}
//			}
//		}
//		return defaultLabel;
//	}
//
//	public static List<SysDictDto> getDictList(String type){
//		@SuppressWarnings("unchecked")
//		Map<String, List<SysDictDto>> dictMap = (Map<String, List<SysDictDto>>)CacheUtils.get(CACHE_DICT_MAP);
//		if (dictMap==null){
//			dictMap = Maps.newHashMap();
//			for (SysDictDto dict : dictMapper.findAllList(new SysDictDto())){
//				List<SysDictDto> dictList = dictMap.get(dict.getType());
//				if (dictList != null){
//					dictList.add(dict);
//				}else{
//					dictMap.put(dict.getType(), Lists.newArrayList(dict));
//				}
//			}
//			CacheUtils.put(CACHE_DICT_MAP, dictMap);
//		}
//		List<SysDictDto> dictList = dictMap.get(type);
//		if (dictList == null){
//			dictList = Lists.newArrayList();
//		}
//		return dictList;
//	}

	/*
	 * 反射根据对象和属性名获取属性值
	 */
	public static Object getValue(Object obj, String filed) {
		try {
			Class clazz = obj.getClass();
			PropertyDescriptor pd = new PropertyDescriptor(filed, clazz);
			Method getMethod = pd.getReadMethod();//获得get方法

			if (pd != null) {

				Object o = getMethod.invoke(obj);//执行get方法返回一个Object
				return o;

			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
