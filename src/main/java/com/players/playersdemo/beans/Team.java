package com.players.playersdemo.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Team {

    @Id
    private Long id;
    private String abbreviation;
    private String city;
    private String conference;
    private String division;
    private String full_name;
    private String name;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private List<Player> playerList;

}
