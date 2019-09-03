package ua.footballdata.model.mapper;

import com.amazonaws.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ua.footballdata.model.entity.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class GambleCompetitionMapper extends AbstractMapper<GambleCompetition, CompetitionEntity> {
    public static final Logger logger = LoggerFactory.getLogger(GambleCompetitionMapper.class);

    @Override
    public GambleCompetition convertToEntity(CompetitionEntity dto) {
        GambleCompetition gambleCompetition = modelMapper().map(dto, GambleCompetition.class);

        gambleCompetition.setId(dto.getId());
        gambleCompetition.setSeasonId(dto.getCurrentSeason().getId());
        gambleCompetition.setName(dto.getName() + " - " + getSeasonName(dto.getCurrentSeason()));

        List<GambleStage> gambleStageList = new ArrayList<>();
        if (dto.getCurrentSeason().getStages() != null && !dto.getCurrentSeason().getStages().isEmpty()) {
            for (SeasonStage seasonStage :
                    dto.getCurrentSeason().getStages()) {
                gambleStageList.add(new GambleStage(seasonStage.getId(), seasonStage.getName()));
            }
        }
        gambleCompetition.setStages(gambleStageList);
        return gambleCompetition;
    }

    @Override
    public CompetitionEntity convertToDto(GambleCompetition entity) {

        return null;
    }

    private String getYearFromDate(String stringDate) {
        if (StringUtils.isNullOrEmpty(stringDate)) {
            return "";
        }
        String year = stringDate.substring(0, stringDate.indexOf("-"));
        return year;
    }

    private String getSeasonName(SeasonEntity season) {
        String str = getYearFromDate(season.getStartDate());
        str += " - " + getYearFromDate(season.getEndDate());
        return str;
    }
}
