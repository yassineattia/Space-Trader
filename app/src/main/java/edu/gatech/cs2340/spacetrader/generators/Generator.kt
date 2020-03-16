package edu.gatech.cs2340.spacetrader.generators

interface Generator<T> {

    fun generate(): T
}