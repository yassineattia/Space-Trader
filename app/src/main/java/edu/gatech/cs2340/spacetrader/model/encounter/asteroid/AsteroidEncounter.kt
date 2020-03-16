package edu.gatech.cs2340.spacetrader.model.encounter.asteroid

import android.support.v7.app.AppCompatActivity
import android.view.View
import edu.gatech.cs2340.spacetrader.model.Player
import edu.gatech.cs2340.spacetrader.model.encounter.Encounter
import edu.gatech.cs2340.spacetrader.util.TravelStatus
import kotlinx.android.synthetic.main.popup_encounter.*

class AsteroidEncounter: Encounter {
    private var view: AppCompatActivity? = null
    private var status: TravelStatus? = null

    override fun initiateEncounter(player: Player, status: TravelStatus) {
        this.status = status

        val popUp = AsteroidPopUp( view!!, "Astroid Encounter", status)

        popUp.display()
    }

    override fun setView(view: AppCompatActivity) {
        this.view = view
    }
}