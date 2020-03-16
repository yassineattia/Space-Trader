package edu.gatech.cs2340.spacetrader.model.transaction

import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetrader.entity.Good
import edu.gatech.cs2340.spacetrader.entity.inventory.Inventory
import edu.gatech.cs2340.spacetrader.model.GameManager

class Sell: Transactional {

    override fun performTransaction(good: Good, amount: Int, activity: AppCompatActivity, buyer: Inventory) {
        val manager = GameManager.INSTANCE!!
        val currPlanet = manager.currentPlanet
        val player = manager.player
        val total = amount * good.price(currPlanet)
        val playerBalance = player.credits

        player.ship.inventory.removeFromInv(good, amount)
        buyer.addToInv(good, amount)
        player.credits = playerBalance + total
    }

    override fun mode(): String {
        return "SELL"
    }
}