package com.javaex.dao;

import com.javaex.vo.UserVo;

public class UserTest {

	public static void main(String[] args) {

		UserDao userDao = new UserDao();
		
		UserVo vo = new UserVo("test", "1q2w3e4r", "강한솔", "male");
		userDao.insert(vo);
	}

}
