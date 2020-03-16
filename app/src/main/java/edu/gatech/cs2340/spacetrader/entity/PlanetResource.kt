package edu.gatech.cs2340.spacetrader.entity

import java.io.Serializable

enum class PlanetResource: Serializable {
    NO_SPECIAL_RESOURCE, MINERAL_RICH, MINERAL_POOR, DESERT, LOTS_OF_WATER, RICH_SOIL,
    POOR_SOIL, RICH_FAUNA, LIFELESS, WEIRD_MUSHROOMS, LOST_OF_HERBS, ARTISTIC, WARLIKE
}