package edu.gatech.cs2340.spacetrader.model.interfaces

interface Provider<T> {

    fun provide(): T
}