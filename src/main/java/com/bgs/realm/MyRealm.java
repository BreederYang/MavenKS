package com.bgs.realm;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.bgs.bean.User;
import com.bgs.service.UserService;
public class MyRealm extends AuthorizingRealm{
	@Autowired
	private UserService se;
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//获取用户名
		String username = principalCollection.getPrimaryPrincipal().toString();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取用户的角色
		Set<String> roles = se.getRoles(username);
		//获取用户的权限
	///	Set<String> prems = se.getpermissions(username);
		info.setRoles(roles);
	//	info.setStringPermissions(prems);
		return info;
	}
	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户名
		String username = token.getPrincipal().toString();
		//查询用户是否存在
		User user = se.getUser(username);
		if(null!=user){
			/**
			 * 获取用户名密码跟用户输入的用户名密码进行匹配
			 * 第一个参数:数据库的用户名
			 * 第二个参数:数据库的密码
			 * 第三个参数:域的名称
			 */
			Subject subject = SecurityUtils.getSubject();
			subject.getSession().setAttribute("user", user);
			
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
					ByteSource.Util.bytes("admin"),this.getName());
			return authenticationInfo;
		}
		return null;
	}

}
