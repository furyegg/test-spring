package test.spring.mvc.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
	
	private static final String PASSWORD = "$2a$10$QgEK9PwbieszUm2XoVhoQ.gyxVk5sUgK3Xq/9Qw337CF3MWo5KROe";
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("user", PASSWORD, Collections.emptyList());
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = encoder.encode("password");
		System.out.printf(password);
	}
}