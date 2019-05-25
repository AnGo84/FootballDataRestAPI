package ua.footballdata.model.mapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * This class provides generic methods to convert between types.
 *
 */
public abstract class GenericConverter {

	/**
	 * Converts a source to a type destination.
	 * 
	 * @param source          The source object
	 * @param typeDestination The type destination
	 * @return The object created
	 */
	public static <T, E> E mapper(T source, Class<E> typeDestination) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(source, typeDestination);

	}

	/**
	 * Converts a source to a type destination.
	 * 
	 * @param source      The source object
	 * @param destination The destination object
	 * @return The object created
	 */
	public static <T, E> E mapper(T source, E destination) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.map(source, destination);

		return destination;
	}

	/**
	 * Converts a source to a type destination.
	 * 
	 * @param source The souce object
	 * @return The object created
	 */
	public static <E, T> List<E> mapper(List<T> source, Type destinationType) {

		List<E> model = null;
		if (source != null && destinationType != null) {

			ModelMapper modelMapper = new ModelMapper();

			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			model = modelMapper.map(source, destinationType);
		}

		return model;
	}
}
