package ua.footballdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.footballdata.model.entity.User;
import ua.footballdata.model.entity.UserRole;
import ua.footballdata.repositories.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service("userRoleService")
public class UserRoleServiceImpl {
	@Autowired
	private UserRoleRepository userRoleRepository;

	public List<UserRole> findAllUserRoles() {
		return userRoleRepository.findAll();
	}


}
