package ua.footballdata.model.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.footballdata.model.Area;
import ua.footballdata.model.entity.AreaEntity;
import ua.footballdata.service.TeamEntityServiceImpl;

@Component
public class AreaMapper extends AbstractMapper<AreaEntity, Area> {
	public static final Logger logger = LoggerFactory.getLogger(AreaMapper.class);

	@Autowired
	private TeamEntityServiceImpl teamService;

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
		logger.info("Convert Area To Entity: " + dto);
		AreaEntity AreaEntity = modelMapper().map(dto, AreaEntity.class);

		/*
		 * if (areaService == null) { logger.error("AreaService is NULL"); }
		 */

		return AreaEntity;
	}

}
