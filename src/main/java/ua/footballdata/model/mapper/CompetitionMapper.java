package ua.footballdata.model.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.footballdata.model.Competition;
import ua.footballdata.model.entity.CompetitionEntity;
import ua.footballdata.service.AreaEntityServiceImpl;

@Component
public class CompetitionMapper extends AbstractMapper<CompetitionEntity, Competition> {
	public static final Logger logger = LoggerFactory.getLogger(CompetitionMapper.class);

	@Autowired
	private AreaEntityServiceImpl areaService;

	@Override
	public Competition convertToDto(CompetitionEntity entity) {
		/*
		 * modelMapper.addMappings(new PropertyMap<CompetitionEntity, Competition>() {
		 * 
		 * @Override protected void configure() { map().set
		 * (source.getBillingAddress().getStreet());
		 * map().setBillingCity(source.getBillingAddress().getCity()); } });
		 */

		return modelMapper().map(entity, Competition.class);
	}

	@Override
	public CompetitionEntity convertToEntity(Competition dto) {
		logger.info("Convert Competition To Entity: " + dto);
		CompetitionEntity competitionEntity = modelMapper().map(dto, CompetitionEntity.class);

		/*
		 * if (areaService == null) { logger.error("AreaService is NULL"); }
		 */

		if (areaService.isExist(dto.getArea().getId())) {

			competitionEntity.setArea(areaService.findById(dto.getArea().getId()));
		}
		return competitionEntity;
	}

}
