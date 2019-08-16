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

	@Autowired
	private UserRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		logger.info("loadUserByUsername: " + login);
		// Optional<User> optionalUser = usersRepository.findByLogin(login);

		/*
		 * logger.info("All users: "); try { usersRepository.findAll().forEach(item ->
		 * logger.info("User: " + item)); } catch (Exception e) { logger.error("Mess: "
		 * + e.getMessage(), e); } try { Optional<User> user =
		 * usersRepository.findById((long) 1); logger.info("User id=1: " +
		 * user.orElse(null)); } catch (Exception e) { logger.error("Get id=1 Mess: " +
		 * e.getMessage(), e); } try { List<User> users =
		 * usersRepository.findUsersById((long) 1); logger.info("Users LIST id=1: " +
		 * users.get(0)); } catch (Exception e) {
		 * logger.error("Get Users LIST id=1 Mess: " + e.getMessage(), e); }
		 */
		Optional<User> optionalUser = null;
		try {
			optionalUser = Optional.of(usersRepository.findByLogin(login));
			logger.info("Get by Login: " + optionalUser);
		} catch (Exception e) {
			logger.error("For Login " + login + ": " + e.getMessage(), e);
		}

		return Optional.ofNullable(optionalUser)
				.orElseThrow(() -> new UsernameNotFoundException("User's Login Not Found")).map(UserDetailsImpl::new)
				.get();
	}
}
