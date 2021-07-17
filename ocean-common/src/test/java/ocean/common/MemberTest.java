package ocean.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ocean.common.enums.ResourceType;
import ocean.common.model.entity.Member;
import ocean.common.model.entity.Permission;
import ocean.common.service.MemberService;
import ocean.common.service.PermissionService;
import ocean.common.service.RoleService;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "classpath:service-spring.xml" })
public class MemberTest {

	@Autowired
	MemberService memberService;

	@Autowired
	RoleService roleService;

	@Autowired
	PermissionService permissionService;

	TestDataBuilder mockData;

	@BeforeEach
	void init() {
		mockData = new TestDataBuilder();
		mockData.initMemberData(memberService, roleService, permissionService);
	}

	@Test
	@Transactional
	public void commonTest() {
		Optional<Member> member = memberService.findByUsername("Rojar");
		List<Permission> permissions = permissionService.readPermissions(member.get().getId());
		assertEquals(2, permissions.size());

		List<Permission> permissions2 = permissionService.readPermissions(ResourceType.API, PageRequest.of(1, 5));
		assertEquals(5, permissions2.size());
	}

}
