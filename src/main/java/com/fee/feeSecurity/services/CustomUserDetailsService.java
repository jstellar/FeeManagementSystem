package com.fee.feeSecurity.services;

import com.fee.feeSecurity.dao.UserDAO;
import com.fee.feeSecurity.dto.UserDto;
import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);

		if (user == null) {
			log.error("Invalid username or password!");
			throw new UsernameNotFoundException("Invalid username or password!");
		}
		Set<GrantedAuthority> grantedAuthorities = getAuthorities(user);
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

	private Set<GrantedAuthority> getAuthorities(User user) {
		Collection<Role> roleByUserId = user.getRoles();
		final Set<GrantedAuthority>  authorities = roleByUserId.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().toString().toUpperCase())).collect(Collectors.toSet());
		return authorities;

	}

}
