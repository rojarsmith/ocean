package ocean.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ocean.common.model.entity.Role;
import ocean.common.repository.RoleRepository;
import ocean.common.service.RoleService;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role update(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public List<Role> update(List<Role> roles) {
		return roleRepository.saveAll(roles);
	}
}
