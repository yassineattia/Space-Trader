package edu.gatech.cs2340.spacetrader.entity

import android.util.Log
import edu.gatech.cs2340.spacetrader.entity.inventory.PlayerInventory
import edu.gatech.cs2340.spacetrader.model.Planet
import java.io.Serializable

class Ship(type: ShipType): Serializable {
    constructor() : this(ShipType.GNAT)

    private var type: ShipType = type
        private set(value) {
            field = value
        }

    var fuel: Double = 20.0
    var health: Double = type.health

    fun getFuelLevel ():Double {
        return fuel
    }

    fun addFuel(fuelToAdd: Double) {
        fuel += fuelToAdd
    }

    private fun removeFuel(fuelToBeRemoved: Double) {
        fuel -= fuelToBeRemoved
    }

    fun addHealth(healthToAdd: Int) {
        fuel += healthToAdd
    }

    private fun removeHealth(healthToBeRemoved: Int) {
        fuel -= healthToBeRemoved
    }

    fun canTravel(planet1: Planet, planet2: Planet): Boolean {
        var distance = Math.sqrt(Math.pow((planet2.coordinate.x - planet1.coordinate.x).toDouble(),
                2.0) + Math.pow((planet2.coordinate.y- planet1.coordinate.y).toDouble(), 2.0))
        distance *= 3
        if (fuel > distance ) {
            removeFuel(distance)
            return true
        }
        Log.d("myTag","Can travel is returning false")
        return false
    }

    val inventory: PlayerInventory = PlayerInventory(type.cargoCap)

    fun updateShip(shipType: ShipType) {
        //When you do, make sure you transfer that ship's inventory
    }

    override fun toString(): String {
        return "Type: $type\n" +
                "Inventory: $inventory\n" +
                "Fuel: $fuel"
    }
}