package ocean.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ocean.common.model.entity.Member;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-14
 */
@Repository
public interface MemberRepository extends BaseRepository<Member> {

	List<Member> getAllByEmailEquals(String email);

	List<Member> getAllByUsernameEquals(String username);

	Optional<Member> findByUsername(String username);

	Optional<Member> findMemberByEmail(String email);

}
