package ua.footballdata.model.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazonaws.util.StringUtils;

import ua.footballdata.model.entity.CompetitionEntity;
import ua.footballdata.model.entity.GambleCompetition;
import ua.footballdata.model.entity.SeasonEntity;

@Component
public class GambleCompetitionMapper extends AbstractMapper<GambleCompetition, CompetitionEntity> {
	public static final Logger logger = LoggerFactory.getLogger(GambleCompetitionMapper.class);

	@Override
	public GambleCompetition convertToEntity(CompetitionEntity dto) {
		GambleCompetition gambleCompetition = modelMapper().map(dto, GambleCompetition.class);

		gambleCompetition.setId(dto.getId());
		gambleCompetition.setSeasonId(dto.getCurrentSeason().getId());
		gambleCompetition.setName(dto.getName() + " - " + getSeasonName(dto.getCurrentSeason()));

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
