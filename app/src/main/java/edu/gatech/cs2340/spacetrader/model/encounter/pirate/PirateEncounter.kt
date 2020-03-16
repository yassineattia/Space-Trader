package edu.gatech.cs2340.spacetrader.model.encounter.pirate

import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetrader.model.Player
import edu.gatech.cs2340.spacetrader.model.encounter.Encounter
import edu.gatech.cs2340.spacetrader.util.TravelStatus

class PirateEncounter: Encounter {
    private var view: AppCompatActivity? = null
    private var status: TravelStatus? = null

    override fun initiateEncounter(player: Player, status: TravelStatus) {
        this.status = status

        val popUp = PiratePopUp( view!!, status )

        popUp.display()
    }

    override fun setView(view: AppCompatActivity) {
        this.view = view
    }
}