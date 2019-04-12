package ua.footballdata.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ua.footballdata.model.Competition;
import ua.footballdata.model.CompetitionMatches;
import ua.footballdata.restservice.CompetitionMatchesRestServiceImpl;
import ua.footballdata.restservice.CompetitionRestServiceImpl;

@Service("competitionMatchesService")
public class CompetitionMatchesAppServiceImp implements AppService<CompetitionMatches>{
	private static final Logger logger = LoggerFactory.getLogger(CompetitionMatchesAppServiceImp.class);
	
	@Value("${footballdata.token}")
	private String token;
	
	private CompetitionMatchesRestServiceImpl restService;
	
	public CompetitionMatchesAppServiceImp() {
		
	}

	public CompetitionMatchesAppServiceImp(String token) {
		logger.info("Token for set: {}", token);
		this.token = token;
		this.restService = new CompetitionMatchesRestServiceImpl(token);
	}
	
	@Override
	public CompetitionMatches findById(long id) {
		logger.info("Id {}", id);
		logger.info("restService is null: {}", restService==null);
		logger.info("Token for set: {}", token);
		restService = new CompetitionMatchesRestServiceImpl(token);
		
		/*Competition competition = new Competition();
		competition.setId(id);
		competition.setCode("Code: " + String.valueOf(id));
		return competition;*/
		
		return restService.findById(id);
	}

	@Override
	public List<CompetitionMatches> findAllData() {
		// TODO Auto-generated method stub
		return null;
	}

}
