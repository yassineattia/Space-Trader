package edu.gatech.cs2340.spacetrader.generators

import edu.gatech.cs2340.spacetrader.model.Planet
import edu.gatech.cs2340.spacetrader.model.interfaces.UniverseProvider
import edu.gatech.cs2340.spacetrader.util.Coordinate

class MapGenerator(private val provider: UniverseProvider, private val centerCoord: Coordinate,
                   private val rows: Int, private val cols: Int): MappedGenerator<Planet> {

    override fun generate(): Map<Coordinate, Planet> {
        val universe = provider.provide()
        val closePlanets = universe.calculateClosePlanets(centerCoord, rows / 2)
        val halfRow = rows / 2
        val halfCol = cols / 2
        val map = mutableMapOf<Coordinate, Planet>()

        closePlanets.forEach {
            val newRow = (it.key.x - centerCoord.x) + halfRow
            val newCol = (it.key.y - centerCoord.y) + halfCol
            val coord = Coordinate(newRow, newCol)

            map[coord] = it.value
        }

        return map
    }
}