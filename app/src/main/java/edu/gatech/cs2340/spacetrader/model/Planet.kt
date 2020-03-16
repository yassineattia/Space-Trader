package edu.gatech.cs2340.spacetrader.model

import edu.gatech.cs2340.spacetrader.entity.IncreaseEvent
import edu.gatech.cs2340.spacetrader.entity.PlanetResource
import edu.gatech.cs2340.spacetrader.entity.TechLevel
import edu.gatech.cs2340.spacetrader.entity.inventory.PlanetInventory
import edu.gatech.cs2340.spacetrader.util.Coordinate
import java.io.Serializable

class Planet(val name: String, val coordinate: Coordinate,
             private val resource: PlanetResource, val techLevel: TechLevel): Serializable {

    var event: IncreaseEvent = IncreaseEvent.NONE
    var solarSystem: SolarSystem? = null
    val inventory: PlanetInventory = PlanetInventory(this)

    override fun toString(): String {
        return "{name=$name,coord=$coordinate,resource=$resource,tech_level=$techLevel}"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other !is Planet) {
            return false
        }
        val other: Planet = other
        return name == other.name
                && coordinate == other.coordinate
                && resource == other.resource
                && techLevel == other.techLevel
    }

//    override fun hashCode(): Int {
//        return super.hashCode()
//    }

}