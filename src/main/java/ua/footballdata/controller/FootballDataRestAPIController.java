package ua.footballdata.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.footballdata.error.CustomErrorType;
import ua.footballdata.model.Competition;
import ua.footballdata.model.CompetitionMatches;
import ua.footballdata.model.Team;
import ua.footballdata.service.CompetitionAppServiceImp;
import ua.footballdata.service.CompetitionMatchesAppServiceImp;
import ua.footballdata.service.TeamAppServiceImp;


@RestController
@RequestMapping("/api")
public class FootballDataRestAPIController {
    private static final Logger logger = LoggerFactory.getLogger(FootballDataRestAPIController.class);
    
    @Value("${footballdata.token}")
    private String token;
    
    @Autowired
    private CompetitionAppServiceImp competitionService;
    
    @Autowired
    private CompetitionMatchesAppServiceImp competitionMatchesService;
    
    @Autowired
    private TeamAppServiceImp teamService;
 
    // -------------------Retrieve All Competitions---------------------------------------------
 
    @RequestMapping(value = "/competitions", method = RequestMethod.GET)
    public ResponseEntity<List<Competition>> listAllUsers() {
        List<Competition> competitions = competitionService.findAllData();
        if (competitions.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Competition>>(competitions, HttpStatus.OK);
    }
 
 // -------------------Retrieve Single Competition------------------------------------------
    
    @RequestMapping(value = "/competitions/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCompetition(@PathVariable("id") long id) {
        logger.info("Fetching Competition with id {}", id);
        logger.info("Token: {}", token);
        
        Competition competition = competitionService.findById(id);
        if (competition == null) {
            logger.error("Competition with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Competition with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Competition>(competition, HttpStatus.OK);
    }
    // -------------------Retrieve Competition's matches------------------------------------------
    
    @RequestMapping(value = "/competitions/{id}/matches", method = RequestMethod.GET)
    public ResponseEntity<?> getCompetitionMatches(@PathVariable("id") long id) {
    	logger.info("Fetching CompetitionMatches with id {}", id);
    	logger.info("Token: {}", token);
    	
    	CompetitionMatches competitionMatches = competitionMatchesService.findById(id);
    	if (competitionMatches == null) {
    		logger.error("Matches for Competition with id {} not found.", id);
    		return new ResponseEntity(new CustomErrorType("Matches for Competition with id " + id 
    				+ " not found"), HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<CompetitionMatches>(competitionMatches, HttpStatus.OK);
    }
    
    // -------------------Retrieve Single Team------------------------------------------
       
       @RequestMapping(value = "/teams/{id}", method = RequestMethod.GET)
       public ResponseEntity<?> getTeam(@PathVariable("id") long id) {
           logger.info("Fetching Competition with id {}", id);
           logger.info("Token: {}", token);
           
           Team team = teamService.findById(id);
           if (team == null) {
               logger.error("Team with id {} not found.", id);
               return new ResponseEntity(new CustomErrorType("Competition with id " + id 
                       + " not found"), HttpStatus.NOT_FOUND);
           }
           return new ResponseEntity<Team>(team, HttpStatus.OK);
       }
}
