package edu.gatech.cs2340.spacetrader.model.encounter.asteroid

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.model.encounter.EncounterPopUp
import edu.gatech.cs2340.spacetrader.util.TravelStatus

class AsteroidPopUp(view: AppCompatActivity, title: String, var status: TravelStatus) : EncounterPopUp(view, title, status) {


    override fun onChoice1(window: PopupWindow) {
        if ( Math.random() <= .3f ) {
            success()
        } else {
            hit()
        }
    }

    override fun onChoice2(window: PopupWindow) {
            home()
    }

    override fun onChoice3(window: PopupWindow) {
    }

    override fun getDescription(): String {
        return "You have encountered an unexpected asteroid storm! Will you return to your last planet or attempt to fly through it?"
    }

    override fun setChoice1(choice: Button) {
        choice.text = "Onward!"
    }

    override fun setChoice2(choice: Button) {
        choice.text = "Return"
    }

    override fun setChoice3(choice: Button) {
        choice.visibility = View.GONE
    }

    private fun hit() {
        val gm = GameManager.INSTANCE!!
        val health = gm.player.ship.health * .2
        val planet = gm.universe.getRandomPlanet()
        gm.currentPlanet = planet
        setFinalDisplay( "You got hit! Your health has been reduced to $health and you have landed on ${planet.name}" )
    }

    private fun success() {
        val gm = GameManager.INSTANCE!!
        gm.currentPlanet = status.nextPlanet
        setFinalDisplay( "You have successfully navigated the asteroid field" )
    }

    private fun home() {
        status.interrupted = true
        setFinalDisplay( "You have successfully returned home")
    }
}