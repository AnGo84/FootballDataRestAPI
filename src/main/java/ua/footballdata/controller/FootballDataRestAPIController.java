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
import ua.footballdata.service.CompetitionAppServiceImp;


@RestController
@RequestMapping("/api")
public class FootballDataRestAPIController {
    private static final Logger logger = LoggerFactory.getLogger(FootballDataRestAPIController.class);
    
    @Value("${footballdata.token}")
    private String token;
    
    @Autowired
    private CompetitionAppServiceImp competitionService;
 
    // -------------------Retrieve All Competitions---------------------------------------------
 
    @RequestMapping(value = "/competition/", method = RequestMethod.GET)
    public ResponseEntity<List<Competition>> listAllUsers() {
        List<Competition> competitions = competitionService.findAllData();
        if (competitions.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Competition>>(competitions, HttpStatus.OK);
    }
 
 // -------------------Retrieve Single Competition------------------------------------------
    
    @RequestMapping(value = "/competition/{id}", method = RequestMethod.GET)
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

}
