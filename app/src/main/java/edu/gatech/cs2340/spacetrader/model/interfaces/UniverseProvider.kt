package edu.gatech.cs2340.spacetrader.model.interfaces

import edu.gatech.cs2340.spacetrader.model.Universe

interface UniverseProvider: Provider<Universe> {

    override fun provide(): Universe
}