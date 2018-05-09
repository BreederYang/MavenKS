package com.bgs.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bgs.bean.Product;
import com.bgs.bean.User;
import com.bgs.mapper.UserMapper;
@Service
public class UserServiceImp implements UserService{
	@Autowired
	private UserMapper dao;
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return dao.getUser(username);
	}

	@Override
	public Set<String> getRoles(String username) {
		// TODO Auto-generated method stub
		return dao.getRoles(username);
	}

	@Override
	public Set<String> getpermissions(String username) {
		// TODO Auto-generated method stub
		return dao.getpermissions(username);
	}

	@Override
	public List<Product> findProList() {
		// TODO Auto-generated method stub
		return dao.findProList();
	}

	@Override
	public int addProduct(Product p) {
		// TODO Auto-generated method stub
		return dao.addProduct(p);
	}

	@Override
	public int delProduct(int id) {
		// TODO Auto-generated method stub
		return dao.delProduct(id);
	}

}
