package com.jiafuwei.spring.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jiafuwei.spring.dao.IUserDao;
import com.jiafuwei.spring.po.User;
import com.jiafuwei.spring.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}

}  
