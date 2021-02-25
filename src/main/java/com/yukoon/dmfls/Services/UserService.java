package com.yukoon.dmfls.Services;

import com.yukoon.dmfls.Entities.User;
import com.yukoon.dmfls.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;

	@Transactional
	public User login(User user) {
		User user_temp = userRepo.login(user.getUsername());
		//验证密码
		if (user_temp != null && user_temp.getPassword().equals(user.getPassword())) {
			return user_temp;
		}
		return null;
	}

	@Transactional
	public User autoLogin(String username) {
		return userRepo.login(username);
	}

	@Transactional
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Transactional
	public User findById(Integer id) {
		return userRepo.findOne(id);
	}

	@Transactional
	public void saveUser(User user) {
		userRepo.saveAndFlush(user);
	}

	@Transactional
	public User findDetailsByUsername(String username) {
		return userRepo.findAllDetailsByUsername(username);
	}

}
