package edu.gatech.cs2340.spacetrader.model.saving

import android.content.Context
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.util.Size

interface Savable {
    fun save(manager: GameManager, size: Size?, context: Context): Boolean
    fun load(context: Context): Boolean

} //Savable