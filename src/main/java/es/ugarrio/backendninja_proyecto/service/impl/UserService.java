package es.ugarrio.backendninja_proyecto.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.ugarrio.backendninja_proyecto.entity.UserRole;
import es.ugarrio.backendninja_proyecto.repository.ContactRepository;
import es.ugarrio.backendninja_proyecto.repository.UserRepository;



@Service("userService")
public class UserService implements UserDetailsService {
	
	//Inyectamos el repositorio
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		es.ugarrio.backendninja_proyecto.entity.User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return builUser(user, authorities);
	}
	
	// Construimos un usuario de spring security a partir de nuestro usuario y lista de roles
	private User builUser(es.ugarrio.backendninja_proyecto.entity.User user, List<GrantedAuthority>  authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}
	
	// Transforma nuestro listado de UserRole en una lista de GrantedAuthority que es como lo
	// spring security maneja los roles de usuario
	private List<GrantedAuthority> buildAuthorities (Set<UserRole> userRoles) {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		for (UserRole userRole: userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}
}



