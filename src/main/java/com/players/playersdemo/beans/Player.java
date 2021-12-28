package com.players.playersdemo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Player {

    @Id
    private Long id;
    private String first_name;
    private String height_feet;
    private String height_inches;
    private String last_name;
    private String position;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;
    private String weight_pounds;


}
