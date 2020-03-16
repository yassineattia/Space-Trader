package edu.gatech.cs2340.spacetrader.generators

import android.util.Log
import edu.gatech.cs2340.spacetrader.entity.PlanetResource
import edu.gatech.cs2340.spacetrader.entity.TechLevel
import edu.gatech.cs2340.spacetrader.model.Planet
import edu.gatech.cs2340.spacetrader.util.Coordinate

class PlanetGenerator(private var planets: Int, private val names: NameProvider,
                      private val coordinateGen: CoordinateGen): MappedGenerator<Planet> {
    private val resourceTypes = PlanetResource.values()
    private val techLevels = TechLevel.values()

    override fun generate(): Map<Coordinate, Planet> {
        val solarSystem = HashMap<Coordinate, Planet>()
        coordinateGen.generateBasePoint()

        while(planets > 0) {
            val coord = coordinateGen.generate()
            val resource = resourceTypes[(0 until resourceTypes.size - 1).random()]
            val techLevel = techLevels[(0 until techLevels.size - 1).random()]
            val name = names.next()
            val planet = Planet(name, coord, resource, techLevel)
            Log.d("PlanetGenerator", planet.toString())

            solarSystem[coord] = planet
            planets--
        } //while planets greater than 0

        return solarSystem
    } //generate
} //PlanetGenerator