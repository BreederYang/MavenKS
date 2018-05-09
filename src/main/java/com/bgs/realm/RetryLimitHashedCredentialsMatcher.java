package com.bgs.realm;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
/**
 * @author 
 *@Date 2018年3月26日 下午5:26:13
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    // 声明一个缓存接口，这个接口是Shiro缓存管理的一部分，它的具体实现可以通过外部容器注入
    private Cache<String, AtomicInteger> passwordRetryCache;
    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
    	 //赋予缓存对象，此处获取的是我们在ehcache.xml文件中配置,注意getCache("")获取的是xml中的name 
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        //AtomicInteger是一个提供原子操作的Integer类，通过线程安全的方式操作加减。
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (retryCount == null) {//如果用户未登陆过 
            retryCount = new AtomicInteger(0);//新建一个登录次数 
            passwordRetryCache.put(username, retryCount);//放入缓存中 
        }
        // 自定义一个验证过程：当用户连续输入密码错误5次以上禁止用户登录1分钟
        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();//抛出用户锁定异常类
        }
        // //判断用户是否可用，即是否为正确的账号密码  
        boolean match = super.doCredentialsMatch(token, info);
        if (match) {
        	//移除缓存中用户的登录次数  
            passwordRetryCache.remove(username);
        }
        return match;
    }
    public static void main(String[] args) {
    	AtomicInteger in=new AtomicInteger(0);
		for(int i=1;i<=10;i++){
			System.out.println(in.incrementAndGet());
		}
	}
}
