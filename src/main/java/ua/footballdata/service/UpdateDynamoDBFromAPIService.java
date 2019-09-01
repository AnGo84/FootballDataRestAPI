package ua.footballdata.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper.FailedBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.footballdata.error.CustomErrorType;
import ua.footballdata.model.*;
import ua.footballdata.model.entity.*;
import ua.footballdata.model.mapper.*;
import ua.footballdata.serviceAPI.*;
import ua.footballdata.utils.DateTimeUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service("updateDynamoDBFromAPIService")
public class UpdateDynamoDBFromAPIService {
    public static final Logger logger = LoggerFactory.getLogger(UpdateDynamoDBFromAPIService.class);

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

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
    private CompetitionTeamsAppServiceImp competitionTeamsService;
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

    /**
     * Update list of Areas in DynamoDB from API service
     *
     * @param entities
     * @return true if processed without errors
     * @throws CustomErrorType
     */
    public boolean updateAreasList(List<AreaEntity> entities) throws CustomErrorType {
        List<FailedBatch> failedList = null;
        try {
            failedList = dynamoDBMapper.batchSave(entities);
        } catch (Exception e) {
            logger.error("Error on saving Areas: " + e.getMessage(), e);
            new CustomErrorType("Error on saving Areas: " + e.getMessage());
        }
        if (failedList != null && !failedList.isEmpty()) {
            for (FailedBatch failedBatch : failedList) {
                logger.error("Error on saving Areas : " + failedBatch.getException().getMessage());

            }
            throw new CustomErrorType("Saving Areas has " + failedList.size() + " errors.");
        }

        return true;
    }

    /**
     * Update competition Item by ID in Competitions table in DynamoDB
     *
     * @param id
     * @return true if processed without errors
     * @throws CustomErrorType
     */

    public boolean updateCompetitionById(long id) throws CustomErrorType {
        Competition competition = competitionAPIService.findById(id);
        logger.info("Fetching Competition with id {} from API: {}", id, competition);

        if (competition == null) {
            throw new CustomErrorType("Competition with id " + id + " not found.");
        }

        // updateArea(competition.getArea());
        CompetitionEntity competitionEntity = competitionMapper.convertToEntity(competition);


        SeasonEntity seasonEntity = updateCompetitionSeason(competition.getId(), competition.getCurrentSeason());
        if (seasonEntity != null) {
            competitionEntity.getCurrentSeason().setStages(seasonEntity.getStages());
        }

        competitionEntityService.update(competitionEntity);
        return true;
    }

    public boolean updateTeamsListFromCompetition(long competitionId) throws CustomErrorType {
        logger.info("Update TeamsList for Competition with id {}", competitionId);
        CompetitionTeams competitionTeams = competitionTeamsService.findById(competitionId);
        if (competitionTeams == null) {
            logger.error("Teams not found.");
            throw new CustomErrorType("Areas not found");
        }
        List<Team> list = competitionTeams.getTeams();
        if (list == null || list.isEmpty()) {
            logger.error("Areas not found.");
            throw new CustomErrorType("Areas not found");
        }

        List<TeamEntity> entities = new ArrayList<TeamEntity>();
        for (Team team : list) {
            TeamEntity entity = teamMapper.convertToEntity(team);
            entities.add(entity);
        }
        return updateTeamsList(entities);

    }

    public boolean updateTeamsList(List<TeamEntity> entities) throws CustomErrorType {
        List<FailedBatch> failedList = null;

        try {
            failedList = dynamoDBMapper.batchSave(entities);
        } catch (Exception e) {
            logger.error("Error on saving Teams: " + e.getMessage(), e);
            new CustomErrorType("Error on saving Teams: " + e.getMessage());
        }
        if (failedList != null && !failedList.isEmpty()) {
            for (FailedBatch failedBatch : failedList) {
                logger.error("Error on saving Teams : " + failedBatch.getException().getMessage());

            }
            throw new CustomErrorType("Saving Teams has " + failedList.size() + " errors.");
        }

        return true;
    }

    public SeasonEntity updateCompetitionSeason(long competitionId, Season season) throws CustomErrorType {
        logger.info("Update Season {} for Competition with id {} from API", season, competitionId);
        updateTeamsListFromCompetition(competitionId);
        List<TeamEntity> teamEntities = teamEntityServiceImpl.findAll();

        if (season != null) {
            SeasonEntity seasonEntity = seasonMapper.convertToEntity(season);
            if (season.getWinner() != null) {
                TeamEntity teamEntity = findTeamEntity(season.getWinner().getId(), teamEntities);
                seasonEntity.setWinner(teamEntity);
            }
            seasonEntity.setMatches(getCompetitionSeasonMatches(competitionId, null, teamEntities));
            seasonEntity.setStages(getSeasonStagesList(seasonEntity));
            seasonEntityServiceImpl.update(seasonEntity);

            return seasonEntity;
        }
        return null;
    }

    private List<SeasonStage> getSeasonStagesList(SeasonEntity season) {
        if (season == null || season.getMatches() == null || season.getMatches().isEmpty()) {
            return null;
        }
        Map<String, Stage> map = new HashMap<>();

        for (MatchEntity match : season.getMatches()) {
            if (map.containsKey(match.getStage())) {
                Stage stage = map.get(match.getStage());
                if (stage.getFrom().compareTo(DateTimeUtils.getDateFromString(match.getUtcDate())) > 0) {
                    stage.setFrom(DateTimeUtils.getDateFromString(match.getUtcDate()));
                } else if (stage.getTill().compareTo(DateTimeUtils.getDateFromString(match.getUtcDate())) < 0) {
                    stage.setTill(DateTimeUtils.getDateFromString(match.getUtcDate()));
                }
            } else {
                map.put(match.getStage(),
                        new Stage(match.getStage(), DateTimeUtils.getDateFromString(match.getUtcDate()), DateTimeUtils.getDateFromString(match.getUtcDate())));
            }
        }

        List<Stage> stagesList = map.values().stream().sorted(Comparator.comparing(Stage::getFrom))
                .collect(Collectors.toList());
        int i = 0;
        List<SeasonStage> seasonStagesList = new ArrayList<>();
        for (Stage stage : stagesList) {
            stage.setId(i++);
            seasonStagesList.add(new SeasonStage(stage.id, stage.name));
        }

        return seasonStagesList;
    }


    private TeamEntity findTeamEntity(long teamId, List<TeamEntity> teamEntities) {
        TeamEntity teamEntity = teamEntities.stream().filter(team -> team.getId() == teamId).findAny().orElse(null);
        return teamEntity;
    }

    public List<MatchEntity> getCompetitionSeasonMatches(long competitionId, String seasonYearStart,
                                                         List<TeamEntity> teamEntities) throws CustomErrorType {
        logger.info("Get Season {} Matches for Competition with id {} from API:", seasonYearStart, competitionId);
        List<MatchEntity> matchEntities = new ArrayList<>();
        CompetitionMatches competitionMatches = competitionMatchesService.findById(competitionId);
        if (competitionMatches == null || competitionMatches.getMatches() == null
                || competitionMatches.getMatches().isEmpty()) {
            throw new CustomErrorType("Matches for competition with id " + competitionId + " not found.");
        }
        for (Match match : competitionMatches.getMatches()) {
            MatchEntity matchEntity = matchMapper.convertToEntity(match);
            TeamEntity teamEntity = findTeamEntity(match.getHomeTeam().getId(), teamEntities);
            matchEntity.setHomeTeam(teamEntity);
            teamEntity = findTeamEntity(match.getAwayTeam().getId(), teamEntities);
            matchEntity.setAwayTeam(teamEntity);
            logger.info("Match: " + match);
            logger.info("MatchEntity: " + matchEntity);
            matchEntities.add(matchEntity);
        }

        return matchEntities;
    }

    /******************************************/
    /*
     * public boolean updateCompetitionSeason(Long competitionId, Season season)
     * throws CustomErrorType {
     *
     * if (season != null) { if (season.getWinner() != null) {
     * updateTeam(season.getWinner().getId()); }
     *
     * SeasonEntity seasonEntity = seasonMapper.convertToEntity(season);
     *
     * seasonEntityServiceImpl.update(seasonEntity); CompetitionSeasonEntity
     * competitionSeasonEntity = new CompetitionSeasonEntity();
     * competitionSeasonEntity.setCompetitionId(competitionId);
     * competitionSeasonEntity.setSeasonId(seasonEntity.getId()); if
     * (!competitionSeasonEntityService.isExist(competitionSeasonEntity)) {
     * competitionSeasonEntityService.update(competitionSeasonEntity); } return
     * true; } return false; }
     */

    /*
     * public boolean updateCompetitionById(long id) throws CustomErrorType {
     * Competition competition = competitionAPIService.findById(id);
     * logger.info("Fetching Competition with id {} from API: {}", id, competition);
     *
     * if (competition == null) { throw new CustomErrorType("Competition with id " +
     * id + " not found."); }
     *
     * // updateArea(competition.getArea()); CompetitionEntity competitionEntity =
     * competitionMapper.convertToEntity(competition);
     *
     * competitionEntityService.update(competitionEntity);
     * updateSeason(competition.getId(), competition.getCurrentSeason()); return
     * true; }
     */
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
        // updateCompetitionById(id);
        // updateCompetitionMatches(id);
        return true;
    }

    /* Inner class*/
    class Stage {
        private int id;
        private String name;
        private Date from;
        private Date till;

        public Stage() {
        }

        public Stage(String name, Date from, Date till) {
            this.name = name;
            this.from = from;
            this.till = till;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getFrom() {
            return from;
        }

        public void setFrom(Date from) {
            this.from = from;
        }

        public Date getTill() {
            return till;
        }

        public void setTill(Date till) {
            this.till = till;
        }
    }
}
