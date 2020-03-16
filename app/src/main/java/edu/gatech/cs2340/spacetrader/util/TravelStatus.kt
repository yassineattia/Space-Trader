package edu.gatech.cs2340.spacetrader.util

import edu.gatech.cs2340.spacetrader.model.Planet

class TravelStatus(val currentPlanet: Planet, val nextPlanet: Planet) {
    var interrupted: Boolean = false
}