package ua.footballdata.model.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ua.footballdata.model.Area;
import ua.footballdata.model.entity.AreaEntity;

@Component
public class AreaMapper extends AbstractMapper<AreaEntity, Area> {
	public static final Logger logger = LoggerFactory.getLogger(AreaMapper.class);

	/*
	 * @Autowired private TeamEntityServiceImpl teamService;
	 */

	@Override
	public Area convertToDto(AreaEntity entity) {
		/*
		 * modelMapper.addMappings(new PropertyMap<CompetitionEntity, Competition>() {
		 * 
		 * @Override protected void configure() { map().set
		 * (source.getBillingAddress().getStreet());
		 * map().setBillingCity(source.getBillingAddress().getCity()); } });
		 */

		return modelMapper().map(entity, Area.class);
	}

	@Override
	public AreaEntity convertToEntity(Area dto) {
		// logger.info("Convert Area: " + dto);
		AreaEntity areaEntity = modelMapper().map(dto, AreaEntity.class);
		// logger.info("Converted Entity: " + areaEntity);
		/*
		 * if (areaService == null) { logger.error("AreaService is NULL"); }
		 */

		return areaEntity;
	}

}
