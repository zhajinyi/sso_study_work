package org.bureaumanager.bureaumanagerpro.sysConfig.utils;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.bureaumanager.bureaumanagerpro.sysConfig.dao.SysMenuMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.dao.SysOrganizationMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.dao.SysRoleMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.dao.SysUserMapper;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysMenuDto;
import org.bureaumanager.bureaumanagerpro.sysConfig.pojo.dto.SysUserDto;

import java.util.Date;
import java.util.List;


public class UserUtils {

	private static SysUserMapper userMapper = SpringContextHolder.getBean(SysUserMapper.class);
	private static SysRoleMapper roleMapper = SpringContextHolder.getBean(SysRoleMapper.class);
	private static SysMenuMapper menuMapper = SpringContextHolder.getBean(SysMenuMapper.class);
	private static SysOrganizationMapper orgMapper = SpringContextHolder.getBean(SysOrganizationMapper.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_Organization_ID_ = "oid_";

	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_ORG_LIST = "orgList";
	public static final String CACHE_ORG_ALL_LIST = "orgAllList";
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static SysUserDto get(String id){
		SysUserDto user = userMapper.selectByPrimaryKey(USER_CACHE_ID_);
		if (user ==  null){
			user = userMapper.selectByPrimaryKey(id);
			if (user == null){
				return null;
			}

//			user.setRoleList(roleMapper.selectByPrimaryKey(user.getEmpId()));
		}
		return user;
	}
	
	/**
	 * 根据登录名获取用户
	 * @param loginName
	 * @return 取不到返回null
	 */
//	public static SysUserDto getByLoginName(String loginName){
//		SysUserDto user = (SysUserDto)CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
//		if (user == null){
//			user = userMapper.getByLoginName(new SysUserDto(null, loginName));
//			if (user == null){
//				return null;
//			}
//			user.setRoleList(roleMapper.findList(new Role(user)));
//		}
//		return user;
//	}
	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_AREA_LIST);
		removeCache(CACHE_ORG_LIST);
		removeCache(CACHE_ORG_ALL_LIST);
//		UserUtils.clearCache(getUser());
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
//	public static void clearCache(SysUserDto user){
//		if (user.getOffice() != null && user.getOffice().getId() != null){
//		}
//	}
	
	/**
	 * 获取当前用户
	 * @return 取不到返回 new SysUserDto()
	 */
//	public static SysUserDto getUser(){
//		Principal principal = getPrincipal();
//		if (principal!=null){
//			SysUserDto user = get(principal.getId());
//			if (user != null){
//				return user;
//			}
//			return new SysUserDto();
//		}
//		// 如果没有登录，则返回实例化空的User对象。
//		return new SysUserDto();
//	}

	/**
	 * 获取当前用户角色列表
	 * @return
	 */
//	public static List<Role> getRoleList(){
//		@SuppressWarnings("unchecked")
//		List<Role> roleList = (List<Role>)getCache(CACHE_ROLE_LIST);
//		if (roleList == null){
//			SysUserDto user = getUser();
//			if (user.isAdmin()){
//				roleList = roleMapper.findAllList(new Role());
//			}else{
//				Role role = new Role();
//				role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user.getCurrentUser(), "o", "u"));
//				roleList = roleMapper.findList(role);
//			}
//			putCache(CACHE_ROLE_LIST, roleList);
//		}
//		return roleList;
//	}
	
	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static List<SysMenuDto> getMenuList(){
		@SuppressWarnings("unchecked")
		List<SysMenuDto> menuList = (List<SysMenuDto>)getCache(CACHE_MENU_LIST);
//		if (menuList == null){
//			SysUserDto user = getUser();
//			if (user.isAdmin()){
//				menuList = menuMapper.selectByTemplate(new SysMenuDto());
//			}else{
//				SysMenuDto m = new SysMenuDto();
//				m.setUserId(user.getId());
//				menuList = menuMapper.selectByTemplate(m);
//			}
//			putCache(CACHE_MENU_LIST, menuList);
//		}
		return menuList;
	}
	
	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static SysMenuDto getTopMenu(){
		SysMenuDto topMenu =  getMenuList().get(0);
		return topMenu;
	}
	/**
	 * 获取当前用户授权的区域
	 * @return
	 */
//	public static List<Area> getAreaList(){
//		@SuppressWarnings("unchecked")
//		List<Area> areaList = (List<Area>)getCache(CACHE_AREA_LIST);
//		if (areaList == null){
//			areaList = areaDao.findAllList(new Area());
//			putCache(CACHE_AREA_LIST, areaList);
//		}
//		return areaList;
//	}
	
	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
//	public static List<SysOrganizationDto> getOfficeList(){
//		@SuppressWarnings("unchecked")
//		List<SysOrganizationDto> orgList = (List<SysOrganizationDto>)getCache(CACHE_ORG_LIST);
//		if (orgList == null){
//			SysUserDto user = getUser();
//			if (user.isAdmin()){
//				orgList = orgMapper.findAllList(new SysOrganizationDto());
//			}else{
//				SysOrganizationDto org = new SysOrganizationDto();
//				org.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
//				orgList = orgMapper.findList(org);
//			}
//			putCache(CACHE_ORG_LIST, orgList);
//		}
//		return orgList;
//	}

	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
//	public static List<SysOrganizationDto> getOfficeAllList(){
//		@SuppressWarnings("unchecked")
//		List<SysOrganizationDto> orgList = (List<SysOrganizationDto>)getCache(CACHE_ORG_ALL_LIST);
//		if (orgList == null){
//			orgList = orgMapper.findAllList(new SysOrganizationDto());
//		}
//		return orgList;
//	}
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前登录者对象
	 */
//	public static Principal getPrincipal(){
//		try{
//			Subject subject = SecurityUtils.getSubject();
//			Principal principal = (Principal)subject.getPrincipal();
//			if (principal != null){
//				return principal;
//			}
////			subject.logout();
//		}catch (UnavailableSecurityManagerException e) {
//
//		}catch (InvalidSessionException e){
//
//		}
//		return null;
//	}
	
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
//			subject.logout();
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	// ============== SysUserDto Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
//		Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
//		getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
//		getCacheMap().remove(key);
		getSession().removeAttribute(key);
	}
	
	public static String getTime(Date date){
		StringBuffer time = new StringBuffer();
        Date date2 = new Date();
        long temp = date2.getTime() - date.getTime();    
        long days = temp / 1000 / 3600/24;                //相差小时数
        if(days>0){
        	time.append(days+"天");
        }
        long temp1 = temp % (1000 * 3600*24);
        long hours = temp1 / 1000 / 3600;                //相差小时数
        if(days>0 || hours>0){
        	time.append(hours+"小时");
        }
        long temp2 = temp1 % (1000 * 3600);
        long mins = temp2 / 1000 / 60;                    //相差分钟数
        time.append(mins + "分钟");
        return  time.toString();
	}


//	//发送注册码
//	public static String sendRandomCode(String uid, String pwd, String tel, String randomCode) throws IOException {
//		//发送内容
//		String content = "您的验证码是："+randomCode+"，有效期30分钟，请在有效期内使用。";
//
//		return SMSUtils.send(uid, pwd, tel, content);
//
//	}
	
//	//注册用户重置密码
//	public static String sendPass(String uid, String pwd, String tel, String password) throws IOException {
//		//发送内容
//		String content = "您的新密码是："+password+"，请登录系统，重新设置密码。";
//		return SMSUtils.send(uid, pwd, tel, content);
//
//	}
	
	/**
	 * 导出Excel调用,根据姓名转换为ID
	 */
//	public static SysUserDto getByUserName(String name){
//		SysUserDto u = new SysUserDto();
//		u.setName(name);
//		List<SysUserDto> list = userMapper.findList(u);
//		if(list.size()>0){
//			return list.get(0);
//		}else{
//			return new SysUserDto();
//		}
//	}
	/**
	 * 导出Excel使用，根据名字转换为id
	 */
//	public static SysOrganizationDto getByOfficeName(String name){
//		SysOrganizationDto o = new SysOrganizationDto();
//		o.setName(name);
//		List<SysOrganizationDto> list = orgMapper.findList(o);
//		if(list.size()>0){
//			return list.get(0);
//		}else{
//			return new SysOrganizationDto();
//		}
//	}
	/**
	 * 导出Excel使用，根据名字转换为id
	 */
//	public static Area getByAreaName(String name){
//		Area a = new Area();
//		a.setName(name);
//		List<Area> list = areaDao.findList(a);
//		if(list.size()>0){
//			return list.get(0);
//		}else{
//			return new Area();
//		}
//	}
	
}
