package com.players.playersdemo.rest;

import com.players.playersdemo.services.PlayerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RequiredArgsConstructor
@RestController
@RequestMapping("/player")
@Slf4j
public class PlayerController {

    private final PlayerServiceImpl playerService;



    @RequestMapping(value = "/{id}", produces = "text/csv")
    public void getPlayer(@PathVariable(value = "id") Long id, HttpServletResponse servletResponse){
        try {
            servletResponse.setContentType("text/csv");
            servletResponse.addHeader("Content-Disposition","attachment; filename=\"player.csv\"");
            playerService.getPlayer(id,servletResponse.getWriter());
        }catch (Exception e){
            log.error("Exception: {}", e.getMessage());
        }
    }

}
