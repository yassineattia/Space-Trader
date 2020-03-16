package edu.gatech.cs2340.spacetrader.model.encounter.merchant

import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetrader.model.Player
import edu.gatech.cs2340.spacetrader.model.encounter.Encounter
import edu.gatech.cs2340.spacetrader.model.encounter.pirate.PiratePopUp
import edu.gatech.cs2340.spacetrader.util.TravelStatus

class MerchantEncounter: Encounter {
    private var view: AppCompatActivity? = null
    private var status: TravelStatus? = null

    override fun setView(view: AppCompatActivity) {
        this.view = view
    } //setView

    override fun initiateEncounter(player: Player, status: TravelStatus) {
        this.status = status

        val popUp = MerchantPopUp(view!!, status)

        popUp.display()
    } //initiateEncounter

} //MerchantEncounter