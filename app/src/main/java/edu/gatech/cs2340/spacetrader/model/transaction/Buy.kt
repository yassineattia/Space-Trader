package edu.gatech.cs2340.spacetrader.model.transaction

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import edu.gatech.cs2340.spacetrader.entity.Good
import edu.gatech.cs2340.spacetrader.entity.inventory.Inventory
import edu.gatech.cs2340.spacetrader.model.GameManager

class Buy: Transactional {

    override fun performTransaction(good: Good, amount: Int, activity: AppCompatActivity, seller:Inventory) {
        val manager = GameManager.INSTANCE!!
        val currPlanet = manager.currentPlanet
        val player = manager.player
        val total = amount * good.price(currPlanet)
        val playerBalance = player.credits

        when {
            total > playerBalance ->
                Toast.makeText(activity, "Not enough credits!", Toast.LENGTH_SHORT).show()
            amount + player.ship.inventory.size > player.ship.inventory.cap ->
                Toast.makeText(activity, "Not enough cargo space!", Toast.LENGTH_SHORT).show()
            else -> {
                player.ship.inventory.addToInv(good, amount)

                seller.removeFromInv(good, amount)
                manager.player.credits = playerBalance - total
            }
        } //if total exceeds player money
    }

    override fun mode(): String {
        return "BUY"
    }
}