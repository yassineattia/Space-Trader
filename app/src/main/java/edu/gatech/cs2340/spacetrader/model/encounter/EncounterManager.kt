package edu.gatech.cs2340.spacetrader.model.encounter

import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.model.encounter.asteroid.AsteroidEncounter
import edu.gatech.cs2340.spacetrader.model.encounter.merchant.MerchantEncounter
import edu.gatech.cs2340.spacetrader.model.encounter.pirate.PirateEncounter
import edu.gatech.cs2340.spacetrader.util.TravelStatus

class EncounterManager(private val view: AppCompatActivity) {

    private var encounters = HashSet<Class<out Encounter>>()

    init {
        encounters.add(MerchantEncounter::class.java)
        encounters.add(PirateEncounter::class.java)
        encounters.add(AsteroidEncounter::class.java)
    }

    fun attemptEncounter(status: TravelStatus): Boolean {
        if ( Math.random() < .20f ) {
//       if (true) {
            val encounter = encounters.random().newInstance()
            val instance = GameManager.INSTANCE!!

            encounter.setView(view)
            encounter.initiateEncounter(instance.player, status)
            return true
        }

        return false
    }
}