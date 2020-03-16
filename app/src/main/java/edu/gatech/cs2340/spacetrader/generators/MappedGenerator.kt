package edu.gatech.cs2340.spacetrader.generators

import edu.gatech.cs2340.spacetrader.util.Coordinate

interface MappedGenerator<T>: Generator<Map<Coordinate, T>> {
    override fun generate(): Map<Coordinate, T>
}