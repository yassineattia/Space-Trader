package edu.gatech.cs2340.spacetrader.entity.inventory

import edu.gatech.cs2340.spacetrader.entity.Good
import edu.gatech.cs2340.spacetrader.model.GameManager

class PlayerInventory(override var cap: Int, goods: MutableMap<Good, Int>): Inventory() {

    constructor(cap: Int) : this(cap, mutableMapOf())

    override val inv: MutableMap<Good, Int> = goods
    override var size: Int = 0

    fun valueOf(): Int {
        var total = 0
        inv.forEach {
            total += it.key.price(GameManager.INSTANCE!!.currentPlanet)
        }
        return total
    }

    override fun toString(): String {
        var string = ""
        inv.forEach {
            string += "Good: ${it.key}, Quantity: ${it.value}\n"
        }
        return string
    }
}