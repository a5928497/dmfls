package com.yukoon.dmfls.Services;

import com.yukoon.dmfls.repositories.PermissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionService {
	@Autowired
	private PermissionRepo permissionRepo;

	@Transactional
	public List<String> getPermissions(Integer role_id) {
		return permissionRepo.findPermNameByRoleid(role_id);
	}
}
