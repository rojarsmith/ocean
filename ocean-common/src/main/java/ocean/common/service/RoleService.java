package ocean.common.service;

import java.util.List;

import ocean.common.model.entity.Role;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
public interface RoleService extends BaseService<RoleService> {
	Role update(Role role);

	List<Role> update(List<Role> roles);
}
