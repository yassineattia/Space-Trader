package edu.gatech.cs2340.spacetrader.viewmodel

import android.content.Intent
import android.widget.Toast
import edu.gatech.cs2340.spacetrader.entity.IncreaseEvent
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.model.Planet
import edu.gatech.cs2340.spacetrader.model.encounter.EncounterManager
import edu.gatech.cs2340.spacetrader.runnable.IncreaseEventRunnable
import edu.gatech.cs2340.spacetrader.util.TravelStatus
import edu.gatech.cs2340.spacetrader.views.TravelActivity
import edu.gatech.cs2340.spacetrader.views.UniverseMapActivity
import kotlinx.android.synthetic.main.activity_travel.*


class TravelViewModel(private val view: TravelActivity) {
    private val encounterMan = EncounterManager(view)

    fun addExtras(targetPlanet: Planet) {
        if ( targetPlanet.event != IncreaseEvent.NONE ) {
            view.event_msg.text = "WARNING! This planet is currently experiencing "+
                    targetPlanet.event + "!"
        }
    }

    fun attemptTravel(planet1 : Planet) {
        val man = GameManager.INSTANCE!!
        val status = TravelStatus(man.currentPlanet, planet1)

        val canTravel = man.player.ship.canTravel(planet1,
                man.currentPlanet)

        if (canTravel) {
            val encountered = encounterMan.attemptEncounter(status)

            if (encountered) {
                return
            }

            IncreaseEventRunnable(man.provide(), 50).run()
            if (!status.interrupted) {
                man.currentPlanet = view.intent.extras!!["Planet"] as Planet
            }
            view.startActivity(Intent(view, UniverseMapActivity::class.java))
            val fuel = String.format("%.2f", man.player.ship.getFuelLevel())
            val toast1 = Toast.makeText(view.applicationContext,
                    "You have $fuel fuel left",
                    Toast.LENGTH_SHORT)
            toast1.show()
            view.finish()
        } else {
            val toast = Toast.makeText(view.applicationContext,
                    "Not enough fuel",
                    Toast.LENGTH_SHORT)
            toast.show()

        }
    }
}