package ocean.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;

import ocean.common.enums.ResourceType;
import ocean.common.model.entity.Permission;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
public interface PermissionService extends BaseService<Permission> {

	List<Permission> readPermissions(Long userId);

	Optional<Permission> readPermission(Long id);

	List<Permission> readPermissions(ResourceType type);

	List<Permission> readPermissions(ResourceType type, PageRequest page);

	Permission update(Permission resource);

	List<Permission> update(List<Permission> resource);

	int delete(ResourceType type);
}
