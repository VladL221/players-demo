package com.players.playersdemo.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.players.playersdemo.beans.Player;

import java.io.Writer;

public interface PlayerService {

    public Player getPlayer(Long id, Writer writer) throws JsonProcessingException;


}
