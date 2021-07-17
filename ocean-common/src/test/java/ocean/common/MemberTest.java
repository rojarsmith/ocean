package ocean.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
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
import ocean.common.service.impl.MemberServiceImpl;

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
	
	@Test
	@Transactional
	public void  memberServiceTest(){
		boolean isEmailExist = memberService.isEmailExist("");
		assertEquals(isEmailExist, false);
		boolean isEmailExist2 = memberService.isEmailExist("dev@aext.io");
		assertEquals(isEmailExist2, true);

		Executable executable = new Executable() {
			public void execute() {
				MemberService ms1 = new MemberServiceImpl();
				ms1.isEmailExist("");
			}
		};
		assertThrows(Exception.class, executable);

		MemberService ms1 = new MemberServiceImpl();
		assertThrows(Exception.class, () -> {
			ms1.isEmailExist("");
		});

		Optional<Member> m3 = memberService.findByEmail("dev@aext.io");
		assertEquals(m3.isPresent(), true);
	}

}
