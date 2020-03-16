package edu.gatech.cs2340.spacetrader.model.encounter

import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetrader.entity.Good
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.util.TravelStatus
import kotlin.random.Random

abstract class PlunderEncounterPopUp(view: AppCompatActivity, private val title: String, status: TravelStatus) : EncounterPopUp(view, title, status) {
    protected fun plunder(){
        val goods: HashSet<Good> = HashSet()
        val plundered: HashMap<Good, Int> = HashMap()

        goods.addAll(Good.values())

        for (i in 1..Random.nextInt(4)) {
            val good = goods.random()

            goods.remove(good)
            plundered[good] = 1 + Random.nextInt(4)
        }

        val notAdded = GameManager.INSTANCE!!.player.ship.inventory.addAllToInv(plundered)

        notAdded.keys.forEach {
            plundered.remove(it)
        }
        displayFinalScreen("You plundered the pirate and received:", plundered)
    }

    private fun displayFinalScreen(desc: String, plundered: Map<Good, Int>) {
        val builder = StringBuilder( desc )

        builder.append( "\n" )

        for (entry: Map.Entry<Good, Int> in plundered.entries) {
            builder.append( "- " )
            builder.append( entry.key.name )
            builder.append( ": " )
            builder.append( entry.value )
            builder.append( "\n" )
        }

        setFinalDisplay( builder.toString() )
    }
}