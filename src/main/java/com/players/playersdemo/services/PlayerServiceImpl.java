package com.players.playersdemo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.players.playersdemo.beans.Player;
import com.players.playersdemo.beans.Team;
import com.players.playersdemo.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    private String playerData = "https://www.balldontlie.io/api/v1/players/";

    @Override
    public Player getPlayer(Long id, Writer writer) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> response = restTemplate.getForEntity(playerData+id, String.class);
        Player player = objectMapper.readValue(response.getBody(), new TypeReference<Player>(){});
        log.info("player: {}",player);
        playerRepository.save(player);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            csvPrinter.printRecord(
                    "player id: " +player.getId(),
                    "first name: "+ player.getFirst_name(),
                    "height in feet: "+ player.getHeight_feet(),
                    "height in inches: "+player.getHeight_inches(),
                    "last name: "+player.getLast_name(),
                    "position: "+player.getPosition(),
                    "weight in pounds: "+player.getWeight_pounds(),
                    "team id: "+ player.getTeam().getId(),
                    "abbreviation: "+ player.getTeam().getAbbreviation(),
                    "city: "+ player.getTeam().getCity(),
                    "conference: "+player.getTeam().getConference(),
                    "division: "+player.getTeam().getDivision(),
                    "full name: "+player.getTeam().getFull_name(),
                    "name: "+ player.getTeam().getName()
                    );
        }catch (IOException e){
            log.error("Error: {}", e.getMessage());
        }
        //playerRepository.save(player);
        return null;
    }

}
