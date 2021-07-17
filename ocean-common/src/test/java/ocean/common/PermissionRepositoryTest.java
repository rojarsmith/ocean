package ocean.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ocean.common.model.entity.Permission;
import ocean.common.repository.PermissionRepository;
import ocean.common.service.MemberService;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "classpath:service-spring.xml" })
public class PermissionRepositoryTest {

	@Autowired
	MemberService memberService;

	@Autowired
	PermissionRepository permissionRepository;

	TestDataBuilder mockData;

	@BeforeEach
	void init() {
		mockData = new TestDataBuilder();
		mockData.initRepository(permissionRepository);
	}

	@Test
	public void commonTest() {
		Optional<Permission> q1 = permissionRepository.findById(1001L);
		assertEquals(true, q1.isPresent());
		List<Permission> q2 = permissionRepository.findAllById(Arrays.asList(1001L, 1002L));
		assertEquals(2, q2.size());
		List<Permission> q3 = permissionRepository.findById(1001L, 1001L);
		assertEquals(1, q3.size());
		List<Permission> q4 = permissionRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1001L, 1002L);
		assertEquals(2, q4.size());
	}

	@Test
	public void pageTest() {
		Page<Permission> pageResult = permissionRepository.findAll(PageRequest.of(1, 5, Sort.by("id").descending()));
		List<Permission> permissions = pageResult.getContent();
		assertEquals(5, permissions.size());
	}

}
