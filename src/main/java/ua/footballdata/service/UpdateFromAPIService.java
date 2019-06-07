package ua.footballdata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.footballdata.error.CustomErrorType;
import ua.footballdata.model.Area;
import ua.footballdata.model.Competition;
import ua.footballdata.model.CompetitionMatches;
import ua.footballdata.model.Match;
import ua.footballdata.model.Season;
import ua.footballdata.model.Team;
import ua.footballdata.model.entity.AreaEntity;
import ua.footballdata.model.entity.CompetitionEntity;
import ua.footballdata.model.entity.CompetitionSeasonEntity;
import ua.footballdata.model.entity.MatchEntity;
import ua.footballdata.model.entity.SeasonEntity;
import ua.footballdata.model.entity.TeamEntity;
import ua.footballdata.model.mapper.AreaMapper;
import ua.footballdata.model.mapper.CompetitionMapper;
import ua.footballdata.model.mapper.MatchMapper;
import ua.footballdata.model.mapper.SeasonMapper;
import ua.footballdata.model.mapper.TeamMapper;
import ua.footballdata.serviceAPI.AreaAppServiceImp;
import ua.footballdata.serviceAPI.CompetitionAppServiceImp;
import ua.footballdata.serviceAPI.CompetitionMatchesAppServiceImp;
import ua.footballdata.serviceAPI.TeamAppServiceImp;

@Service("updateFromAPIService")
public class UpdateFromAPIService {
	public static final Logger logger = LoggerFactory.getLogger(UpdateFromAPIService.class);
	@Autowired
	private CompetitionEntityServiceImpl competitionEntityService;
	@Autowired
	private CompetitionSeasonEntityService competitionSeasonEntityService;
	@Autowired
	private SeasonEntityServiceImpl seasonEntityServiceImpl;
	@Autowired
	private AreaEntityServiceImpl areaEntityServiceImpl;
	@Autowired
	private TeamEntityServiceImpl teamEntityServiceImpl;
	@Autowired
	private MatchEntityServiceImpl matchEntityServiceImpl;

	@Autowired
	private CompetitionAppServiceImp competitionAPIService;
	@Autowired
	private AreaAppServiceImp areaAppServiceImp;
	@Autowired
	private TeamAppServiceImp teamAppServiceImp;
	@Autowired
	private CompetitionMatchesAppServiceImp competitionMatchesService;

	@Autowired
	private CompetitionMapper competitionMapper;
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private SeasonMapper seasonMapper;
	@Autowired
	private TeamMapper teamMapper;
	@Autowired
	private MatchMapper matchMapper;

	public boolean updateCompetitionById(long id) throws CustomErrorType {
		Competition competition = competitionAPIService.findById(id);
		logger.info("Fetching Competition with id {} from API: {}", id, competition);

		if (competition == null) {
			throw new CustomErrorType("Competition with id " + id + " not found.");
		}
		updateArea(competition.getArea());
		CompetitionEntity competitionEntity = competitionMapper.convertToEntity(competition);
		competitionEntityService.update(competitionEntity);
		updateSeason(competition.getId(), competition.getCurrentSeason());
		return true;
	}

	public boolean updateArea(Area area) throws CustomErrorType {
		if (area != null) {
			AreaEntity areaEntity = null;
			if (!areaEntityServiceImpl.isExist(area.getId())) {
				area = areaAppServiceImp.findById(area.getId());
				areaEntity = areaMapper.convertToEntity(area);
				areaEntityServiceImpl.update(areaEntity);
			}
			/*
			 * else { areaEntity = areaEntityServiceImpl.findById(area.getId()); }
			 */
			// AreaEntity areaEntity = areaMapper.convertToEntity(area);
			// areaEntityServiceImpl.update(areaEntity);
			return true;
		}
		return false;
	}

	public boolean updateSeason(Long competitionId, Season season) throws CustomErrorType {
		if (season != null) {
			if (season.getWinner() != null) {
				updateTeam(season.getWinner().getId());
			}

			SeasonEntity seasonEntity = seasonMapper.convertToEntity(season);

			seasonEntityServiceImpl.update(seasonEntity);
			CompetitionSeasonEntity competitionSeasonEntity = new CompetitionSeasonEntity();
			competitionSeasonEntity.setCompetitionId(competitionId);
			competitionSeasonEntity.setSeasonId(seasonEntity.getId());
			if (!competitionSeasonEntityService.isExist(competitionSeasonEntity)) {
				competitionSeasonEntityService.update(competitionSeasonEntity);
			}
			return true;
		}
		return false;
	}

	public boolean updateTeam(Long id) throws CustomErrorType {
		if (id != null) {
			Team team = null;
			if (!teamEntityServiceImpl.isExist(id)) {
				team = teamAppServiceImp.findById(id);
				updateArea(team.getArea());
				TeamEntity teamEntity = teamMapper.convertToEntity(team);
				teamEntityServiceImpl.update(teamEntity);
			}
			/*
			 * Team team = teamAppServiceImp.findById(id); updateArea(team.getArea());
			 * TeamEntity teamEntity = teamMapper.convertToEntity(team);
			 * teamEntityServiceImpl.update(teamEntity); return true;
			 */
		}
		return false;
	}

	public boolean updateCompetitionMatches(long id) throws CustomErrorType {

		CompetitionMatches competitionMatches = competitionMatchesService.findById(id);
		if (competitionMatches == null || competitionMatches.getMatches() == null
				|| competitionMatches.getMatches().isEmpty()) {
			throw new CustomErrorType("Matches for competition with id " + id + " not found.");
		}

		for (Match match : competitionMatches.getMatches()) {
			MatchEntity matchEntity = matchMapper.convertToEntity(match);
			updateTeam(match.getHomeTeam().getId());
			updateTeam(match.getAwayTeam().getId());
			logger.info("Match: " + match);
			logger.info("MatchEntity: " + matchEntity);
			matchEntityServiceImpl.update(matchEntity);
		}

		return true;
	}

	public boolean updateCompetitionWithMatches(long id) throws CustomErrorType {
		updateCompetitionById(id);
		updateCompetitionMatches(id);
		return true;
	}
}
