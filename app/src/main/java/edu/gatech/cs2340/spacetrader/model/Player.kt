package edu.gatech.cs2340.spacetrader.model

import edu.gatech.cs2340.spacetrader.entity.Ship
import edu.gatech.cs2340.spacetrader.entity.ShipType
import edu.gatech.cs2340.spacetrader.entity.SkillsData
import java.io.Serializable

class Player(val name: String, var credits: Int, private var skills: SkillsData, var ship: Ship):
        Serializable {

    constructor(name: String, skills: SkillsData, ship: ShipType) : this(name, 1000, skills, Ship())

    override fun toString(): String {
        return "Player: {$name}\n" +
                "Credits: {$credits}" +
                "Skills: {$skills}\n" +
                "Ship: {$ship}\n"
    }
} //Player