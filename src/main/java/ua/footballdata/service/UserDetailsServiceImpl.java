package ua.footballdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.footballdata.model.entity.User;
import ua.footballdata.model.entity.UserDetailsImpl;
import ua.footballdata.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<User> optionalUser = usersRepository.findByLogin(login);
		return Optional.ofNullable(optionalUser)
				.orElseThrow(() -> new UsernameNotFoundException("User's Login Not Found")).map(UserDetailsImpl::new)
				.get();
	}
}
