package ua.footballdata.model.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ua.footballdata.model.Team;
import ua.footballdata.model.entity.TeamEntity;

@Component
public class TeamMapper extends AbstractMapper<TeamEntity, Team> {
	public static final Logger logger = LoggerFactory.getLogger(TeamMapper.class);

	@Override
	public Team convertToDto(TeamEntity entity) {
		/*
		 * modelMapper.addMappings(new PropertyMap<CompetitionEntity, Competition>() {
		 * 
		 * @Override protected void configure() { map().set
		 * (source.getBillingAddress().getStreet());
		 * map().setBillingCity(source.getBillingAddress().getCity()); } });
		 */

		return modelMapper().map(entity, Team.class);
	}

	@Override
	public TeamEntity convertToEntity(Team dto) {
		logger.info("Convert Team To Entity: " + dto);
		TeamEntity TeamEntity = modelMapper().map(dto, TeamEntity.class);

		/*
		 * if (TeamService == null) { logger.error("TeamService is NULL"); }
		 */

		return TeamEntity;
	}

}
