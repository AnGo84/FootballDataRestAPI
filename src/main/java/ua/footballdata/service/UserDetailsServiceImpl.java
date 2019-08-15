package ua.footballdata.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.footballdata.model.entity.User;
import ua.footballdata.model.entity.UserDetailsImpl;
import ua.footballdata.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	public static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	private UserRepository usersRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository usersRepository) {
		super();
		this.usersRepository = usersRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		logger.info("Try loadUserByUsername: " + login);
		// Optional<User> optionalUser = usersRepository.findByLogin(login);

		logger.info("All users: " + usersRepository.findAll());
		logger.info("User id=0: " + usersRepository.findById(0L));

		Optional<User> optionalUser = Optional.of(usersRepository.findByLogin(login));
		logger.info("Get by Login: " + optionalUser);
		return Optional.ofNullable(optionalUser)
				.orElseThrow(() -> new UsernameNotFoundException("User's Login Not Found")).map(UserDetailsImpl::new)
				.get();
	}
}
