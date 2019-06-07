package ua.footballdata.model.mapper;

import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.footballdata.model.Match;
import ua.footballdata.model.entity.MatchEntity;
import ua.footballdata.service.SeasonEntityServiceImpl;
import ua.footballdata.service.TeamEntityServiceImpl;

@Component
public class MatchMapper extends AbstractMapper<MatchEntity, Match> {
	public static final Logger logger = LoggerFactory.getLogger(MatchMapper.class);

	@Autowired
	private SeasonEntityServiceImpl seasonService;

	@Autowired
	private TeamEntityServiceImpl teamService;

	private PropertyMap<MatchEntity, Match> getEntityToDTOPropertyMap() {
		PropertyMap<MatchEntity, Match> matchMap = new PropertyMap<MatchEntity, Match>() {
			protected void configure() {

				// map().setScore(score); Street(source.getAddress().getStreet());
				map(source.getWinner(), destination.getScore().getWinner());
				map(source.getDuration(), destination.getScore().getDuration());
				map(source.getScoreFullTimeHomeTeam(), destination.getScore().getFullTime().getHomeTeam());
				map(source.getScoreFullTimeAwayTeam(), destination.getScore().getFullTime().getAwayTeam());
				map(source.getScoreExtraTimeHomeTeam(), destination.getScore().getExtraTime().getHomeTeam());
				map(source.getScoreExtraTimeAwayTeam(), destination.getScore().getExtraTime().getAwayTeam());
				map(source.getScorePenaltiesHomeTeam(), destination.getScore().getPenalties().getHomeTeam());
				map(source.getScorePenaltiesAwayTeam(), destination.getScore().getPenalties().getAwayTeam());

			}
		};
		return matchMap;
	}

	private PropertyMap<Match, MatchEntity> getDTOToEntityPropertyMap() {
		PropertyMap<Match, MatchEntity> matchMap = new PropertyMap<Match, MatchEntity>() {
			protected void configure() {

				// map().setScore(score); Street(source.getAddress().getStreet());
				map().setWinner(source.getScore().getWinner());
				map().setDuration(source.getScore().getDuration());
				map().setScoreFullTimeHomeTeam(source.getScore().getFullTime().getHomeTeam());
				map().setScoreFullTimeAwayTeam(source.getScore().getFullTime().getAwayTeam());
				map().setScoreExtraTimeHomeTeam(source.getScore().getExtraTime().getHomeTeam());
				map().setScoreExtraTimeAwayTeam(source.getScore().getExtraTime().getAwayTeam());
				map().setScorePenaltiesHomeTeam(source.getScore().getPenalties().getHomeTeam());
				map().setScorePenaltiesAwayTeam(source.getScore().getPenalties().getAwayTeam());

				// map(source.getScore().getWinner(), destination.getWinner());
				// map(source.getScore().getDuration(), destination.getDuration());
				// map(source.getScore().getFullTime().getHomeTeam(),
				// destination.getScoreFullTimeHomeTeam());
				// map(source.getScore().getFullTime().getAwayTeam(),
				// destination.getScoreFullTimeAwayTeam());
				// map(source.getScore().getExtraTime().getHomeTeam(),
				// destination.getScoreExtraTimeHomeTeam());
				// map(source.getScore().getExtraTime().getAwayTeam(),
				// destination.getScoreExtraTimeAwayTeam());
				// map(source.getScore().getPenalties().getHomeTeam(),
				// destination.getScorePenaltiesHomeTeam());
				// map(source.getScore().getPenalties().getAwayTeam(),
				// destination.getScorePenaltiesAwayTeam());
				// logger.info("Converted MatchEntity: " + map());
			}
		};
		return matchMap;
	}

	@Override
	public Match convertToDto(MatchEntity entity) {
		modelMapper().addMappings(getEntityToDTOPropertyMap());
		return modelMapper().map(entity, Match.class);
	}

	@Override
	public MatchEntity convertToEntity(Match dto) {
		logger.info("Convert Match To Entity: " + dto);
		// modelMapper().addMappings(getDTOToEntityPropertyMap());
		MatchEntity matchEntity = modelMapper().map(dto, MatchEntity.class);

		matchEntity.setWinner(dto.getScore().getWinner());
		matchEntity.setDuration(dto.getScore().getDuration());
		matchEntity.setScoreFullTimeHomeTeam(dto.getScore().getFullTime().getHomeTeam());
		matchEntity.setScoreFullTimeAwayTeam(dto.getScore().getFullTime().getAwayTeam());
		matchEntity.setScoreExtraTimeHomeTeam(dto.getScore().getExtraTime().getHomeTeam());
		matchEntity.setScoreExtraTimeAwayTeam(dto.getScore().getExtraTime().getAwayTeam());
		matchEntity.setScorePenaltiesHomeTeam(dto.getScore().getPenalties().getHomeTeam());
		matchEntity.setScorePenaltiesAwayTeam(dto.getScore().getPenalties().getAwayTeam());

		/*
		 * if (areaService == null) { logger.error("AreaService is NULL"); }
		 */
		if (seasonService.isExist(dto.getSeason().getId())) {
			matchEntity.setHomeTeam(teamService.findById(dto.getHomeTeam().getId()));
		}
		if (teamService.isExist(dto.getHomeTeam().getId())) {
			matchEntity.setHomeTeam(teamService.findById(dto.getHomeTeam().getId()));
		}
		if (teamService.isExist(dto.getAwayTeam().getId())) {
			matchEntity.setAwayTeam(teamService.findById(dto.getAwayTeam().getId()));
		}
		return matchEntity;
	}

}
