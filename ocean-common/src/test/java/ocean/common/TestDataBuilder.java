package ocean.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ocean.common.enums.ResourceType;
import ocean.common.model.entity.Member;
import ocean.common.model.entity.Permission;
import ocean.common.model.entity.Role;
import ocean.common.repository.PermissionRepository;
import ocean.common.service.MemberService;
import ocean.common.service.PermissionService;
import ocean.common.service.RoleService;

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

	void initMemberData(//
			MemberService memberService, //
			RoleService roleService, //
			PermissionService permissionService) {

		if (memberService.isEmailExist("rojarsmith@gmail.com")) {
			return;
		}

		Permission p1 = new Permission();
		p1.setId(1001L)
				//
				.setName("create new user")
				//
				.setPath("PUT:/api/v1/member/test")
				//
				.setType(ResourceType.API);

		Permission p2 = new Permission();
		p2.setId(1002L)
				//
				.setName("Delete user")
				//
				.setPath("DELETE:/api/v1/member/test2")
				//
				.setType(ResourceType.API);
		permissionService.update(Arrays.asList(p1, p2));

		List<Permission> ps1 = new ArrayList<>();
		for (int i = 1; i <= 99; i++) {
			Permission p = new Permission();
			p.setId(2000L + i)
					//
					.setName("Name " + i)
					//
					.setPath("DELETE:/api/v1/member/test" + i)
					//
					.setType(ResourceType.API);

			ps1.add(p);
		}
		permissionService.update(ps1);

		Role role = new Role("ROLE_ADMIN", "Admin", Arrays.asList(p1, p2));
		roleService.update(role);

		Member m1 = new Member();
		m1.setEmail("rojarsmith@gmail.com");
		m1.setUsername("Rojar");
		m1.setPassword("abc");
		m1.setRoleList(Arrays.asList(role));
		memberService.update(m1);

		Member m2 = new Member();
		m2.setEmail("dev@aext.io");
		m2.setUsername("Dev かいはつ");
		m2.setPassword("abc");
		m2.setRoleList(Arrays.asList(role));
		memberService.update(m2);
	}

}
