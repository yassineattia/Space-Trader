package edu.gatech.cs2340.spacetrader.model.encounter

import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetrader.model.Player
import edu.gatech.cs2340.spacetrader.util.TravelStatus

interface Encounter {

    fun initiateEncounter(player: Player, status: TravelStatus)

    fun setView(view: AppCompatActivity)
}