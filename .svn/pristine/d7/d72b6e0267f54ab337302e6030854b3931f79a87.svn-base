package org.bureaumanager.bureaumanagerpro.sysConfig.utils;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @Author: BaoLing
 * @Date: 2019/1/3 11:07
 * @Description: CredentialMatcher,密码校验方法继承SimpleCredentialsMatcher或HashedCredentialsMatcher类，自定义实现doCredentialsMatch方法
 * @Version: 1.0
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //System.out.println("这边是密码校对");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = new String(usernamePasswordToken.getPassword());
        String dbPassword = (String) info.getCredentials();//数据库里的密码
        return this.equals(password, dbPassword);
    }
}
