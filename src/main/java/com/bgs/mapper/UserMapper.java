package com.bgs.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.bgs.bean.Product;
import com.bgs.bean.User;

public interface UserMapper {
	//查询用户的方法
	User getUser(@Param("username")String username);
	//根据用户名查询该用户名角色的方法
	Set<String> getRoles(@Param("username")String username);
	//根据用户名查询用户权限的方法
	Set<String> getpermissions(@Param("username")String username);
	
	List<Product> findProList();
	
	int addProduct(Product p);
	
	int delProduct(@Param("id")int id);
}
