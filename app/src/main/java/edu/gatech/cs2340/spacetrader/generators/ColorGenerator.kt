package edu.gatech.cs2340.spacetrader.generators

import android.graphics.Color
import java.util.*

class ColorGenerator: Generator<Int> {

    override fun generate(): Int {
        val rand = Random()

        return Color.argb(255, rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))
    }
}