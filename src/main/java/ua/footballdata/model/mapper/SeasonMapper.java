package ua.footballdata.model.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ua.footballdata.model.Season;
import ua.footballdata.model.entity.SeasonEntity;

@Component
public class SeasonMapper extends AbstractMapper<SeasonEntity, Season> {
	public static final Logger logger = LoggerFactory.getLogger(SeasonMapper.class);

	/*
	 * @Autowired private TeamEntityServiceImpl teamService;
	 */

	@Override
	public Season convertToDto(SeasonEntity entity) {
		/*
		 * modelMapper.addMappings(new PropertyMap<CompetitionEntity, Competition>() {
		 * 
		 * @Override protected void configure() { map().set
		 * (source.getBillingAddress().getStreet());
		 * map().setBillingCity(source.getBillingAddress().getCity()); } });
		 */

		return modelMapper().map(entity, Season.class);
	}

	@Override
	public SeasonEntity convertToEntity(Season dto) {
		logger.info("Convert Season To Entity: " + dto);
		SeasonEntity seasonEntity = modelMapper().map(dto, SeasonEntity.class);

		/*
		 * if (areaService == null) { logger.error("AreaService is NULL"); }
		 */

		/*
		 * if (dto.getWinner() != null && teamService.isExist(dto.getWinner().getId()))
		 * {
		 * 
		 * seasonEntity.setWinner(teamService.findById(dto.getWinner().getId())); }
		 */
		return seasonEntity;
	}

}
