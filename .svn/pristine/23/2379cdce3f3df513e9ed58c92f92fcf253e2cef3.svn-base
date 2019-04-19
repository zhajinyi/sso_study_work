package org.bureaumanager.bureaumanagerpro.sysConfig.dao.shiro.session;

import org.apache.shiro.session.Session;

import java.util.Collection;

/**
 *   对于sessionDao，shiro也提供了自己的实现，常用的是ehcache的实现。
 *   Ehcache是jvm级别的，多个应用就会产生多个缓存示例，
 *   无法做到信息跨进程共享。要实现共享，就要重写sessionDao，通过实现我们自己的Dao，做到同一个会话信息的唯一性。
 */
public interface SessionDAO extends org.apache.shiro.session.mgt.eis.SessionDAO {

	/**
	 * 获取活动会话
	 * @param includeLeave 是否包括离线（最后访问时间大于3分钟为离线会话）
	 * @return
	 */
	public Collection<Session> getActiveSessions(boolean includeLeave);
	
	/**
	 * 获取活动会话
	 * @param includeLeave 是否包括离线（最后访问时间大于3分钟为离线会话）
	 * @param principal 根据登录者对象获取活动会话
	 * @param filterSession 不为空，则过滤掉（不包含）这个会话。
	 * @return
	 */
	public Collection<Session> getActiveSessions(boolean includeLeave, Object principal, Session filterSession);
	
}
