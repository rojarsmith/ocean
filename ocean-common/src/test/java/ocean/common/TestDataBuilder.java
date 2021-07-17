package ocean.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ocean.common.enums.ResourceType;
import ocean.common.model.entity.Permission;
import ocean.common.repository.PermissionRepository;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
public class TestDataBuilder {

	public TestDataBuilder() {
	}

	void initRepository(PermissionRepository permissionRepository) {
		permissionRepository.deleteAll();

		Permission p1 = new Permission();
		p1.setId(1001L)
				//
				.setName("create new user")
				//
				.setPath("PUT:/member/test")
				//
				.setType(ResourceType.API);

		Permission p2 = new Permission();
		p2.setId(1002L)
				//
				.setName("Delete user")
				//
				.setPath("DELETE:/member/test2")
				//
				.setType(ResourceType.API);
		permissionRepository.saveAll(Arrays.asList(p1, p2));

		List<Permission> ps1 = new ArrayList<>();
		for (int i = 1; i <= 99; i++) {
			Permission p = new Permission();
			p.setId(2000L + i)
					//
					.setName("Name " + i)
					//
					.setPath("DELETE:/member/test" + i)
					//
					.setType(ResourceType.API);

			ps1.add(p);
		}
		permissionRepository.saveAll(ps1);
	}

}
