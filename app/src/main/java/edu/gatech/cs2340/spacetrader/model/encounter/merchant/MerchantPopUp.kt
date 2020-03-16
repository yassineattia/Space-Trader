package edu.gatech.cs2340.spacetrader.model.encounter.merchant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import edu.gatech.cs2340.spacetrader.entity.inventory.MerchantInventory
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.model.encounter.PlunderEncounterPopUp
import edu.gatech.cs2340.spacetrader.util.TravelStatus
import edu.gatech.cs2340.spacetrader.views.market.MarketMenuActivity

class MerchantPopUp(view: AppCompatActivity, val status: TravelStatus, title: String = "Merchant Encounter") : PlunderEncounterPopUp(view, title, status) {
    val stock = MerchantInventory()
    override fun setChoice1(choice: Button) {
        choice.visibility = View.GONE
    }

    override fun setChoice2(choice: Button) {
        choice.text = "Attempt Trade"
        val layoutParams = choice.layoutParams
        layoutParams.width = 550
        choice.layoutParams = layoutParams

    }

    override fun setChoice3(choice: Button) {
        choice.text = "Attack"
        val layoutParams = choice.layoutParams
        layoutParams.width = 550
        choice.layoutParams = layoutParams
    }

    override fun onChoice1(window: PopupWindow) {
       //only two choices, button 1 is deleted
    }

    override fun onChoice2(window: PopupWindow) {
        attemptTrade()

        val gm = GameManager.INSTANCE!!
        gm.currentPlanet = status.nextPlanet
    }

    override fun onChoice3(window: PopupWindow) {
        if(Math.random() < .5f) {
            plunder()
        } else {
            GameManager.INSTANCE!!.player.ship.health *= Math.random()
            GameManager.INSTANCE!!.currentPlanet = GameManager.INSTANCE!!.universe.getRandomPlanet()
            setFinalDisplay( "Your attack failed and the merchant overwhelmed you, but you managed" +
            "to escape to ${GameManager.INSTANCE!!.currentPlanet} with ${GameManager.INSTANCE!!.player.ship.health}" +
            "health left")
        }

        val gm = GameManager.INSTANCE!!
        gm.currentPlanet = status.nextPlanet
    }

    override fun getDescription(): String {
        return "You see a merchant in the distance, what would you like to do?"
    }

    private fun attemptTrade() {
        if(Math.random() < .75f) {
            view.startActivity(Intent(view, MarketMenuActivity::class.java).apply {
                putExtra("merchant", true)
                putExtra("stock", stock) })
            setFinalDisplay("Trade successful!")
        } else {
            setFinalDisplay("The merchant doesn't seem interested in trading...")
        } //if trader is interested
    } //attemptTrade

}