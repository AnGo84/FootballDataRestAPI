package ua.footballdata.model.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

/**
 * This class provides methods to convert between types.
 *
 */

//@Component
public abstract class AbstractMapper<T, E> {
	/*
	 * @Autowired ModelMapper modelMapper;
	 */

	public abstract E convertToDto(T entity);

	public abstract T convertToEntity(E dto);

	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setFieldMatchingEnabled(true)
				.setSkipNullEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE);
		return mapper;
	}
}
