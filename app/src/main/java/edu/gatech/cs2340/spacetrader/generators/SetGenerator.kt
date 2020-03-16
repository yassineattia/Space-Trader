package edu.gatech.cs2340.spacetrader.generators

interface SetGenerator<T>: Generator<Set<T>> {

    override fun generate(): Set<T>
}