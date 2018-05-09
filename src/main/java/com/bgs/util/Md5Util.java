package com.bgs.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Util {
	public static String getMd5String(String passwrod){
		String pwd = new Md5Hash(passwrod).toString();
		return pwd;
	}
	//给密码加盐
	public static String getMdString(String password,String stalt,int num){
		String pwd = new Md5Hash(password, stalt,num).toString();
		return pwd;
	}
	
	public static void main(String[] args) {
		System.out.println(Md5Util.getMdString("a","admin",1024));
	}
}
