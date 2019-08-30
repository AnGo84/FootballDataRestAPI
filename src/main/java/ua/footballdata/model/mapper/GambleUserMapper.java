package ua.footballdata.model.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ua.footballdata.model.entity.GambleUser;
import ua.footballdata.model.entity.User;

@Component
public class GambleUserMapper extends AbstractMapper<GambleUser, User> {
	public static final Logger logger = LoggerFactory.getLogger(GambleUserMapper.class);

	@Override
	public User convertToDto(GambleUser entity) {

		return modelMapper().map(entity, User.class);
	}

	@Override
	public GambleUser convertToEntity(User dto) {
		GambleUser entity = modelMapper().map(dto, GambleUser.class);
		return entity;
	}

}
