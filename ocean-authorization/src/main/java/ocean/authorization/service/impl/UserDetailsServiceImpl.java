package ocean.authorization.service.impl;

import static ocean.common.constant.CachePrefix.MEMBER_PREFIX;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ocean.authorization.security.MemberDetails;
import ocean.common.model.entity.Member;
import ocean.common.model.entity.Permission;
import ocean.common.model.entity.Role;
import ocean.common.service.DataCacheService;
import ocean.common.service.LocaleMessageSourceService;
import ocean.common.service.MemberService;
import ocean.common.util.JSON;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-24
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	@Autowired
	MemberService memberService;

	@Autowired
	DataCacheService dataCacheService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> member = memberService.findByUsername(username);
		if (member.isEmpty()) {
			throw new UsernameNotFoundException(localeMessageSourceService.getMessage("AUTHENTICATION_FAILED"));
		}

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		for (Role role : member.get().getRoleList()) {
			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role.getCode());
			authorities.add(sga);

			for (Permission r : role.getPermissionList()) {
				authorities.add(new SimpleGrantedAuthority(r.getId().toString()));
			}
		}

		Optional<String> jm = JSON.stringify(member.get());
		dataCacheService.update(MEMBER_PREFIX + username, jm.get(), 2592000);

		return new MemberDetails( //
				member.get().getId(), //
				member.get().getUsername(), //
				member.get().getEmail(), //
				member.get().getPassword(), true, authorities);
	}

}
