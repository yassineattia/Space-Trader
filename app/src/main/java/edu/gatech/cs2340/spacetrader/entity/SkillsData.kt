package edu.gatech.cs2340.spacetrader.entity

import java.io.Serializable

data class SkillsData(val skillsMap: MutableMap<Skill, Int>):Serializable {

    init{
        for(s: Skill in Skill.values()) {
            skillsMap[s] = 0
        }
    }

    fun addPoint(s: Skill) {
        skillsMap[s] = skillsMap[s]!! + 1
    }

    fun removePoint(s: Skill) {
        skillsMap[s] = skillsMap[s]!! - 1
    }

    override fun toString(): String {
        var s = ""
        skillsMap.forEach{
            (skill, value) -> s += "$skill: $value, "
        }
        s = s.substring(0, s.length - 2)
        return s
    }
}